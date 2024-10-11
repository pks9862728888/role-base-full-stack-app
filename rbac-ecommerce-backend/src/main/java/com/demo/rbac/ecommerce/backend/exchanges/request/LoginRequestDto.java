package com.demo.rbac.ecommerce.backend.exchanges.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "password")
public class LoginRequestDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
}