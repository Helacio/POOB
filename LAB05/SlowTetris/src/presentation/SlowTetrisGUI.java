package presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;



public class SlowTetrisGUI extends JFrame {
	
	private JMenuBar menuBar;
	private JMenu menuFiles;
	private JMenuItem menuItemNew, menuItemSave, menuItemExit, menuItemOpen;
	
	
	public SlowTetrisGUI() {
		super("SlowTetris");
		prepareElements();
		prepareActions();
		prepareElementsMenu();
		prepareActionsMenu();
		
		this.setVisible(true);
	}
	
	public void prepareElements() {
		//Get the size of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int width = screenSize.width / 2;
		int height = screenSize.height / 2;
		
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
	}
	
	public void prepareActions() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent ev) {
					int result = JOptionPane.showConfirmDialog(null, "Do you want to exit?");
					if(result == JOptionPane.YES_OPTION) {
						dispose();
					}
				}
			});
	}
	
	public void prepareElementsMenu() {
		menuBar = new JMenuBar();
		menuFiles = new JMenu("Archivo");
		
		menuItemNew = new JMenuItem("Nuevo");
		menuItemOpen = new JMenuItem("Abrir");
		menuItemSave = new JMenuItem("Guardar");
		menuItemExit = new JMenuItem("Salir");
		
		//Add items to the menu
		menuFiles.add(menuItemNew);
		menuFiles.add(menuItemOpen);
		menuFiles.add(menuItemSave);
		menuFiles.add(menuItemExit);
		
		menuBar.add(menuFiles);
		
		this.setJMenuBar(menuBar);
	}
	
	public void prepareActionsMenu() {
		//Item salir
		menuItemExit.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ev) {
					int result = JOptionPane.showConfirmDialog(null, "Do you want to exit");
					if(result == JOptionPane.YES_OPTION) {
						dispose();
					}
				}
			});
	}

	public static void main(String[] args) {
		SlowTetrisGUI game = new SlowTetrisGUI();
	} 
	
}

