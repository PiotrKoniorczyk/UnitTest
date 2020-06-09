package testing.account;

import java.util.List;

public interface AccountReposytory {

    List<Account> getAllAccounts();
    List<String> getByName(String name);
}
