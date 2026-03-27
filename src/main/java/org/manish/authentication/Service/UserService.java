package org.manish.authentication.Service;

import org.manish.authentication.dto.UserResponseDTO;
import org.manish.authentication.entity.User;
import org.manish.authentication.entity.UserDTO;
import org.manish.authentication.exception.InvalidPasswordException;
import org.manish.authentication.exception.UserAlreadyExistsException;
import org.manish.authentication.exception.UserNotFoundException;
import org.manish.authentication.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository _userRepository;

    public UserService(UserRepository userRepository) {
        _userRepository = userRepository;
    }


    public UserResponseDTO register(UserDTO userDTO)  {

        boolean isUserExist = _userRepository.existsByUsername(userDTO.getUsername());
        if(isUserExist){
            throw new UserAlreadyExistsException("Username already exists!");
        }
        User user  = new User();
        user.setUsername(userDTO.getUsername());
        user.setEnabled(false);
        user.setRole("USER");
        user.setPassword(userDTO.getPassword());
        User savedUser = _userRepository.save(user);
        return new UserResponseDTO(savedUser.getId(),savedUser.getUsername(),savedUser.getRole(),savedUser.getEnabled());
    }

    public UserResponseDTO loginuser(UserDTO userDTO){
        User user  = _userRepository.findByUsername(userDTO.getUsername());
        if (user == null){
            throw new UserNotFoundException("User not found!");
        }
        if(!user.getPassword().equals(userDTO.getPassword()) ){
            throw new InvalidPasswordException("Invalid password!");
        }
        return new UserResponseDTO(user.getId(),user.getUsername(),user.getRole(),user.getEnabled());
    }


}
