package tp.pr4.views.window;

import tp.pr4.control.WindowController;
import tp.pr4.logic.*;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel implements GameObserver {

	//Attributes
	//TODO:Add components of the panel to the attributes
	private WindowController cntr;
		
	public BoardPanel(WindowController cntr, Game game) {
		this.cntr = cntr;
		initGUI();
		game.addObserver(this);
	}

	public BoardPanel() {
		initGUI();
	}

	public void initGUI() {
		JPanel mainPanel = new JPanel(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		JPanel red = createSquareJPanel(Color.red, 100);
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(red, c);

		JPanel blue = createSquareJPanel(Color.blue, 70);
		c.gridx = 1;
		c.gridy = 1;
		mainPanel.add(blue, c);

		JPanel yellow = createSquareJPanel(Color.yellow, 40);
		c.gridx = 2;
		c.gridy = 2;
		mainPanel.add(yellow, c);

		JPanel green = createSquareJPanel(Color.green, 30);
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.VERTICAL;
		mainPanel.add(green, c);

		JPanel magenta = createSquareJPanel(Color.magenta, 50);
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(magenta, c);

		mainPanel.setOpaque(true);

		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BoardPanel();
			}
		});
	}

	// In this method, we create a square JPanel of a colour and set size
	// specified by the arguments.

	private JPanel createSquareJPanel(Color color, int size) {
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(color);
		tempPanel.setMinimumSize(new Dimension(size, size));
		tempPanel.setMaximumSize(new Dimension(size, size));
		tempPanel.setPreferredSize(new Dimension(size, size));
		return tempPanel;
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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void onAddObserver(Board board, Counter nextPlayer) {
		// TODO Auto-generated method stub

	}

}