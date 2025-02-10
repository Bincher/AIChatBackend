package com.example.AIChat.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.AIChat.dto.request.user.DeleteFriendRequestDto;
import com.example.AIChat.dto.request.user.GetUserListRequestDto;
import com.example.AIChat.dto.request.user.PatchFriendRequestDto;
import com.example.AIChat.dto.request.user.PostFriendRequestDto;
import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.dto.response.user.DeleteFriendResponseDto;
import com.example.AIChat.dto.response.user.GetMyFriendResponseDto;
import com.example.AIChat.dto.response.user.GetUserListResponseDto;
import com.example.AIChat.dto.response.user.PatchFriendResponseDto;
import com.example.AIChat.dto.response.user.PostFriendResponseDto;
import com.example.AIChat.entity.FriendshipEntity;
import com.example.AIChat.entity.UserEntity;
import com.example.AIChat.repository.FriendshipRepository;
import com.example.AIChat.repository.UserRepository;
import com.example.AIChat.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{

    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;

    public Integer getUserIdByLoginId(String loginId) {
        return userRepository.findIdByLoginId(loginId)
                .map(UserEntity::getId)
                .orElseThrow(() -> new EntityNotFoundException(loginId));
    }

    public Integer getUserIdByNickname(String nickname) {
        return userRepository.findIdByNickname(nickname)
                .map(UserEntity::getId)
                .orElseThrow(() -> new EntityNotFoundException(nickname));
    }

    @Override
    public ResponseEntity<? super GetUserListResponseDto> getUserList(GetUserListRequestDto dto, String loginId) {
        
        List<UserEntity> userEntities = new ArrayList<>();
        
        try{

            userEntities = userRepository.findByNicknameContainsOrderById(dto.getNickname());

            Integer currentUserId = getUserIdByLoginId(loginId);
            userEntities = userEntities.stream()
                .filter(user -> !user.getId().equals(currentUserId))
                .collect(Collectors.toList());

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserListResponseDto.success(userEntities);
    }

    @Override
    public ResponseEntity<? super PostFriendResponseDto> postFriend(PostFriendRequestDto dto, String loginId) {

        try {
            boolean existsFriend = userRepository.existsByNickname(dto.getNickname());
            if(!existsFriend) return GetUserListResponseDto.noExistUser();

            boolean existsUser = userRepository.existsByLoginId(loginId);
            if(!existsUser) return GetUserListResponseDto.noExistUser();

            Integer userId = getUserIdByLoginId(loginId);
            Integer friendId = getUserIdByNickname(dto.getNickname());

            FriendshipEntity existingFriendship = friendshipRepository.findByUserIdAndFriendId(friendId, userId);

            if (existingFriendship != null) {
                // 3-1. STATUS 확인
                String status = existingFriendship.getStatus();
                if ("PENDING".equals(status)) {
                    // PENDING 상태 -> ACCEPTED로 변경
                    existingFriendship.setStatus("ACCEPTED");
                    friendshipRepository.save(existingFriendship);
                    return PostFriendResponseDto.success();
                } else if ("ACCEPTED".equals(status)) {
                    // 이미 친구 상태
                    return PostFriendResponseDto.ExistedFriend();
                }
            } else {
                // 4. 새로운 친구 요청 생성
                FriendshipEntity newFriendship = new FriendshipEntity(userId, friendId);
                friendshipRepository.save(newFriendship);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostFriendResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchFriendResponseDto> patchFriend(PatchFriendRequestDto dto,
            String loginId) {
        try {
            boolean existsFriend = userRepository.existsByNickname(dto.getNickname());
            if(!existsFriend) return GetUserListResponseDto.noExistUser();

            boolean existsUser = userRepository.existsByLoginId(loginId);
            if(!existsUser) return GetUserListResponseDto.noExistUser();

            Integer friendId = getUserIdByLoginId(loginId);
            Integer userId = getUserIdByNickname(dto.getNickname());

            FriendshipEntity friendshipEntity = friendshipRepository.findByUserIdAndFriendId(userId, friendId);
            if (friendshipEntity == null) {
                return PatchFriendResponseDto.NotExistUser(); // 요청이 없으면 에러 반환
            }

            boolean isAccept = dto.isFriendAccpet();
            if (isAccept) {
                // 수락 -> STATUS를 ACCEPTED로 변경
                friendshipEntity.setStatus("ACCEPTED");
                friendshipRepository.save(friendshipEntity);

                FriendshipEntity reverseFriendship = new FriendshipEntity(friendId, userId);
                reverseFriendship.setStatus("ACCEPTED");
                friendshipRepository.save(reverseFriendship);
            } else {
                // 거절 -> 엔티티 삭제
                friendshipRepository.delete(friendshipEntity);
            }
                
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchFriendResponseDto.success();
    }
    
    @Override
    public ResponseEntity<? super GetMyFriendResponseDto> getMyFriend(String loginId) {
        List<UserEntity> friends = new ArrayList<>();

        try {
            // 1. 현재 사용자 ID 가져오기
            Integer currentUserId = getUserIdByLoginId(loginId);

            // 2. 친구 관계 조회
            List<FriendshipEntity> friendships = friendshipRepository.findAcceptedFriends(currentUserId);

            // 3. 친구 ID 추출
            List<Integer> friendIds = friendships.stream()
            .map(FriendshipEntity::getFriendId)
            .collect(Collectors.toList());

            // 4. 친구 정보 조회
            if (!friendIds.isEmpty()) {
                friends = userRepository.findAllById(friendIds);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        // 5. 결과 반환
        return GetMyFriendResponseDto.success(friends);
    }

    @Override
    public ResponseEntity<? super DeleteFriendResponseDto> deleteFriend(DeleteFriendRequestDto dto, String loginId) {
        try {
            boolean existsFriend = userRepository.existsByNickname(dto.getNickname());
            if(!existsFriend) return GetUserListResponseDto.noExistUser();

            boolean existsUser = userRepository.existsByLoginId(loginId);
            if(!existsUser) return GetUserListResponseDto.noExistUser();

            Integer friendId = getUserIdByLoginId(loginId);
            Integer userId = getUserIdByNickname(dto.getNickname());

            FriendshipEntity friendshipEntity = friendshipRepository.findByUserIdAndFriendId(userId, friendId);
            FriendshipEntity reverseFriendshipEntity = friendshipRepository.findByUserIdAndFriendId(friendId, userId);
            if (friendshipEntity == null || reverseFriendshipEntity == null) {
                return PatchFriendResponseDto.NotExistUser(); // 요청이 없으면 에러 반환
            }

            friendshipRepository.delete(friendshipEntity);
            friendshipRepository.delete(reverseFriendshipEntity);
                
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return DeleteFriendResponseDto.success();
    }
}
