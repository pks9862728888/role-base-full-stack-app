package com.demo.rbac.ecommerce.backend.repositories;

import com.demo.rbac.ecommerce.backend.entities.user.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
