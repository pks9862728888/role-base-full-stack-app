package com.demo.rbac.ecommerce.backend.services;

import com.demo.rbac.ecommerce.backend.entities.user.UserAccount;
import com.demo.rbac.ecommerce.backend.repositories.UserAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
class UserAccountServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private UserAccountService userAccountService;

    @Test
    void testFindByUserNameWillReturnCorrectUser() {
        String testUsername = "testUsername";
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(testUsername);
        when(userAccountRepository.findByUsername(Mockito.matches(testUsername)))
                .thenReturn(userAccount);
        UserAccount userAccountByUsername = userAccountService.findUserByUsername(testUsername);
        assertEquals(userAccountByUsername, userAccount);
        verify(userAccountRepository, times(1)).findByUsername(testUsername);
    }
}
