package com.example.demo.api_controller;

import com.example.demo.model.GameUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void signup(SignupRequest dto) throws UserAlreadyExistException {

        boolean alreadyExist = userRepository.existsByUsername(dto.getUsername());

        if(alreadyExist) {
            throw new UserAlreadyExistException(dto.getUsername());
        } else {
            GameUser newUser = new GameUser(dto.getUsername(), encoder.encode(dto.getPassword()));

            // Define role
            Role roleUser = roleRepository.findByName(RoleEnum.ROLE_USER);
            List<Role> roleList = Arrays.asList(roleUser);
            newUser.setRoleList(roleList);
            userRepository.save(newUser);
        }

    }
}
