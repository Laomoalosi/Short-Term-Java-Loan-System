/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuloan;

import java.io.*;
import java.util.*;

/**
 *
 * @author Laone Moalosi
 */
public class FileReader {
    
    File file = new File("LogFile.txt");
    private Scanner sc;
    
    
    public void openFileToRead() {
        
        try {
            sc = new Scanner(file);
        }
        
        catch(FileNotFoundException e) {
            System.out.println(e);
        }
    }
    
    
    /** Stores contents in a text file
     * 
     * @return 
     */
    public ArrayList<String> createArrayList() {
        
        ArrayList<String> log = new ArrayList();
        
        while(sc.hasNext()) {
            String word = sc.next();
            log.add(word);
        }
        return log;
    }
    
    
    //Method to close file when finished.
    public void closeFile() {
	
	sc.close();
    }
    
}
