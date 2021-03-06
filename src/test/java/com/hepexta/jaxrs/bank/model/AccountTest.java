package com.hepexta.jaxrs.bank.model;

import com.hepexta.jaxrs.bank.ex.DepositException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountTest {

    private Client client;
    private Account account;
    private BigDecimal balance;

    @Before
    public void setUp() throws Exception {
        client = new Client("1", "John Smith");
        balance = new BigDecimal(1000);
        account = new Account("ACC001", client, balance);
    }

    @Test
    public void test_deposit() {
        executeDeposit(new BigDecimal(500));
        executeDeposit(new BigDecimal(100));
        executeDeposit(new BigDecimal(900));
        Assert.assertEquals(new BigDecimal(2500), account.getBalance());
    }

    @Test(expected = DepositException.class)
    public void test_deposit_not_enough_money() {
        account.deposit(BigDecimal.ZERO);
    }

    @Test
    public void test_withdrawal() {
        executeWithdrawal(new BigDecimal(500));
        executeWithdrawal(new BigDecimal(100));
        executeWithdrawal(new BigDecimal(400));
        Assert.assertEquals(BigDecimal.ZERO, account.getBalance());
    }

    @Test(expected = DepositException.class)
    public void test_withdraw_not_enough_money() {
        account.withdraw(new BigDecimal(2000));
    }

    private void executeDeposit(BigDecimal amount) {
        Runnable runnable = () -> account.deposit(amount);
        runnable.run();
    }

    private void executeWithdrawal(BigDecimal amount) {
        Runnable runnable = () -> account.withdraw(amount);
        runnable.run();
    }

}