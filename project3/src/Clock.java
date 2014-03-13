
/**
 * Clock.java
 *  
 */

import java.util.ArrayList;

/**
 * Simple clock puzzle
 * 
 * @author Dylan Bannon
 * @author Ben Collins
 */

public class Clock implements Puzzle<Config> {
	
	/**
	 * total number of hours on the clock
	 */
	private int hours;
	/**
	 * start time
	 */
	private int start;
	/**
	 * end time
	 */
	private int end;
	
	/**
	 * Initializes the variables
	 * 
	 * @param hours - total number of hours on the clock
	 * @param start - start time
	 * @param end - end time
	 */
	public Clock(int hours, int start, int end) {
		this.hours = hours;
		this.start = start;
		this.end = end;
	}

	/* (non-Javadoc)
	 * @see Puzzle#getGoal()
	 */
	@Override
	public ClockConfig getGoal() {
		return new ClockConfig(end,end);
	}

	/* (non-Javadoc)
	 * @see Puzzle#getNeighbors(int)
	 */
	@Override
	public ArrayList<Config> getNeighbors(Config config) {
		ClockConfig config1 = (ClockConfig) config;
		ArrayList<Config> neighbors = new ArrayList<Config>();
		ClockConfig toAdd = new ClockConfig(config1.getCurrent()+1,end);
		
		if (toAdd.getCurrent() == hours+1) {
			toAdd.setCurrent(1); 
		}
		neighbors.add(toAdd);
		toAdd = new ClockConfig(config1.getCurrent()-1,end);
		
		if (toAdd.getCurrent() == 0) {
			toAdd.setCurrent(hours);
		}
		neighbors.add(toAdd);
		return neighbors;
	}

	/* (non-Javadoc)
	 * @see Puzzle#getStart()
	 */
	@Override
	public ClockConfig getStart() {
		return new ClockConfig(start,end);
	}
	
	/**
	 * The main method, uses the Solver to print the solution
	 * @param args - the command line arguments
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: java Clock hours start goal");
			System.exit(0);
		}

		Solver solver = new Solver(new Clock(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2])));
		ArrayList<Config> solution = solver.solve();
		for (int i=0;i<solution.size();i++) {
			System.out.println("Step " + i + ": " + solution.get(i));
		}
		
	}

}
