package com.intoThe.service;

import com.intoThe.dto.UserDTO;
import com.intoThe.dto.request.EmailRequest;
import com.intoThe.dto.response.AuthenticationServiceResponse;
import com.intoThe.dto.response.EmailServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientServices {
    public static ResponseEntity<AuthenticationServiceResponse> callUserService(UserDTO userDTO,
                                                                                WebClient webClient){
        return webClient.post()
                .uri("/usersOpr/v1/addNewUser")
                .bodyValue(userDTO)
                .retrieve()
                .toEntity(AuthenticationServiceResponse.class)
                .block();
    }

    public static ResponseEntity<EmailServiceResponse> callEmailNotificationService(
            EmailRequest request, WebClient client){

        return client.post()
                .uri("email/sendMail")
                .bodyValue(request)
                .retrieve()
                .toEntity(EmailServiceResponse.class)
                .block();
    }
}
