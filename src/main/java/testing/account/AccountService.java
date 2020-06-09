package testing.account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountService {

    private AccountReposytory accountReposytory;

    public AccountService(AccountReposytory accountReposytory) {
        this.accountReposytory = accountReposytory;
    }
    List<Account> getAllActiveAccounts(){
        return accountReposytory.getAllAccounts().stream()
                .filter(Account::isActive)
                .collect(Collectors.toList());
    }

    List<String> findByName(String name){
        return accountReposytory.getByName(name);

    }
}
