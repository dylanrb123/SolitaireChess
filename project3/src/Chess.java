

/**
 * Chess.java
 *
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * @author Dylan Bannon
 *
 */
public class Chess extends Observable implements Puzzle<Config> {
	/**
	 * number of rows
	 */
	public static int BOARD_ROWS = 4;
	/**
	 * number of columns
	 */
	public static int BOARD_COLS = 4;
	/**
	 * number of spaces
	 */
	public static int NUM_SPACES = BOARD_ROWS*BOARD_COLS;
	/**
	 * the starting config
	 */
	private Config startConfig;
	/**
	 * the current config
	 */
	private ChessConfig currentConfig;
	/**
	 * number of moves so far
	 */
	private int numMoves;

	//	private int numMoves;
	//	private Stack undoStack;

	/**
	 * constructor
	 * @param startConfig - the start config
	 */
	public Chess(Config startConfig) {
		this.setStartConfig(startConfig);
		this.currentConfig = (ChessConfig) startConfig;
		numMoves = 0;
	}

	/* (non-Javadoc)
	 * @see Puzzle#getGoal()
	 */
	@Override
	public Config getGoal() {
		return null;
	}

	/* (non-Javadoc)
	 * @see Puzzle#getNeighbors(java.lang.Object)
	 */
	@Override
	public ArrayList<Config> getNeighbors(Config config) {
		ChessConfig config1 = (ChessConfig) config;
		ArrayList<Config> neighbors = new ArrayList<Config>();
		ArrayList<ChessConfig> chessNeighbors = config1.getNeighbors();
		for (Config c : chessNeighbors) neighbors.add(c);
		return neighbors;
	}

	/* (non-Javadoc)
	 * @see Puzzle#getStart()
	 */
	@Override
	public Config getStart() {
		// TODO Auto-generated method stub
		return getStartConfig();
	}

	/**
	 * The main method
	 * @param args - The command line arguments
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Chess input-file");
			System.exit(0);
		}


		String inputFileName = args[0];
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(inputFileName));
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		String firstLine = null;
		try {
			firstLine = reader.readLine();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		String[] rcList = firstLine.split("\\s+");
		BOARD_ROWS = Integer.parseInt(rcList[0]);
		BOARD_COLS = Integer.parseInt(rcList[1]);
		ChessConfig startConfig = null;
		String currentLine = "";
		int numPieces = 0;
		ChessPiece[][] board = new ChessPiece[BOARD_ROWS][BOARD_COLS];
		try {
			for (int i=0;i<BOARD_ROWS;i++) {
				currentLine = reader.readLine();
				String[] tempList = currentLine.split("\\s+");
				for (int j=0; j<Chess.BOARD_COLS;j++) {
					board[i][j] = makePiece(tempList[j]);
					if (board[i][j] != ChessPiece.EMPTY) numPieces++;
				}
			}
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		startConfig = new ChessConfig(board, numPieces);
		Chess game = new Chess(startConfig);
		Solver solver = new Solver(game);
		ArrayList<Config> path = solver.solve();
		if (path == null) System.out.println("No solution.");
		else for (Config c : path) System.out.println("Step " + path.indexOf(c) + ":" + c + "\n");

		ChessFrame gui = new ChessFrame("Solitare Chess   Dylan Bannon <drb2857>", game);
	}

	
	/**
	 * makes a move
	 * @param piece1 - move from
	 * @param piece2 - move to
	 * @param loc1 - location of move from
	 * @param loc2 - location of move to
	 */
	public void makeMove(ChessPiece piece1, ChessPiece piece2, Location loc1, Location loc2) {
		ChessConfig nextConfig = new ChessConfig(currentConfig.getChessBoard(), currentConfig.getNumPieces());
		if (piece1 != ChessPiece.EMPTY && piece2 != ChessPiece.EMPTY) {
			ChessPiece[][] newBoard = new ChessPiece[Chess.BOARD_ROWS][Chess.BOARD_COLS];
			for (int row=0;row<Chess.BOARD_ROWS;row++) {
				for (int col=0;col<Chess.BOARD_COLS;col++) {
					newBoard[row][col] = currentConfig.getChessBoard()[row][col];
				}
			}
			newBoard[loc2.getRow()][loc2.getCol()] = piece1;
			newBoard[loc1.getRow()][loc1.getCol()] = ChessPiece.EMPTY;
			nextConfig.setChessBoard(newBoard);
			numMoves++;
		}

		if (currentConfig.getNeighbors().contains(nextConfig)) {
			currentConfig = nextConfig;
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * resets back to start
	 */
	public void reset() {
		currentConfig = (ChessConfig)startConfig;
		numMoves = 0;
		setChanged();
		notifyObservers();
	}

	/**
	 * make a piece
	 * @param s - string value of a piece
	 * @return the piece
	 */
	public static ChessPiece makePiece(String s) {
		if (s.equals("P")) {
			return ChessPiece.PAWN;
		} else if (s.equals("R")) {
			return ChessPiece.ROOK;
		} else if (s.equals("N")) {
			return ChessPiece.KNIGHT;
		} else if (s.equals("B")) {
			return ChessPiece.BISHOP;
		} else if (s.equals("Q")) {
			return ChessPiece.QUEEN;
		} else if (s.equals("K")) {
			return ChessPiece.KING;
		} else {
			return ChessPiece.EMPTY;
		}
	}

	/**
	 * gets the current config
	 * @return
	 */
	public ChessConfig getCurrentConfig() {
		return currentConfig;
	}

	/**
	 * gets the number of moved
	 * @return - the number of moves
	 */
	public int getNumMoves() {
		return numMoves;
	}

	/**
	 * @return the startConfig
	 */
	public ChessConfig getStartConfig() {
		return (ChessConfig) startConfig;
	}

	/**
	 * @param startConfig the startConfig to set
	 */
	public void setStartConfig(Config startConfig) {
		this.startConfig = startConfig;
	}

}



