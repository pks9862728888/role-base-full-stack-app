package com.demo.rbac.ecommerce.userservice.controllers.auth;

import com.demo.rbac.ecommerce.userservice.exchanges.request.LoginRequestDto;
import com.demo.rbac.ecommerce.userservice.exchanges.response.LoginResponseDto;
import com.demo.rbac.ecommerce.userservice.services.UserAccountService;
import com.demo.rbac.ecommerce.userservice.services.auth.JwtUtilsService;
import com.demo.rbac.ecommerce.persistence.entitites.user.UserAccount;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtilsService jwtUtilsService;
    private final UserAccountService userAccountService;

    private static void logLoginFailedException(LoginRequestDto reqDto, Exception e) {
        log.warn("Login failed for user: {} ex: {}", reqDto.getUsername(), e.toString());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto reqDto, HttpServletResponse response) {
        LoginResponseDto responseDto = new LoginResponseDto(HttpStatus.BAD_REQUEST);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(reqDto.getUsername(), reqDto.getPassword()));
            String username = authentication.getName();
            UserAccount userAccount = userAccountService.findUserByUsername(username);
            String token = jwtUtilsService.createJwtToken(userAccount);
            responseDto.setBearerToken(token);
            responseDto.setHttpStatus(HttpStatus.OK);
            responseDto.setMessage("Login success!");
            log.info("User: {} logged in!", userAccount.getUsername());
        } catch (LockedException e) {
            logLoginFailedException(reqDto, e);
            responseDto.setMessage("Account is locked, please contact support...");
        } catch (BadCredentialsException e) {
            responseDto.setMessage("Incorrect username or password");
        } catch (InternalAuthenticationServiceException e) {
            logLoginFailedException(reqDto, e);
            responseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseDto.setMessage("Login failed! Please check username and password and try again...");
        } catch (Exception e) {
            logLoginFailedException(reqDto, e);
            responseDto.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDto.setMessage("Internal server error. Please contact support...");
        }
        return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
    }
}
