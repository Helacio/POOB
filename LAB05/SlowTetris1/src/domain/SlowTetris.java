package domain;

import presentation.SlowTetrisGUI;


public class SlowTetris {

	private char[][] boardGame;
	private SlowTetrisGUI gui;

	public SlowTetris(int height, int weidht) {
		
		gui = new SlowTetrisGUI();
		
		inicializateBoard(height, weidht);
	
	}
	
	public void refresh() {
		
	for (int i = 0; i < boardGame.length ; i++) {
				
			for (int j = 0; j < boardGame[i].length ; j++) {
					
				boardGame[i][j] = '.';
					
			}
				
		}
		
		gui.refresh();
		
	
	}
	
	public void inicializateBoard(int height, int weidht) {
		
		boardGame = new char[height][weidht]; 
		
		for (int i = 0; i < height ; i++) {
			
			for (int j = 0; j < weidht ; j++) {
				
				boardGame[i][j] = '.';
				
			}
			
		}
		
		System.out.println(java.util.Arrays.deepToString(boardGame));
	}
	
	
	public static void main(String[] args) {
        
        
        SlowTetris g = new SlowTetris(0, 0);
        
        
	}
	
}
