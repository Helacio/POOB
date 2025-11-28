package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.Serializable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class Test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Proof {
    
    
    @Test 
    public void shouldSave() {
    	ValleyGUI gameToSave = new ValleyGUI();
    	File testFile =  new File("textValley.txt");
    	gameToSave.theValley.save(testFile);
    	assertTrue("File should be exists", testFile.exists());
    	testFile.delete();
    }
    
}
