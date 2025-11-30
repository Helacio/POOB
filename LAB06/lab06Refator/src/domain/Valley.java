package domain;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/*
 * Valley is part's backend code, here make aperations to move in 
 * ValleuGUI the units 
 */

public class Valley implements Serializable{
    public static final int SIZE=25;
    private Unit[][] places;
    
    public Valley() {
        places=new Unit[SIZE][SIZE];
        
        for (int r=0;r<SIZE;r++){
            
            for (int c=0;c<SIZE;c++){
                
                places[r][c]=null;
                
            }
        }
        
        someUnits();
        
    }
    
    public int  getSize(){
        return SIZE;
    }

    public Unit getUnit(int r,int c){
        return places[r][c];
    }

    public void setUnit(int r, int c, Unit e){
        places[r][c]=e;
    }

    public void someUnits(){   
    	//Cells
    	
        Wolf akela = new Wolf(this, 10, 10);
        //Wolf larka = new Wolf(this, 15, 15);
        setUnit(10, 10, akela);
        //setUnit(15, 15, larka);
        
        //Add some hays
        Hay hay1 = new Hay();
        setUnit(20,20, hay1);
        
        //Wolf marie = new Wolf(this, 12,7);
        //Wolf rob = new Wolf(this,13, 9);
        Hay hay2 = new Hay();
        Hay hay3 = new Hay();
        setUnit(21,21, hay2);
        setUnit(21,24, hay3);
        //setUnit(12, 7, marie);
        //setUnit(13,9, rob);
        
    }
    
    public int neighborsEquals(int r, int c){
        int num=0;
        if (inValley(r,c) && places[r][c]!=null){
            
            for(int dr=-1; dr<2;dr++){
                
                for (int dc=-1; dc<2;dc++){
                    
                    if ((dr!=0 || dc!=0) && inValley(r+dr,c+dc) && 
                        (places[r+dr][c+dc]!=null) &&  (places[r][c].getClass()==places[r+dr][c+dc].getClass()))
                    
                        num++;
                        
                }
            }
        }
        return num;
    }
   

    public boolean isEmpty(int r, int c){
        return (inValley(r,c) && places[r][c]==null);
    }    
        
    private boolean inValley(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
   
    public void ticTac(){
        for(int r = 0; r < SIZE; r++){
            for(int c = 0; c < SIZE; c++){
                Unit unit = places[r][c];
                if(unit != null){
                    unit.act();
                }
            }
        }
        
        for(int r = 0; r < SIZE; r++){
            for(int c = 0; c < SIZE; c++){
                Unit unit = places[r][c];
                if(unit != null){
                }
            }
        }
    }
    
    
    /**
     * Opens a specific file
     * This method is in construction
     * @param file the name or path of the file
     * @throws ValleyException if the method is called, tells you the open option is in construction
     
    public Valley open(File file) throws ValleyException {
    	throw new ValleyException(ValleyException.OPTION_OPEN + " Archivo: " + file.getName());
    }
    */
    /**
     * Saves a specific file
     * This method is in construction
     * @param file the name or path of the file
     * @throws ValleyException if the method is called, tells you the save option is in construction
     */
    public void save(File file) throws ValleyException{
    	if (file == null) throw new ValleyException(ValleyException.OPTION_SAVE + " Archivo: " + file.getName());
    	try {
    		FileOutputStream fos = new FileOutputStream(file);
    		BufferedOutputStream bos = new BufferedOutputStream(fos);
    		ObjectOutputStream oos = new ObjectOutputStream(bos);
    		
    		oos.writeObject(this);
    		oos.close();
    		
    	} catch (IOException e) {
    		JOptionPane.showMessageDialog(null, "Error al intentar guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    
    /** Un segundo intento de save
     * 
     */
    public void save2(File file) {

    	try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
    		out.writeObject(this);
    		
    	} catch (IOException e) {
    		JOptionPane.showMessageDialog(null, "Error al intentar guardar el archivo.",  "Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
    /**
     * Opens a specific file
     * This method is in construction
     * @param file the name or path of the file
     * @throws ValleyException if the method is called, tells you the open option is in construction
     */
    public Valley open00(File file) throws ValleyException {
    	throw new ValleyException(ValleyException.OPTION_OPEN + " Archivo: " + file.getName());
    }
    
    /**
     * Saves a specific file
     * This method is in construction
     * @param file the name or path of the file
     * @throws ValleyException if the method is called, tells you the save option is in construction
     */
    public void save00(File file) throws ValleyException{
    	throw new ValleyException(ValleyException.OPTION_SAVE + " Archivo: " + file.getName());
    }
    
    /**
     * import a specific file
     * This method is in construction
     * @param file the name or path of the file
     * @throws ValleyException if the method is called, tells you the import option is in construction
     */
    public Valley importFile00(File file) throws ValleyException{
    	throw new ValleyException(ValleyException.OPTION_IMPORT + " Archivo: " + file.getName());
    }
    
    /**
     * export a specific file
     * This method is in construction
     * @param file the name or path of the file
     * @throws ValleyException if the method is called, tells you the export option is in construction
     */
    public void exportFile00(File file) throws ValleyException{
    	if (file == null) throw new ValleyException(ValleyException.OPTION_EXPORT + " Archivo: " + file.getName());
    	
    	
    	
    	
    }
    
    /**
     * import a specific file
     * This method is in construction
     * @param file the name or path of the file
     * @throws ValleyException if the method is called, tells you the import option is in construction
     */
    public Valley importFile(File file) throws ValleyException{
    	if (file == null) throw new ValleyException(ValleyException.OPTION_IMPORT + " Archivo: " + file.getName());
		Valley valley = new Valley();
    	
    	try (FileReader fr = new FileReader(file);
    		BufferedReader br = new BufferedReader(fr)) {
    		String linea;
    		br.readLine();
    		br.readLine();
    		while ((linea = br.readLine()) != null) {
    			linea = linea.trim();
    			
    			if (linea.startsWith("Size board:")) break;
    			
    			String[] partes = linea.trim().split(":");
    			if(partes.length >= 3) {
    				String tipo = partes[0].trim();
                    int row = Integer.parseInt(partes[1].trim());
                    int col = Integer.parseInt(partes[2].trim());
                    
                    if (tipo.equals("Wolf")) {
                        Wolf toPut = new Wolf(valley, col, row);
                        places[row][col] = toPut;
                    } else if (tipo.equals("Hay")) {
                        Hay toPut = new Hay(valley, row, col);
                        places[row][col] = toPut;
                    }
    			}
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return valley;
    }
    
    /**
     * export a specific file
     * This method is in construction
     * @param file the name or path of the file
     * @throws IOException 
     * @throws ValleyException if the method is called, tells you the export option is in construction
     */
    public void exportFile(File file) throws IOException, ValleyException{
    	
    	if (file == null) {
    		throw new ValleyException(ValleyException.OPTION_EXPORT + "File: null path provided");
    	}
    	
    	try(FileWriter fileToWrite = new FileWriter(file);
    			BufferedWriter writer = new BufferedWriter(fileToWrite)){
    	
    		writer.write("=======ValleyGameDomain========\n");
    		writer.write("Objects in cells: coordenade i, coordenade j\n");
    		for(int i = 0; i < SIZE; i++) {
    			for(int j = 0; j < SIZE; j++) {
    				if (places[i][j] != null) {
    					writer.write(places[i][j].getClass().getSimpleName()  + ":" + "     " + i  + ":" + "     "+ j + "\n");
    				}
    			}
    		}
    		writer.write("Size board: " + SIZE + " X " + SIZE);
    	} catch (IOException e) {
    		e.printStackTrace();
    	} 
    }
    
    public Valley open(File file) throws ClassNotFoundException {
    	try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
    		return (Valley) in.readObject();
    	} catch (IOException e){
    		JOptionPane.showMessageDialog(null, "Error al intentar abrir el archivo", "error", JOptionPane.ERROR_MESSAGE);
    		return null;    	
    	}
    }
      
}
