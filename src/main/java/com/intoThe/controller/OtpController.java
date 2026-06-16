package com.intoThe.controller;

import com.intoThe.dto.response.OtpServiceResponse;
import com.intoThe.repository.OtpRepository;
import com.intoThe.service.OtpService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otpService")
public class OtpController {

    private final OtpService otpService;
    public OtpController(OtpService otpService){
        this.otpService = otpService;
    }

    @PostMapping("/generateOtp")
    public ResponseEntity<OtpServiceResponse> generateOtp(@RequestHeader String userEmail){
        return otpService.generateOtp(userEmail);
    }

    @PostMapping("/validateOtp")
    public ResponseEntity<OtpServiceResponse> validateOtp(@Valid
            @NotBlank(message = "Please provide otp!") @RequestHeader String otp,
            @NotBlank(message = "Please enter a mail!") @Email(message = "Not an email!") @RequestHeader String email){

        return otpService.validateOtp(email, otp);
    }

}
