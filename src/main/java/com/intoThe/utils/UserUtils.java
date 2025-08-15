package com.intoThe.utils;

import com.intoThe.entities.Users;
import com.intoThe.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class UserUtils {

    public static Users isUserExist(Long userId, UserRepository userRepository){
        Users users = userRepository.findById(userId).
                orElseThrow(() -> new NoSuchElementException("User not found!..."));
        return users;
    }
}
