package com.demo.rbac.controllers.registration;

import com.demo.rbac.controllers.GenericExceptionHandler;
import com.demo.rbac.exchanges.request.UserRegistrationRequestDto;
import com.demo.rbac.exchanges.response.UserRegistrationResponseDto;
import com.demo.rbac.services.registration.UserRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class UserRegistrationController extends GenericExceptionHandler {
    private final UserRegistrationService userRegistrationService;

    @PostMapping("/user")
    public ResponseEntity<UserRegistrationResponseDto> registerUser(
            @Valid @RequestBody UserRegistrationRequestDto reqDto) {
        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto();
        boolean registrationSuccess = false;
        if (!(userRegistrationService.emailAlreadyRegistered(reqDto, responseDto) ||
                userRegistrationService.usernameTaken(reqDto, responseDto))) {
            registrationSuccess = userRegistrationService.registerUser(reqDto);
            if (registrationSuccess) {
                responseDto.setMessage("Registration successful!! Please login...");
            }
        }
        responseDto.setStatus(registrationSuccess);
        return new ResponseEntity<>(responseDto, registrationSuccess ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
