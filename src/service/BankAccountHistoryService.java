package service;

import domain.AccountHistory;
import domain.BankClient;

public class BankAccountHistoryService {
    public AccountHistory getAccountHistory(BankClient bankClient) {
        return new AccountHistory();
    }
}
