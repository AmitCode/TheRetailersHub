package com.intoThe.dto.request;

import com.intoThe.validation.annoation.AtLeastOneRequired;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AtLeastOneRequired
@Getter
@Setter
@NoArgsConstructor
public class UserLoginRequest {
    private String userEmail;

    @NotBlank(message = "Password can't be blank.")
    private String password;

    private String mobileNumber;
}
