package com.demo.rbac.repositories;

import com.demo.rbac.entities.users.UserAccount;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);

    boolean existsUserByEmail(@NotNull String email);

    boolean existsUserByUsername(@NotNull String username);
}
