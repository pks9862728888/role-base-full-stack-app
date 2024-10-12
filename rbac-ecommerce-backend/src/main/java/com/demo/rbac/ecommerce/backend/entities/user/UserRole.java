package com.demo.rbac.ecommerce.backend.entities.user;

import com.demo.rbac.ecommerce.backend.entities.Audit;
import com.demo.rbac.ecommerce.backend.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@ToString(callSuper = true)
public class UserRole extends Audit implements Serializable {
    @Id
    private Long userId;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @MapsId
    @OneToOne(targetEntity = UserAccount.class)
    private UserAccount userAccount;

    @Tolerate
    public UserRole() {
        // Empty constructor is created so that
        // using new operator also object can be created
    }
}
