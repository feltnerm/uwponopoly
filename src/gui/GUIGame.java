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

import javax.swing.JButton;
import javax.swing.JPanel;

import board.Board;
import config.Config;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class GUIGame implements Runnable, ActionListener {
<<<<<<< HEAD
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
    private JButton buyButton;
    private JButton sellButton;
    private JButton endTurnButton;
    private JButton upgradeButton;
    private JButton downgradeButton;
    private JButton rollButton;

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

	private void initGui() 
   {
      this.createBoard();
      this.createDice();
      this.createContextPanel();
      this.createCommandPanel();
      this.createPlayerStats();
      this.createDashboard();
      this.createWindow();

      this.guiWindow.setVisible(true);
   }

	private void createWindow() {
		this.guiWindow = new GUIWindow();
		guiWindow.getContentPane().add(guiBoard);
		guiWindow.getContentPane().add(dashboardPanel);
		guiWindow.getContentPane().add(deedPanel);
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

	private void createDashboard() 
   {
      dashboardPanel = new JPanel();
      // dashboardPanel.setLayout(new BoxLayout(dashboardPanel, BoxLayout.Y_AXIS));
      dashboardPanel.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();

      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 0;

      dashboardPanel.add(dicePanel, c);

      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 1;
      c.gridy = 0;

      dashboardPanel.add(propertyContextPanel, c);

      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridwidth = 3;
      c.ipady = 110;
      c.weightx = 0.0;
      c.gridx = 0;
      c.gridy = 1;

      dashboardPanel.add(commandPanel, c);
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
   
   private void createCommandPanel()
   {
      commandPanel = new JPanel();
      commandPanel.setLayout(null);

      buyButton = new JButton("Buy!");
      sellButton = new JButton("Sell!");
      endTurnButton = new JButton("End Turn!");
      upgradeButton = new JButton("Upgrade!");
      downgradeButton = new JButton("Downgrade!");

      buyButton.setActionCommand("B");
      buyButton.addActionListener(this);

      commandPanel.add(buyButton);
      buyButton.setBounds( 0,
             0,
             buyButton.getPreferredSize().width,
             buyButton.getPreferredSize().height);

      sellButton.setActionCommand("S");
      sellButton.addActionListener(this);

      commandPanel.add(sellButton);
      sellButton.setBounds( buyButton.getPreferredSize().width,
             0,
             sellButton.getPreferredSize().width,
             sellButton.getPreferredSize().height);

      upgradeButton.setActionCommand("U");
      upgradeButton.addActionListener(this);

      commandPanel.add(upgradeButton);
      upgradeButton.setBounds( 0,
             buyButton.getY() + buyButton.getPreferredSize().height,
             upgradeButton.getPreferredSize().width,
             upgradeButton.getPreferredSize().height);

      downgradeButton.setActionCommand("D");
      downgradeButton.addActionListener(this);

      commandPanel.add(downgradeButton);
      downgradeButton.setBounds( 0,
             upgradeButton.getY() + upgradeButton.getPreferredSize().height,
             downgradeButton.getPreferredSize().width,
             downgradeButton.getPreferredSize().height);

      endTurnButton.setActionCommand("E");
      endTurnButton.addActionListener(this);

      commandPanel.add(endTurnButton);
      endTurnButton.setBounds(0,
             downgradeButton.getY() + downgradeButton.getPreferredSize().height,
             endTurnButton.getPreferredSize().width,
             endTurnButton.getPreferredSize().height);
   }
   
	private void createPlayerStats() {

	}

	public void startGame() {
		// start the game!
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

			guiWindow.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void actionPerformed(ActionEvent e) 
   {
      String cmd = e.getActionCommand();
      if ("R".equals(cmd)) {
             System.out.println("ROLL!");
             this.guiDice.roll();
             this.game.updateGame("");
             //this.game.updateGame("");
      }
      else if ("B".equals(cmd))
      {
         System.out.println("BUY!");
         this.game.updateGame("B");
      }
      else if("S".equals(cmd))
      {
         System.out.println("SELL!");
         this.game.updateGame("S");
      }
      else if("U".equals(cmd))
      {
         System.out.println("UPGRADE!");
         this.game.updateGame("U");
      }
      else if("D".equals(cmd))
      {
         System.out.println("DOWNGRADE!");
         this.game.updateGame("D");
      }
      else if("E".equals(cmd))
      {
         System.out.println("END TURN!");
         this.game.updateGame("E");
      }
      else
      {

      }
   }
   
   public void disableButton(String button)
   {

   }
}