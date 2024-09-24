package com.demo.rbac.exchanges.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class GenericResponseDto {
    private HttpStatus httpStatus;
    private String message;

    public GenericResponseDto() {
    }

    public GenericResponseDto(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
