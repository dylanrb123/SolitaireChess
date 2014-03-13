/**
 * WaterConfig.java
 *
 */

import java.util.ArrayList;

/**
 * Represents a configuration of the water puzzle
 * 
 * @author Dylan Bannon
 * @author Ben Collins
 *
 */
public class WaterConfig implements Config {

	
	/**
	 * List of buckets, each has a capacity and a current amount
	 */
	private ArrayList<WaterBucket> bucketList = new ArrayList<WaterBucket>();
	/**
	 * amount of water wanted in one of the buckets
	 */
	private int goalAmount;
	
	/**
	 * Constructor
	 * @param bucketList - sets bucketList
	 * @param goalAmount - sets goalAmount
	 */
	public WaterConfig(ArrayList<WaterBucket> bucketList, int goalAmount) {
		ArrayList<WaterBucket> newList = new ArrayList<WaterBucket>();
		for (WaterBucket b : bucketList) {
			newList.add(b);
		}
		this.setBucketList(newList);
		this.goalAmount = goalAmount;
	}
	
	/* (non-Javadoc)
	 * @see Config#isGoal()
	 */
	@Override
	public boolean isGoal() {
		// TODO Auto-generated method stub
		for (WaterBucket b : getBucketList()) {
			if (b.getCurrent() == getGoalAmount()) return true;
		}
		return false ;
	}
	
	/**
	 * makes a list of the valid neighbor configs
	 * @return - the list of neighbors
	 */
	public ArrayList<WaterConfig> getNeighbors() {
		ArrayList<WaterConfig> neighbors = new ArrayList<WaterConfig>();
		//empty one bucket
		for (int i=0;i<bucketList.size();i++) {
			WaterConfig neighborConfig = new WaterConfig(new ArrayList<WaterBucket>(),goalAmount);
			ArrayList<WaterBucket> newList = copy(bucketList);
			newList.set(i, new WaterBucket(bucketList.get(i).getCapacity(),0));
			neighborConfig.setBucketList(newList);
			neighbors.add(neighborConfig);
		}
		//fill one bucket
		for (int i=0;i<bucketList.size();i++) {
			WaterConfig neighborConfig = new WaterConfig(new ArrayList<WaterBucket>(),goalAmount);
			ArrayList<WaterBucket> newList = copy(bucketList);
			newList.set(i, new WaterBucket(bucketList.get(i).getCapacity(),bucketList.get(i).getCapacity()));
			neighborConfig.setBucketList(newList);
			neighbors.add(neighborConfig);
		}
		//empty one bucket into another
		for (int i=0;i<bucketList.size();i++) {
			for (int j=0;j<bucketList.size();j++) {
				if (i != j) {
					WaterConfig neighborConfig = new WaterConfig(new ArrayList<WaterBucket>(),goalAmount);
					ArrayList<WaterBucket> newList = copy(bucketList);
					WaterBucket newBucket = new WaterBucket(newList.get(i).getCapacity(),newList.get(i).getCurrent());
					int emptySpace = newBucket.getCapacity()-newBucket.getCurrent();
					if (newList.get(j).getCurrent() <= emptySpace) {
						newBucket.setCurrent(newBucket.getCurrent() + newList.get(j).getCurrent());
						newList.get(j).setCurrent(0);
					} else {
						newBucket.setCurrent(newBucket.getCapacity());
						newList.get(j).setCurrent(newList.get(j).getCurrent()-emptySpace);
					}
					newList.set(i, newBucket);
					neighborConfig.setBucketList(newList);
					neighbors.add(neighborConfig);
				}
			}
		}
		
		
		return neighbors;
	}
	
	/**
	 * deep copy of an ArrayList of WaterBuckets
	 * @param list - list to copy
	 * @return - new list
	 */
	public ArrayList<WaterBucket> copy(ArrayList<WaterBucket> list) {
		ArrayList<WaterBucket> newList = new ArrayList<WaterBucket>();
		for (WaterBucket b : list) {
			newList.add(new WaterBucket(b.getCapacity(),b.getCurrent()));
		}
		return newList;
	}
	 /*
	  * (non-Javadoc)
	  * @see java.lang.Object#toString()
	  */
	@Override
	public String toString() {
		String returnString = "";
		for (WaterBucket b : getBucketList()) {
			returnString += " " + Integer.toString(b.getCurrent());
		}
		return returnString;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return bucketList.hashCode() + new Integer(getGoalAmount()).hashCode(); 
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof WaterConfig) {
			WaterConfig w = (WaterConfig) o;
			return bucketList.equals(w.bucketList);
		} else return false;
	}

	/**
	 * @return the bucketList
	 */
	public ArrayList<WaterBucket> getBucketList() {
		return bucketList;
	}

	/**
	 * @param bucketList the bucketList to set
	 */
	public void setBucketList(ArrayList<WaterBucket> bucketList) {
		this.bucketList = bucketList;
	}

	/**
	 * @return the goalAmount
	 */
	public int getGoalAmount() {
		return goalAmount;
	}

	/**
	 * @param goalAmount the goalAmount to set
	 */
	public void setGoalAmount(int goalAmount) {
		this.goalAmount = goalAmount;
	}

}



