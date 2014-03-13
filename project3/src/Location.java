/**
 * Location.java
 *
 */

/**
 * @author Dylan Bannon
 *
 */
public class Location {
	/**
	 * the row
	 */
	private int row;
	/**
	 * the col
	 */
	private int col;
	
	/**
	 * constructor
	 * @param row - the row
	 * @param col - the col
	 */
	public Location(int row,int col) {
		this.row = row;
		this.col = col;
	}
	
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}
	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	
}



