package gui;

import game.Game;

//import GUIDice;
//import GameFrame;
//import GamePanel;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JFrame;

import player.Player;
import board.Board;
import config.Config;

public class GUIGame implements Runnable
{
    private static boolean DEBUG;

    private Config config;
    private Game game;
    
    private Thread gamethread = new Thread();
    
    private EventQueue events;
    
    private String TITLE = "UWPonopoly";
    
 // Game Board
    private GameFrame window;
    private GUIBoard guiBoard;
    private GUIDice dice;

    private JMenuBar menuBar;
    private JMenu fileMenu, aboutMenu;
    private JMenuItem newMenuItem, quitMenuItem, creditsMenuItem;

    // Player Stats Panel
    private JPanel dashboard_panel;
    private JPanel dice_panel;
    private JPanel player_stats_panel;
    private JPanel property_context_panel;
    private GamePanel deed_panel;

    private JButton sell_button;
    private JButton roll_button;

    static final int TICK_LENGTH_MS = 10;

    // Height & Width of the Window
    static final int DESIRED_WIDTH = 550;
    static final int DESIRED_HEIGHT = 350;

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
    	
    	this.window.setVisible(true);
    }
    
    private void createWindow()
    {
    	this.window = new GUIWindow();
    }
    
    private void createBoard()
    {
    	this.guiBoard = new GUIBoard();
    	
    	this.window.getContentPanel.add(this.guiBoard);
    }
    
    private void createDice()
    {
    	
    }
    
    private void createDashboard()
    {
    	
    }
    
    private void createContextPanel()
    {
    	
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
