package com.intoThe.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Column(unique = true)
    private String userName;
    private String userEmail;
    private Boolean isUserActive;
    private String password;
    private Boolean isUserVerified;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo",fetch = FetchType.EAGER)
//    private List<Address> addresses;


    public Users() {
        this(true, false);
    }
    public Users(Boolean isUserActive) {
        this.isUserActive = isUserActive;
    }

    public Users(Boolean isUserActive, Boolean isUserVerified) {
        this.isUserActive = isUserActive;
        this.isUserVerified = isUserVerified;
    }
}
