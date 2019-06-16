package com.sheepherd.model;

public class SheepMatrix {
	
	int N = 21;
	int n = 1;
	Sheep sheep[][] = new Sheep[N][N];
	
	public SheepMatrix() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sheep[i][j] = new Sheep();
				sheep[i][j].growth = -1;
			}
		}
	}
	
	public void init(int matrixOrder) {
		this.n = matrixOrder;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sheep[i][j].newBorn();
				sheep[i][j].isAlive = false;
				sheep[i][j].nowX = i;
				sheep[i][j].nowY = j;
			}
		}
		//add the first sheep
		sheep[0][0].isAlive = true;
		sheep[1][0].isAlive = true;
		sheep[0][1].isAlive = true;
	}
	
	public boolean isGrownSheep(int x, int y) {
		return sheep[x][y].isGrown();
	}
	public void growSheep(int x, int y) {
		sheep[x][y].grow();
	}
	
	public void newSheep(int x, int y) {
		sheep[x][y].newBorn();
	}
	
	public String printSheep(int x, int y) {
		if (sheep[x][y].isAlive == true) {
			return "s";
		} else {
			return "0";
		}
	}
	
	public boolean isSheepAlive(int x, int y) {
	    return sheep[x][y].isAlive;
	}
	
	public void dieOld(int x, int y) {
		sheep[x][y].dieOld();
	}
	
	public void dieEaten(int x, int y) {
		sheep[x][y].dieEaten();
	}

}
