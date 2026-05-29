package com.intoThe.configuration;

import com.intoThe.filter.JwtApiFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtApiFilter filter;
    public SecurityConfiguration(JwtApiFilter filter){
        this.filter = filter;
    }
    /**
     * This method is used to generate the authentication manager bean. It is used
     * to configure the authentication manager for the application. It is a method
     * that provides the AuthenticationManager object to the user. The
     * AuthenticationManager object is used to configure the authentication
     * manager for the application. It is a method that provides the
     * AuthenticationManager object to the user. The AuthenticationManager object
     * is used to configure the authentication manager for the application. It is a
     * method that provides the AuthenticationManager object to the user. The
     * AuthenticationManager object is used to configure the authentication
     * manager for the application.
     *
     * @return the AuthenticationManager bean
     * @throws Exception if an error occurs while generating the authentication
     *                   manager bean
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        return security.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/auth/login", "/auth/register", "/userService/createNewUser",
                                        "/verify/verifyUserAccount", "/userService/forgot-password-request").permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    /**
     * This method is used to configure the SecurityFilterChain. It is used to
     * configure the security rules for the application. It is a method that
     * provides the HttpSecurity object to the user. The HttpSecurity object is
     * used to configure the security rules for the application. It is used to
     * configure the security rules for the application. It is a method that
     * provides the HttpSecurity object to the user. The HttpSecurity object is
     * used to configure the security rules for the application. It is used to
     * configure the security rules for the application. It is a method that
     * provides the HttpSecurity object to the user. The HttpSecurity object is
     * used to configure the security rules for the application. It is used to
     * configure the security rules for the application. It is a method that
     * provides the HttpSecurity object to the user. The HttpSecurity object is
     * used to configure the security rules for the application. It is used to
     * configure the security rules for the application. It is a method that
     * provides the HttpSecurity object to the user. The HttpSecurity object is
     * used to configure the security rules for the application. It is used to
     * configure the security rules for the application.
     *
     * @param //Security The HttpSecurity object used to configure the security
     *                 rules for the application.
     * @return The configured SecurityFilterChain.
     * @throws Exception If any error occurs during the configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
    throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
