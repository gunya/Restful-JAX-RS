package com.hepexta.jaxrs.bank.ex;

public class DepositException extends RuntimeException {

    public static final String NOT_ENOUGH_MONEY_ERROR_DEPOSIT = "%s doesn't have enough money for deposit";
    public static final String NOT_ENOUGH_MONEY_ERROR_WITHDRAWAL = "%s doesn't have enough money for withdraw";

    public DepositException(String msg) {
        super(msg);
    }
}
