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
public class Admin extends User {
    

    public Admin() {}

    /**
     * 
     * @param userId
     * @param firstName
     * @param surname
     * @param physicalAddress
     * @param username
     * @param password
     * @param userType 
     */
    public Admin(int userId, String firstName, String surname, String physicalAddress, String username, String password, String userType) {
        super(userId, firstName, surname, physicalAddress, username, password, userType);
    }



    
    
}
