package droidPD;

import java.util.ArrayList;

import mazePD.Coordinates;
import mazePD.Maze;
import mazePD.Maze.Content;
import mazePD.Maze.Direction;

public class Droid implements DroidInterface {
	String name;
	Coordinates currentCoords;
	Maze maze;
	LinkedStack<Coordinates> currentCoordinatePath;
	ArrayList<Coordinates> visitedCells;
	Content currentOptions[];
	Direction headDirection = Direction.D00;
	Boolean canVisit = false;

	public Droid () {
		currentCoords = new Coordinates(0,0,0);
		maze = new Maze();
		currentCoordinatePath = new LinkedStack<Coordinates>();
		visitedCells = new ArrayList<Coordinates>();
	}
	
	public Droid(String name, Maze maze) {
		this();
		this.name = name;
		this.maze = maze;
		currentCoords = maze.getMazeStartCoord();
	}
	
	public void solveMaze() {
		for (int i = 0; i < maze.getMazeDepth() - 1; i++) {
			findPath();
			updateSolution();
			System.out.println();
		}
		findEndPath();
		updateSolution();
		System.out.println();
		System.out.println("Solved!");
	}
	
	public void testDroid() {
		currentCoords = maze.getCurrentCoordinates(this);
		System.out.println("Starting coords: " + currentCoords);
	}
	
	public void findPath() {
		currentCoordinatePath = new LinkedStack<Coordinates>();
		visitedCells = new ArrayList<Coordinates>();
		canVisit = false;
		testDroid();
		
		while (maze.scanCurLoc(this) != Content.PORTAL_DN) {
			System.out.println("Coordinates: " + currentCoords);
			currentCoordinatePath.push(currentCoords);
			visitedCells.add(currentCoords);
			currentOptions = maze.scanAdjLoc(this);
			//for (int i = 0; i < currentOptions.length; i++)
				//System.out.println(currentOptions[i]);
			while (!canVisit) {
				if (currentOptions[getLeftIndex()] != Content.EMPTY 
						&& currentOptions[getLeftIndex()] != Content.PORTAL_DN  
						&& currentOptions[getLeftIndex()] != Content.PORTAL_UP) {
					turnHead();
				}
				else {
					canVisit = true;
				}
			}
			
			currentCoords = maze.move(this, getLeftTravelDirection());
			headDirection = getLeftTravelDirection();
			//updateStack(currentCoords);
			canVisit = false;
		}
		currentCoordinatePath.push(currentCoords);
		visitedCells.add(currentCoords);
		System.out.println("Found portal at " + currentCoords);
		currentCoords = maze.usePortal(this, headDirection);
		System.out.println();
	}
	
	public void findEndPath() {
		currentCoordinatePath = new LinkedStack<Coordinates>();
		visitedCells = new ArrayList<Coordinates>();
		canVisit = false;
		testDroid();
		
		while (maze.scanCurLoc(this) != Content.END) {
			System.out.println("Current coordinates: " + currentCoords);
			currentCoordinatePath.push(currentCoords);
			visitedCells.add(currentCoords);
			currentOptions = maze.scanAdjLoc(this);
			for (int i = 0; i < currentOptions.length; i++)
				System.out.println(currentOptions[i]);
			while (!canVisit) {
				if (currentOptions[getLeftIndex()] != Content.EMPTY 
						&& currentOptions[getLeftIndex()] != Content.END  
						&& currentOptions[getLeftIndex()] != Content.PORTAL_UP) {
					turnHead();
				}
				else {
					canVisit = true;
				}
			}
			
			currentCoords = maze.move(this, getLeftTravelDirection());
			headDirection = getLeftTravelDirection();
			canVisit = false;
		}
		
		currentCoordinatePath.push(currentCoords);
		visitedCells.add(currentCoords);
		System.out.println("Found end at " + currentCoords);
	}
	
	public int getLeftIndex() {
		switch (headDirection) {
		case D00:
			return 3;
		case D90:
			return 0;
		case D180:
			return 1;
		case D270:
			return 2;
		default:
			return -1;
		}
	}
	
	public void turnHead() {
		switch(headDirection) {
		case D00:
			headDirection = Direction.D90;
			break;
		case D90:
			headDirection = Direction.D180;
			break;
		case D180:
			headDirection = Direction.D270;
			break;
		case D270:
			headDirection = Direction.D00;
			break;
		default:
			headDirection = null;
		}
	}
	
	public Direction getLeftTravelDirection() {
		switch(headDirection) {
		case D00:
			return Direction.D270;
		case D90:
			return Direction.D00;
		case D180:
			return Direction.D90;
		case D270:
			return Direction.D180;
		default:
			return null;
		}
	}
	
	public String getHeadDirectionWord() {
		switch(headDirection) {
		case D00:
			return "Up";
		case D90:
			return "Right";
		case D180:
			return "Down";
		case D270:
			return "Left";
		default:
			return null;
		}
	}
	
	public void updateStack(Coordinates c) {
		boolean found = false;
		int i = 0;
		while (!found) {
			if (visitedCells.get(i).equals(c)) {
				if (!currentCoordinatePath.isEmpty()) {
					while (!currentCoordinatePath.top().equals(c))
						currentCoordinatePath.pop();
					found = true;
				}
			}
			
			if (i == visitedCells.size()-1)
				found = true;
			
			i++;
		}
	}
	
	public void updateSolution() {
		System.out.println("Path known by droid:");
		int s = currentCoordinatePath.size();
		Coordinates zero = new Coordinates(0,0,0);
		ArrayList<Coordinates> finalCoordinates = new ArrayList<Coordinates>();
		ArrayList<Coordinates> tempList = new ArrayList<Coordinates>();
		for (int i = 0; i < s; i++)
			tempList.add(currentCoordinatePath.pop());

//        for (Coordinates c : tempList) {
//            	finalCoordinates.add(c);
//            }
//        
//        //replacing duplicate coordinates with null coordinates
//        for (int i = 0; i < finalCoordinates.size(); i++) {
//        	if (i+1 < finalCoordinates.size())
//        		if (finalCoordinates.get(i).equals(finalCoordinates.get(i+1)))
//        			finalCoordinates.set(i, zero);
//        }
//        
//        //removing null coordinates
//        tempList = new ArrayList<Coordinates>();
//        
//        for (Coordinates c : finalCoordinates) {
//        	if (!c.equals(zero))
//        		tempList.add(c);
//        }
//        
        //reverse array
        ArrayList<Coordinates> finalCoordinatesOrdered = new ArrayList<Coordinates>();
        for (int i = 0; i < tempList.size(); i++) {
        	finalCoordinatesOrdered.add(tempList.get(tempList.size()-1-i));
        }
        
        for (int i = 0; i < finalCoordinatesOrdered.size(); i++) {
        	System.out.println(finalCoordinatesOrdered.get(i));
        }	
	}
	
	@Override
	public String getName() {
		return name;
	}

}