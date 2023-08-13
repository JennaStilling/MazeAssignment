package mazeTest;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import droidPD.Droid;
import mazePD.Maze;
import mazePD.Maze.MazeMode;

public class MazeTest {
	String filePath = "src\\mazeChecker.txt";
	FileWriter outFile;
	public void runMaze(int dim, int levels) throws IOException {
		System.out.println("Maze Test");
		Maze maze = new Maze(dim,levels,MazeMode.NORMAL);
		System.out.println("Maze - "+maze.toString());
		
		for (int z=0;z<levels;z++)
		{
		  System.out.println("Level - " + z );
		  String[] mazeArray = maze.toStringLevel(z);
		  for (int y=0;y<dim;y++) 						     
			  System.out.println(y+" "+mazeArray[y]);
		}
		
		try {
			FileWriter outFile = new FileWriter(filePath);
			for (int z=0;z<levels;z++)
			{
			outFile.append("Level - " + z + "\n");
			  String[] mazeArray = maze.toStringLevel(z);
			  for (int y=0;y<dim;y++) 						     
				  outFile.append(y+" "+mazeArray[y] + "\n");
			}
			outFile.close();
		}
		
		 catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
		
		
		Droid droid = new Droid("Jakov", maze);
		maze.enterMaze(droid);
		droid.solveMaze();
	}
}