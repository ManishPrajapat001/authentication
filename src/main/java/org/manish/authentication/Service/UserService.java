package org.manish.authentication.Service;

import org.manish.authentication.dto.LoginUserDTO;
import org.manish.authentication.dto.RegisterUserDTO;
import org.manish.authentication.dto.UserResponseDTO;
import org.manish.authentication.entity.User;
import org.manish.authentication.exception.InvalidPasswordException;
import org.manish.authentication.exception.UserAlreadyExistsException;
import org.manish.authentication.exception.UserNotFoundException;
import org.manish.authentication.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//loggs
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper ;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Value("${app.default-role}")
    private String defaultRole;

    @Value("${app.default-enabled}")
    private Boolean defaultEnabled;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public UserResponseDTO register(RegisterUserDTO userDTO)  {

        boolean isUserExist = userRepository.existsByUsername(userDTO.getUsername());
        if(isUserExist){
            logger.error("Attempt to register with existing username: {}", userDTO.getUsername());
            throw new UserAlreadyExistsException("Username already exists!");
        }
        User user  = new User();
        user.setUsername(userDTO.getUsername());
        user.setIsEnabled(defaultEnabled);
        user.setRole(defaultRole);
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(hashedPassword);
        logger.atInfo().log("Registering user with username: {}", userDTO.getUsername());
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserResponseDTO.class);
//        return new UserResponseDTO(savedUser.getId(),savedUser.getUsername(),savedUser.getRole(),savedUser.getIsEnabled());
    }

    public UserResponseDTO loginUser(LoginUserDTO userDTO){
        User user  = userRepository.findByUsername(userDTO.getUsername());
        if (user == null){
            logger.error("Login attempt with non-existing username: {}", userDTO.getUsername());
            throw new UserNotFoundException("User not found!");
        }
//        if(!user.getPassword().equals(userDTO.getPassword()) ){
//            throw new InvalidPasswordException("Invalid password!");
//        }
        if(!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())){
            logger.error("Invalid password attempt for username: {}", userDTO.getUsername());
            throw new InvalidPasswordException("Invalid password!");
        }
        logger.atInfo().log("User logged in successfully: {}", userDTO.getUsername());
        return modelMapper.map(user,UserResponseDTO.class);
//        return new UserResponseDTO(user.getId(),user.getUsername(),user.getRole(),user.getIsEnabled());
    }


}
