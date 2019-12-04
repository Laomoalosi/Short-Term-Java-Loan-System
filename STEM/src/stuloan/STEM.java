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
public class STEM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JDBC jdbc = new JDBC();
        
        double amount = jdbc.getLoanAmount(9); 
        
        System.out.println(amount);
        
        
    }
    
}
