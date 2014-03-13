

/**
 * ChessFrame.java
 *
 */
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * @author Dylan Bannon
 *
 */
public class ChessFrame extends JFrame implements Observer, ActionListener {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the model
	 */
	private Chess model;
	/**
	 * the buttons
	 */
	private ChessButton[][] buttons = new ChessButton[Chess.BOARD_ROWS][Chess.BOARD_COLS];
	/**
	 * the moves counter
	 */
	private JLabel label;

	/**
	 * makes a chess frame
	 * @param s - the title
	 * @param model - the model
	 */
	public ChessFrame(String s, Chess model) {
		super(s);
		this.model = model;


		this.setSize(500,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setVisible(true);

		//set up NORTH, move counter
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		label = new JLabel("Click a piece to start.");
		topPanel.add(label);

		//set up CENTER, grid of ChessButtons
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(Chess.BOARD_ROWS, Chess.BOARD_COLS));
		ChessConfig startConfig = (ChessConfig) model.getStart();
		for (int i=0;i<Chess.BOARD_ROWS;i++) {
			for (int j=0;j<Chess.BOARD_COLS;j++) {

				
				ChessButton button = new ChessButton(startConfig.getChessBoard()[i][j], new Location(i,j));
				button.addActionListener(new ChessButtonListener());
				buttons[i][j] = button;
				
				if ((i+j)%2 == 0) {
					button.setBackground(Color.white);
					button.setOriginalColor(Color.white);
				} else {
					button.setBackground(Color.LIGHT_GRAY);
					button.setOriginalColor(Color.LIGHT_GRAY);
				}
				centerPanel.add(button);
			}
		}

		//Set up SOUTH, help, hint, undo, reset
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(this);
		bottomPanel.add(helpButton);
		bottomPanel.add(new JButton("Hint"));
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ResetButtonListener());
		bottomPanel.add(resetButton);
		

		this.add(topPanel,BorderLayout.NORTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.add(bottomPanel,BorderLayout.SOUTH);
		
		model.addObserver(this);


	}
	 /**
	  * listener for reset button
	  * @author Dylan Bannon
	  *
	  */
	public class ResetButtonListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			model.reset();
			if (ChessButton.getSelectedButton() != null) ChessButton.toggleSelected(ChessButton.getSelectedButton());
			
		}
	}
	
	/**
	 * listener for chess buttons
	 * @author Dylan Bannon
	 *
	 */
	public class ChessButtonListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ChessButton moveFrom = null;
			ChessButton moveTo = (ChessButton) arg0.getSource();
			if (ChessButton.getSelectedButton() == null) {
				ChessButton.toggleSelected(moveTo);
			} else if (ChessButton.getSelectedButton() != null) {
				moveFrom = ChessButton.getSelectedButton();
				ChessButton.toggleSelected(moveFrom);
				model.makeMove(moveFrom.getPiece(), moveTo.getPiece(), moveFrom.getLocation1(), moveTo.getLocation1());

			}
			
		}
		
	}
	

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		for (int row=0;row<Chess.BOARD_ROWS;row++) {
			for (int col=0;col<Chess.BOARD_COLS;col++) {
				buttons[row][col].setPiece(model.getCurrentConfig().getChessBoard()[row][col]);
			}
		}
		int numMoves = model.getNumMoves();
		if (model.getCurrentConfig().isGoal()) {
			label.setForeground(Color.GREEN);
			label.setText("You won in " + numMoves + " moves!");
			for (ChessButton[] row : buttons) {
				for (ChessButton b : row) {
					b.setEnabled(false);
				}
			}
		} else {
			label.setText("Moves: " + numMoves);
		}
		validate();

	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String helpMessage = "Rules of the game:\n\n" +
				"- Pieces move as in regular chess.\n\n" +
				"- To move a piece, select it and then\n" +
				"  select a valid location to move to.\n\n" +
				"- Each move must be a capture. \n\n" +
				"- When there is only one piece left, you win!";
		JOptionPane.showMessageDialog(this,helpMessage,"How to play:", JOptionPane.DEFAULT_OPTION);
	}

}



