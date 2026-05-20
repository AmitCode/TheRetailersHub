package com.intoThe.service.impl;

import com.intoThe.dto.response.AuthenticationServiceResponse;
import com.intoThe.entities.EntityVerificationToken;
import com.intoThe.entities.Users;
import com.intoThe.exceptions.SuppliersOprException.InvalidToken;
import com.intoThe.exceptions.SuppliersOprException.ResourceNotFound;
import com.intoThe.exceptions.SuppliersOprException.VerificationTokenException;
import com.intoThe.mapper.UserDataModelMapper;
import com.intoThe.repository.EntityVerificationTokenRepository;
import com.intoThe.repository.UserRepository;
import com.intoThe.service.WebClientServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class TokenVerificationService {

    private final EntityVerificationTokenRepository verificationTokenRepository;
    private final UserRepository repository;
    private final WebClient userServiceWebClient;
    public TokenVerificationService(EntityVerificationTokenRepository verificationTokenRepository,
                                    UserRepository repository,
                                    @Qualifier("userServiceWebClient") WebClient userServiceWebClient){
        this.verificationTokenRepository = verificationTokenRepository;
        this.repository = repository;
        this.userServiceWebClient = userServiceWebClient;
    }
    public ResponseEntity<AuthenticationServiceResponse> verifyToken(String token){

        Optional<EntityVerificationToken> verificationToken = verificationTokenRepository
                .findByVerificationToken(token);
        if(verificationToken.isEmpty()){
            throw new InvalidToken("Invalid verification token!...");
        }

        EntityVerificationToken entityVerificationToken = verificationToken.get();
        if(entityVerificationToken.getIsVerified().equalsIgnoreCase("Y")){
            throw new VerificationTokenException("User already verified!...");
        }
        entityVerificationToken.setIsVerified("Y");
        verificationTokenRepository.save(entityVerificationToken);

        Optional<Users> usersOptional = repository.findByUserId(entityVerificationToken.getUserId());
        if(usersOptional.isEmpty())
                throw new ResourceNotFound("No User is not found with user id associated with this verification " +
                        "token!...");

        return WebClientServices.callUserService(
                UserDataModelMapper.mapToUserDTO(usersOptional.get()), userServiceWebClient);
    }
}
