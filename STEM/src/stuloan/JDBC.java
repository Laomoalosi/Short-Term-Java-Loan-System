/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuloan;

/**
 *
 * @author Laone Moalosi
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*; 
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JOptionPane;


	public class JDBC {

    //Static constants:
    private static final String DB_URL = "jdbc:mysql://localhost/stuloan";

    //credentials:
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root123";

    private Connection conn;
    private PreparedStatement pstmt;
    private Statement stmt;
    private String sql;
    private ResultSet rs;



    public JDBC() {

        conn = null;
        pstmt = null;
        stmt = null;
        sql = "";
        rs = null;

        try{

            //Open connection
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            System.out.println("Connected.");

        }catch(SQLException e) {System.out.println(e);}

    }
    
    public int addStudent(Student st) {
        
        int id = 0;
        
        try{
            //Create sql statement to query db:
            sql = "INSERT into user "
                    + "(fname, sname, phy_address, username, password, join_date, user_type) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            //Create preparedstatement object using connecion:
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            //Assign values to question marks:
            pstmt.setString(1, st.getFirstName());
            pstmt.setString(2, st.getSurname());
            pstmt.setString(3, st.getPhysicalAddress());
            pstmt.setString(4, st.getUsername());
            pstmt.setString(5, st.getPassword());
            pstmt.setDate(6, st.getJoinDate());
            pstmt.setString(7, "student");
            
            //Execute the update (No resullt set):
            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
                    
            if(rs.next()) {
                id = rs.getInt(1);
            }
            
            return id;

        }catch(SQLException e) {System.out.println(e); return id;}
        
    }
    
    public int addAccount(LoanAccount acc) {
        
        int id = 0;
        
        try{
            //Create sql statement to query db:
            sql = "INSERT into loan_account "
                    + "(student_id, loan_balance) "
                    + "VALUES (?, ?)";
            
            //Create preparedstatement object using connecion:
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            //Assign values to question marks:
            pstmt.setInt(1, acc.getAccountNumber());
            pstmt.setDouble(2, acc.getLoanBalance());
            
            
            //Execute the update (No resullt set):
            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
                    
            if(rs.next()) {
                id = rs.getInt(1);
            }
            
            return id;

        }catch(SQLException e) {System.out.println(e); return id;}
        
    }

    public int validateUser(String username, String password) {

        try{
            // SQL Statement
            sql = "SELECT * FROM user where username = ? AND password = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // Execute the query
            rs = pstmt.executeQuery();

            // Extract result set (Login success):
            if(rs.next()) {return rs.getInt("user_id");}

            // (Login failed):
            else {return 0;}


        }catch(SQLException e) {System.out.println(e); return 0;}

    }
    
    public LoanAccount getAccount(Student stu) {
        
        LoanAccount acc = null;
        
        try{
            // SQL Statement
            sql = "SELECT * FROM loan_account where student_id = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setInt(1, stu.getUserId());

            // Execute the query
            rs = pstmt.executeQuery();

            while(rs.next()) {
                
                acc = new LoanAccount();

                int accNo = rs.getInt("acc_no");
                int studentId = rs.getInt("student_id");
                double loanBalance = rs.getDouble("loan_balance");
                java.sql.Date date = rs.getDate("loan_date");
                
                acc.setAccountNumber(accNo);
                acc.setAccountHolder(stu);
                acc.setLoanBalance(loanBalance);
                acc.setLoanDate(date);
                
            }
				
			
	return acc;


        }catch(SQLException e) {System.out.println(e); return null;}
        
    }
    
    public User getUser(int id) {
        
        User user = null;
        
        try{
            // SQL Statement
            sql = "SELECT * FROM user where user_id = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setInt(1, id);

            // Execute the query
            rs = pstmt.executeQuery();

            while(rs.next()) {
                
                String userType = rs.getString("user_type");
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("fname");
                String surname = rs.getString("sname");
                String phyAddress = rs.getString("phy_address");
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean flagged = rs.getBoolean("flagged");
                
                if(userType.equals("student")) {
                    user = new Student();
                    ((Student)user).setFlagged(flagged);
                    
                }else if(userType.equals("admin")) {
                    user = new Admin();
                }
                
                user.setUserType(userType);
                user.setUserId(userId);
                user.setFirstName(firstName);
                user.setSurname(surname);
                user.setPhysicalAddress(phyAddress);
                user.setUsername(username);
                user.setPassword(password);
                
                
            }
				
			
	return user;


        }catch(SQLException e) {System.out.println(e); return null;}
        
    }
    
    /** Get name of student
     * 
     * @param id
     * @return 
     */
    public String getStudentName(int id) {
        
        String name = "";
        
        try{
            // SQL Statement
            sql = "SELECT fname, sname FROM user where user_id = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setInt(1, id);

            // Execute the query
            rs = pstmt.executeQuery();

            while(rs.next()) {
        
                String firstName = rs.getString("fname");
                String surname = rs.getString("sname");
                
                name = firstName + " " + surname;
                
            }
				
			
	return name;


        }catch(SQLException e) {System.out.println(e); return null;}
        
    }
    
    public ResultSet returnLoanApplication(int id) {
            
        try{
            // SQL Statement
            sql = "SELECT * FROM loan_application where applicant_id = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setInt(1, id);

            // Execute the query
            rs = pstmt.executeQuery();

            return rs;


        }catch(SQLException e) {System.out.println(e); return null;}
        
    }

    public String getUserType(String username) {
		
		String userType = "";
		
        try{
            // SQL Statement
            sql = "SELECT user_type FROM user where username = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setString(1, username);

            // Execute the query
            rs = pstmt.executeQuery();

            if(rs.next()) {
				userType = rs.getString("user_type");
			}
			
			return userType;


        }catch(SQLException e) {System.out.println(e); return "";}
		
    }
    
    public int getStudentNumber(int applicationId) {
		
	int stuNum = 0;
		
        try{
            // SQL Statement
            sql = "SELECT applicant_id FROM loan_application where application_id = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setInt(1, applicationId);

            // Execute the query
            rs = pstmt.executeQuery();

            if(rs.next()) {
				stuNum = rs.getInt("applicant_id");
			}
			
			return stuNum;


        }catch(SQLException e) {System.out.println(e); return 0;}
		
    }
    
    public double getLoanAmount(int applicationId) {
		
	double amount = 0;
		
        try{
            // SQL Statement
            sql = "SELECT loan_amount FROM loan_application where application_id = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setInt(1, applicationId);

            // Execute the query
            rs = pstmt.executeQuery();

            if(rs.next()) {
				amount = rs.getDouble("loan_amount");
			}
			
			return amount;


        }catch(SQLException e) {System.out.println(e); return 0;}
		
    }
	
	public void createLoanApplication(int studentNumber, java.sql.Date date, double amount) { //Implemented by Student
		
		try{
            // SQL Statement
            sql = "INSERT INTO loan_application (applicant_id, application_date,loan_amount) VALUES (?, ?, ?)";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setInt(1, studentNumber);
            pstmt.setDate(2, date);
            pstmt.setDouble(3, amount);

            // Execute the query
            pstmt.executeUpdate();

        }catch(SQLException e) {System.out.println(e);}
		
	}
	
	public void approveLoanApplication(int applicationId, java.sql.Date date) { // By Admin
		
		try{
            // SQL Statement
            sql = "UPDATE loan_application SET approval_date = ?, approved = ? WHERE application_id = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setDate(1, date);
            pstmt.setBoolean(2, true);
            pstmt.setInt(3, applicationId);

            // Execute the query
            pstmt.executeUpdate();

        }catch(SQLException e) {System.out.println(e);}
		
	}
	
	public void applyMonthlyInterest(double balance,double interestRate, int studentNumber) { // By System
	
		interestRate = LoanAccount.INTEREST_RATE;
                
                double newBalance = balance * (1 + interestRate);
		
		try{
            // SQL Statement
            sql = "UPDATE loan_account set loan_balance = ? WHERE holder_id = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, studentNumber);

            // Execute the query
            pstmt.executeUpdate();

        }catch(SQLException e) {System.out.println(e);}
		
	}
        
        public void updateAccountBalance(int studentNumber, double amount) { 
            	
	try{
                    
            // SQL Statement
            sql = "UPDATE loan_account set loan_balance = (loan_balance + ?) WHERE student_id = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, studentNumber);

            // Execute the query
            pstmt.executeUpdate();

        }catch(SQLException e) {System.out.println(e);}
		
	}
        
        public void updateLoanDate(int studentNumber, java.sql.Date date) { 
            	
	try{
                    
            // SQL Statement
            sql = "UPDATE loan_account set loan_date = ? WHERE student_id = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setDate(1, date);
            pstmt.setInt(2, studentNumber);

            // Execute the query
            pstmt.executeUpdate();

        }catch(SQLException e) {System.out.println(e);}
		
	}
        
        public boolean checkFlagged(int userId) {
            
            try{
            // SQL Statement
            sql = "SELECT * FROM user where user_id = ? AND flagged = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setInt(1, userId);
            pstmt.setBoolean(2, true);

            // Execute the query
            rs = pstmt.executeQuery();

            // Student is flagged
            if(rs.next()) {return true;}

            // Student isn't flagged
            else {return false;}


            }catch(SQLException e) {System.out.println(e); return false;}
        }
        
        public boolean checkBalanceCleared(int userId) {
            
            try{
            // SQL Statement
            sql = "SELECT * FROM loan_aacount where student_id = ? AND balance = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setInt(1, userId);
            pstmt.setInt(2, 0);

            // Execute the query
            rs = pstmt.executeQuery();

            // Balance is Zero
            if(rs.next()) {return true;}

            // Balance is yet to be paid (Not equal zero)
            else {return false;}


            }catch(SQLException e) {System.out.println(e); return false;}
        }
        
        /**
         * 
         * @param today - today's date
         * @return users who joined on current day
         */
        public ResultSet returnNewUsers(java.sql.Date today) {
        
        try{
            // SQL Statement
            sql = "SELECT user_id AS Student_Number, fname AS Name, sname AS Surname, phy_address AS Address "
                    + "FROM user where join_date = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setDate(1, today);

            // Execute the query
            rs = pstmt.executeQuery();
            
            return rs;


        }catch(SQLException e) {System.out.println(e); return null;}
        
    }
        
    public ResultSet returnApplications() {
        
        try{
            // SQL Statement
            sql = "SELECT `user`.`user_id` AS Stu_Num, `user`.`fname` AS Name, `user`.`sname` AS Surname, `loan_application`.`application_id` AS Application_Id, `loan_application`.`application_date` AS Date, `loan_application`.`loan_amount` AS Amount, `loan_application`.`approval_date` AS Approval_Date, `loan_application`.`approved` AS Approved "
                    + "FROM `user` INNER JOIN `loan_application` "
                    + "ON `user`.`user_id` = `loan_application`.`applicant_id`";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Execute the query
            rs = pstmt.executeQuery();
            
            return rs;


        }catch(SQLException e) {System.out.println(e); return null;}
        
    }    
        
    public double getLoanBalance(int studentNumber) {
        
        double balance = 0;
        
        try{
            // SQL Statement
            sql = "SELECT loan_balance FROM loan_account where student_id = ?";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setInt(1, studentNumber);

            // Execute the query
            rs = pstmt.executeQuery();
            
                while(rs.next()) {
            
                balance = rs.getDouble("loan_balance");
                
            }

            return balance;


        }catch(SQLException e) {System.out.println(e); return 0;}
        
    }    

    public void makeLoanPayment(int studentNumber,double newBalance) {
    
        try {
        
            sql = "UPDATE loan_account SET loan_balance = ? where student_id = ?";
            
            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Bind values
            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, studentNumber);

            // Execute the query
            pstmt.executeUpdate();
        
        }catch(SQLException e) {
            System.out.println(e);
        }
    
    }    
    
    public ResultSet generateLoanHistory() {
        
        try{
            // SQL Statement
            sql = "SELECT `user`.`user_id` AS Student_Number, `user`.`fname` AS Name, `user`.`sname` AS Surname, SUM(`loan_application`.`loan_amount`) AS Total_Loaned_Pula "
                    + "FROM `user` INNER JOIN `loan_application` "
                    + "ON `user`.`user_id` = `loan_application`.`applicant_id` "
                    + "WHERE `loan_application`.`approved` = 1 GROUP BY `user`.`user_id`";

            // Prepared statement object using connection:
            pstmt = conn.prepareStatement(sql);

            // Execute the query
            rs = pstmt.executeQuery();
            
            return rs;


        }catch(SQLException e) {System.out.println(e); return null;}
        
    }
    
    public void closeResources() {
    
        try{
            //Close Statement object if it is still open:
            if(stmt != null)
                stmt.close();
        
        }catch(SQLException se1) {System.out.println(se1);}
        
        try{
            //Close Statement object if it is still open:
            if(pstmt != null)
                stmt.close();
        
        }catch(SQLException se2) {System.out.println(se2);}
        
        try{
            //Close Connection object if it is still open:
            if(conn != null)
                conn.close();
        
        }catch(SQLException se3) {System.out.println(se3);}
        
        try{
            //Close Statement object if it is still open:
            if(rs != null)
                rs.close();
        
        }catch(SQLException se4) {System.out.println(se4);}
    }
    
    
}


