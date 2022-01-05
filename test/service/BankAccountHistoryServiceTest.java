package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class BankAccountHistoryServiceTest {

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void bank_client_should_see_history_of_operations_from_account_in_order_to_check_its_operations() {
        // GIVEN
        final String bankClientFirstName = "david";
        final String bankClientLastName = "ma";
        final LocalDate bankClientBirthday = LocalDate.of(1994, 9, 7);
        final String bankClientAccountId = "FR123456";

        // WHEN

        // THEN
    }
}