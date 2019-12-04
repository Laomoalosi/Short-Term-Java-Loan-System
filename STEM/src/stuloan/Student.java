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
public class Student extends User {

    private LoanAccount loanAccount;
    private boolean flagged;
    private java.sql.Date joinDate;

    
    
    /** Constructor for Registration (No argument):
     * 
     */
    public Student() {
        super();
        this.loanAccount = new LoanAccount();
        this.loanAccount.setAccountHolder(this);
        this.loanAccount.setLoanBalance(0);
        this.loanAccount.setLoanDate(null);
    }

    /**
     * 
     * @param flagged
     * @param userId
     * @param firstName
     * @param surname
     * @param physicalAddress
     * @param username
     * @param password
     * @param userType 
     */
    public Student(boolean flagged, int userId, String firstName, String surname, String physicalAddress, String username, String password, String userType) {
        super(userId, firstName, surname, physicalAddress, username, password, userType);
        this.flagged = flagged;
    }


    public LoanAccount getLoanAccount() {
        return loanAccount;
    }

    public void setLoanAccount(LoanAccount loanAccount) {
        this.loanAccount = loanAccount;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }
    
    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    
    
    
}
