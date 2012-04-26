package gui;

// Import Java packages
import java.util.List;
import java.util.LinkedList;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

// Import Game Mechanics
import game.Game;
import config.Config;

// Import GUI Mechanics
import gui.GUIBoard;
import gui.GUIDice;
import gui.GUIWindow;
import gui.GUIPlayer;

import board.Board; // needed to construct GUIBoard

public class GUIGame implements Runnable
{
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
    	this.createBoard();
    	this.createDice();
    	this.createContextPanel();
    	this.createPlayerStats();
    	this.createDashboard();
    	this.createWindow();
    	
    	this.guiWindow.setVisible(true);
    }
    
    private void createWindow()
    {
    	this.guiWindow = new GUIWindow();
        guiWindow.getContentPane().add( guiBoard );
        guiWindow.getContentPane().add( dashboardPanel );
        guiWindow.pack();
    }
    
    private void createBoard()
    {
    	this.guiBoard = new GUIBoard( new Board() );
    	
    }
    
    private void createDice()
    {
    	this.guiDice = new GUIDice();
        dicePanel = new JPanel();
        dicePanel.setLayout( new BorderLayout() );
        dicePanel.add( guiDice, BorderLayout.NORTH );
        JButton roll_button = new JButton("Roll!");
        roll_button.setPreferredSize(new Dimension(25,50));
        /*roll_button.addActionListener // make the roll button roll the dice
           (   new ActionListener()
               {
               @Override
               public void actionPerformed(ActionEvent e)
               {
               doRoll();
               }
               }
           );*/
        dicePanel.add(roll_button, BorderLayout.SOUTH);
    }
    
    private void createDashboard()
    {
    	dashboardPanel = new JPanel();
    	dashboardPanel.setLayout(new BoxLayout(dashboardPanel, BoxLayout.PAGE_AXIS));
        dashboardPanel.add( propertyContextPanel );
        dashboardPanel.add( dicePanel );
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
        gamethread = new Thread( this );
        gamethread.start();
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
          game.updateGame();

          guiWindow.repaint();
          try {
             Thread.sleep(10);
          } catch (InterruptedException e) {
             e.printStackTrace();
          }
       }

    }
}
