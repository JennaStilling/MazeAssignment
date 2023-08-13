package mazeUI;

import java.io.IOException;

import mazePD.Maze;
import mazePD.Maze.MazeMode;
import mazeTest.MazeTest;

public class MazeMain {
	public static void main (String [] args) throws IOException {
		MazeTest testObject = new MazeTest();
		testObject.runMaze(2, 4);
	}
}
