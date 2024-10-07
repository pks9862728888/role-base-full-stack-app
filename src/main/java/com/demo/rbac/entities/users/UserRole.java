package com.demo.rbac.entities.users;

import com.demo.rbac.entities.Audit;
import com.demo.rbac.enums.Role;
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

@Getter
@Setter
@Entity
@Builder
@ToString(callSuper = true)
public class UserRole extends Audit {
    @Id
    private Long userId;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @MapsId
    @OneToOne(targetEntity = UserAccount.class)
    private UserAccount userAccount;

    @Tolerate
    public UserRole() {
    }
}
