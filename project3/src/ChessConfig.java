import java.util.ArrayList;

/**
 * ChessConfig.java
 *
 */

/**
 * @author Dylan Bannon
 *
 */
public class ChessConfig implements Config {

	/**
	 * the board
	 */
	private ChessPiece[][] chessBoard = new ChessPiece[Chess.BOARD_ROWS][Chess.BOARD_COLS];
	/**
	 * the number of pieces
	 */
	private int numPieces;


	/**
	 * constructor
	 * @param board - the board
	 * @param pieces - the num of pieces
	 */
	public ChessConfig(ChessPiece[][] board, int pieces) {
		chessBoard = board;
		numPieces = pieces;
	}


	/* (non-Javadoc)
	 * @see Config#isGoal()
	 */
	@Override
	public boolean isGoal() {
		return getNumPieces() == 1;
	}

	/**
	 * gets neighbors of the config
	 * @return the neighbors
	 */
	public ArrayList<ChessConfig> getNeighbors() {
		ArrayList<ChessConfig> neighbors = new ArrayList<ChessConfig>();
		for (int row=0;row<Chess.BOARD_ROWS;row++) {
			for (int col=0;col<Chess.BOARD_COLS;col++) {
				ArrayList<Location> potentialMoves = getPotentialMoves(chessBoard[row][col],row,col);

				for (Location l : potentialMoves) {
					if (chessBoard[l.getRow()][l.getCol()] != ChessPiece.EMPTY) {
						ChessPiece[][] neighborBoard = new ChessPiece[Chess.BOARD_ROWS][Chess.BOARD_COLS];
						for (int i=0;i<Chess.BOARD_ROWS;i++) {
							for (int j=0;j<Chess.BOARD_COLS;j++) {
								neighborBoard[i][j] = chessBoard[i][j];
							}
						}
						neighborBoard[l.getRow()][l.getCol()] = chessBoard[row][col];
						neighborBoard[row][col] = ChessPiece.EMPTY;
						ChessConfig neighbor = new ChessConfig(neighborBoard,numPieces-1);
						neighbors.add(neighbor);

					} 
				}

			}
		}
		neighbors.remove(this);
		return neighbors;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder returnString = new StringBuilder();
		for (int row = 0; row<Chess.BOARD_ROWS; row++) {
			returnString.append("\n");
			for (int col = 0; col<Chess.BOARD_COLS; col++) {
				returnString.append(chessBoard[row][col].asString);
			}

		}
		return returnString.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hashCode = 0;
		for (ChessPiece[] c : getChessBoard()) {
			for (ChessPiece p : c) {
				hashCode += p.hashCode();
			}
		}
		return hashCode;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof ChessConfig) {
			ChessConfig config = (ChessConfig) o;
			for (int row=0;row<Chess.BOARD_ROWS;row++) {
				for (int col=0;col<Chess.BOARD_COLS;col++) {
					if (this.chessBoard[row][col] != config.getChessBoard()[row][col]) return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Returns list of all the places that a given piece could potentially move based on its movement rules, regardless
	 * of whether or not it would be capturing a piece.
	 * @param piece - the piece to get moves for
	 * @param row - the row of the piece
	 * @param col - the col of the piece
	 * @return - the list of legal moves
	 */
	private ArrayList<Location> getPotentialMoves(ChessPiece piece, int row, int col) {
		ArrayList<Location> potentialMoves = new ArrayList<Location>();

		switch(piece) {
		case PAWN:
			if (row-1 >= 0) {
				if (col+1 < Chess.BOARD_COLS) {
					potentialMoves.add(new Location(row-1,col+1));
				}
				if (col-1 >= 0) {
					potentialMoves.add(new Location(row-1,col-1));
				}
			}
			if (row+1 < Chess.BOARD_ROWS) {
				if (col+1 < Chess.BOARD_COLS) {
					potentialMoves.add(new Location(row+1,col+1));
				}
				if (col-1 >= 0) {
					potentialMoves.add(new Location(row+1,col-1));
				}
			}
			break;
		case ROOK:
			ForLoop1: for (int row1 = row;row1<Chess.BOARD_ROWS;row1++) {
				if (row1 != row) {
					if (chessBoard[row1][col] != ChessPiece.EMPTY){
						potentialMoves.add(new Location(row1,col));
						break ForLoop1;
					} else {
					potentialMoves.add(new Location(row1,col));
					}
				}
			}
			ForLoop2: for (int row1 = row;row1>=0;row1--) {
				if (row1 != row) {
					if (chessBoard[row1][col] != ChessPiece.EMPTY) {
						potentialMoves.add(new Location(row1,col));
						break ForLoop2;
					} else {
						potentialMoves.add(new Location(row1,col));
					}
				}
			}
			ForLoop3: for (int col1 = col;col1<Chess.BOARD_COLS;col1++) {
				if (col1 != col) {
					if (chessBoard[row][col1] != ChessPiece.EMPTY) {
						potentialMoves.add(new Location(row,col1));
						break ForLoop3;
					} else {
						potentialMoves.add(new Location(row,col1));
					}
				}
			}
			ForLoop4: for (int col1 = col;col1>=0;col1--) {
				if (col1 != col) {
					if (chessBoard[row][col1] != ChessPiece.EMPTY) {
						potentialMoves.add(new Location(row,col1));
						break ForLoop4;
					} else {
						potentialMoves.add(new Location(row,col1));
					}
				}
			}
			break;
		case KNIGHT:
			if (row+2 < Chess.BOARD_ROWS) {
				if (col+1 < Chess.BOARD_COLS) {
					potentialMoves.add(new Location(row+2,col+1));
				}
				if (col-1 >= 0) {
					potentialMoves.add(new Location(row+2,col-1));
				}
			}
			if (row+1 < Chess.BOARD_ROWS) {
				if (col+2 < Chess.BOARD_COLS) {
					potentialMoves.add(new Location(row+1,col+2));
				}
				if (col-2 >= 0) {
					potentialMoves.add(new Location(row+1,col-2));
				}
			}
			if (row-2 >= 0) {
				if (col+1 < Chess.BOARD_COLS) {
					potentialMoves.add(new Location(row-2,col+1));
				}
				if (col-1 >= 0) {
					potentialMoves.add(new Location(row-2,col-1));
				}
			}
			if (row-1 >= 0) {
				if (col+2 < Chess.BOARD_COLS) {
					potentialMoves.add(new Location(row-1,col+2));
				}
				if (col-2 >= 0) {
					potentialMoves.add(new Location(row-1,col-2));
				}
			}
			break;
		case BISHOP:
			
			int row1 = row;
			int col1 = col;
			do {
				row1--;
				col1--;
				if (row1 >= 0 && col1 >= 0) {
					if (chessBoard[row1][col1] != ChessPiece.EMPTY) {
						potentialMoves.add(new Location(row1,col1));
						break;
					} else {
						potentialMoves.add(new Location(row1,col1));
					}
				}
			} while (row1 >= 0 && col1 >= 0);

			row1 = row;
			col1 = col;
			do {
				row1--;
				col1++;
				if (row1 >= 0 && col1 < Chess.BOARD_COLS) {
					if (chessBoard[row1][col1] != ChessPiece.EMPTY) {
						potentialMoves.add(new Location(row1,col1));
						break;
					} else {
						potentialMoves.add(new Location(row1,col1));
					}
				}
			} while (row1 >= 0 && col1 < Chess.BOARD_COLS);

			row1 = row;
			col1 = col;
			do {
				row1++;
				col1++;
				if (row1 < Chess.BOARD_ROWS && col1 < Chess.BOARD_COLS) {
					if (chessBoard[row1][col1] != ChessPiece.EMPTY) {
						potentialMoves.add(new Location(row1,col1));
						break;
					} else {
						potentialMoves.add(new Location(row1,col1));
					}
				}
			} while (row1 < Chess.BOARD_ROWS && col1 < Chess.BOARD_COLS);
			row1 = row;
			col1 = col;

			do {
				row1++;
				col1--;
				if (row1 < Chess.BOARD_ROWS && col1 >= 0) {
					if (chessBoard[row1][col1] != ChessPiece.EMPTY) {
						potentialMoves.add(new Location(row1,col1));
						break;
					} else {
						potentialMoves.add(new Location(row1,col1));
					}
				}
			} while (row1 < Chess.BOARD_ROWS && col1 >= 0);
			break;
		case QUEEN:
			potentialMoves.addAll(getPotentialMoves(ChessPiece.ROOK, row, col));
			potentialMoves.addAll(getPotentialMoves(ChessPiece.BISHOP, row, col));
			break;
		case KING:
			potentialMoves.addAll(getPotentialMoves(ChessPiece.PAWN, row, col));
			if (row-1 >= 0) {
				potentialMoves.add(new Location(row-1,col));
			}
			if (row+1 < Chess.BOARD_ROWS) {
				potentialMoves.add(new Location(row+1,col));
			}
			if (col-1 >= 0) {
				potentialMoves.add(new Location(row,col-1));
			}
			if (col+1 < Chess.BOARD_COLS) {
				potentialMoves.add(new Location(row,col+1));
			}
			break;
		default:
		}

		return potentialMoves;
	}

	/**
	 * @return the chessBoard
	 */
	public ChessPiece[][] getChessBoard() {
		return chessBoard;
	}

	/**
	 * @param chessBoard the chessBoard to set
	 */
	public void setChessBoard(ChessPiece[][] chessBoard) {
		this.chessBoard = chessBoard;
	}

	/**
	 * @return the numPieces
	 */
	public int getNumPieces() {
		return numPieces;
	}

	/**
	 * @param numPieces the numPieces to set
	 */
	public void setNumPieces(int numPieces) {
		this.numPieces = numPieces;
	}
}



