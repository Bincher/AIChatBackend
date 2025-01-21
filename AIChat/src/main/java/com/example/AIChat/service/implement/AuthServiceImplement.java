package com.example.AIChat.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.AIChat.common.CertificationNumber;
import com.example.AIChat.dto.request.auth.EmailCertificationRequestDto;
import com.example.AIChat.dto.request.auth.IdCheckRequestDto;
import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.dto.response.auth.EmailCertificationResponseDto;
import com.example.AIChat.dto.response.auth.IdCheckResponseDto;
import com.example.AIChat.entity.CertificationEntity;
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

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto){

        try{

            String loginId = dto.getLoginId();
            boolean isExistId = userRepository.existsByLoginId(loginId);
            if(isExistId) return IdCheckResponseDto.duplicateId();
        
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

            boolean isExistId = userRepository.existsByLoginId(loginId);
            if(isExistId) return EmailCertificationResponseDto.duplicateId();

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

}
