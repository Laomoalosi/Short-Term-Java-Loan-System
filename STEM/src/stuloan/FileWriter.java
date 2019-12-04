/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuloan;

import java.util.Formatter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Laone Moalosi
 */
public class FileWriter {
    
    public FileWriter() {}
    
    private Formatter x;
    
    public void openFile() {
        
        try {
            x = new Formatter("LogFile.txt");
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    
    
    public void writeToFile(ArrayList<String> previous, String log) {
        
        for(String str: previous) {
            x.format(("%s %s"), str, "\n");
        }
        
        x.format(("%s"), log);

    }
    
    public void closeFile() {
	
		x.close();
    }
    
}
