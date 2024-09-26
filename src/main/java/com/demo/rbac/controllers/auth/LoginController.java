package com.demo.rbac.controllers.auth;

import com.demo.rbac.entities.users.User;
import com.demo.rbac.exchanges.request.LoginRequestDto;
import com.demo.rbac.exchanges.response.GenericResponseDto;
import com.demo.rbac.services.UserService;
import com.demo.rbac.services.auth.JwtUtilsService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<GenericResponseDto> login(
            @Valid @RequestBody LoginRequestDto reqDto, HttpServletResponse response) {
        GenericResponseDto responseDto = new GenericResponseDto(HttpStatus.BAD_REQUEST);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(reqDto.getUsername(), reqDto.getPassword()));
            String username = authentication.getName();
            User user = userService.findUserByUsername(username);
            String token = jwtUtilsService.createJwtToken(user);
            response.setHeader(HttpHeaders.AUTHORIZATION, token);
            responseDto.setHttpStatus(HttpStatus.OK);
            responseDto.setMessage("Login success!");
            log.info("User: {} logged in!", user.getUsername());
        } catch (LockedException e) {
            log.warn("Login failed fr user: {} ex: {}", reqDto.getUsername(), e.toString());
            responseDto.setMessage("Account is locked, please contact support...");
        } catch (BadCredentialsException e) {
            responseDto.setMessage("Invalid username or password");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Login failed for user: {} ex: {}", reqDto.getUsername(), e.toString());
            responseDto.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDto.setMessage("Internal server error. Please contact support...");
        }
        return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
    }
}
