package com.intoThe.dto;

//import com.intoThe.entities.Address;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.OneToMany;
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
    @NotBlank(message = "User Name can't be empty!...")
    private String userName;
    private String isUserActive;
    @NotBlank(message = "User Password can't be empty!...")
    private String userPassword;

}
