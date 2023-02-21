package model;

import java.util.logging.Logger;

public class Cuenta {

    private int accountNumber;
    private int balance;
    private String accountType;
    private String accountStatus;

    public Cuenta() {
    }

    public Cuenta(int accountNumber, int balance, String accountType, String accountStatus) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
  
    
}
