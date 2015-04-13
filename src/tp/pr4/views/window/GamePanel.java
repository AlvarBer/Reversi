package tp.pr4.views.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import tp.pr4.control.WindowController;
import tp.pr4.logic.Board;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Game;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.ReadOnlyBoard;

public class GamePanel extends JPanel implements GameObserver {

		//Attributes
		private WindowController cntr;
		
		private JButton undoButton;
		private JButton resetButton;
				
		//Constructor
		public GamePanel(WindowController cntr, Game game) {
			this.cntr = cntr;
			initGUI();
			game.addObserver(this);	
		}
		
		//Methods
		public void initGUI() {
			this.setLayout(new BorderLayout(5,5));
			
			//Initializes the buttons
			this.undoButton = new JButton ("Undo");
			this.resetButton = new JButton ("Reset");
			
			//Disables Undo Button at the beginning of the game
			undoButton.setEnabled(false);
			
			//Set the icons of the buttons
			this.undoButton.setIcon(new ImageIcon(MainWindow.ICON_PATH + "undo.png"));
			this.resetButton.setIcon(new ImageIcon(MainWindow.ICON_PATH + "reset.png"));
			
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
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							cntr.reset();						
						}			
					}
					);
			
			//Add the border of the panel
			Border b = BorderFactory.createLineBorder(Color.black, 2);
			this.setBorder(BorderFactory.createTitledBorder(b, "Game"));
			
			//Adds the buttons to the panel
			this.add(undoButton, BorderLayout.WEST);
			this.add(resetButton, BorderLayout.EAST);
			
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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void onAddObserver(Board board, Counter nextPlayer) {
		reset(board,nextPlayer,true);

	}

}
