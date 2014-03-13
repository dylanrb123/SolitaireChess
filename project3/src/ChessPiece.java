/**
 * ChessPiece.java
 *
 */

/**
 * @author Dylan Bannon
 *
 */
public enum ChessPiece {
	PAWN("\u265F"),
	ROOK("\u265C"), 
	KNIGHT("\u265E"), 
	BISHOP("\u265D"), 
	KING("\u265A"), 
	QUEEN("\u265B"),
	EMPTY(".");

	/**
	 * unicode value of the chess symbol
	 */
	public final String asString;

	private ChessPiece(String s) {
		asString = s;
	}
	
}



