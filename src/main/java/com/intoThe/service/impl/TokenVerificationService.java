package com.intoThe.service.impl;

import com.intoThe.dto.request.EmailRequest;
import com.intoThe.dto.response.AuthenticationServiceResponse;
import com.intoThe.dto.response.EmailServiceResponse;
import com.intoThe.entities.EntityVerificationToken;
import com.intoThe.entities.Users;
import com.intoThe.exceptions.SuppliersOprException.InvalidToken;
import com.intoThe.exceptions.SuppliersOprException.ResourceNotFound;
import com.intoThe.exceptions.SuppliersOprException.VerificationTokenException;
import com.intoThe.exceptions.SuppliersOprException.VerificationTokenExpired;
import com.intoThe.mapper.UserDataModelMapper;
import com.intoThe.mapper.VerificationTokenModelMapper;
import com.intoThe.repository.EntityVerificationTokenRepository;
import com.intoThe.repository.UserRepository;
import com.intoThe.service.WebClientServices;
import com.intoThe.utils.AuthServiceUtils;
import com.intoThe.utils.HashUtils;
import com.intoThe.utils.VerificationUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class TokenVerificationService {

    private final EntityVerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;
    private final WebClient userServiceWebClient;
    private final WebClient notificationServiceWebClient;

    @Value("${verification.token.expiry.time.unit}")
    private String verificationExpiryTimeUnit;

    public TokenVerificationService(EntityVerificationTokenRepository verificationTokenRepository,
                                    UserRepository userRepository,
                                    @Qualifier("userServiceWebClient") WebClient userServiceWebClient,
                                    @Qualifier("notificationServiceWebClient") WebClient notificationServiceWebClient){
        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;
        this.userServiceWebClient = userServiceWebClient;
        this.notificationServiceWebClient = notificationServiceWebClient;
    }

    @Transactional
    public ResponseEntity<AuthenticationServiceResponse> verifyToken(String token) throws Exception {

        Optional<EntityVerificationToken> verificationToken = verificationTokenRepository
                .findByVerificationToken(token);
        if(verificationToken.isEmpty()){
            throw new InvalidToken("Invalid verification token!...");
        }

        EntityVerificationToken entityVerificationToken = verificationToken.get();
        if(entityVerificationToken.getIsVerified()){
            throw new VerificationTokenException("User already verified!...");
        }

        if(VerificationUtils. isTokenExpired(entityVerificationToken.getTokenGeneratedAt(), verificationExpiryTimeUnit)){
            throw new VerificationTokenExpired("Token has expired!...");
        }

        Optional<Users> usersOptional = userRepository.findByUserId(entityVerificationToken.getUserId());
        if(usersOptional.isEmpty())
            throw new ResourceNotFound("No User is not found with user id associated with this verification " +
                    "token!...");

        Users users = usersOptional.get();
        entityVerificationToken.setIsVerified(true);
        users.setIsUserVerified(true);

        verificationTokenRepository.save(entityVerificationToken);
        userRepository.save(users);

        return WebClientServices.callUserService("/usersOpr/v1/addNewUser",
                UserDataModelMapper.mapToUserDTO(users), userServiceWebClient);
    }

    @Transactional
    public ResponseEntity<AuthenticationServiceResponse> regenerateVerificationLink(String userEmail){
        AuthenticationServiceResponse authenticationServiceResponse;
        try {
            Optional<Users> usersOptional = userRepository.findByUserEmailId(userEmail);
            if(usersOptional.isPresent()){
                Users users = usersOptional.get();
                if(users.getIsUserVerified()){
                    String token = VerificationUtils.generateVerificationToken();
                    String hashToken = HashUtils.getSHA256Hash(token);
                    EntityVerificationToken verificationToken = VerificationTokenModelMapper.getVerificationToken(
                            hashToken, "SHA-256", users.getUserId(), users.getUserName()
                    );

                    verificationTokenRepository.save(verificationToken);
                    EmailRequest emailRequest = AuthServiceUtils.prepareEmailRequest(users, hashToken);
                    ResponseEntity<EmailServiceResponse> responseEntity = WebClientServices
                            .callEmailNotificationService("email/sendMail" ,emailRequest, notificationServiceWebClient);
                }else{
                    throw new VerificationTokenException("User already verified!...");
                }
            }
        }finally {
            authenticationServiceResponse = AuthenticationServiceResponse.createResponse()
                    .setResponseMsg("If an account exists with this email and is not verified, " +
                            "a verification email has been sent.")
                    .setStatusCode(HttpStatus.OK.toString())
                    .setIsOprSuccess(true);
        }
        return new ResponseEntity<>(authenticationServiceResponse, HttpStatus.OK);
    }
}
