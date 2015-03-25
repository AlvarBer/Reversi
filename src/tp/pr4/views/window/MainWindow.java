package tp.pr4.views.window;

import tp.pr4.control.WindowController;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Game;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.ReadOnlyBoard;

import javax.swing.*;

import java.awt.*;

import tp.pr4.logic.Board;

public class MainWindow extends javax.swing.JFrame implements GameObserver {
	
	//Atributes
	private WindowController cntr;
	private Game game;


	//Constructor
	public MainWindow(Game g,WindowController c) {
		super("~[Visual Gravity]~");
		this.game = g;
		initGUI();	
		this.game.addObserver(this); 
	}
			

	private void initGUI() {
		//TODO:Set the positions of the panels before attaching them to the main panel. ex: mainPanel.add(boardPanel, BorderLayout.CENTER);
		
		//Creates the main panel and attaches it to the main frame
		JPanel mainPanel = new JPanel (new GridLayout());
		this.setContentPane(mainPanel);
		
		//Creates a board panel and attaches it to the main panel
		JPanel boardPanel = new BoardPanel(cntr,game);
		mainPanel.add(boardPanel);
		
		//Creates a panel with reset and undo buttons and attaches it to the main panel
		JPanel gamePanel = new GamePanel(cntr,game);
		mainPanel.add(gamePanel);
		
		//Creates a panel for changing the game and attaches it to the main panel
		JPanel changeGamePanel = new ChangeGamePanel(cntr,game);
		mainPanel.add(changeGamePanel);		
		
		//Creates a bar that display the turn and attaches it to the main panel
		JPanel turnBarPanel = new TurnBarPanel(cntr,game);
		mainPanel.add(turnBarPanel);
		
		//TODO:Add buttons on the main panel
		this.pack(); //Fits the main frame to the size of its panels
		this.setVisible(true);
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

	/*
	private JPanel createFixedJPanel(Color color, int size) {
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(color);
		tempPanel.setMinimumSize(new Dimension(size, size));
		tempPanel.setMaximumSize(new Dimension(size, size));
		tempPanel.setPreferredSize(new Dimension(size, size));
		return tempPanel;
	} 
	*/

    @Override
    public void onAddObserver(Board board, Counter nextPlayer) {
        
    }
}
