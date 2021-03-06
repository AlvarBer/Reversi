package tp.pr5.views.window;

import tp.pr5.control.WindowController;
import tp.pr5.logic.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements GameObserver {

	//Attributes
	private WindowController cntr;
		
	private JButton undoButton;
	private JButton resetButton;
	private JButton randomButton;
	private JButton quitButton;
				
		//Constructor
		public GamePanel(WindowController cntr, Game game) {
			this.cntr = cntr;
			initGUI();
			game.addObserver(this);	
		}
		
		//Methods
		public void initGUI() {
			this.setLayout(new GridBagLayout());

			GridBagConstraints c = new GridBagConstraints();

			c.insets = new Insets(10,10,10,10);
			c.fill = GridBagConstraints.NONE;

			//Initializes the buttons
			this.undoButton = new JButton ("Undo");
			this.resetButton = new JButton ("Reset");
			this.randomButton = new JButton("Random Move");
			this.quitButton = new JButton ("Quit");
			
			//Disables Undo Button at the beginning of the game
			undoButton.setEnabled(false);

			
			//Set the icons of the buttons
			this.undoButton.setIcon(new ImageIcon(MainWindow.ICON_PATH + "undo.png"));
			this.resetButton.setIcon(new ImageIcon(MainWindow.ICON_PATH + "reset.png"));
			this.randomButton.setIcon(new ImageIcon(MainWindow.ICON_PATH + "random.png"));
			this.quitButton.setIcon(new ImageIcon(MainWindow.ICON_PATH + "exit.png"));
			
			//Add the listeners to the buttons
			undoButton.addActionListener(
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							new Thread () {
								public void run () {
									cntr.undo();
								}
							}.start();	
							
							
						}			
					}
					);
			
			resetButton.addActionListener(
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							new Thread () {
								public void run () {
									cntr.reset();
								}
							}.start();
							
						}
					}
			);
			randomButton.addActionListener(
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							new Thread () {
								public void run () {
									cntr.randomMove();
								}
							}.start();
							
						}
					}
			);
			
			quitButton.addActionListener(
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							int n = JOptionPane.showOptionDialog(new JFrame(),
									"Are sure you want to quit?", "Quit",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									null, null);

							if (n == 0) {
								new Thread () {
									public void run () {
										cntr.requestQuit();
									}
								}.start();
								
							}												
							
						}
					}
			);

			//Add the border of the panel
			Border b = BorderFactory.createLineBorder(Color.black, 2);
			this.setBorder(BorderFactory.createTitledBorder(b, "Game"));
			
			//Adds the buttons to the panel
			c.gridx = 0;
			c.gridy = 0;
			this.add(undoButton, c);
			c.gridx = 1;
			this.add(resetButton, c);
			c.gridx = 0;
			c.gridy = 1;
			this.add(randomButton, c);
			c.gridx = 1;
			this.add(quitButton, c);		
		}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, final Counter player,
			final Counter nextPlayer) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if(nextPlayer.getMode() == PlayerType.HUMAN) {
					undoButton.setEnabled(true);
					randomButton.setEnabled(true);
				}					
			}
			});	
	}

	@Override
	public void moveExecStart(Counter player) {
		if(player.getMode() == PlayerType.AUTO) {
			undoButton.setEnabled(false);
			randomButton.setEnabled(false);
		}


	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				randomButton.setEnabled(false);
				undoButton.setEnabled(false);
			}
			});	
	}

	@Override
	public void onMoveError(String msg) {
		// TODO Auto-generated method stub

	}

	public void onUndoStart(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible) {
		undoButton.setEnabled(false);
	}
	
	@Override
	public void onUndoFinish(ReadOnlyBoard board, final Counter nextPlayer, final boolean undoPossible) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				undoButton.setEnabled(undoPossible);
				if (nextPlayer.getMode() == PlayerType.AUTO)
					randomButton.setEnabled(false);
			}
			});		
		

	}

	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				randomButton.setEnabled(true);
			}
			});	
	}

	@Override
	public void onAddObserver(Board board, Counter nextPlayer) {
		reset(board,nextPlayer,true);

	}

}
