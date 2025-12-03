package com.intoThe.dto;

import com.intoThe.entities.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long userId;
    @NotBlank(message = "User First Name is required")
    private String userFirstName;

    @NotBlank(message = "User Middle Name is required")
    private String userMiddleName;

    @NotBlank(message = "User Last Name is required")
    private String userLastName;

    @NotBlank(message = "User Contact Number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits")
    private String userContactNumber;

    @NotBlank(message = "User Email is required")
    @Email(message = "Email must be in proper format")
    private String userEmailId;
    private String isUserActive;
    private String isMobileVarified;
    private String isEmailVarified;
    @NotBlank(message = "User Password can't be empty!...")
    private String userPassword;
    @NotBlank(message = "Re-confirm your password!...")
    private String isPasswordVarified;
    private List<AddressDTO> addresses;

}
