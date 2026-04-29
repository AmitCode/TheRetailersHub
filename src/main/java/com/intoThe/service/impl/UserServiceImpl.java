package com.intoThe.service.impl;

import com.intoThe.dto.UserDTO;
import com.intoThe.dto.response.AuthenticationServiceResponse;
import com.intoThe.entities.Users;
import com.intoThe.exceptions.SuppliersOprException.UserNameAlreadyExist;
import com.intoThe.mapper.UserDataModelMapper;
import com.intoThe.repository.UserRepository;
import com.intoThe.service.UserService;
import com.intoThe.utils.UserUtils;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final WebClient webClient;
    //private final RestTemplate restTemplate;
    private final UserDataModelMapper userModelMapper = new UserDataModelMapper();
    AuthenticationServiceResponse response;

    //@Autowired ----> // @Autowired needed — ONLY ONE constructor
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           WebClient webClient) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.webClient = webClient;
        //this.restTemplate = restTemplate;
    }

    /**
     * This method is used to add a new user to the database.
     *
     * @param userDTO The {@link UserDTO} object containing the details of the user to be added.
     * @return A string indicating that the data has been saved successfully.
     */
    @Override
    @Transactional
    public ResponseEntity<?> addUser(UserDTO userDTO) {

        userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
            synchronized (this){

            }

//            if(UserUtils.isUserNameAlreadyExist(userDTO.getUserName(), userRepository)){
//                System.out.println("User Name: " + userDTO.getUserName());
//                throw  new UserNameAlreadyExist("User with this user name already exist!...");
//            }else{
//
//                Users newUser = userRepository.save(userModelMapper.mapToUser(userDTO));
//                //AuthenticationServiceResponse response1 = restTemplate.post
//                try{
//                    response = webClient.post()
//                            .uri("/usersOpr/v1/addNewUser")
//                            .bodyValue(userDTO)
//                            .retrieve()
//                            .bodyToMono(AuthenticationServiceResponse.class)
//                            .block();
//
//                    response.setResponseMsg("User created successfully!...")
//                            .setIsOprSuccess(true)
//                            .setStatusCode(HttpStatus.CREATED.toString());
//                }catch (WebClientResponseException webClientResponseException){
//                    return ResponseEntity
//                            .status(webClientResponseException.getStatusCode())
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .body(webClientResponseException.getResponseBodyAsString());
//                }
//            }
            /*
            Commented the above code for handling the race conditions issue.so i had 3 3 options to handle this.
            Option1:- synchronized(this)
            i haven't used this because :-> it only works per JVM instance, not across:
                1.multiple app instances (microservices, scaling).
                2.multiple nodes
            Option2:- synchronized(ClassName.class)
            we will not use this because ->
                1.Global lock across all threads.
                2.Kills throughput under load.
                3.Still doesn’t work in distributed systems
            Option3:- Correct Approach (Production Standard)
                1. Enforce uniqueness at DB level(this is mandatory)
                  ---> @Column(unique = true)
                        private String userName;
                2. Remove manual check -> UserUtils.isUserNameAlreadyExist(...)
                3. Handle exception instead as i did below
                   This is:-
                    1.atomic.
                    2.thread-safe.
                    3.works across distributed systems.
            */
            try{
                Users newUser = userRepository.save(userModelMapper.mapToUser(userDTO));
                //Calling userService(microservice) for creating the new user.
                webClient.post()
                        .uri("/usersOpr/v1/addNewUser")
                        .bodyValue(userDTO)
                        .retrieve()
                        .bodyToMono(AuthenticationServiceResponse.class)
                        .block();

                response.setResponseMsg("User created successfully!...")
                        .setIsOprSuccess(true)
                        .setStatusCode(HttpStatus.CREATED.toString());
            }catch (DataIntegrityViolationException ex) {
                throw new UserNameAlreadyExist("User with this user name already exist!...");
            }catch (WebClientResponseException webClientResponseException){
                return ResponseEntity
                        .status(webClientResponseException.getStatusCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(webClientResponseException.getResponseBodyAsString());
            }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    /**
     * This method is used to update an existing user in the database.
     *
     * @param userDTO The {@link UserDTO} object containing the details of the user to be updated.
     * @return The updated {@link UserDTO} object.
     */
    @Override
    public UserDTO updateUser(UserDTO userDTO){

        Long userId = userDTO.getUserId();
        Users users = UserUtils.isUserExist(userId,userRepository);
        users.setUserName(userDTO.getUserName());
        users.setIsUserActive(userDTO.getIsUserActive());
        users.setPassword(userDTO.getUserPassword());
        users = userRepository.save(users);

        return userModelMapper.mapToUserDTO(users);
    }

    /**
     * This method is used to delete an existing user from the database.
     *
     * @param userId The ID of the user to be deleted.
     * @return A string indicating that the user has been successfully deleted.
     */
    @Override
    public String deleteUser(Long userId) {
        Users users = UserUtils.isUserExist(userId,userRepository);
        userRepository.deleteById(userId);
        return "User has been deleted!...";
    }

    /**
     * This method is used to activate or deactivate a user in the database.
     *
     * @param userId The ID of the user to be activated or deactivated.
     * @param isActive A string that is case-insensitive and can only be "Y" for activation or "N" for deactivation.
     * @return A string indicating that the user has been successfully activated or deactivated.
     * @throws IllegalArgumentException if the isActive parameter is neither "Y" nor "N".
     */
    @Override
    public String activateOrDeactivate(Long userId, String isActive) {
        Users users = UserUtils.isUserExist(userId,userRepository);
        users.setIsUserActive(isActive);
        users = userRepository.save(users);
        if (isActive.equalsIgnoreCase("Y"))
            return "User has been Activated!...";
        return "User has been Deactivated!...";
    }

    /**
     * This method is used to get the user information for a given userId.
     *
     * @param userId The userId of the user to get the information for.
    /**
     *

     * @return A list of {@link UserDTO} objects representing all users in the database.

     * @return A {@link UserDTO} object containing the information of the user.
     */
    @Override
    public UserDTO getUserInfo(Long userId) {
        return userModelMapper.mapToUserDTO(UserUtils.isUserExist(userId,userRepository));
    }

    /**
     * This method is used to get the user information for all users in the database.
     *
     * @return A list of {@link UserDTO} objects representing all users in the database.
     */
    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> usersList = userRepository.findAll();
        return userModelMapper.mapToListOfUserDTO(usersList);
    }
}
