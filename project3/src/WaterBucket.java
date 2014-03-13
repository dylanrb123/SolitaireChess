/**
 * WaterBucket.java
 *
 *
 */

/**
 * Represents a single bucket of water for the water puzzle
 * 
 * @author Dylan Bannon
 * @author Ben Collins
 *
 */
public class WaterBucket {
	/**
	 * capacity of the bucket
	 */
	private int capacity;
	/**
	 * current amount in the bucket
	 */
	private int current;
	
	/**
	 * Constructor, defaults current to 0.
	 * @param capacity - capacity to set
	 */
	public WaterBucket(int capacity) {
		this.capacity = capacity;
		current = 0;
	}

	/**
	 * Constructor
	 * @param capacity - the capacity to set
	 * @param current - the current amount to set
	 */
	public WaterBucket(int capacity, int current) {
		this.capacity = capacity;
		this.current = current;
	}
	 /*
	  * (non-Javadoc)
	  * @see java.lang.Object#hashCode()
	  */
	@Override
	public int hashCode() {
		return new Integer(current).hashCode() + new Integer(capacity).hashCode();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof WaterBucket) {
			WaterBucket w = (WaterBucket) o;
			return (capacity == w.capacity && current == w.current);
		} else return false;
	}
	
	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * @return the current contents
	 */
	public int getCurrent() {
		return current;
	}
	
	/**
	 * @param i the current amount to set
	 */
	public void setCurrent(int i) {
		current = i;
	}
}



