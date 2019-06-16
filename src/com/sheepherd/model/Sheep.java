package com.sheepherd.model;

public class Sheep extends Herd {
	
	
	public void newBorn() {
		isAlive = true;
		age = 1;
		LIFETIME = 25;
		growth = 1;
		GROWNLEVEL = 6;
	}

}
