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
	
	//Constants
	protected static final String ICON_PATH= "res/";
	
	//Attributes
	private WindowController cntr;
	private Game game;


	//Constructor
	public MainWindow(Game g,WindowController c) {
		super("[Visual Gravity]");
		this.cntr = c;
		this.game = g;
		initGUI();	
		this.game.addObserver(this); 
	}
			

	private void initGUI() {
		
		
		//Creates the main panel and attaches it to the main frame
		JPanel mainPanel = new JPanel (new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.setContentPane(mainPanel);
		
		this.setMinimumSize(new Dimension (800,370));
	
		//Creates a board panel and attaches it to the main panel
		JPanel boardPanel = new BoardPanel(cntr,game);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		mainPanel.add(boardPanel,c);
		
		//Creates a panel with reset and undo buttons and attaches it to the main panel
		JPanel gamePanel = new GamePanel(cntr,game);
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 1;
		mainPanel.add(gamePanel,c);
		
		//Creates a panel for changing the game and attaches it to the main panel
		JPanel changeGamePanel = new ChangeGamePanel(cntr,game);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		mainPanel.add(changeGamePanel,c);		
		
			
		//TODO:Add buttons on the main panel
		this.pack(); //Fits the main frame to the size of its panels
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Quit the game and finish the process at closing 
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


    @Override
    public void onAddObserver(Board board, Counter nextPlayer) {
    	reset(board,nextPlayer,true);
        
    }
}
