package com.sheepherd.model;

public class GrassBlock {
	
	int grassUnit, GRASSFULL;
	
	public void changeGrassUnit(int delta) {
		if (delta >= 0) {
			if (grassUnit < GRASSFULL) {
				grassUnit = grassUnit + delta;
			}
		} else if (delta < 0) {
			if (grassUnit > 1) {
				grassUnit = grassUnit + delta;
			}
		}

	}

}
