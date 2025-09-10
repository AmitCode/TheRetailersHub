package com.intoThe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.jetbrains.annotations.NotNull;

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
    private String userContactNumber;

    @NotBlank(message = "User Email is required")
    @Email(message = "Email must be in proper format")
    private String userEmailId;
    private String isUserActive;
    private String isMobileVarified;
    private String isEmailVarified;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserContactNumber() {
        return userContactNumber;
    }

    public void setUserContactNumber(String userContactNumber) {
        this.userContactNumber = userContactNumber;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getIsUserActive() {
        return isUserActive;
    }

    public void setIsUserActive(String isUserActive) {
        this.isUserActive = isUserActive;
    }

    public String getIsMobileVarified() {
        return isMobileVarified;
    }

    public void setIsMobileVarified(String isMobileVarified) {
        this.isMobileVarified = isMobileVarified;
    }

    public String getIsEmailVarified() {
        return isEmailVarified;
    }

    public void setIsEmailVarified(String isEmailVarified) {
        this.isEmailVarified = isEmailVarified;
    }
}
