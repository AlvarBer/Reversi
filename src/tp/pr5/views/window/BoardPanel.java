package tp.pr5.views.window;

import tp.pr5.logic.Counter;
import tp.pr5.logic.ReadOnlyBoard;
import tp.pr5.control.WindowController;
import tp.pr5.logic.*;

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
	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player, Counter nextPlayer) {
		int rows = board.getWidth();
		int cols = board.getHeight();
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols;++j) {
				final int auxI = i;
				final int auxJ = j;
				final Counter v = board.getPosition(i+1, j+1);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						addCounterIcon(buttons[auxI][auxJ],v);
					}
					});
		
				c.gridy = j;
				c.gridx = i;
			}
		}
		currentTurn = nextPlayer;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				turnTxt.setText(currentTurn.toString() + " turn");
				revalidate();
			}
			});		
			
	}
	
	@Override
	public void moveExecStart(Counter player) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				turnTxt.setText("Moving...");
			}
			});		
	}

	@Override
	public void onGameOver(ReadOnlyBoard board, final Counter winner) {
		int rows = board.getWidth();
		int cols = board.getHeight();
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols;++j) {
				final int auxI = i;
				final int auxJ = j;
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						buttons[auxI][auxJ].setEnabled(false);
					}
					});								
			}
		}
		active = false;
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (winner != Counter.EMPTY)
					turnTxt.setText("Game is finished. " + winner + " wins");
				else
					turnTxt.setText("Game ends in a draw");
			}
			});			
	}

	@Override
	public void onMoveError(String msg) {
		JOptionPane.showMessageDialog(this,
				"That was not a valid move. Please, try again",
				"Invalid move",
				JOptionPane.ERROR_MESSAGE);

	}

	@Override
	public void onUndoStart(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible) {
		int rows = board.getWidth();
		int cols = board.getHeight();
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols;++j) {
				final int auxI = i;
				final int auxJ = j;
				final Counter v = board.getPosition(i+1, j+1);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						addCounterIcon(buttons[auxI][auxJ],v);
					}
					});		
			
				c.gridy = j;
				c.gridx = i;
			}
		}
		currentTurn = nextPlayer;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				turnTxt.setText(currentTurn.toString() + " turn");
				revalidate();
			}
			});		
						
	}
	
	@Override
	public void onUndoFinish(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible) {
	}

	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset(final ReadOnlyBoard board, Counter player, Boolean undoPossible) {

		final int rows = board.getWidth();
		final int cols = board.getHeight();
		buttons = new JButton[rows][cols];
		counterPanel.removeAll();				
		currentTurn = player;
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols;++j) {
				final int auxI = i;
				final int auxJ = j;
				final Counter v = board.getPosition(i+1, j+1);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						buttons[auxI][auxJ] = createButton(auxI, auxJ, v);
						c.gridy = auxJ;
						c.gridx = auxI;
						counterPanel.add(buttons[auxI][auxJ], c);
					}
					});				
			}
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				turnTxt.setText(currentTurn.toString() + " turn");
				revalidate();	
			}
			});	
			
			
		active = true;
		
		
	}

	@Override
	public void onAddObserver(Board board, Counter nextPlayer) {
		reset(board,nextPlayer,true);

	}

	private JButton createButton(final int i, final int j, final Counter v) {
		final JButton x = new JButton();
		x.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (active && cntr.getPlayerMode() == PlayerType.HUMAN){
					new Thread () {
						public void run () {
							cntr.makeMove(i + 1, j + 1, currentTurn);
						}
					}.start();			
				}
			}

		});
		
			
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				addCounterIcon(x,v);
			}
			});	
		
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