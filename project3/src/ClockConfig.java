/**
 * ClockConfig.java
 *
 */

/**
 * Represents a configuration of the clock puzzle
 * 
 * @author Dylan Bannon
 * @author Ben Collins
 *
 */
public class ClockConfig implements Config {
	
	/**
	 * goal time
	 */
	private int goal;
	/**
	 * current time
	 */
	private int current;
	
	/**
	 * Constructor
	 * @param current - sets current
	 * @param goal - sets goal
	 */
	public ClockConfig(int current, int goal) {
		this.goal = goal;
		this.current = current;
	}
	
	/* (non-Javadoc)
	 * @see Config#isGoal()
	 */
	@Override
	public boolean isGoal() {
		// TODO Auto-generated method stub
		return current == goal;
	}
	
	/**
	 * gets current
	 * @return - current
	 */
	public int getCurrent() {
		return current;
	}
	
	/**
	 * sets current
	 * @param current - current
	 */
	public void setCurrent(int current) {
		this.current = current;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Integer.toString(current);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof ClockConfig) {
			ClockConfig c = (ClockConfig) o;
			if (current == c.current && goal == c.goal) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (new Integer(current).hashCode() + new Integer(goal).hashCode());
	}

}



