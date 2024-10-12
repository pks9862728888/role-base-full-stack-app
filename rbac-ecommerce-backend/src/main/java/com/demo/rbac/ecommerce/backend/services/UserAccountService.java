package com.demo.rbac.ecommerce.backend.services;

import com.demo.rbac.ecommerce.backend.entities.user.UserAccount;
import com.demo.rbac.ecommerce.backend.repositories.UserAccountRepository;
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
