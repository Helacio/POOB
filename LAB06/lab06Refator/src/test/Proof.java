package test;
import presentation.*;
import domain.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.Serializable;


/**
 * The test class Test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Proof {
    
    
    @Test 
    public void shouldSave() throws ValleyException {
    	ValleyGUI gameToSave = new ValleyGUI();
    	File testFile =  new File("textValley.txt");
    	gameToSave.getValley().save(testFile);
    	assertTrue("File should be exists", testFile.exists());
    	testFile.delete();
    }
    
}