package testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTest {

    @Test
    void myTest(){
        Account newAccount = new Account();
        assertFalse(newAccount.isActive(), "Check if new account is not active");
    }
    @Test
    void myTest2(){
        Account newAccount = new Account();
        assertFalse(newAccount.isActive());
        newAccount.activate();
        assertTrue(newAccount.isActive());
    }

}
