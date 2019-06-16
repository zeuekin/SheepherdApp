package com.sheepherd.model;

public class WolfMatrix {
	
	int N = 21;
	int n = 1;
	Wolf wolf[][] = new Wolf[N][N];
	
	public WolfMatrix() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				wolf[i][j] = new Wolf();
				wolf[i][j].growth = -1;
			}
		}
	}
	
	public void init(int matrixOrder) {
		this.n = matrixOrder;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				wolf[i][j].newBorn();
				wolf[i][j].isAlive = false;
				wolf[i][j].nowX = i;
				wolf[i][j].nowY = j;
			}
		}
		//add the first wolf
		wolf[n-1][n-1].isAlive = true;
	}
	
	public boolean isGrownWolf(int x, int y) {
		return wolf[x][y].isGrown();
	}
	public void growWolf(int x, int y) {
		wolf[x][y].grow();
	}
	
	public void moveWolf(int xFrom, int yFrom, int xTo, int yTo) {
		if (xFrom != xTo && yFrom != yTo) {
			wolf[xTo][yTo].moveTo(wolf[xFrom][yFrom].age, wolf[xFrom][yFrom].growth);
			wolf[xFrom][yFrom].moveFrom();
		}
	}
	
	public String printWolf(int x, int y) {
		if (wolf[x][y].isAlive == true) {
			return "w";
		} else {
			return "0";
		}
	}
	
	public void newWolf(int x, int y) {
		wolf[x][y].newBorn();
	}
	
	public void dieOld(int x, int y) {
		wolf[x][y].dieOld();
	}
	

}
