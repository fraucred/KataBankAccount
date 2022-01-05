package domain;

import java.time.LocalDate;

public class BankClient {

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String accountId;
    private final AccountHistory accountHistory;

    public BankClient(String firstName, String lastName, LocalDate birthday, String accountId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.accountId = accountId;
        this.accountHistory = new AccountHistory();
    }

    public AccountHistory getAccountHistory() {
        return accountHistory;
    }

}