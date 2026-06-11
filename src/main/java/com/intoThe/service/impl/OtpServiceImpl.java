package com.intoThe.service.impl;

import com.intoThe.dto.response.OtpServiceResponse;
import com.intoThe.entities.EntityVerificationToken;
import com.intoThe.entities.OtpEntity;
import com.intoThe.exceptions.SuppliersOprException.InternalServerErrorException;
import com.intoThe.exceptions.SuppliersOprException.OtpValidationException;
import com.intoThe.mapper.VerificationTokenModelMapper;
import com.intoThe.repository.OtpRepository;
import com.intoThe.service.OtpService;
import com.intoThe.utils.HashUtils;
import com.intoThe.utils.VerificationTokenUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;

    public OtpServiceImpl(OtpRepository otpRepository){
        this.otpRepository = otpRepository;
    }

    @Transactional
    @Override
    public ResponseEntity<OtpServiceResponse> validateOtp(String email, String otp) {
        try{
            Optional<OtpEntity> otpEntity = otpRepository.findByEmail(email);
            if(otpEntity.isEmpty())
                throw new OtpValidationException("OTP does not exist or expired");
            OtpEntity storedOtp = otpEntity.get();
            if(!storedOtp.getOtp().equalsIgnoreCase(otp)){
                throw new OtpValidationException("Wrong otp!...");
            }else{

                String token = VerificationTokenUtils.generateVerificationToken();
                String hashToken = HashUtils.getSHA256Hash(token);
                EntityVerificationToken verificationToken = VerificationTokenModelMapper.getVerificationToken(
                        hashToken, "SHA-256", storedOtp.getUserId(), storedOtp.getUserEmail()
                );

                OtpServiceResponse response = OtpServiceResponse.createOtpResponse()
                        .setStatus(HttpStatus.OK.toString())
                        .setMessageToken(hashToken)
                        .setStatus("Otp Validation is successful.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        }catch (Exception ex){
            throw new InternalServerErrorException("{Exception in Validate Otp} - [Ex] :- " + ex.getMessage());
        }
        //return null;
    }
}
