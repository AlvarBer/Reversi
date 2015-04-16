package tp.pr4.views.window;

import tp.pr4.control.WindowController;
import tp.pr4.logic.*;

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
							cntr.undo();
							
						}			
					}
					);
			
			resetButton.addActionListener(
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							cntr.reset();
						}
					}
			);
			randomButton.addActionListener(
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							cntr.randomMove();
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
								cntr.requestQuit();
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
	public void moveExecFinished(ReadOnlyBoard board, Counter player,
			Counter nextPlayer) {
		undoButton.setEnabled(true);

	}

	@Override
	public void moveExecStart(Counter player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		undoButton.setEnabled(false);
		randomButton.setEnabled(false);

	}

	@Override
	public void onMoveError(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
		undoButton.setEnabled(undoPossible);

	}

	@Override
	public void onUndoNotPossible() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		randomButton.setEnabled(true);

	}

	@Override
	public void onAddObserver(Board board, Counter nextPlayer) {
		reset(board,nextPlayer,true);

	}

}
