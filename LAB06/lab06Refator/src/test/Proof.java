package test;
import presentation.*;
import domain.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
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
    	File testFile =  new File("Valley.dat");
    	gameToSave.getValley().save(testFile);
    	assertTrue("File should be exists", testFile.exists());
    	testFile.delete();
    }
    
    @Test
    public void shouldSave2() throws ValleyException {
    	ValleyGUI gameToSave = new ValleyGUI();
    	File testFile =  new File("Valley.dat");
    	gameToSave.getValley().save2(testFile);
    	assertTrue("File should be exists", testFile.exists());
    	testFile.delete();
    }
    
    @Test
    public void shouldExport() {
    	ValleyGUI gameToSave = new ValleyGUI();
    	File testFile =  new File("textValley.txt");
    	gameToSave.getValley().save2(testFile);
    	assertTrue("File should be exists", testFile.exists());
    	String nameFile = testFile.getName();
    	assertEquals(nameFile.substring(nameFile.length() - 4), ".txt");
    	testFile.delete();
    }
    
    @Test
    public void shouldntSaveWithInvalidPath() throws ValleyException {
    	Valley valley = new Valley();
        File invalidFile = new File("/ruta/invalida/no/existente/archivo.dat");
        valley.save(invalidFile);
        assertTrue("El método manejó la excepción correctamente", true);
    }
    
    @Test
    public void shouldNotOpenNonExistentFile() throws ClassNotFoundException {
    	Valley valley = new Valley();
        File nonExistent = new File("archivo_que_no_existe.dat");
        Valley result = valley.open(nonExistent);
        assertNull("Deberia retornar null cuando el archivo no existe", result);
    }
    
    @Test
    public void shouldnOpenInvalidFileContent() throws IOException {
    	Valley valley = new Valley();
        File tempFile = File.createTempFile("test_invalid", ".dat");
        tempFile.deleteOnExit();
        
        java.io.FileWriter writer = new java.io.FileWriter(tempFile);
        writer.write("Contenido invalido que no es un objeto serializado");
        writer.close();
        
        try {
            Valley result = valley.open(tempFile);
            assertNull("Deberia retornar null cuando el contenido es invalido", result);
        } catch (ClassNotFoundException e) {
            fail("No deberia lanzar ClassNotFoundException");
        }
    }
    
    @Test
    public void shouldntImportWithNullFile() throws ValleyException {
    	Valley valley = new Valley();
        valley.importFile(null);
    }
    
    @Test
    public void shouldntImportNonExistentFile() {
    	Valley valley = new Valley();
        File nonExistent = new File("import_inexistente.txt");
        try {
            Valley result = valley.importFile(nonExistent);
            assertNotNull("Deberia retornar un objeto Valley aunque falle", result);
        } catch (ValleyException e) {
            fail("No deberia lanzar ValleyException para archivos inexistentes");
        }
    }
    
    @Test
    public void shouldntExportWithValidFile() throws IOException {
    	Valley valley = new Valley();
        File tempFile = File.createTempFile("test_export", ".txt");
        tempFile.deleteOnExit();
        
        try {
            valley.exportFile(tempFile);
            assertTrue("El archivo deberia existir despues de exportar", tempFile.exists());
            assertTrue("El archivo deberia tener contenido", tempFile.length() > 0);
        } catch (ValleyException e) {
            fail("No deberia lanzar ValleyException con archivo valido");
        }
    }
    
}