package tp.pr4.views.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import tp.pr4.control.WindowController;
import tp.pr4.logic.Board;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Game;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.GameType;
import tp.pr4.logic.ReadOnlyBoard;

public class ChangeGamePanel extends JPanel implements GameObserver {
	
	//Attributes
	private WindowController cntr;
	
	private JComboBox gameSelection;
	private JButton changeButton;

	//Board Settings Components
	private JLabel widthLabel;
	private JLabel heightLabel;
	private JTextField widthField;
	private JTextField heightField;
	
	public ChangeGamePanel(WindowController cntr, Game game) {
		this.cntr = cntr;
		initGUI();
		game.addObserver(this);	
	}
	
	public void initGUI() {
		this.setLayout(new GridLayout(3, 1, 20, 20));
		
		//List of games of the ComboBox
		String games[] = {"CONNECT4", "COMPLICA", "GRAVITY"};
		
		//Initializes the components
		this.gameSelection = new JComboBox <String> (games);
		this.changeButton = new JButton ("Change");
		this.widthField = new JTextField();
		this.heightField = new JTextField();
		this.widthLabel = new JLabel("Width");
		this.heightLabel = new JLabel("Height");
				
		
		
		//Set the listener of the ComboBox
		
		gameSelection.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String item = (String) gameSelection.getSelectedItem();
						if (item.equals("GRAVITY")) {
							widthLabel.setVisible(true);
							heightLabel.setVisible(true);
							widthField.setVisible(true);
							heightField.setVisible(true);
						}
						else {
							widthLabel.setVisible(false);
							heightLabel.setVisible(false);
							widthField.setVisible(false);
							heightField.setVisible(false);
						}			
					}
					
				});
		
		//Set the listener of the Change Button
		
		changeButton.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String item = (String) gameSelection.getSelectedItem();
						switch (item) {
						case "CONNECT4": cntr.changeGame(GameType.CONNECT4, 0, 0); break;
						case "COMPLICA": cntr.changeGame(GameType.COMPLICA, 0, 0); break;
						case "GRAVITY":  {
							try {
								int width = Integer.parseInt(widthField.getText());
								int height = Integer.parseInt(heightField.getText());
								if (width <= 0 || height <= 0)
									throw new IllegalArgumentException();				
								cntr.changeGame(GameType.GRAVITY, width, height);
							}
							catch (IllegalArgumentException ex) {
								JFrame frame = new JFrame();
								JOptionPane.showMessageDialog(frame,
								"Please, enter a valid height/weight for the board",
								"Invalid dimensions",
								JOptionPane.ERROR_MESSAGE);
							}
							
						}
						}				
					}
					
				});
				
		
		//Set as default the first element of the ComboBox
		gameSelection.setSelectedIndex(0);
		
		
		//Set the icons of the buttons
		this.changeButton.setIcon(new ImageIcon(MainWindow.ICON_PATH + "check.png"));
		
		//Add the border of the panel
		Border b = BorderFactory.createLineBorder(Color.black, 2);
		this.setBorder(BorderFactory.createTitledBorder(b, "Change game"));
		
		//Adds the components to the panel
		this.add(gameSelection);
		this.add(changeButton);
		this.add(heightLabel);
		this.add(widthLabel);
		this.add(heightField);
		this.add(widthField);

		
		
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
		reset(board,nextPlayer,true);

	}

}
