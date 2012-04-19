package gui;

// Import Java packages
import java.util.List;
import java.util.LinkedList;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.*;

// Import Game Mechanics
import game.Game;
import config.Config;

// Import GUI Mechanics
import gui.GUIBoard;
import gui.GUIDice;
import gui.GUIWindow;
import gui.GUIPlayer;

public class GUIGame implements Runnable
{
    private boolean DEBUG;

    // Game Elements
    private Config config;
    private Game game;
    
    // Events and Threads
    static final int TICK_LENGTH_MS = 10;
    private boolean running = false;
    private Thread gamethread = new Thread();
    private EventQueue events;
    
    // GUI Elements
    private GUIWindow guiWindow;
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
    

    public GUIGame(boolean debug)
    {
        this(debug, new Config());
    }

    public GUIGame(boolean debug, Config config)
    {
        this.DEBUG = debug;
        this.config = config;
    }
    
    public void initGame()
    {
    	this.game = new Game(this.DEBUG, this.config);
    	this.game.initGame();
    	
    	this.initGui();
    }
    
    private void initGui()
    {
    	this.createWindow();
    	this.createBoard();
    	this.createDice();
    	this.createDashboard();
    	this.createContextPanel();
    	this.createPlayerStats();
    	
    	this.guiWindow.setVisible(true);
    }
    
    private void createWindow()
    {
    	this.guiWindow = new GUIWindow();
    }
    
    private void createBoard()
    {
    	this.guiBoard = new GUIBoard();
    	
    }
    
    private void createDice()
    {
    	this.guiDice = new GUIDice();
    }
    
    private void createDashboard()
    {
    	dashboardPanel = new JPanel();
    	dashboardPanel.setLayout(new BoxLayout(dashboardPanel, BoxLayout.PAGE_AXIS));
    }
    
    private void createContextPanel()
    {
    	propertyContextPanel = new JPanel();
    	propertyContextPanel.setLayout(new BorderLayout());
    	
    	deedPanel = new GamePanel(500, 100, Color.WHITE);
    	deedPanel.setStatic(true);
    	//@TODO:
    	//deedPanel.setPreferredSize(new Dimension(this.guiSpace.WIDTH, this.guiSpace.HEIGHT));
    	
    	this.guiBoard.setDeedPanel(deedPanel);
    }
    
    private void createPlayerStats()
    {
    	
    }
    
    public void startGame()
    {
        // start the game!
    	this.game.startGame();
    }
    
    public void run()
    {
       Thread current = Thread.currentThread();
       long lastLoopTime = System.currentTimeMillis();

       while (this.running)
       {
          long delta = System.currentTimeMillis() - lastLoopTime;
          lastLoopTime = System.currentTimeMillis();

          // do stuff...
          this.gameUpdate();

          try {
             Thread.sleep(10);
          } catch (InterruptedException e) {
             e.printStackTrace();
          }
       }

    }
}
