package com.demo.rbac.ecommerce.backend.controllers.registration;

import com.demo.rbac.ecommerce.backend.controllers.GenericExceptionHandler;
import com.demo.rbac.ecommerce.backend.exchanges.request.UserRegistrationRequestDto;
import com.demo.rbac.ecommerce.backend.exchanges.response.UserRegistrationResponseDto;
import com.demo.rbac.ecommerce.backend.services.registration.UserRegistrationService;
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

    @PostMapping("/buyer")
    public ResponseEntity<UserRegistrationResponseDto> registerUser(
            @Valid @RequestBody UserRegistrationRequestDto reqDto) {
        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto();
        boolean registrationSuccess = false;
        if (!(userRegistrationService.emailAlreadyRegistered(reqDto, responseDto) ||
                userRegistrationService.usernameTaken(reqDto, responseDto))) {
            registrationSuccess = userRegistrationService.registerBuyer(reqDto);
            if (registrationSuccess) {
                responseDto.setMessage("Registration successful!! Please login...");
            }
        }
        responseDto.setStatus(registrationSuccess);
        return new ResponseEntity<>(responseDto, registrationSuccess ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
