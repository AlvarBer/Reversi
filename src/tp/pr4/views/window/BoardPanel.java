package tp.pr4.views.window;

import tp.pr4.control.WindowController;
import tp.pr4.logic.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel implements GameObserver {

	//Attributes
	//TODO:Add components of the panel to the attributes
	private WindowController cntr;
	private JButton[][] buttons;
	private GridBagConstraints c;
	private boolean active;
	private Counter currentTurn;
	private JLabel turnTxt;

	public BoardPanel(WindowController cntr, Game game) {
		this.cntr = cntr;
		currentTurn = Counter.EMPTY;
		initGUI();
		game.addObserver(this);
	}

	public void initGUI() {
		this.setLayout(new BorderLayout());
		//We don't care about the initial number of rows and columns we create it with, we get that when we reset it
		JPanel CounterPanel = new JPanel(new GridBagLayout());
		JPanel TurnPanel = new JPanel();

		this.add(CounterPanel, BorderLayout.CENTER);
		this.add(TurnPanel, BorderLayout.PAGE_END);

		turnTxt = new JLabel("Unkown");
		TurnPanel.add(turnTxt);

		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		CounterPanel.setPreferredSize((new Dimension(400, 200)));
	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player, Counter nextPlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveExecStart(Counter player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		active = false;

	}

	@Override
	public void onMoveError(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible) {
		// TODO Auto-generated method stub

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
		this.removeAll();
		currentTurn = player;

		for (int i = 0; i < rows; ++i) {
			for (int j = 0; i < cols;++j) {
				Counter v = board.getPosition(i+1, j+1);
				buttons[i][j] = createButton(i, j, v);
				c.gridy = i;
				c.gridx = j;
			}
		}
		this.revalidate();
		active = true;
	}

	@Override
	public void onAddObserver(Board board, Counter nextPlayer) {
		// TODO Auto-generated method stub

	}

	private JButton createButton(final int i, final int j, Counter v) {
		JButton x = new JButton();
		x.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (active) cntr.makeMove(i + 1, j + 1, currentTurn);
			}

		});
		return x;
	}

}