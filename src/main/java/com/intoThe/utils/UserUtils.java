package com.intoThe.utils;

import com.intoThe.entities.Users;
import com.intoThe.exceptions.SuppliersOprException.ResourceNotFound;
import com.intoThe.exceptions.SuppliersOprException.SuppliersOprExceptionHandler;
import com.intoThe.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    /**
     * Checks if a user exists in the database.
     *
     * @param userId The ID of the user to be checked.
     * @param userRepository The repository to use for the check.
     * @return The user object if the user exists, null otherwise.
     * @throws SuppliersOprExceptionHandler if the user does not exist.
     */
    public static Users isUserExist(Long userId, UserRepository userRepository) {
        return userRepository.findById(userId)
                .orElseThrow(() -> (new ResourceNotFound("User with this id :" + userId + " not found!...")));
    }
}
