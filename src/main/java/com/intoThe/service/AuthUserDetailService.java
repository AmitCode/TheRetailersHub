package com.intoThe.service;

import com.intoThe.entities.Users;
import com.intoThe.exceptions.SuppliersOprException.JwtTokenValidationException;
import com.intoThe.exceptions.SuppliersOprException.UserNameNotFound;
import com.intoThe.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthUserDetailService implements UserDetailsService {
    private final UserRepository repository;
    public AuthUserDetailService(UserRepository repository){
        this.repository = repository;
    }
    @NotNull
    @Override
    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
        UserDetails userDetails;
        try{
            Optional<Users> userOptional = repository.findByUserName(username);
            if(userOptional.isEmpty())
                throw new UserNameNotFound("User does not exists!.");
            Users user = userOptional.get();
            userDetails = new User(user.getUserName(), user.getPassword(),
                    List.of(new SimpleGrantedAuthority("Admin")));
        }catch (Exception exception){
            throw new JwtTokenValidationException("{Error while user detail retrieval}-[" + exception.getMessage()
            +"]");
        }
        return userDetails;
    }
}
