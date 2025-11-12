package presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class SlowTetrisGUI extends JFrame {
	
	private JMenuBar menuBar;
	private JMenu menuFiles;
	private JMenuItem menuItemNew, menuItemSave, menuItemExit, menuItemOpen;
	private JTextField hide, width, score, time;
	private JPanel infoPanel, optionsPanel, configPanel, controlPanel, arrowsPanel, boardPanel;
	private JTextField configHeight, configWidth;
	
	
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
		
		//Setting the layout like BorderLayOut
		setLayout(new BorderLayout());

		//Centering the JFrame
		int width = (int) ((int) screenSize.width / 2.3) ;
		int height = (int) ((int) screenSize.height / 1.2);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		
		//Performance North zone
		configPanel = new JPanel(new FlowLayout());
		configPanel.add(new JLabel("Height:"));
		configHeight = new JTextField(3);
		configPanel.add(configHeight);
		
		configPanel.add(new JLabel("Width"));
		configWidth = new JTextField(3);
		configPanel.add(configWidth);
		
		JButton btnConfirm = new JButton("Confirm");
		//Generate the board
		btnConfirm.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						prepareElementsBoard();
					}
				});
		configPanel.add(btnConfirm);
		
		
		add(configPanel, BorderLayout.NORTH);
		
		//Performance West zone
		optionsPanel = new JPanel();
		optionsPanel.setLayout(new GridLayout(4,1, 10, 10));
		optionsPanel.add(new JButton("Modify"));
		optionsPanel.add(new JButton("Save"));
		optionsPanel.add(new JButton("Restart"));
		optionsPanel.add(new JButton("Open"));
		
		add(optionsPanel, BorderLayout.WEST);
		
		
		//Preparing buttons game
		arrowsPanel = new JPanel(new BorderLayout());
		controlPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(4,4,4,4);
		
		JButton btnWest = new JButton("w");
		c.gridx = 0;
		c.gridy = 1;
		controlPanel.add(btnWest, c);
		
		JButton btnRotRight = new JButton("RR");
		c.gridx = 2;
		c.gridy = 1;
		controlPanel.add(btnRotRight, c);
		
		JButton btnRotLeft = new JButton("RL");
		c.gridx = 1;
		c.gridy = 1;
		controlPanel.add(btnRotLeft, c);
		
		JButton btnRight = new JButton("R");
		c.gridx = 3;
		c.gridy = 1;
		controlPanel.add(btnRight,c);
		
		JButton btnDown = new JButton("D");
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		controlPanel.add(btnDown, c);
		
		arrowsPanel.add(controlPanel, BorderLayout.CENTER);
		add(arrowsPanel, BorderLayout.SOUTH);
		
		
		//Preparing East zone
		
		//Info panel
		JPanel eastPanel = new JPanel(new BorderLayout());
		infoPanel = new JPanel(new GridLayout(2,1,5,5));
		infoPanel.add(new JLabel("Score:"));
		JTextField infoScore = new JTextField("0", 5);
		infoScore.setEditable(false);
		infoPanel.add(infoScore);
		
		infoPanel.add(new JLabel("Time:"));
		JTextField infoTime = new JTextField("00:00", 5);
		infoTime.setEditable(false);
		infoPanel.add(infoTime);
		
		//Options panel
		optionsPanel = new JPanel(new GridLayout(2,1,10,15));
		JButton btnChangeColor = new JButton("Change Color");
		optionsPanel.add(btnChangeColor);
		JButton btnRefresh = new JButton("Refresh");
		optionsPanel.add(btnRefresh);
		
		eastPanel.add(infoPanel, BorderLayout.NORTH);
		eastPanel.add(optionsPanel, BorderLayout.CENTER);
		
		add(eastPanel, BorderLayout.EAST);
		
	}
	
	public void prepareElementsBoard() {
		if(boardPanel != null) remove(boardPanel);
		
		int height = Integer.parseInt(configWidth.getText());
		int width = Integer.parseInt(configHeight.getText());
		
		if(height < 0 || width < 0) {
			JOptionPane.showMessageDialog(this, "Ingrese valores positivos");
		}
		
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(height, width));
		boardPanel.setBackground(Color.LIGHT_GRAY);
		
		//Creating an array to store the cells
		int totalCells = height * width;
		JPanel[] cells = new JPanel [totalCells];
		for(int i = 0; i < totalCells; i++) {
			cells[i] = new JPanel();
			cells[i].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			cells[i].setBackground(Color.WHITE);
			boardPanel.add(cells[i]);
		}
		
		
		add(boardPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	public void refresh() {
		prepareElementsBoard();
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
		
		menuItemOpen.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						
						JFileChooser file = new JFileChooser();
						
						int result = file.showOpenDialog(null);
						
							
						String path = file.getSelectedFile().getAbsolutePath();
							
						JOptionPane.showMessageDialog(null, "File path selected: " + path + "\n" + "\tYou want open. ");
						
						
						
					}
				
				
				});
			
			menuItemSave.addActionListener(
				new ActionListener() {
					
					public void actionPerformed(ActionEvent ev) {
						
						JFileChooser file = new JFileChooser();
						
						int result = file.showSaveDialog(null);
						
							
						String path = file.getSelectedFile().getAbsolutePath();
							
						JOptionPane.showMessageDialog(null, "File path selected: " + path + "\n" + "\tYou want save. ");
						
						
					}
					
					
				}
					
					
			);
	}

	public static void main(String[] args) {
		SlowTetrisGUI game = new SlowTetrisGUI();
	} 
	
}

