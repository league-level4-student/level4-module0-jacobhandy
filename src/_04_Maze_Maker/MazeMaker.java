package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		selectNextPath(maze.cells[randGen.nextInt(maze.cells.length)][randGen.nextInt(maze.cells.length)]);
		
		//5. call selectNextPath method with the randomly selected cell
		//see above
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		
		//C. if has unvisited neighbors,
		if(getUnvisitedNeighbors(currentCell).size() > 0) {
			//C1. select one at random.
			Random r = new Random();
			
			//C2. push it to the stack
			uncheckedCells.push(getUnvisitedNeighbors(currentCell).get(r.nextInt(getUnvisitedNeighbors(currentCell).size())));
			//C3. remove the wall between the two cells
			removeWalls(currentCell, uncheckedCells.peek());
			//C4. make the new cell the current cell and mark it as visited
			Cell newCell = uncheckedCells.peek();
			newCell = currentCell;
			//C5. call the selectNextPath method with the current cell
			selectNextPath(newCell);
			
		//D. if all neighbors are visited
		if(getUnvisitedNeighbors(currentCell).size() == 0) {
			//D1. if the stack is not empty
			if(uncheckedCells.empty() == false) {
				// D1a. pop a cell from the stack
				
				// D1b. make that the current cell
				newCell = uncheckedCells.pop();
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(newCell);
			}
			
		}
			
		}
			
				
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(Math.abs(c1.getX() - c2.getX()) == 100) {
			if(c1.hasEastWall()) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
			else if(c1.hasWestWall()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
		}
		if(Math.abs(c1.getY() - c2.getY()) == 100) {
			if(c1.hasNorthWall()) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
			else if(c1.hasSouthWall()) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisited = new ArrayList<Cell>();
		if(maze.getCell(c.getX() + 100, c.getY()).hasBeenVisited() == false){
			unvisited.add(maze.getCell(c.getX() + 100, c.getY()));
		}
		if(maze.getCell(c.getX() - 100, c.getY()).hasBeenVisited() == false){
			unvisited.add(maze.getCell(c.getX() - 100, c.getY()));
		}
		if(maze.getCell(c.getX(), c.getY() + 100).hasBeenVisited() == false){
			unvisited.add(maze.getCell(c.getX(), c.getY() + 100));
		}
		if(maze.getCell(c.getX(), c.getY() - 100).hasBeenVisited() == false){
			unvisited.add(maze.getCell(c.getX(), c.getY() - 100));
		}
		return unvisited;
	}
}
