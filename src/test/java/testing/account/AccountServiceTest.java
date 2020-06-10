package testing.account;

import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class AccountServiceTest {

    @Test
    void getAllActiveAccount() {
        //give
        List<Account> accounts = prepareAccountData();
        AccountReposytory accountReposytory = mock(AccountReposytory.class);
        AccountService accountService = new AccountService(accountReposytory);
        //when(accountReposytory.getAllAccounts()).thenReturn(accounts);
        given(accountReposytory.getAllAccounts()).willReturn(accounts);

        //when
        List<Account> accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, hasSize(2));
    }

    @Test
    void getNoActiveAccount() {
        //give
        AccountReposytory accountReposytory = mock(AccountReposytory.class);
        AccountService accountService = new AccountService(accountReposytory);
        //when(accountReposytory.getAllAccounts()).thenReturn(accounts);
        given(accountReposytory.getAllAccounts()).willReturn(Collections.emptyList());

        //when
        List<Account> accountList = accountService.getAllActiveAccounts();

        //then
        assertThat(accountList, hasSize(0));
    }

    @Test
    void getAccountByName() {
        //give
        AccountReposytory accountReposytory = mock(AccountReposytory.class);
        AccountService accountService = new AccountService(accountReposytory);
        //when(accountReposytory.getAllAccounts()).thenReturn(accounts);
        given(accountReposytory.getByName("Dawid")).willReturn(Collections.singletonList("Nowak"));

        //when
        List<String> accountNames = accountService.findByName("Dawid");

        //then
        assertThat(accountNames, contains("Nowak"));
    }


    private List<Account> prepareAccountData() {
        Address address1 = new Address("Kwiatowa", "33.5");
        Account account1 = new Account(address1);

        Account account2 = new Account();

        Address address3 = new Address("Piekarska", "12b");
        Account account3 = new Account(address3);

        return Arrays.asList(account1, account2, account3);
    }
}

