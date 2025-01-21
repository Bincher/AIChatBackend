package com.example.AIChat.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.AIChat.common.CertificationNumber;
import com.example.AIChat.dto.request.auth.CheckCertificationRequestDto;
import com.example.AIChat.dto.request.auth.EmailCertificationRequestDto;
import com.example.AIChat.dto.request.auth.IdCheckRequestDto;
import com.example.AIChat.dto.request.auth.SignUpRequestDto;
import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.dto.response.auth.CheckCertificationResponseDto;
import com.example.AIChat.dto.response.auth.EmailCertificationResponseDto;
import com.example.AIChat.dto.response.auth.IdCheckResponseDto;
import com.example.AIChat.dto.response.auth.SignUpResponseDto;
import com.example.AIChat.entity.CertificationEntity;
import com.example.AIChat.entity.UserEntity;
import com.example.AIChat.provider.EmailProvider;
import com.example.AIChat.repository.CertificationRepository;
import com.example.AIChat.repository.UserRepository;
import com.example.AIChat.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{

    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final EmailProvider emailProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto){

        try{

            String loginId = dto.getLoginId();
            boolean isExistLoginId = userRepository.existsByLoginId(loginId);
            if(isExistLoginId) return IdCheckResponseDto.duplicateId();
        
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return IdCheckResponseDto.success();
    }
    
    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {
            
            String loginId = dto.getLoginId();
            String email = dto.getEmail();

            boolean isExistLoginId = userRepository.existsByLoginId(loginId);
            if(isExistLoginId) return EmailCertificationResponseDto.duplicateId();

            boolean isExistEmail = userRepository.existsByEmail(email);
            if(isExistEmail) return EmailCertificationResponseDto.duplicateEmail();

            String certificationNumber = CertificationNumber.getCertificationNumber();

            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if(!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            CertificationEntity certificationEntity = new CertificationEntity(loginId, email, certificationNumber);
            certificationRepository.save(certificationEntity);


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
        try {

            String loginId = dto.getLoginId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByLoginId(loginId);
            if(certificationEntity == null) return CheckCertificationResponseDto.certificationFail();

            boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if(!isMatched) return CheckCertificationResponseDto.certificationFail();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> SignUp(SignUpRequestDto dto) {
        
        try{
            
            String loginId = dto.getLoginId();
            boolean existedLoginId = userRepository.existsByLoginId(loginId);
            if(existedLoginId) return SignUpResponseDto.duplicateId();

            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if(existedEmail) return SignUpResponseDto.duplicateEmail();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            String certificationNumber = dto.getCertificationNumber();
            CertificationEntity certificationEntity = certificationRepository.findByLoginId(loginId);
            boolean isMatched =
                certificationEntity.getEmail().equals(email) &&
                certificationEntity.getCertificationNumber().equals(certificationNumber);
            if(!isMatched) return SignUpResponseDto.certificationFail();

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            boolean agreedPersonal = dto.isAgreedPersonal();
            if(!agreedPersonal) return SignUpResponseDto.disagreedPersonal();

            // certificationRepository.delete(certificationEntity);
            certificationRepository.deleteByLoginId(loginId);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

}
