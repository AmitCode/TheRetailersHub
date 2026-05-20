package com.intoThe.mapper;

import com.intoThe.entities.EntityVerificationToken;

public class VerificationTokenModelMapper {
    public static EntityVerificationToken getVerificationToken(String token, String tokeHashType,
                                                               Long userId, String userName){
        return EntityVerificationToken.createEntityVerificationToken()
                .setVerificationToken(token)
                .setTokenHashType(tokeHashType)
                .setTokenValidDuration(15)
                .setUserId(userId);
    }
}
