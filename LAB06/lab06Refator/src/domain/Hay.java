package domain;

import java.awt.Color;
import java.io.Serializable;

public class Hay implements Unit, Serializable {

	private static final long serialVersionUID = 1L;

	// color index which maps to colors [RED, YELLOW, RED, YELLOW, RED]
	private int colorPhase;

	public Hay() {
	        colorPhase = 0;
	}

	/**
	* Optional constructor that installs itself into a valley at the given position
	**/
	public Hay(Valley valley, int r, int c) {
		this();
		if (valley != null) {
			valley.setUnit(r, c, this);
	    }
	}

	@Override
	public void act() {
	// advance the color phase in a 5-step
	colorPhase = (colorPhase + 1) % 5;
	}

	@Override
	public int shape() {
	// Hay packages are square
	return Unit.SQUARE;
	}

	@Override
	public Color getColor() {
	// color sequence: 0-RED, 1-YELLOW, 2-RED, 3-YELLOW, 4-RED
	 if (colorPhase % 2 == 0) {
		 return Color.red;
	 } else {
		 return Color.yellow;
	 	}
	}
	
	@Override
	public boolean isResource() {
		return true;
	}

	@Override
	public boolean isAnimal() {
		return false;
	}
}