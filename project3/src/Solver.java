/**
 * Solver.java
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Efficient implementation of BFS to solve puzzles
 * 
 * @author Dylan Bannon
 * @author Ben Collins
 */
public class Solver {
	
	/**
	 * the puzzle object
	 */
	private Puzzle<Config> puzzle;
	/**
	 * the predecessors list, used to build the path and for memoization
	 */
	private HashMap<Config,Config> predecessors = new HashMap<Config,Config>();
	
	/**
	 * Initializes the fields
	 * @param puzzle - the puzzle object
	 */
	public Solver(Puzzle<Config> puzzle) {
		this.setPuzzle(puzzle);
	}
	/**
	 * Solves the puzzle
	 * @return - the path to the solution
	 */
	public ArrayList<Config> solve() {
		ArrayList<Config> queue = new ArrayList<Config>();
		boolean foundGoal;
		
		Config start = getPuzzle().getStart();
		
		Config current = start;
		Config goal = null;
		foundGoal = (start.isGoal());
		
		queue.add(start);
		predecessors.put(start, null);
		
		while (!queue.isEmpty() && ! foundGoal) {
			current = queue.remove(0);
			if (current.isGoal()) {
				goal = current;
				foundGoal = true;
				break;
			}
			for (Config neighbor : getPuzzle().getNeighbors(current)) {
				if (!predecessors.containsKey(neighbor)) {
					predecessors.put(neighbor,current);
					queue.add(neighbor);
					
				}
			}
		}
		if (foundGoal) {
			return buildPath(start, goal);
		} else {
			return null;
		}
	}
	
	/**
	 * builds the path from the predecessors list, the start, and the goal
	 * 
	 * @param start - the starting config
	 * @param goal - the particular goal config
	 * @return - the path
	 */
	private ArrayList<Config> buildPath(Config start, Config goal) {
		Stack<Config> pathStack = new Stack<Config>();
		ArrayList<Config> path = new ArrayList<Config>();
		if (predecessors.containsKey(goal)) {
			Config temp = goal;
			while (temp != null) {
				pathStack.push(temp);
				temp = predecessors.get(temp);
			}
		}
		while (!pathStack.isEmpty()) {
			path.add(pathStack.pop());
		}
		return path;
	}
	/**
	 * @return the puzzle
	 */
	public Puzzle<Config> getPuzzle() {
		return puzzle;
	}
	/**
	 * @param puzzle the puzzle to set
	 */
	public void setPuzzle(Puzzle<Config> puzzle) {
		this.puzzle = puzzle;
	}

}



