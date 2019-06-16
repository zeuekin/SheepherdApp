package com.sheepherd.model;

public class GrassMatrix {
	
	int N = 20;
	int n = 1;
	GrassBlock grassBlock[][] = new GrassBlock[N][N];
    
	public GrassMatrix() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				grassBlock[i][j] = new GrassBlock();
			}
		}
		//GrassBlock grassBlock[][] = new GrassBlock[n][n];
	}
	
	public void init(int matrixOrder) {
		this.n = matrixOrder;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grassBlock[i][j].GRASSFULL = 20;
				grassBlock[i][j].grassUnit = 10;
			}
		}
	}
	
	public void changeGrass(int x, int y, int delta) {
		grassBlock[x][y].changeGrassUnit(delta);
	}
	
	public int printGrassBlock(int x, int y) {
			return grassBlock[x][y].grassUnit;
		}
		
	

}
