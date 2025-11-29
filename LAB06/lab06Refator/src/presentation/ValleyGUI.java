package presentation;
import domain.Valley;

import domain.ValleyException;
import domain.Animal;
import domain.Unit;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ValleyGUI extends JFrame{  
    public static final int SIDE=20;

    public final int SIZE;
    private JButton ticTacButton;
    private JPanel  controlPanel;
    private PhotoValley photo;
    private Valley theValley;
    private JMenuBar menuBar;
    private JMenu menuFiles;
    private JMenuItem menuItemNew, menuItemSave, menuItemOpen, 
    menuItemExit, menuItemImport, menuItemExport;
   
    
    public ValleyGUI() {
        theValley=new Valley();
        SIZE=theValley.getSize();
        prepareElements();
        prepareElementsMenu();
        prepareActions();
        prepareActionsMenu();
    }
    
    private void prepareElements() {
        setTitle("Schelling Valley");
        photo=new PhotoValley(this);
        ticTacButton=new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo,BorderLayout.NORTH);
        add(ticTacButton,BorderLayout.SOUTH);
        setSize(new Dimension(SIDE*SIZE+15,SIDE*SIZE+72)); 
        setResizable(false);
        photo.repaint();
        setLocationRelativeTo(null);
    }
    
    public void prepareElementsMenu() {
    	menuBar = new JMenuBar();
		menuFiles = new JMenu("Archivo");
		
		menuItemNew = new JMenuItem("Nuevo");
		menuItemOpen = new JMenuItem("Abrir");
		menuItemSave = new JMenuItem("Guardar");
		menuItemExit = new JMenuItem("Salir");
		menuItemImport = new JMenuItem("Importar");
		menuItemExport = new JMenuItem("Exportar");
		
		menuFiles.add(menuItemNew);
		menuFiles.addSeparator();
		menuFiles.add(menuItemOpen);
		menuFiles.add(menuItemSave);
		menuFiles.addSeparator();
		menuFiles.add(menuItemImport);
		menuFiles.add(menuItemExport);
		menuFiles.addSeparator();
		menuFiles.add(menuItemExit);
		
		menuBar.add(menuFiles);
		setJMenuBar(menuBar);
    }
    
    
    public Valley getValley() {
    	return theValley;
    }
    
    /**
     * Prepare the necessary components to start the menu
     */
    public void prepareActionsMenu(){
    	menuItemNew.addActionListener(
    			new ActionListener() {
					public void actionPerformed(ActionEvent e) {
    				JOptionPane.showMessageDialog(
    						ValleyGUI.this, "Se ha establecido el juego.", "Nuevo juego", JOptionPane.INFORMATION_MESSAGE);
					}	
    			});
    	/** Primera version
    	menuItemOpen.addActionListener(
    			new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					JFileChooser fileChooser = new JFileChooser();
    					int result = fileChooser.showOpenDialog(ValleyGUI.this);
    					if(result == JFileChooser.APPROVE_OPTION) {
    						File selectedFile = fileChooser.getSelectedFile();
    						try {
    							theValley.open(selectedFile);
    						} catch(ValleyException ve) {
    							JOptionPane.showMessageDialog(ValleyGUI.this, ve.getMessage());
    						}
    					}
    				}
    			}
    		);
    	
    	menuItemSave.addActionListener(
    			new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					JFileChooser fileChooser = new JFileChooser();
    					int result = fileChooser.showSaveDialog(ValleyGUI.this);
    					if(result == JFileChooser.APPROVE_OPTION) {
    						File selectedFile = fileChooser.getSelectedFile();
    						try {
    							theValley.save(selectedFile);
    						} catch(ValleyException ve) {
    							 JOptionPane.showMessageDialog(ValleyGUI.this, ve.getMessage());
    						}
    					}
    				}
    			}
    		);
    	*/
    	menuItemImport.addActionListener(
    			new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					JFileChooser fileChooser = new JFileChooser();
    					int result = fileChooser.showOpenDialog(ValleyGUI.this);
    					if(result == JFileChooser.APPROVE_OPTION) {
    						File selectedFile = fileChooser.getSelectedFile();
    						try {
    							theValley.importFile(selectedFile);
    						} catch(ValleyException ve) {
    							JOptionPane.showMessageDialog(ValleyGUI.this, ve.getMessage());
    						}
    					}
    				}
    			}
    		);
    	menuItemExport.addActionListener(
    			new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					JFileChooser fileChooser = new JFileChooser();
    					int result = fileChooser.showSaveDialog(ValleyGUI.this);
    					if(result == JFileChooser.APPROVE_OPTION) {
    						File selectedFile = fileChooser.getSelectedFile();
    						try {
    							theValley.exportFile(selectedFile);
    						}catch(IOException e1) {
    							JOptionPane.showMessageDialog(ValleyGUI.this, e1.getMessage());
    						}
    					}
    				}
    			}	
    		);
    	menuItemExit.addActionListener(
    			new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					ValleyGUI.this.optionExit();
    				}
    			}
    		);
    	menuItemNew.addActionListener(
    			new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					ValleyGUI.this.optionNew();
    				}
    			}
    		);
    	menuItemSave.addActionListener(
    			new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					JFileChooser fileChooser = new JFileChooser();
    					fileChooser.setFileFilter(new FileNameExtensionFilter("DAT files", "dat"));
    					int result = fileChooser.showSaveDialog(ValleyGUI.this);
    					if (result == JFileChooser.APPROVE_OPTION) {
    						File selectedFile = fileChooser.getSelectedFile();
    						if(!selectedFile.getName().endsWith(".dat")) {
    							selectedFile = new File(selectedFile.getAbsolutePath() + ".dat");
    						}
    						try {
								theValley.save2(selectedFile);
							} catch (ValleyException e1) {
								JOptionPane.showMessageDialog(ValleyGUI.this, e1.getMessage());
							}
    					}
    				}
    			});
    	menuItemOpen.addActionListener(
    			new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					JFileChooser fileChooser = new JFileChooser();
    					int result = fileChooser.showOpenDialog(ValleyGUI.this);
    					if(result == JFileChooser.APPROVE_OPTION) {
    						File selectedFile = fileChooser.getSelectedFile();
    						Valley loadedValley = null;
							try {
								loadedValley = theValley.open(selectedFile);
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
    						if(loadedValley != null) {
    							theValley = loadedValley;
    							photo.repaint();
    						}
    					}
    				}
    				
    			});
    }
    
    private void optionExit() {
    	System.exit(0);
    }
    
    private void optionNew() {
    	theValley =  null;
    	theValley = new Valley();
    }
    
    private void prepareActions(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        
        ticTacButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    ticTacButtonAction();
                }
            });
    }

    private void ticTacButtonAction() {
        theValley.ticTac();
        photo.repaint();
    }

    public Valley gettheValley(){
        return theValley;
    }
    
    public static void main(String[] args) {
        ValleyGUI cg=new ValleyGUI();
        cg.setVisible(true);
    }  
}

class PhotoValley extends JPanel{
    private ValleyGUI gui;

    public PhotoValley(ValleyGUI gui) {
        this.gui=gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE*gui.SIZE+10, gui.SIDE*gui.SIZE+10));         
    }


    public void paintComponent(Graphics g){
        Valley theValley=gui.gettheValley();
        super.paintComponent(g);
         
        for (int c=0;c<=theValley.getSize();c++){
            g.drawLine(c*gui.SIDE,0,c*gui.SIDE,theValley.getSize()*gui.SIDE);
        }
        for (int f=0;f<=theValley.getSize();f++){
            g.drawLine(0,f*gui.SIDE,theValley.getSize()*gui.SIDE,f*gui.SIDE);
        }       
        for (int f=0;f<theValley.getSize();f++){
            for(int c=0;c<theValley.getSize();c++){
                if (theValley.getUnit(f,c)!=null){
                    g.setColor(theValley.getUnit(f,c).getColor());
                    if (theValley.getUnit(f,c).shape()==Unit.SQUARE){                  
                        g.fillRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2);   
                    }else {
                        g.fillOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                    }
                    if (theValley.getUnit(f,c).isAnimal()){
                        g.setColor(Color.red);
                        if (((Animal)theValley.getUnit(f,c)).getEnergy()>=50){
                            g.drawString("u",gui.SIDE*c+6,gui.SIDE*f+15);
                        } else {
                            g.drawString("~",gui.SIDE*c+6,gui.SIDE*f+17);
                        }
                    }    
                }
            }
        }
    }
    
}