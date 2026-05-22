package com.intoThe.service.impl;

import com.intoThe.dto.UserDTO;
import com.intoThe.dto.request.EmailRequest;
import com.intoThe.dto.response.AuthenticationServiceResponse;
import com.intoThe.dto.response.EmailServiceResponse;
import com.intoThe.entities.EntityVerificationToken;
import com.intoThe.entities.Users;
import com.intoThe.exceptions.SuppliersOprException.EmailIdAlreadyExist;
import com.intoThe.exceptions.SuppliersOprException.UserNameAlreadyExist;
import com.intoThe.mapper.UserDataModelMapper;
import com.intoThe.mapper.VerificationTokenModelMapper;
import com.intoThe.repository.EntityVerificationTokenRepository;
import com.intoThe.repository.UserRepository;
import com.intoThe.service.UserService;
import com.intoThe.service.WebClientServices;
import com.intoThe.utils.AuthServiceUtils;
import com.intoThe.utils.HashUtils;
import com.intoThe.utils.UserUtils;
import com.intoThe.utils.VerificationUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EntityVerificationTokenRepository tokenRepository;
    private final WebClient notificationServiceWebClient;
    private final WebClient userServiceWebClient;
    //private final RestTemplate restTemplate;
    private final UserDataModelMapper userModelMapper = new UserDataModelMapper();
    AuthenticationServiceResponse response;

    //@Autowired ----> // @Autowired needed — ONLY ONE constructor
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           @Qualifier("notificationServiceWebClient") WebClient webClient,
                           @Qualifier("userServiceWebClient") WebClient userServiceWebClient,
                           EntityVerificationTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.notificationServiceWebClient = webClient;
        this.tokenRepository = tokenRepository;
        this.userServiceWebClient = userServiceWebClient;
        //this.restTemplate = restTemplate;
    }

    /**
     * This method is used to add a new user to the database.
     *
     * @param userDTO The {@link UserDTO} object containing the details of the user to be added.
     * @return A string indicating that the data has been saved successfully.
     */
    /*@Transactional:-
    * This annotation only works inside ONE database transaction.
    * This will only control the transaction within the auth service.
    * This will not roll back if the user service or notification service api call fails.\
    * for this we will have to use Kafka or RabbitMQ for distributed transaction management.*/
//    @Override
//    @Transactional
//    public ResponseEntity<?> addUser(UserDTO userDTO) {
//        userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
////            synchronized (this){
////
////            }
//
////            if(UserUtils.isUserNameAlreadyExist(userDTO.getUserName(), userRepository)){
////                System.out.println("User Name: " + userDTO.getUserName());
////                throw  new UserNameAlreadyExist("User with this user name already exist!...");
////            }else{
////
////                Users newUser = userRepository.save(userModelMapper.mapToUser(userDTO));
////                //AuthenticationServiceResponse response1 = restTemplate.post
////                try{
////                    response = webClient.post()
////                            .uri("/usersOpr/v1/addNewUser")
////                            .bodyValue(userDTO)
////                            .retrieve()
////                            .bodyToMono(AuthenticationServiceResponse.class)
////                            .block();
////
////                    response.setResponseMsg("User created successfully!...")
////                            .setIsOprSuccess(true)
////                            .setStatusCode(HttpStatus.CREATED.toString());
////                }catch (WebClientResponseException webClientResponseException){
////                    return ResponseEntity
////                            .status(webClientResponseException.getStatusCode())
////                            .contentType(MediaType.APPLICATION_JSON)
////                            .body(webClientResponseException.getResponseBodyAsString());
////                }
////            }
//            /*
//            Commented the above code for handling the race conditions issue.so i had 3 3 options to handle this.
//            Option1:- synchronized(this)
//            i haven't used this because :-> it only works per JVM instance, not across:
//                1.multiple app instances (microservices, scaling).
//                2.multiple nodes
//            Option2:- synchronized(ClassName.class)
//            we will not use this because ->
//                1.Global lock across all threads.
//                2.Kills throughput under load.
//                3.Still doesn’t work in distributed systems
//            Option3:- Correct Approach (Production Standard)
//                1. Enforce uniqueness at DB level(this is mandatory)
//                  ---> @Column(unique = true)
//                        private String userName;
//                2. Remove manual check -> UserUtils.isUserNameAlreadyExist(...)
//                3. Handle exception instead as i did below
//                   This is:-
//                    1.atomic.
//                    2.thread-safe.
//                    3.works across distributed systems.
//            */
//        if(UserUtils.isUserNameAlreadyExist(userDTO.getUserName(), userRepository)){
//            throw new UserNameAlreadyExist("User already exists with this username!...");
//        }else if (UserUtils.isUserExistWithEmail(userDTO.getUserEmail(), userRepository)){
//            throw new EmailIdAlreadyExist("User already exists with the Email!...");
//        }else {
//            try {
//
//                Users newUser = userRepository.save(userModelMapper.mapToUser(userDTO));
//                //Calling userService(microservice) for creating the new user.
//                ResponseEntity<AuthenticationServiceResponse> responseEntity = WebClientServices.callUserService(userDTO, webClient);
//                if(responseEntity.getStatusCode().equals(HttpStatus.CREATED)){
//                    response = new AuthenticationServiceResponse();
//                    response.setResponseMsg("User created successfully!...")
//                            .setIsOprSuccess(true)
//                            .setStatusCode(HttpStatus.CREATED.toString());
//                }else{
//                    return responseEntity;
//                }
//            } catch (DataIntegrityViolationException ex) {
//                throw new UserNameAlreadyExist("User with this user name already exist!...");
//            } catch (WebClientResponseException webClientResponseException) {
//                return ResponseEntity
//                        .status(webClientResponseException.getStatusCode())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(webClientResponseException.getResponseBodyAsString());
//            }
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        }
//    }

@Override
@Transactional
public ResponseEntity<?> addUser(UserDTO userDTO) {

    if(UserUtils.isUserNameAlreadyExist(userDTO.getUserName(), userRepository)){
        throw new UserNameAlreadyExist("User already exists with this username!...");

    }else if (UserUtils.isUserExistWithEmail(userDTO.getUserEmail(), userRepository)){
        throw new EmailIdAlreadyExist("User already exists with the Email!...");

    }else {
        try {

            userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
            Users newUser = userRepository.save(UserDataModelMapper.mapToUser(userDTO));

            String token = VerificationUtils.generateVerificationToken();
            String hashToken = HashUtils.getSHA256Hash(token);
            EntityVerificationToken verificationToken = VerificationTokenModelMapper.getVerificationToken(
                    hashToken, "SHA-256", newUser.getUserId(), newUser.getUserName()
            );

            tokenRepository.save(verificationToken);
            EmailRequest emailRequest = AuthServiceUtils.prepareEmailRequest(newUser, hashToken);
            ResponseEntity<EmailServiceResponse> responseEntity = WebClientServices
                    .callEmailNotificationService("email/sendMail", emailRequest, notificationServiceWebClient);

            response = AuthenticationServiceResponse.createResponse()
                    .setResponseMsg("User created successfully!...")
                    .setIsOprSuccess(true)
                    .setStatusCode(HttpStatus.CREATED.toString());

        } catch (DataIntegrityViolationException ex) {
            throw new UserNameAlreadyExist("User with this user name already exist!...");
        } catch (WebClientResponseException webClientResponseException) {
            return ResponseEntity
                    .status(webClientResponseException.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(webClientResponseException.getResponseBodyAsString());
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

    /**
     * This method is used to update an existing user in the database.
     *
     * @param userDTO The {@link UserDTO} object containing the details of the user to be updated.
     * @return The updated {@link UserDTO} object.
     */
    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDTO){

        String userName = userDTO.getUserName();
        Users users = UserUtils.isUserExist(userName,userRepository);
        users.setUserName(userDTO.getUserName());
        users.setIsUserActive(userDTO.getIsUserActive());
        users.setPassword(userDTO.getUserPassword());
        users = userRepository.save(users);

        return UserDataModelMapper.mapToUserDTO(users);
    }

    /**
     * This method is used to delete an existing user from the database.
     *
     * @param userName The ID of the user to be deleted.
     * @return A string indicating that the user has been successfully deleted.
     */
    @Transactional
    @Override
    public ResponseEntity<AuthenticationServiceResponse> deleteUser(String userName) {
        Users users = UserUtils.isUserExist(userName,userRepository);

        ResponseEntity<AuthenticationServiceResponse> authenticationServiceResponse = WebClientServices
                .callUserServiceDeleteUser("/deleteUser", userName, userServiceWebClient);

        if(authenticationServiceResponse.getStatusCode().is2xxSuccessful()){
            userRepository.deleteByUserName(userName);
            response = AuthenticationServiceResponse.createResponse()
                    .setStatusCode(HttpStatus.ACCEPTED.toString())
                    .setResponseMsg("User has been deleted successfully!...")
                    .setIsOprSuccess(true);
        }else{
            response = AuthenticationServiceResponse.createResponse()
                    .setStatusCode(authenticationServiceResponse.getBody().getStatusCode())
                    .setResponseMsg(authenticationServiceResponse.getBody().getResponseMsg())
                    .setIsOprSuccess(false);
        }

        return new ResponseEntity<> (response, HttpStatus.ACCEPTED);
    }

    /**
     * This method is used to activate or deactivate a user in the database.
     *
     * @param userName The ID of the user to be activated or deactivated.
     * @param isActive A string that is case-insensitive and can only be "Y" for activation or "N" for deactivation.
     * @return A string indicating that the user has been successfully activated or deactivated.
     * @throws IllegalArgumentException if the isActive parameter is neither "Y" nor "N".
     */
    @Transactional
    @Override
    public ResponseEntity<AuthenticationServiceResponse> activateOrDeactivate(String userName, Boolean isActive) {
        Users users = UserUtils.isUserExist(userName,userRepository);
        users.setIsUserActive(isActive);

        ResponseEntity<AuthenticationServiceResponse> authenticationServiceResponse = WebClientServices
                .callUserServiceActiveAndDeActivate("/deleteUser", userName, isActive, userServiceWebClient);

        if(authenticationServiceResponse.getStatusCode().is2xxSuccessful()){
            userRepository.deleteByUserName(userName);
            response = AuthenticationServiceResponse.createResponse()
                    .setStatusCode(HttpStatus.ACCEPTED.toString())
                    .setResponseMsg((isActive) ? "User has been deleted successfully!..."
                            : "User has been Deactivated!...")
                    .setIsOprSuccess(true);
        }else{
            response = AuthenticationServiceResponse.createResponse()
                    .setStatusCode(authenticationServiceResponse.getBody().getStatusCode())
                    .setResponseMsg(authenticationServiceResponse.getBody().getResponseMsg())
                    .setIsOprSuccess(false);
        }

        return new ResponseEntity<> (response, HttpStatus.ACCEPTED);
    }

    /**
     * This method is used to get the user information for a given userId.
     *
     * @param userName The userId of the user to get the information for.
    /**
     *

     * @return A list of {@link UserDTO} objects representing all users in the database.

     * @return A {@link UserDTO} object containing the information of the user.
     */
    @Transactional
    @Override
    public UserDTO getUserInfo(String userName) {
        return UserDataModelMapper.mapToUserDTO(UserUtils.isUserExist(userName,userRepository));
    }

    /**
     * This method is used to get the user information for all users in the database.
     *
     * @return A list of {@link UserDTO} objects representing all users in the database.
     */
    @Transactional
    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> usersList = userRepository.findAll();
        return userModelMapper.mapToListOfUserDTO(usersList);
    }
}
