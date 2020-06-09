package testing.account;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceStubTest {

    @Test
    void getAllActiveAccount(){
        //give
        AccountReposytory accountReposytoryStab = new AccountReposytoryStab();
        AccountService accountService = new AccountService(accountReposytoryStab);

         //when
        List<Account> accountList= accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, hasSize(2));
    }





}