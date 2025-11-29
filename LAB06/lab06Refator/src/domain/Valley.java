package domain;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/*No olviden adicionar la documentacion*/
public class Valley implements Serializable{
    static private int SIZE=25;
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
        Wolf akela = new Wolf(this, 10, 10);
        Wolf larka = new Wolf(this, 15, 15);
        setUnit(10, 10, akela);
        setUnit(15, 15, larka);
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
    		if (file != null) {
    			FileWriter saving = new FileWriter(file);
    			saving.write("SIZE: " + SIZE + "\n");
    			
    			for (int i = 0; i < places.length; i++) {
    				for (int j = 0; j < places[i].length; j++) {
    					saving.write("Unit " + "[" + i+ "]" +"[" + j+ "]" + ":"+ places[i][j].getClass().getSimpleName() + "\n");
    				}
    			}
    			saving.close();
    		}
    	} catch (IOException e) {
    		JOptionPane.showMessageDialog(null, "Error al intentar guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    
    /** Un segundo intento de save
     * 
     */
    public void save2(File file) throws ValleyException{
    	try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
    		out.writeObject(this);
    	} catch(IOException e) {
    		JOptionPane.showMessageDialog(null, "Error al intentar guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
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
    public Valley importFile(File file) throws ValleyException{
    	throw new ValleyException(ValleyException.OPTION_IMPORT + " Archivo: " + file.getName());
    }
    
    /**
     * export a specific file
     * This method is in construction
     * @param file the name or path of the file
     * @throws ValleyException if the method is called, tells you the export option is in construction
     */
    public void exportFile(File file) throws ValleyException{
    	throw new ValleyException(ValleyException.OPTION_EXPORT + " Archivo: " + file.getName());
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
