package domain;

import java.util.Random;

import presentation.SlowTetrisGUI;


public class SlowTetris {

	private char[][] boardGame;
	private Piece currentPiece;
	private int height, width;

	public SlowTetris(int height, int width) {
		this.height = height;
		this.width = width;
		
		inicializateBoard(height, width);
	
	}
	//pendiente aquiiiii
	public void refresh() {
	for (int i = 0; i < boardGame.length ; i++) {
			for (int j = 0; j < boardGame[i].length ; j++) {
				boardGame[i][j] = '0';
			}
		}
	}
	
	public void inicializateBoard(int height, int weidht) {
		boardGame = new char[height][weidht]; 
		for (int i = 0; i < height ; i++) {
			for (int j = 0; j < weidht ; j++) {
				boardGame[i][j] = '0';
			}
		}
		
		System.out.println(java.util.Arrays.deepToString(boardGame));
	}
	
	
	public void generatePiece() {
		fixPiece();
		char[] types = {'I', 'O','S','T','J','L','Z'};
		Random random = new Random();
		int idx = random.nextInt(types.length);
		
		currentPiece = new Piece(types[idx]);
	}
	
	public char[][] getBoardWithPiece(){
		char[][] visualBoard = new char[height][width];
		for(int i = 0 ; i < height; i++) {
            for(int j = 0; j < width; j++) {
                visualBoard[i][j] = boardGame[i][j];
            }
        }
		if(currentPiece != null) {
			drawPiece(visualBoard, currentPiece);
		}
		return visualBoard;
	}
	
	
	public void drawPiece(char[][] board, Piece p) {
		char[][] shape = p.getPiece();
		int ri = p.getRow();
		int ci = p.getCol();
		
		for(int r = 0; r < shape.length; r++) {
			for(int c = 0; c < shape[r].length; c++) {
				if(shape[r][c] != '0') {
					int rr = ri + r;
					int cc = ci + c;
					
					if(rr >= 0 && rr < height && cc >= 0 && cc < width) {
						board[rr][cc] = shape[r][c];
					}
				}
			}
		}
	}
	
	public void fixPiece() {
		if(currentPiece != null) {
			drawPiece(boardGame, currentPiece);
			currentPiece = null;
		}
	}
	
	//Verificar si la pieza se puede mover
	private boolean couldMove(int newRow, int newCol) {
		char[][] shape = currentPiece.getPiece();
		
		for(int i = 0; i < shape.length; i++) {
			for(int j = 0; j < shape[i].length; j++) {
				if(shape[i][j] != '0') {
					int rr = newRow + i;
					int cc = newCol + j;
					
					if(rr < 0 || rr >= height || cc < 0 || cc >= width) {
						return false;
					}
					
					if(boardGame[rr][cc] != '0') {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	//Aplicando verificaciones antes de cada movimiento
	
	public void movePieceDown() {
		if(currentPiece != null) {
			int newRow = currentPiece.getRow() +1;
			if(couldMove(newRow, currentPiece.getCol())) {
				currentPiece.moveDown();
			}
		}
	}
	
	public void movePieceLeft() {
		if(currentPiece != null) {
			int newRow = currentPiece.getCol() -1;
			if(couldMove(newRow, currentPiece.getCol())) {
				currentPiece.moveLeft();
			}
		}		
	}
	
	public void movePieceRight() {
		if(currentPiece != null) {
			int newRow = currentPiece.getCol() +1;
			if(couldMove(newRow, currentPiece.getCol())) {
				currentPiece.moveRight();
			}
		}
	}
	
	public void clearLine(int row) {
		for(int i = 0; i < width; i++) {
			boardGame[row][i] = '0';
		}
	}
	public void rotatePiece() {
		if(currentPiece != null) {
			currentPiece.rotateEast();
			if(!couldMove(currentPiece.getRow(), currentPiece.getCol())) {
				for(int i = 0; i < 3; i++) {
					currentPiece.moveRight();
				}
			}
		}
	}
	
	// A medias revisar!!
	public void dropPiece() {
		while(couldMove(currentPiece.getRow()+ 1, currentPiece.getCol())) {
			currentPiece.moveDown();
		}
		fixPiece();
		checkLines();
		
	}
	
	public void checkLines() {
		for(int i = height -1; i >= 0; i --) {
			if(lineFull(i)) {
				deleteLine(i);
				i++;
			}
		}
	}
	
	public boolean lineFull(int row) {
		for(int col = 0; col < width; col++) {
			if(boardGame[row][col] == '0') {
				return false;
			}
		}
		return true;
	}
	
	public void deleteLine(int row) {
		for(int i = row; i>0; i--) {
			for(int j = 0; j < width; j++) {
				boardGame[i][j] = boardGame[-1+i][0];
			}
		}
	}
	
	
	public Piece getCurrentPiece() {
		return currentPiece;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	
	public static void main(String[] args) {
        
        
        SlowTetris game = new SlowTetris(0, 0);
	}
	

	
}
