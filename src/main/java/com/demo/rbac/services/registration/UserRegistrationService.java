package com.demo.rbac.services.registration;

import com.demo.rbac.entities.User;
import com.demo.rbac.entities.UserDetails;
import com.demo.rbac.exchanges.request.UserRegistrationRequestDto;
import com.demo.rbac.exchanges.response.UserRegistrationResponseDto;
import com.demo.rbac.repositories.UserDetailsRepository;
import com.demo.rbac.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRegistrationService {
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public boolean registerUser(@Valid UserRegistrationRequestDto reqDto) {
        User user = mapToUserEntity(reqDto);
        user = userRepository.saveAndFlush(user);
        UserDetails userDetails = new UserDetails();
        userDetails.setUser(user);
        userDetailsRepository.saveAndFlush(userDetails);
        log.info("User: {} registered", user.getUsername());
        return true;
    }

    private User mapToUserEntity(UserRegistrationRequestDto reqDto) {
        User user = new User();
        user.setEmail(reqDto.getEmail());
        user.setUsername(reqDto.getUsername());
        user.setPassword(passwordEncoder.encode(reqDto.getPassword()));
        user.setLocked(false);
        user.setEnabled(true);
        return user;
    }

    public boolean emailAlreadyRegistered(UserRegistrationRequestDto reqDto, UserRegistrationResponseDto responseDto) {
        boolean existsByEmail = userRepository.existsUserByEmail(reqDto.getEmail());
        if (existsByEmail) {
            responseDto.setMessage("User already registered with this email!");
        }
        return existsByEmail;
    }

    public boolean usernameTaken(UserRegistrationRequestDto reqDto, UserRegistrationResponseDto responseDto) {
        boolean existsByUsername = userRepository.existsUserByUsername(reqDto.getUsername());
        if (existsByUsername) {
            responseDto.setMessage("Username is already taken!");
        }
        return existsByUsername;
    }
}
