/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuloan;

import java.sql.Date;

/**
 *
 * @author Laone Moalosi
 */
public class LoanAccount {
    
    protected static final double INTEREST_RATE = 0.1;
    private int accountNumber;
    private Student accountHolder;
    private double loanBalance;
    private Date loanDate;

    public LoanAccount() {
        this.loanBalance = 0;
        this.loanDate = null;
    }
    
    public LoanAccount(int accountNumber, Student accountHolder, double loanBalance, Date loanDate) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.loanBalance = loanBalance;
        this.loanDate = loanDate; // Same date as application approval date
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Student getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Student accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(double loanBalance) {
        this.loanBalance = loanBalance;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }
    
    
    
    
    
    
    
}
