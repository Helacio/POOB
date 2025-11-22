package presentation;
import javax.swing.*;
import domain.SlowTetris;

import domain.SlowTetris;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;



public class SlowTetrisGUI extends JFrame {
	
	private int widthB, heightB;
	
	private SlowTetris game;
	private JMenuBar menuBar;
	private JMenu menuFiles;
	private JMenuItem menuItemNew, menuItemSave, menuItemExit, menuItemOpen;
	private JTextField score, time, configHeight, configWidth;
	private JPanel infoPanel, optionsPanel, configPanel, controlPanel, arrowsPanel, boardPanel;
	private JButton btnPlay, btnChangeColor, btnRefresh, btnDown, btnRight, btnRotLeft, btnRotRight, btnLeft, btnDrop;
	private JPanel[] cells;
	
	
	public SlowTetrisGUI() {
		
		super("SlowTetris");
		prepareElements();
		prepareActions();
		prepareElementsMenu();
		prepareActionsMenu();
		
	}
	
	public void prepareElements() {
		//Get the size of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		//Setting the layout like BorderLayOut
		setLayout(new BorderLayout());

		//Centering the JFrame
		widthB = (int) ((int) screenSize.width / 2.3) ;
		heightB = (int) ((int) screenSize.height / 1.2);
		this.setSize(widthB, heightB);
		this.setLocationRelativeTo(null);
		
		//Performance North zone
		configPanel = new JPanel(new FlowLayout());
		configPanel.add(new JLabel("Height:"));
		configHeight = new JTextField(3);
		configPanel.add(configHeight);
		
		configPanel.add(new JLabel("Width"));
		configWidth = new JTextField(3);
		configPanel.add(configWidth);
		
		btnPlay = new JButton("Play");
		
		configPanel.add(btnPlay);
		
		
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
		
		btnLeft = new JButton("w");
		c.gridx = 0;
		c.gridy = 1;
		controlPanel.add(btnLeft, c);
		
		btnRotRight = new JButton("RR");
		c.gridx = 2;
		c.gridy = 1;
		controlPanel.add(btnRotRight, c);
		
		btnRotLeft = new JButton("RL");
		c.gridx = 1;
		c.gridy = 1;
		controlPanel.add(btnRotLeft, c);
		
		btnRight = new JButton("R");
		c.gridx = 3;
		c.gridy = 1;
		controlPanel.add(btnRight,c);
		
		btnDown = new JButton("D");
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		controlPanel.add(btnDown, c);
		
		//Boton drop
		btnDrop = new JButton("Drop");
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		controlPanel.add(btnDrop, c);
		
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
		btnChangeColor = new JButton("Change Color");
		optionsPanel.add(btnChangeColor);
		btnRefresh = new JButton("Refresh");
		optionsPanel.add(btnRefresh);
		
		eastPanel.add(infoPanel, BorderLayout.NORTH);
		eastPanel.add(optionsPanel, BorderLayout.CENTER);
		
		add(eastPanel, BorderLayout.EAST);
		
		setVisible(true);
		
	}
	
	public void prepareElementsBoard() {
		if(boardPanel != null) remove(boardPanel);
		
		int heightB = Integer.parseInt(configWidth.getText());
		int widthB = Integer.parseInt(configHeight.getText());
		
		if(heightB < 0 || widthB < 0) {
			JOptionPane.showMessageDialog(this, "Ingrese valores positivos");
		}
		
		game = new SlowTetris(heightB, widthB);
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(heightB, widthB));
		boardPanel.setBackground(Color.LIGHT_GRAY);
		
		//Creating an array to store the cells
		int totalCells = heightB * widthB;
		
		cells = new JPanel [totalCells];
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
		
		btnChangeColor.addActionListener(
			new ActionListener() {	
				public void actionPerformed(ActionEvent e) {
					
					changeColorMatrix();
				
				}
		
		});
		
		btnPlay.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						prepareElementsBoard();
						
					}
			
		});
		
		btnRefresh.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						refresh();	
					}
				}
		);
		
		btnDown.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(game != null) {
							game.movePieceDown();
							refresh();
						}
					}
				});
		
		btnRight.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(game != null) {
							game.movePieceRight();
							refresh();
						}
					}
				});
		
		btnLeft.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(game != null) {
							game.movePieceLeft();
							refresh();
						}
					}
				});
		
		btnRotRight.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(game != null) {
							game.rotatePiece();
							refresh();
						}
					}
				});
		
		//Temporal
		btnRotLeft.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(game != null) {
							game.dropPiece();
							refresh();
						}
					}
				});
		
		btnDrop.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(game != null) {
							game.dropPiece();
							refresh();
						}
					}
				});
		
	}
	
	public void refresh() {
		
		if(game == null || cells == null) {
			return;
		}
		
		char[][] board = game.getBoardWithPiece();
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++ ) {
				int indexCell = i * board[0].length +j;
				
				if(board[i][j] == '0') {
					cells[indexCell].setBackground(Color.white);
				} else {
					cells[indexCell].setBackground(getPieceColor(board[i][j]));
				}
			}
		}
		
		boardPanel.revalidate();
		boardPanel.repaint();
	
	}
	
	private Color getPieceColor(char type) {
		switch(type) {
			case 'I':
				return Color.RED;
			case 'O': 
				return Color.BLUE;
		    case 'S': 
		    	return Color.GREEN;
		    case 'T': 
		    	return Color.ORANGE;
		    case 'J': 
		    	return Color.PINK;
		    case 'L': 
		    	return Color.CYAN;
		    case 'Z': 
		    	return Color.GRAY;
		    default: 
		    	return Color.WHITE;
		}
	}
	
	
	private void changeColorMatrix() {
	
		Color color = JColorChooser.showDialog(this, "Change color game", boardPanel.getBackground());
		
		
			
		for (JPanel p : cells) {
			
			if (color != null) {
				p.setBackground(color); 
			}
		
		}
		
		if (boardPanel != null) {
			
			boardPanel.revalidate();
			boardPanel.repaint();
			boardPanel.setBackground(color);
			
		}
		
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
			});
		
		JButton btnNewPiece = new JButton("New Piece");
        arrowsPanel.add(btnNewPiece, BorderLayout.NORTH);
        
        btnNewPiece.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				if(game != null) {
        	                game.generatePiece();
        	                refresh();
        	            }
        			}
        });
		}
	
	
	public static void main(String[] args) {
        
        SlowTetrisGUI gui = new SlowTetrisGUI();
        
	}
	
}