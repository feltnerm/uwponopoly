package gui;

// Import Java packages
import game.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.util.List;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import board.Board;
import config.Config;

public class GUIGame implements Runnable, ActionListener {
	private boolean DEBUG;

	// Game Elements
	private Config config;
	private Game game;

	// Events and Threads
	static final int TICK_LENGTH_MS = 10;
	private boolean running = false;
	private Thread gamethread;
	private EventQueue events;

	// GUI Elements
	private GUIWindow guiWindow;
	private GUIConfig guiConfig;
	private GUIBoard guiBoard;
	private GUIDice guiDice;
	private GUIPlayer guiPlayer;
	private List<GUIPlayer> guiPlayers;

	// Panels
	private JPanel dashboardPanel;
	private JPanel dicePanel;
	private JPanel playerStatsPanel;
	private JPanel propertyContextPanel;
	private GamePanel deedPanel;

	// Buttons
	private JButton sellButton;
	private JButton rollButton;
	private JButton endTurnButton;
	private JButton improveButton;

    // Player Stats Labels
    private ArrayList<JLabel> player_names;
    private ArrayList<JLabel> player_cash;

	public GUIGame(boolean debug) {
		this(debug, new Config(debug));
	}

	public GUIGame(boolean debug, Config config) {
		this.DEBUG = debug;
		this.config = config;
	}

	public void initGame() {
		this.game = new Game(this.DEBUG, this.config);
		this.game.initGame();

		this.initGui();
        System.out.println("initGame()");
	}

	private void initGui() {
		this.createBoard();
		this.createDice();
		this.createContextPanel();
		this.createPlayerStats();
		this.createDashboard();
		this.createWindow();

		this.guiWindow.setVisible(true);
	}

	private void createWindow() {
		this.guiWindow = new GUIWindow();
		guiWindow.getContentPane().add(guiBoard);
		guiWindow.getContentPane().add(dashboardPanel);
		//guiWindow.getContentPane().add(deedPanel);
		guiWindow.pack();
	}

	private void createBoard() {
		this.guiBoard = new GUIBoard(new Board(this.DEBUG), this.game.getPlayers());

	}

	private void createDice() {
		this.guiDice = new GUIDice(this.game.getDice());
		dicePanel = new JPanel();
		dicePanel.setLayout(new BorderLayout());
		dicePanel.add(guiDice, BorderLayout.NORTH);
		JButton roll_button = new JButton("Roll!");
		roll_button.setPreferredSize(new Dimension(25, 50));
		roll_button.setActionCommand("R");
		roll_button.addActionListener(this);
		dicePanel.add(roll_button, BorderLayout.SOUTH);
	}

	private void createDashboard() {
		dashboardPanel = new JPanel();
		dashboardPanel
				.setLayout(new BoxLayout(dashboardPanel, BoxLayout.Y_AXIS));
		dashboardPanel.add(playerStatsPanel);
		dashboardPanel.add(deedPanel);
		dashboardPanel.add(dicePanel);
		dashboardPanel.add(propertyContextPanel);
	}

	private void createContextPanel() {
		propertyContextPanel = new JPanel();
		propertyContextPanel.setLayout(new BorderLayout());

		deedPanel = new GamePanel(500, 100, Color.WHITE);
		deedPanel.setStatic(true);
		deedPanel.setPreferredSize(new Dimension(GUISpace.DEED_WIDTH,
				GUISpace.DEED_HEIGHT));

		this.guiBoard.setDeedPanel(deedPanel);
	}

	private void createPlayerStats() 
    {
       playerStatsPanel = new JPanel();
       playerStatsPanel.setLayout(new BoxLayout(playerStatsPanel, BoxLayout.Y_AXIS));
       player_names = new ArrayList<JLabel>();
       player_cash = new ArrayList<JLabel>();
       for( int i = 0; i < game.getNumPlayers(); i++ )
       {
          player_names.add( new JLabel( "Player " + (i+1) ) );
          playerStatsPanel.add( player_names.get(i) );
          JLabel temp = new JLabel( "$" + game.getPlayers().get(i).getMoney() );
          player_cash.add( temp );
          playerStatsPanel.add( temp );
       }
	}

    // updates the player stat JLabels
    private void recalculatePlayerStats()
    {
       for( int i = 0; i < player_cash.size(); i++ )
       {
          player_cash.get(i).setText("$" + game.getPlayers().get(i).getMoney() );
          player_cash.get(i).repaint();
       }
    }

	public void startGame() {
		// start the game!
        running = true;
		this.game.startGame();
		gamethread = new Thread(this);
		gamethread.start();
	}

	@Override
	public void run() {
		Thread current = Thread.currentThread();
		long lastLoopTime = System.currentTimeMillis();

		while (this.running) {
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();

			recalculatePlayerStats();
            //playerStatsPanel.repaint();
            guiWindow.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ("R".equals(cmd)) {
			System.out.println("ROLL!");
			this.game.updateGame("");
			this.guiDice.simulateRoll();
			//this.game.updateGame("");
		} else {

		}
	}
}
