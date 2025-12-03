package com.intoThe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "INTO_USER_DATA")
@AllArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userFirstName;
    private String userMiddleName;
    private String userLastName;
    private String userContactNumber;
    private String userEmailId;
    private String isUserActive;
    private String isMobileVarified;
    private String isEmailVarified;
    private String password;
    private String confirmPassword;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo",fetch = FetchType.EAGER)
    private List<Address> addresses;


    public Users() {
        this("Y");
    }
    public Users(String isUserActive) {
        this.isUserActive = isUserActive;
    }

}
