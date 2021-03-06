package tp.pr5.views.window;

import tp.pr5.control.WindowController;
import tp.pr5.logic.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		c.fill = GridBagConstraints.NONE;

		//List of games of the ComboBox
		String games[] = {"CONNECT4", "COMPLICA", "GRAVITY", "REVERSI"};
		
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
						final String item = (String) gameSelection.getSelectedItem();
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								if (item.equals("GRAVITY")) {
									widthLabel.setVisible(true);
									heightLabel.setVisible(true);
									widthField.setVisible(true);
									heightField.setVisible(true);
								} else {
									widthLabel.setVisible(false);
									heightLabel.setVisible(false);
									widthField.setVisible(false);
									heightField.setVisible(false);
								}
							}
							});	
						
					}

				});
		
		//Set the listener of the Change Button
		
		changeButton.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String item = (String) gameSelection.getSelectedItem();
						switch (item) {
							case "CONNECT4":
								new Thread () {
									public void run () {
										cntr.changeGame(GameType.CONNECT4, 0, 0);
									}
								}.start();			
							break;
							case "COMPLICA":
								new Thread () {
									public void run () {
										cntr.changeGame(GameType.COMPLICA, 0, 0);
									}
								}.start();							
								break;
							case "GRAVITY":
								try {
									final int width = Integer.parseInt(widthField.getText());
									final int height = Integer.parseInt(heightField.getText());
									if (width <= 0 || height <= 0 || width > 25 || height > 25)
										throw new IllegalArgumentException();
									new Thread () {
										public void run () {
											cntr.changeGame(GameType.GRAVITY, width, height);
										}
									}.start();										
								} catch (IllegalArgumentException ex) {
									JFrame frame = new JFrame();
									JOptionPane.showMessageDialog(frame,
											"Please, enter a valid height/weight for the board",
											"Invalid dimensions",
											JOptionPane.ERROR_MESSAGE);
								}
							break;
							case "REVERSI":
								new Thread () {
									public void run () {
										cntr.changeGame(GameType.REVERSI, 0, 0);
									}
								}.start();							
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

		//We put the text closer to the text fields
		heightLabel.setHorizontalAlignment(SwingConstants.CENTER);
		heightLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		widthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		widthLabel.setVerticalAlignment(SwingConstants.BOTTOM);

		//Adds the components to the panel
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		this.add(gameSelection, c);
		c.gridx = 1;
		this.add(changeButton, c);
		c.gridx = 0;
		c.gridy = 1;
		this.add(heightLabel, c);
		c.gridx = 1;
		this.add(widthLabel, c);
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(heightField, c);
		c.gridx = 1;
		this.add(widthField, c);
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
	public void onUndoStart(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onUndoFinish(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible) {
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
