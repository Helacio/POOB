package domain;

import presentation.SlowTetrisGUI;

import java.util.ArrayList;

public class SlowTetris {

	private ArrayList<ArrayList<Character>> board = new ArrayList<>();

	public SlowTetris() {
		
		SlowTetrisGUI gui = new SlowTetrisGUI();
		gui.setVisible(true);
		
	}
	
}
