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
public class LoanApplication {
    
    int applicationId; 
    int applicantId; // Shows which student.
    boolean approved;
    Date applicationDate;
    Date approvalDate;  
    double loanAmount; // Transfered to what is reflected in balance.

    public LoanApplication() {}
    
    public LoanApplication(int applicationId, int applicantId, boolean approved, Date applicationDate, Date approvalDate, double loanAmount) {
        this.applicationId = applicationId;
        this.applicantId = applicantId;
        this.approved = approved;
        this.applicationDate = applicationDate;
        this.approvalDate = approvalDate;
        this.loanAmount = loanAmount;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
    
    
    
    
}
