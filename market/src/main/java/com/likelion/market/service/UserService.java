package com.likelion.market.service;

import com.likelion.market.entity.Role;
import com.likelion.market.entity.UserEntity;
import com.likelion.market.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity create(String username, String password, String email, String phone, String address) {
        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRole(Role.USER);

        userRepository.save(user);

        return user;
    }
}
