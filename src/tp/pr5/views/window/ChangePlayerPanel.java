package tp.pr5.views.window;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

import tp.pr5.control.WindowController;
import tp.pr5.logic.Board;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Game;
import tp.pr5.logic.GameObserver;
import tp.pr5.logic.ReadOnlyBoard;

public class ChangePlayerPanel extends JPanel implements GameObserver {
	
	//Attributes
	private WindowController cntr;
	
	private JComboBox gameSelection;
	private JButton changeButton;

	//Board Settings Components
	private JLabel whitePlayerLabel;
	private JLabel blackPlayerLabel;
	private JSpinner whitePlayerSpinner;
	private JSpinner blackPlayerSpinner;
	
	public ChangePlayerPanel(WindowController cntr, Game game) {
		this.cntr = cntr;
		initGUI();
		game.addObserver(this);	
	}
	
	public void initGUI() {
		
		//List of player types of the Spinner
		String playerTypes[] = {"Human", "Automatic"};
		
		//Initializes the components
		this.blackPlayerLabel = new JLabel("BLACK player:");
		this.whitePlayerLabel = new JLabel("WHITE player:");
		SpinnerListModel playerTypeModel = new SpinnerListModel(playerTypes);
		this.whitePlayerSpinner = new JSpinner (playerTypeModel);
		this.blackPlayerSpinner = new JSpinner (playerTypeModel);
	
	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player,
			Counter nextPlayer) {
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
		// TODO Auto-generated method stub

	}

}
