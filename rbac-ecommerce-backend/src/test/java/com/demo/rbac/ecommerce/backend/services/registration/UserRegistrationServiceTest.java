package com.demo.rbac.ecommerce.backend.services.registration;

import com.demo.rbac.ecommerce.backend.entities.user.UserAccount;
import com.demo.rbac.ecommerce.backend.entities.user.UserDetails;
import com.demo.rbac.ecommerce.backend.entities.user.UserRole;
import com.demo.rbac.ecommerce.backend.enums.Role;
import com.demo.rbac.ecommerce.backend.exchanges.request.UserRegistrationRequestDto;
import com.demo.rbac.ecommerce.backend.exchanges.response.UserRegistrationResponseDto;
import com.demo.rbac.ecommerce.backend.repositories.UserAccountRepository;
import com.demo.rbac.ecommerce.backend.repositories.UserDetailsRepository;
import com.demo.rbac.ecommerce.backend.repositories.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserRegistrationServiceTest {
    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserRegistrationService userRegistrationService;

    @Test
    void mapToUserEntity() {
        // Given
        UserRegistrationRequestDto requestDto = getUserRegistrationRequestDto();
        String encodedPassword = "encodedPassword";
        // When
        when(passwordEncoder.encode(requestDto.getPassword())).thenReturn(encodedPassword);
        UserAccount userAccount = userRegistrationService.mapToUserEntity(requestDto);
        // Then
        Assertions.assertEquals(requestDto.getEmail(), userAccount.getEmail());
        Assertions.assertEquals(requestDto.getUsername(), userAccount.getUsername());
        Assertions.assertEquals(encodedPassword, userAccount.getPassword());
        Assertions.assertFalse(userAccount.isLocked());
        Assertions.assertTrue(userAccount.isEnabled());
        Mockito.verify(passwordEncoder, times(1)).encode(requestDto.getPassword());
    }

    @Test
    void registerBuyerSuccess() {
        // Given
        UserRegistrationRequestDto requestDto = getUserRegistrationRequestDto();
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("username");
        userAccount.setId(1L);
        UserRole userRole = UserRole.builder().role(Role.ROLE_BUYER).build();
        // When
        when(userAccountRepository.saveAndFlush(any(UserAccount.class))).thenReturn(userAccount);
        when(userRoleRepository.saveAndFlush(any(UserRole.class))).thenReturn(userRole);
        when(userDetailsRepository.save(any(UserDetails.class))).thenReturn(new UserDetails());
        boolean result = userRegistrationService.registerBuyer(requestDto);
        // Then
        Assertions.assertTrue(result);
        verify(userAccountRepository).saveAndFlush(any(UserAccount.class));
        verify(userRoleRepository).saveAndFlush(any(UserRole.class));
        verify(userDetailsRepository).saveAndFlush(any(UserDetails.class));
    }

    @Test
    void emailAlreadyRegisteredTrue() {
        when(userAccountRepository.existsUserByEmail(Mockito.anyString())).thenReturn(true);
        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto();
        boolean existsByEmail = userRegistrationService.emailAlreadyRegistered(
                getUserRegistrationRequestDto(), responseDto);
        Assertions.assertTrue(existsByEmail);
        Assertions.assertEquals("User already registered with this email!", responseDto.getMessage());
        verify(userAccountRepository, times(1))
                .existsUserByEmail(anyString());
    }

    @Test
    void emailAlreadyRegisteredFalse() {
        when(userAccountRepository.existsUserByEmail(Mockito.anyString())).thenReturn(false);
        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto();
        boolean existsByEmail = userRegistrationService.emailAlreadyRegistered(
                getUserRegistrationRequestDto(), responseDto);
        Assertions.assertFalse(existsByEmail);
        Assertions.assertNull(responseDto.getMessage());
        verify(userAccountRepository, times(1))
                .existsUserByEmail(Mockito.anyString());
    }

    @Test
    void usernameTakenTrue() {
        when(userAccountRepository.existsUserByUsername(Mockito.anyString())).thenReturn(true);
        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto();
        boolean userNameTaken = userRegistrationService.usernameTaken(getUserRegistrationRequestDto(), responseDto);
        Assertions.assertTrue(userNameTaken);
        Assertions.assertEquals("Username is already taken!", responseDto.getMessage());
        verify(userAccountRepository, times(1))
                .existsUserByUsername(Mockito.anyString());
    }

    @Test
    void usernameTakenFalse() {
        when(userAccountRepository.existsUserByUsername(Mockito.anyString())).thenReturn(false);
        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto();
        boolean userNameTaken = userRegistrationService.usernameTaken(getUserRegistrationRequestDto(), responseDto);
        Assertions.assertFalse(userNameTaken);
        Assertions.assertNull(responseDto.getMessage());
        verify(userAccountRepository, times(1))
                .existsUserByUsername(Mockito.anyString());
    }

    private static UserRegistrationRequestDto getUserRegistrationRequestDto() {
        UserRegistrationRequestDto requestDto = new UserRegistrationRequestDto();
        requestDto.setUsername("username");
        requestDto.setPassword("testPassword");
        requestDto.setEmail("testemail@example.com");
        return requestDto;
    }
}
