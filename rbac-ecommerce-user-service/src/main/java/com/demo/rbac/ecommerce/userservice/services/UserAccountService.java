package com.demo.rbac.ecommerce.userservice.services;

import com.demo.rbac.ecommerce.persistence.entitites.user.UserAccount;
import com.demo.rbac.ecommerce.persistence.repositories.user.UserAccountRepository;
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
