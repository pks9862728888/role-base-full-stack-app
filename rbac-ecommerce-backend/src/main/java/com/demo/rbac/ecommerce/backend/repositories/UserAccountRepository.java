package com.demo.rbac.ecommerce.backend.repositories;

import com.demo.rbac.ecommerce.backend.entities.users.UserAccount;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);

    boolean existsUserByEmail(@NotNull String email);

    boolean existsUserByUsername(@NotNull String username);
}
