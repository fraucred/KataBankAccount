package domain;

import java.time.LocalDate;

public class BankClient {

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String accountId;

    public BankClient(String firstName, String lastName, LocalDate birthday, String accountId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.accountId = accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public BankClient setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BankClient setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public BankClient setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public BankClient setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }
}