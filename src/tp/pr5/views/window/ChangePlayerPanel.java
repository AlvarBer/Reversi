package tp.pr5.views.window;

import tp.pr5.control.WindowController;
import tp.pr5.logic.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListDataListener;
import java.awt.*;

public class ChangePlayerPanel extends JPanel implements GameObserver {
	
	//Attributes
	private WindowController cntr;
	
	private JButton changeButton;

	//Board Settings Components
	private JLabel whitePlayerLabel;
	private JLabel blackPlayerLabel;
	private JComboBox whitePlayerList;
	private JComboBox blackPlayerList;

	
	
	public ChangePlayerPanel(WindowController cntr, Game game) {
		this.cntr = cntr;
		initGUI();
		game.addObserver(this);	
	}
	
	public void initGUI() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		c.fill = GridBagConstraints.NONE;
		
		//List of player types of the Spinner
		String playerTypes[] = {"Human", "Automatic"};
		
		//Initializes the components
		this.blackPlayerLabel = new JLabel("BLACK player:");
		this.whitePlayerLabel = new JLabel("WHITE player:");

		whitePlayerList = new JComboBox<PlayerType>(new PlayersModel(Counter.WHITE));
		blackPlayerList = new JComboBox<PlayerType>(new PlayersModel(Counter.BLACK));
		
		//Add the border of the panel
		Border b = BorderFactory.createLineBorder(Color.black, 2);
		this.setBorder(BorderFactory.createTitledBorder(b, "Select player type"));
		
		//Adds the components to the panel
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(blackPlayerLabel, c);
		c.gridy = 1;
		this.add(whitePlayerLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(blackPlayerList, c);
		c.gridy = 1;
		this.add(whitePlayerList, c);
	
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
	
	private class PlayersModel implements ComboBoxModel<PlayerType> {
		
		//Attributes
		private Counter player;
		private PlayerType selected;

		public PlayersModel(Counter player) { 
			this.player = player; 
			this.selected = player.getMode(); 
		}
		
		@Override
		public int getSize() {
			return 2;
		}

		@Override
		public PlayerType getElementAt(int index) {
			if (index == 0)
				return PlayerType.HUMAN;
			else
				return PlayerType.AUTO;
		}

		@Override
		public void addListDataListener(ListDataListener l) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removeListDataListener(ListDataListener l) {
			// TODO Auto-generated method stub

		}

		@Override
		public void setSelectedItem(Object anItem) {
			this.selected = (PlayerType) anItem; 
			cntr.setPlayerMode(player, this.selected);
		}

		@Override
		public PlayerType getSelectedItem() {
			return this.selected;
		}
	}
}
