package com.sheepherd.model;

public abstract class Herd {
	
	int age, LIFETIME; 
	int growth, GROWNLEVEL;
	boolean isAlive;
	int nowX,nowY;
	int targetX, targetY;
	
	public void look() {
		
	}
	public boolean isGrown() {
		if (this.growth >= this.GROWNLEVEL) {
			growth = 1;
			return true;
		} else {
			return false;
		}
	}
	public void grow() {
		growth ++;
	}
	
	public void dieOld() {
		if (age >= LIFETIME) {
			isAlive = false;
			age = 1;
			growth = 1;
		} else {
			age ++;
		}
	}
	
	public void dieEaten() {
		isAlive = false;
	}
	
	public void moveFrom() {
		isAlive = false;
		age = 1;
		growth  = 1;
	}
	public void moveTo(int age, int growth) {
		this.isAlive = true;
		this.age = age;
		this.growth = growth;	
	}

}
