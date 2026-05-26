package com.intoThe.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PasswordResetRequest {
    @NotBlank(message = "Verification token is not found!...")
    private String token;
    @NotBlank(message = "Please provide Old Password!...")
    private String oldPassword;
    @NotBlank(message = "Please provide New Password!...")
    private String newPassword;
}
