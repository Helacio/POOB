package domain;

import java.util.ArrayList;
import java.util.Arrays;

public class Piece {
	
	private char[][] shape;
	public final static ArrayList<Character> types = new ArrayList<>(Arrays.asList( 'I', 'O', 'S', 'T', 'J', 'L', 'Z')) ;
	public String color;
	public int row, col;
	
	public Piece(char type) {
		//por defecto la matriz tendra como "eje"
		row = 0;
		col = 3;
		
		switch (type) {
		
			case 'I':
				shape = new char[][]{
					{'I','I','I','I'}
					};
				this.color = "RED";
				break;
				
			case 'O':
				shape = new char[][] {
					{'O', 'O'},
					{'O', 'O'}
				};
				this.color = "BLUE";
				break;
				
			case 'S':
				shape = new char[][] {
					{'0','S','S'},
					{'S','S','0'}
				};
				this.color = "GREEN";
				break;
				
			case 'T':
				shape = new char[][] {
					{'0','T','0'},
					{'T','T','T'}
				};
				this.color = "ORANGE";
				break;
				
			case 'J':
				shape = new char[][] {
					{'J','0','0'},
					{'O', 'J','0'}
				};
				this.color = "PINK";
				break;
				
			case 'L':
				shape = new char[][] {
					{'L','0','0'},
					{'L','L','L'}
				};
				this.color = "CYAN";
				break;
				
			case 'Z':
				shape = new char[][] {
					{'Z','Z','0'},
					{'0','Z','Z'}
				};
				this.color = "GRAY";
				break;
			}
		}
		
	
		/**
		 * Rotate the figure
		 */
		public void rotateEast() {
			int row = shape.length;
			int col = shape[0].length;
			
			char[][] rotatedShape = new char[col][row];
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					rotatedShape[j][row -1 -i] = shape[i][j];
				}
			}
			shape = rotatedShape;
		}
		
		public char[][] getPiece() {
			return shape;
		}
		
		public String getColor() {
			return color;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}
		
		public void moveLeft() {
			col--;
		}
		
		public void moveRight() {
			col++;
		}
		
		public void moveDown() {
			row++;
		}

	}

