package com.demo.rbac.exchanges.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString(callSuper = true)
public class LoginResponseDto extends GenericResponseDto {
    @ToString.Exclude
    private String bearerToken;

    public LoginResponseDto(HttpStatus httpStatus) {
        super(httpStatus);
    }
}
