package com.intoThe.entities;

import com.intoThe.enums.OtpTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Entity
@Table(name = "INTO_OTP_INFO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OtpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recordId;
    private long userId;
    private String userEmail;
    private String opt;
    private OtpTypes otpTypes;
    @CreationTimestamp
    private LocalDateTime otpGeneratedAt;
    @Value("${otp.retry.count}")
    private int optRetry;
    @Value("${otp.expiry.time.duration}")
    private int otpValidDuration;
    @Value("${otp.expiry.time.duration.unit}")
    private String otpValidDurationUnit;
    private boolean isVerified;
}
