package com.demo.rbac.ecommerce.backend.repositories;

import com.demo.rbac.ecommerce.backend.entities.users.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
