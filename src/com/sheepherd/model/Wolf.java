package com.sheepherd.model;

public class Wolf extends Herd {
	
	public void eatSheep() {
		
	}
	
	public void newBorn() {
		isAlive = true;
		age = 1;
		LIFETIME = 25;
		growth = 1;
		GROWNLEVEL = 5;
	}


}
