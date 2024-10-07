package com.demo.rbac.services;

import com.demo.rbac.entities.users.UserAccount;
import com.demo.rbac.repositories.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccount findUserByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }
}
