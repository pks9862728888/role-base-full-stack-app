package com.demo.rbac.repositories;

import com.demo.rbac.entities.users.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsUserByEmail(@NotNull String email);

    boolean existsUserByUsername(@NotNull String username);
}
