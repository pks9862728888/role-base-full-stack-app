package com.demo.rbac.ecommerce.persistence.repositories.user;

import com.demo.rbac.ecommerce.persistence.entitites.user.UserAccount;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);

    boolean existsUserByEmail(@NonNull String email);

    boolean existsUserByUsername(@NonNull String username);
}
