package tp.pr4.views.window;

import tp.pr4.logic.Counter;
import tp.pr4.logic.Game;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.ReadOnlyBoard;

import javax.swing.*;
import java.awt.*;
import tp.pr4.logic.Board;

public class MainWindow extends javax.swing.JFrame implements GameObserver {

	//Constructor
	public MainWindow(Game g) {
		super("Board Games");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel boardPanel = createFixedJPanel(Color.red, 100);
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(boardPanel, c);
		JPanel gamePanel = createFixedJPanel(Color.red, 100);
		//g.getBoard().getHeight(); //TODO: Ask if this breaks encapsulation
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
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
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

	private JPanel createFixedJPanel(Color color, int size) {
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(color);
		tempPanel.setMinimumSize(new Dimension(size, size));
		tempPanel.setMaximumSize(new Dimension(size, size));
		tempPanel.setPreferredSize(new Dimension(size, size));
		return tempPanel;
	}

        @Override
        public void onAddObserver(Board board, Counter nextPlayer) {
            
        }
}
