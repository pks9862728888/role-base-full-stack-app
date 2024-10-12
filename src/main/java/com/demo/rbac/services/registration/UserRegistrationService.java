package com.demo.rbac.services.registration;

import com.demo.rbac.entities.users.UserAccount;
import com.demo.rbac.entities.users.UserDetails;
import com.demo.rbac.entities.users.UserRole;
import com.demo.rbac.enums.Role;
import com.demo.rbac.exchanges.request.UserRegistrationRequestDto;
import com.demo.rbac.exchanges.response.UserRegistrationResponseDto;
import com.demo.rbac.repositories.UserDetailsRepository;
import com.demo.rbac.repositories.UserAccountRepository;
import com.demo.rbac.repositories.UserRoleRepository;
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
    private final UserAccountRepository userAccountRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public boolean registerBuyer(@Valid UserRegistrationRequestDto reqDto) {
        UserAccount userAccount = mapToUserEntity(reqDto);
        userAccount = userAccountRepository.saveAndFlush(userAccount);
        UserRole userRole = UserRole.builder()
                .userId(userAccount.getId())
                .userAccount(userAccount)
                .role(Role.ROLE_BUYER)
                .build();
        userRoleRepository.saveAndFlush(userRole);
        UserDetails userDetails = new UserDetails();
        userDetails.setUserAccount(userAccount);
        userDetailsRepository.saveAndFlush(userDetails);
        log.info("User: {} registration successful having role: {}", userAccount.getUsername(), userRole.getRole());
        return true;
    }

    public UserAccount mapToUserEntity(UserRegistrationRequestDto reqDto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(reqDto.getEmail());
        userAccount.setUsername(reqDto.getUsername());
        userAccount.setPassword(passwordEncoder.encode(reqDto.getPassword()));
        userAccount.setLocked(false);
        userAccount.setEnabled(true);
        return userAccount;
    }

    public boolean emailAlreadyRegistered(UserRegistrationRequestDto reqDto, UserRegistrationResponseDto responseDto) {
        boolean existsByEmail = userAccountRepository.existsUserByEmail(reqDto.getEmail());
        if (existsByEmail) {
            responseDto.setMessage("User already registered with this email!");
        }
        return existsByEmail;
    }

    public boolean usernameTaken(UserRegistrationRequestDto reqDto, UserRegistrationResponseDto responseDto) {
        boolean existsByUsername = userAccountRepository.existsUserByUsername(reqDto.getUsername());
        if (existsByUsername) {
            responseDto.setMessage("Username is already taken!");
        }
        return existsByUsername;
    }
}
