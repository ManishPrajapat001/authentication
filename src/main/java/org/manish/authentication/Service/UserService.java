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
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper ;
    @Value("${app.default-role}")
    private String defaultRole;

    @Value("${app.default-enabled}")
    private Boolean defaultEnabled;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public UserResponseDTO register(RegisterUserDTO userDTO)  {

        boolean isUserExist = userRepository.existsByUsername(userDTO.getUsername());
        if(isUserExist){
            throw new UserAlreadyExistsException("Username already exists!");
        }
        User user  = new User();
        user.setUsername(userDTO.getUsername());
        user.setIsEnabled(defaultEnabled);
        user.setRole(defaultRole);
        user.setPassword(userDTO.getPassword());
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserResponseDTO.class);
//        return new UserResponseDTO(savedUser.getId(),savedUser.getUsername(),savedUser.getRole(),savedUser.getIsEnabled());
    }

    public UserResponseDTO loginUser(LoginUserDTO userDTO){
        User user  = userRepository.findByUsername(userDTO.getUsername());
        if (user == null){
            throw new UserNotFoundException("User not found!");
        }
        if(!user.getPassword().equals(userDTO.getPassword()) ){
            throw new InvalidPasswordException("Invalid password!");
        }
        return modelMapper.map(user,UserResponseDTO.class);
//        return new UserResponseDTO(user.getId(),user.getUsername(),user.getRole(),user.getIsEnabled());
    }


}
