package org.manish.authentication.Service;

import org.manish.authentication.entity.User;
import org.manish.authentication.entity.UserDTO;
import org.manish.authentication.exception.UserAlreadyExistsException;
import org.manish.authentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository _userRepository;



    public User register(UserDTO userDTO)  {

        boolean isUserExist = _userRepository.existsByUsername(userDTO.getUsername());
        if(isUserExist){
            throw new UserAlreadyExistsException("Username already exists!");
        }
        User user  = new User();
        user.setUsername(userDTO.getUsername());
        user.setEnabled(false);
        user.setRole("USER");
        user.setPassword(userDTO.getPassword());
        return _userRepository.save(user);
    }


}
