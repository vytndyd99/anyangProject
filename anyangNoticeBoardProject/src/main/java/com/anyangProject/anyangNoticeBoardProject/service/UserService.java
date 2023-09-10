package com.anyangProject.anyangNoticeBoardProject.service;

import com.anyangProject.anyangNoticeBoardProject.DataNotFoundException;
import com.anyangProject.anyangNoticeBoardProject.entitiy.UserEntity;
import com.anyangProject.anyangNoticeBoardProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity create(String username, String email, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(userEntity);
        return userEntity;
    }

    public UserEntity getUser(String username) {
        Optional<UserEntity> userEntity = this.userRepository.
                findByusername(username);
        if(userEntity.isPresent()) {
            return userEntity.get();
        }
        else {
            throw new DataNotFoundException("userEntity not found");
        }
    }
}
