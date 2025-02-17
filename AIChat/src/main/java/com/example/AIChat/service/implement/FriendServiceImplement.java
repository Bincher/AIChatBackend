package com.example.AIChat.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.AIChat.dto.request.friend.AcceptFriendRequestDto;
import com.example.AIChat.dto.request.friend.InviteFriendRequestDto;
import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.dto.response.friend.DeleteFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetInviteFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetMyFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetUserListResponseDto;
import com.example.AIChat.dto.response.friend.AcceptFriendResponseDto;
import com.example.AIChat.dto.response.friend.InviteFriendResponseDto;
import com.example.AIChat.entity.FriendshipEntity;
import com.example.AIChat.entity.UserEntity;
import com.example.AIChat.repository.FriendshipRepository;
import com.example.AIChat.repository.UserRepository;
import com.example.AIChat.service.FriendService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendServiceImplement implements FriendService{

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
    public ResponseEntity<? super GetUserListResponseDto> getUserList(String nickname, String loginId) {
        
        List<UserEntity> userEntities = new ArrayList<>();
        
        try{

            userEntities = userRepository.findByNicknameContainsOrderById(nickname);

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
    public ResponseEntity<? super InviteFriendResponseDto> inviteFriend(InviteFriendRequestDto dto, String loginId) {

        try {
            boolean existsFriend = userRepository.existsByNickname(dto.getNickname());
            if(!existsFriend) return InviteFriendResponseDto.noExistUser();

            boolean existsUser = userRepository.existsByLoginId(loginId);
            if(!existsUser) return InviteFriendResponseDto.noExistUser();

            Integer userId = getUserIdByLoginId(loginId);
            Integer friendId = getUserIdByNickname(dto.getNickname());

            FriendshipEntity existingFriendship = friendshipRepository.findByUserIdAndFriendId(friendId, userId);

            if (existingFriendship != null) {

                String status = existingFriendship.getStatus();
                if ("PENDING".equals(status)) {

                    existingFriendship.setStatus("ACCEPTED");
                    friendshipRepository.save(existingFriendship);
                    FriendshipEntity reverseFriendship = new FriendshipEntity(userId, friendId);
                    reverseFriendship.setStatus("ACCEPTED");
                    friendshipRepository.save(reverseFriendship);

                    return InviteFriendResponseDto.success();
                } else if ("ACCEPTED".equals(status)) {
                    return InviteFriendResponseDto.existedFriend();
                }
            } else {

                FriendshipEntity duplicatedPosting = friendshipRepository.findByUserIdAndFriendId(userId, friendId);
                if(duplicatedPosting != null){
                    return InviteFriendResponseDto.duplicatedPosting();
                }

                FriendshipEntity newFriendship = new FriendshipEntity(userId, friendId);
                friendshipRepository.save(newFriendship);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return InviteFriendResponseDto.success();
    }

    @Override
    public ResponseEntity<? super AcceptFriendResponseDto> acceptFriend(AcceptFriendRequestDto dto,
            String loginId) {
        try {
            boolean existsFriend = userRepository.existsByNickname(dto.getNickname());
            if(!existsFriend) return AcceptFriendResponseDto.noExistUser();

            boolean existsUser = userRepository.existsByLoginId(loginId);
            if(!existsUser) return AcceptFriendResponseDto.noExistUser();

            Integer userId = getUserIdByLoginId(loginId);
            Integer friendId = getUserIdByNickname(dto.getNickname());

            FriendshipEntity friendshipEntity = friendshipRepository.findByUserIdAndFriendId(friendId, userId);
            if (friendshipEntity == null) {
                return AcceptFriendResponseDto.noExistUser();
            }

            boolean isAccept = dto.isFriendAccept();
            if (isAccept) {

                friendshipEntity.setStatus("ACCEPTED");
                friendshipRepository.save(friendshipEntity);

                FriendshipEntity reverseFriendship = new FriendshipEntity(userId, friendId);
                reverseFriendship.setStatus("ACCEPTED");
                friendshipRepository.save(reverseFriendship);
            } else {

                friendshipRepository.delete(friendshipEntity);
            }
                
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return AcceptFriendResponseDto.success();
    }
    
    @Override
    public ResponseEntity<? super GetMyFriendResponseDto> getMyFriend(String loginId) {
        List<UserEntity> friends = new ArrayList<>();

        try {
            boolean existsUser = userRepository.existsByLoginId(loginId);
            if(!existsUser) return GetMyFriendResponseDto.noExistUser();

            Integer currentUserId = getUserIdByLoginId(loginId);

            List<FriendshipEntity> friendships = friendshipRepository.findByUserIdAndStatus(currentUserId, "ACCEPTED");

            List<Integer> friendIds = friendships.stream()
                .map(FriendshipEntity::getFriendId)
                .collect(Collectors.toList());

            if (!friendIds.isEmpty()) {
                friends = userRepository.findAllById(friendIds);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetMyFriendResponseDto.success(friends);
    }

    @Override
    public ResponseEntity<? super DeleteFriendResponseDto> deleteFriend(String nickname, String loginId) {
        try {
            boolean existsFriend = userRepository.existsByNickname(nickname);
            if(!existsFriend) return DeleteFriendResponseDto.noExistUser();

            boolean existsUser = userRepository.existsByLoginId(loginId);
            if(!existsUser) return DeleteFriendResponseDto.noExistUser();

            Integer friendId = getUserIdByLoginId(loginId);
            Integer userId = getUserIdByNickname(nickname);

            FriendshipEntity friendshipEntity = friendshipRepository.findByUserIdAndFriendId(userId, friendId);
            FriendshipEntity reverseFriendshipEntity = friendshipRepository.findByUserIdAndFriendId(friendId, userId);
            if (friendshipEntity == null || reverseFriendshipEntity == null) {
                return AcceptFriendResponseDto.noExistUser();
            }

            friendshipRepository.delete(friendshipEntity);
            friendshipRepository.delete(reverseFriendshipEntity);
                
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return DeleteFriendResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetInviteFriendResponseDto> getInviteFriend(String loginId) {
        List<UserEntity> users = new ArrayList<>();

        try {
            boolean existsUser = userRepository.existsByLoginId(loginId);
            if(!existsUser) return GetMyFriendResponseDto.noExistUser();

            Integer currentUserId = getUserIdByLoginId(loginId);

            List<FriendshipEntity> friendships = friendshipRepository.findByFriendIdAndStatus(currentUserId, "PENDING");

            List<Integer> friendIds = friendships.stream()
                .map(FriendshipEntity::getUserId)
                .collect(Collectors.toList());

            if (!friendIds.isEmpty()) {
                users = userRepository.findAllById(friendIds);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetInviteFriendResponseDto.success(users);
    }
}
