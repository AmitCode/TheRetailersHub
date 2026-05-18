package com.intoThe.service;

import com.intoThe.dto.UserDTO;
import com.intoThe.dto.response.AuthenticationServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientServices {
    public static ResponseEntity<AuthenticationServiceResponse> callUserService(UserDTO userDTO,
                                                                                WebClient webClient){
        ResponseEntity<AuthenticationServiceResponse> response = webClient.post()
                .uri("/usersOpr/v1/addNewUser")
                .bodyValue(userDTO)
                .retrieve()
                .toEntity(AuthenticationServiceResponse.class)
                .block();
    }
}
