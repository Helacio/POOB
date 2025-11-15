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
				
			case 'O':
				shape = new char[][] {
					{'O', 'O'},
					{'O', 'O'}
				};
				this.color = "BLUE";
				
			case 'S':
				shape = new char[][] {
					{'0','S','S'},
					{'S','S','0'}
				};
				this.color = "GREEN";
			case 'T':
				shape = new char[][] {
					{'0','T','0'},
					{'T','T','T'}
				};
				this.color = "ORANGE";
				
			case 'J':
				shape = new char[][] {
					{'J','0','0'},
					{'O', 'J','0'}
				};
				this.color = "PINK";
				
			case 'L':
				shape = new char[][] {
					{'L','0','0'},
					{'L','L','L'}
				};
				this.color = "CYAN";
				
			case 'Z':
				shape = new char[][] {
					{'Z','Z','0'},
					{'0','Z','Z'}
				};
				this.color = "GRAY";
		}
		/**
		public void rotateEast() {
			int row = shape.length;
			int col = shape[0].lenght;
			
			
		}
		*/
	}
	

}
