package com.demo.rbac.ecommerce.persistence.repositories.user;

import com.demo.rbac.ecommerce.persistence.entitites.user.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
