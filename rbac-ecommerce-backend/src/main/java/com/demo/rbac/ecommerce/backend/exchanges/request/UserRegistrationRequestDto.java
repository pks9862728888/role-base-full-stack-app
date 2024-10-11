package com.demo.rbac.ecommerce.backend.exchanges.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "password")
public class UserRegistrationRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String username;
    @NotNull
    private String password;
}
