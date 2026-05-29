package com.intoThe.entities;

import com.intoThe.enums.OtpTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Entity
@Table(name = "INTO_OTP_INFO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OtpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recordId;
    private long userId;
    private String userEmail;
    private String otp;
    @Enumerated(EnumType.STRING)
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

    public static OtpEntity getOtpEntity(){
        return new OtpEntity();
    }

    public OtpEntity setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public OtpEntity setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public OtpEntity setOpt(String otp) {
        this.otp = otp;
        return this;
    }

    public OtpEntity setOtpTypes(OtpTypes otpTypes) {
        this.otpTypes = otpTypes;
        return this;
    }

    public OtpEntity setVerified(boolean verified) {
        isVerified = verified;
        return this;
    }

    public OtpEntity setRecordId(long recordId) {
        this.recordId = recordId;
        return this;
    }

    public OtpEntity setOtpGeneratedAt(LocalDateTime otpGeneratedAt) {
        this.otpGeneratedAt = otpGeneratedAt;
        return this;
    }

    public OtpEntity setOptRetry(int optRetry) {
        this.optRetry = optRetry;
        return this;
    }

    public OtpEntity setOtpValidDuration(int otpValidDuration) {
        this.otpValidDuration = otpValidDuration;
        return this;
    }

    public OtpEntity setOtpValidDurationUnit(String otpValidDurationUnit) {
        this.otpValidDurationUnit = otpValidDurationUnit;
        return this;
    }
}
