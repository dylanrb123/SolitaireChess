

/**
 * ChessButton.java
 *
 */
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
/**
 * @author Dylan Bannon
 *
 */
public class ChessButton extends JButton{
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * selected button
	 */
	private static ChessButton current = null;
	/**
	 * piece value for button
	 */
	private ChessPiece piece;
	/**
	 * whether or not a piece is selected
	 */
	private boolean selected;
	/**
	 * the original color
	 */
	private Color originalColor;
	/**
	 * the location
	 */
	private Location location;

	/**
	 * constructor
	 * @param p - the piece
	 * @param l - the location
	 */
	public ChessButton(ChessPiece p, Location l) {
		super(p.asString);
		this.piece = p;
		if (piece == ChessPiece.EMPTY) this.setText("");
		this.setFont(new Font("Serif", Font.PLAIN,40));
		this.selected = false;
		this.location = l;
	}
	
	/**
	 * sets the piece
	 * @param p - the piece
	 */
	public void setPiece(ChessPiece p) {
		this.piece = p;
		if (p != ChessPiece.EMPTY) this.setText(p.asString);
		else this.setText("");
	}
	
	/**
	 * 
	 * @return the piece
	 */
	public ChessPiece getPiece() {
			return piece;
		}
	
	/**
	 * toggles a button
	 * @param b - the button to toggle
	 */
	public static void toggleSelected(ChessButton b) {
		if (b.piece != ChessPiece.EMPTY) {
			if (current == null) {
				current = b;
				b.selected = !b.selected;
				if (b.selected) b.setBackground(Color.gray);
				else b.setBackground(b.originalColor);
			} else if (current.equals(b)) {
				current = null;
				b.selected = !b.selected;
				if (b.selected) b.setBackground(Color.gray);
				else b.setBackground(b.originalColor);
			}
		}

	}
	
	/**
	 * 
	 * @param c - the original color
	 */
	public void setOriginalColor(Color c) {
		this.originalColor = c;
	}
	
	/**
	 * 
	 * @return - the selected button
	 */
	public static ChessButton getSelectedButton() {
		return current;
	}
	
	/**
	 * 
	 * @return - the location
	 */
	public Location getLocation1() {
		return this.location;
	}


}



