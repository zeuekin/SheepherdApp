/**
 * The main algorithom of grassland simulation.
 */
package com.sheepherd.frame;

import com.sheepherd.model.GrassMatrix;
import com.sheepherd.model.SheepMatrix;
import com.sheepherd.model.WolfMatrix;

/**
 * @author zou_e
 *
 */
public class Simulation {
	
	static GrassMatrix grassMatrix = new GrassMatrix();
	static SheepMatrix sheepMatrix = new SheepMatrix();
	static WolfMatrix wolfMatrix = new WolfMatrix();
	
	public Simulation() {

	}
	
	public void init(int matrixOrder) {
		Start.mainF.appendLog("Start init "+String.valueOf(matrixOrder)+" order matrix \n");
		grassMatrix.init(matrixOrder);
		sheepMatrix.init(matrixOrder);
		wolfMatrix.init(matrixOrder);
	}
	
	public void runCycles(int matrixOrder, int cycleInt) {
			Start.mainF.appendLog("run "+String.valueOf(cycleInt)+" cycles \n");
			for (int time = 0; time < cycleInt; time ++) {
				for (int i = 0; i < matrixOrder; i++) {
					for (int j = 0; j < matrixOrder; j++) {
						grassMatrix.changeGrass(i, j, 1);//grassUnit increase 1 each cycle			
						if (sheepMatrix.printSheep(i, j) == "s") {
							//System.out.println(String.valueOf(i) + "," + String.valueOf(j));
							grassMatrix.changeGrass(i, j, -2);
							sheepMatrix.growSheep(i, j);
							if (sheepMatrix.isGrownSheep(i, j) == true) {
								var newPosision = findEmptyGrass(i, j, matrixOrder, true);
								sheepMatrix.newSheep(newPosision[0], newPosision[1]);
							}
						}
					}	
					
				}
				for (int m = 0; m < matrixOrder; m++) {
					for (int n = 0; n < matrixOrder; n++) {
						if (wolfMatrix.printWolf(m, n) == "w") {
							var newPosision = findSheep(m, n, matrixOrder);
							int xTo = m, yTo = n;
							if ((newPosision[0] - m) > 0) {
								xTo = m + 1;
							} else if ((newPosision[0] - m) < 0) {
								xTo = m - 1;
							}
							if ((newPosision[1] - n) > 0) {
								yTo = n + 1;
							} else if ((newPosision[1] - n) < 0) {
								yTo = n - 1;
							}
							wolfMatrix.moveWolf(m, n, xTo, yTo);
							if (sheepMatrix.printSheep(xTo, yTo) == "s") {
								sheepMatrix.dieEaten(xTo, yTo);
								wolfMatrix.growWolf(xTo, yTo);
								if (wolfMatrix.isGrownWolf(xTo, yTo) == true) {
									var newWolfPosition = findEmptyGrass(xTo, yTo, matrixOrder, false);
									wolfMatrix.newWolf(newWolfPosition[0], newWolfPosition[1]);
								}
							}
						}
					}
				}
				for (int m = 0; m < matrixOrder; m++) {
					for (int n = 0; n < matrixOrder; n++) {
						if (wolfMatrix.printWolf(m, n) == "w")
							wolfMatrix.dieOld(m, n);
						if (sheepMatrix.printSheep(m, n) == "s")
							sheepMatrix.dieOld(m, n);
					}
				}
			}
			printGrasslandMatrix(matrixOrder);
			//printGrassMatrix(matrixOrder);
			//printWolfMatrix(matrixOrder);
			//printSheepMatrix(matrixOrder);
			
		
		
	}
	
	public int[] findSheep(int x, int y, int matrixOrder) {
		int [] position = {x, y};
		double distance, disMin = 1000000;
		for (int i = 0; i < matrixOrder; i++) {
			for (int j = 0; j < matrixOrder; j++) {
				if (sheepMatrix.printSheep(i, j) == "s" && wolfMatrix.printWolf(i, j) != "w") {
					distance = Math.pow((i - x), 2) + Math.pow((j - y), 2);
					if (distance < disMin) {
						position[0] = i;
						position[1] = j;
						disMin = distance;
					} else if (distance == disMin) {
						if (Math.random() > 0.3) {
							position[0] = i;
							position[1] = j;
						}
					}
				} 
			}
		}
		
		return position;
	}
	
	public int[] findEmptyGrass(int x, int y, int matrixOrder, boolean isSheep) {
		int[] position = {0, 0};
		double distance, disMin = 1000000;
		for (int i = 0; i < matrixOrder; i++) {
			for (int j = 0; j < matrixOrder; j++) {
				if (isSheep == true) {
					if (grassMatrix.printGrassBlock(i,j) > 10
							&& sheepMatrix.printSheep(i, j) != "s"
							&& wolfMatrix.printWolf(i, j) != "w") {
						distance = Math.pow((i - x), 2) + Math.pow((j - y), 2);
						if (distance < disMin) {
							position[0] = i;
							position[1] = j;
							disMin = distance;
						} else if (distance == disMin) {
							if (Math.random() > 0.3) {
								position[0] = i;
								position[1] = j;
							}
						}
					}
				} else if (isSheep == false) {
					if (sheepMatrix.printSheep(i, j) != "s"	&& wolfMatrix.printWolf(i, j) != "w") {
						distance = Math.pow((i - x), 2) + Math.pow((j - y), 2);
						if (distance < disMin) {
							position[0] = i;
							position[1] = j;
							disMin = distance;
						} else if (distance == disMin) {
							if (Math.random() > 0.3) {
								position[0] = i;
								position[1] = j;
							}
						}
					}
				}


			}
		}
		return position;
	}
	
	public void printGrasslandMatrix(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (sheepMatrix.printSheep(i, j) == "s") {
					Start.mainF.appendLog(sheepMatrix.printSheep(i, j)+"   ");
				} else if (wolfMatrix.printWolf(i, j) == "w") {
					Start.mainF.appendLog(wolfMatrix.printWolf(i, j)+"   ");
				} else {
					Start.mainF.appendLog("-   ");
				}
			}
			Start.mainF.appendLog("\n");
		}
	}
	
	public void printGrassMatrix(int n) {
		for (int i =0; i < n; i++) {
			for (int j =0; j < n; j++) {
				Start.mainF.appendLog(grassMatrix.printGrassBlock(i, j)+" ");
			}
			Start.mainF.appendLog("\n");
		}
	}
	public void printSheepMatrix(int n) {
		for (int i =0; i < n; i++) {
			for (int j =0; j < n; j++) {
				Start.mainF.appendLog(sheepMatrix.printSheep(i, j)+" ");
			}
			Start.mainF.appendLog("\n");
		}
	}
	public void printWolfMatrix(int n) {
		for (int i =0; i < n; i++) {
			for (int j =0; j < n; j++) {
				Start.mainF.appendLog(wolfMatrix.printWolf(i, j)+" ");
			}
			Start.mainF.appendLog("\n");
		}
	}
	public void printSheepMatrixValue(int n) {
		for (int i =0; i < n; i++) {
			for (int j =0; j < n; j++) {
				//Start.mainF.appendLog(sheepMatrix.printSheepGrowth(i, j)+" ");
			}
			Start.mainF.appendLog("\n");
		}
	}
	
}
