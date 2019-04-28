package bankAccount;


import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;


public class BankAccountTest {
    private static final String DEFAULT_NAME = "Qnko";
    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal("1000");

    private static final String INVALID_NAME = "Az";
    private static final BigDecimal INVALID_BALANCE = new BigDecimal("-1000");

    private BankAccount bankAccount;

    @Before
    public void setUp() {
        this.bankAccount = new BankAccount(DEFAULT_NAME, DEFAULT_BALANCE);
    }

    @Test
    public void setUp_shouldBeCorrect() {
        assertEquals(DEFAULT_NAME, this.bankAccount.getName());
        assertEquals(DEFAULT_BALANCE, this.bankAccount.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_shouldThrowWithInvalidName() {
        this.bankAccount = new BankAccount(INVALID_NAME, DEFAULT_BALANCE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_shouldThrowWithInvalidBalance() {
        this.bankAccount = new BankAccount(DEFAULT_NAME, INVALID_BALANCE);
    }
}