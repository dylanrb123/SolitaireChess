import java.util.ArrayList;

/**
 * Water.java
 *
 */

/**
 * Water puzzle implementation
 * 
 * @author Dylan Bannon
 * @author Ben Collins
 *
 */
public class Water implements Puzzle<Config> {
	
	/**
	 * amount of water wanted in one of the buckets
	 */
	int goalAmount;
	/**
	 * Starting list, all buckets initialized and empty
	 */
	ArrayList<WaterBucket> startList;
	
	/**
	 * Constructor
	 * @param goalAmount - sets goal
	 * @param startList - sets startList
	 */
	public Water(int goalAmount, ArrayList<WaterBucket> startList) {
		this.goalAmount = goalAmount;
		this.startList = startList;
	}
	
	/* (non-Javadoc)
	 * @see Puzzle#getGoal()
	 */
	@Override
	public Config getGoal() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Puzzle#getNeighbors(java.lang.Object)
	 */
	@Override
	public ArrayList<Config> getNeighbors(Config config) {
		WaterConfig config1 = (WaterConfig) config;
		ArrayList<Config> neighbors = new ArrayList<Config>();
		ArrayList<WaterConfig> waterNeighbors = config1.getNeighbors();
		for (WaterConfig w : waterNeighbors) {
			neighbors.add(w);
		}
		return neighbors;
	}

	/* (non-Javadoc)
	 * @see Puzzle#getStart()
	 */
	@Override
	public Config getStart() {
		return new WaterConfig(startList,goalAmount);
	}
	
	/**
	 * The main method, gets the solution from the solver and prints it out.
	 * @param args - command line arguments, must have at least two (first is desired amount, 
	 * second and beyond are bucket sizes).
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Usage: java Water amount jug1 jug2 ...");
			System.exit(0);
		}
		ArrayList<WaterBucket> bucketList = new ArrayList<WaterBucket>();
		int goal = Integer.parseInt(args[0]);
		for (int i=1;i<args.length;i++) {
			bucketList.add(new WaterBucket(Integer.parseInt(args[i])));
		}
		Water waterPuzzle = new Water(goal,bucketList);
		Solver theSolver = new Solver(waterPuzzle);
		ArrayList<Config> path = theSolver.solve();
		if (path == null) {
			System.out.println("No solution.");
		} else {
			for (int i=0;i<path.size();i++) {
				System.out.println("Step " + i + ":" + path.get(i));
			}
		}
	}
}



