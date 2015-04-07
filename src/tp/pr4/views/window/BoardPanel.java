package tp.pr4.views.window;

import tp.pr4.control.WindowController;
import tp.pr4.logic.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel implements GameObserver {

	//Attributes

	private WindowController cntr;
	private GridBagConstraints c;
	
	private JPanel counterPanel;
	private JButton[][] buttons;
	
	private JPanel turnPanel;
	private JLabel turnTxt;
	private boolean active;
	private Counter currentTurn;
	
	

	public BoardPanel(WindowController cntr, Game game) {
		this.cntr = cntr;
		currentTurn = Counter.EMPTY;
		initGUI();
		game.addObserver(this);
	}

	public void initGUI() {
		this.setLayout(new BorderLayout());
		//We don't care about the initial number of rows and columns we create it with, we get that when we reset it
		this.counterPanel = new JPanel(new GridBagLayout());
		this.turnPanel = new JPanel();

		this.add(counterPanel, BorderLayout.CENTER);
		this.add(turnPanel, BorderLayout.PAGE_END);

		turnTxt = new JLabel("Unkown");
		turnPanel.add(turnTxt);

		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		//counterPanel.setPreferredSize((new Dimension(400, 200)));
	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player, Counter nextPlayer) {
		reset(board,nextPlayer,true);

	}

	@Override
	public void moveExecStart(Counter player) {
		turnTxt.setText("Moving...");

	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		active = false;
		turnTxt.setText("Game is finished. " + winner + " wins");
	}

	@Override
	public void onMoveError(String msg) {
		JOptionPane.showMessageDialog(this,
				"That was not a valid move. Please, try again",
				"Invalid move",
				JOptionPane.ERROR_MESSAGE);

	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible) {
		reset(board,nextPlayer,true);

	}

	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		int rows = board.getWidth();
		int cols = board.getHeight();
		buttons = new JButton[rows][cols];
		counterPanel.removeAll();
		currentTurn = player;
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols;++j) {
				Counter v = board.getPosition(i+1, j+1);
				buttons[i][j] = createButton(i, j, v);
				c.gridy = j;
				c.gridx = i;
				counterPanel.add(buttons[i][j], c);
			}
		}
		currentTurn = player;
		turnTxt.setText(currentTurn.toString() + " turn");
		this.revalidate();		
		active = true;
		
		
	}

	@Override
	public void onAddObserver(Board board, Counter nextPlayer) {
		reset(board,nextPlayer,true);

	}

	private JButton createButton(final int i, final int j, Counter v) {
		JButton x = new JButton();
		x.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (active) cntr.makeMove(i + 1, j + 1, currentTurn);
				
			}

		});
			
		addCounterIcon(x,v);
		return x;
	}

	private void addCounterIcon(JButton button, Counter turn) {
		if (turn == Counter.BLACK)
			button.setIcon(new ImageIcon(MainWindow.ICON_PATH + "black_counter.png") );
		else if (turn == Counter.WHITE)
			button.setIcon(new ImageIcon(MainWindow.ICON_PATH + "white_counter.png") );
		else
			button.setIcon(new ImageIcon(MainWindow.ICON_PATH + "empty_counter.png") );
		
	}



}