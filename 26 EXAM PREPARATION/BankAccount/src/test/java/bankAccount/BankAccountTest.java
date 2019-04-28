package bankAccount;


import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;


public class BankAccountTest {
    private static final String DEFAULT_NAME = "Qnko";
    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal("1000");
    private static final BigDecimal DEPOSIT = new BigDecimal("1000");
    private static final BigDecimal BALANCE = new BigDecimal("2000");

    private static final String NAME_LESS_THAN_3_SYMBOLS = "Az";
    private static final String NAME_LONGER_THAN_25_SYMBOLS = "skdaslohfodifuhgwoefnowenfowenfoweofnwoef";
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
    public void setName_shouldThrowWithShortName() {
        this.bankAccount = new BankAccount(NAME_LESS_THAN_3_SYMBOLS, DEFAULT_BALANCE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_shouldThrowWithLongName() {
        this.bankAccount = new BankAccount(NAME_LONGER_THAN_25_SYMBOLS, DEFAULT_BALANCE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setName_shouldThrowWithInvalidBalance() {
        this.bankAccount = new BankAccount(DEFAULT_NAME, INVALID_BALANCE);
    }

    @Test
    public void deposit_shouldWorkCorrectWithValidAmount() {
        this.bankAccount.deposit(DEPOSIT);
        assertEquals(BALANCE, this.bankAccount.getBalance());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void deposit_shouldThrowWithZeroValue() {
        this.bankAccount.deposit(BigDecimal.ZERO);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void deposit_shouldThrowWithNegativeValue() {
        this.bankAccount.deposit(INVALID_BALANCE);
    }

    @Test
    public void withdraw_shouldReturnCorrectAmount() {
        BigDecimal withdraw = this.bankAccount.withdraw(DEFAULT_BALANCE);
        assertEquals(DEFAULT_BALANCE, withdraw);
        assertEquals(BigDecimal.ZERO, this.bankAccount.getBalance());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void withdraw_shouldThrowOnNegativeAmount() {
        this.bankAccount.withdraw(INVALID_BALANCE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void withdraw_shouldThrowOnAmountBiggerThanBalance() {
        this.bankAccount.withdraw(BALANCE);
    }
}