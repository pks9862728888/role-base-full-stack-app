package com.demo.rbac.services;

import com.demo.rbac.entities.users.UserAccount;
import com.demo.rbac.repositories.UserAccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserAccountServiceTest {

    @MockBean
    private UserAccountRepository userAccountRepository;

    @Autowired
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
    }
}
