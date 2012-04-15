/**  

 @author UWP_User 
*/
package game;

import java.util.LinkedList;
import java.util.ListIterator;

import board.Board;
import board.Space;

import config.Config;

import dice.Dice;

import player.Player;



public class Game implements Runnable
{
   private boolean DEBUG = false;

   private Thread gamethread;
   private boolean running = false;

   private Board board;
   private Dice dice;

   private LinkedList<Player> players;
   private ListIterator<Player> players_iter;
   private Player current_player;

   private Config config;
   
   private static int SPACES = 40;
   private static int NUM_PLAYERS = 2;
   private static int STARTING_CASH = 200;
   private static int JAIL_FINE = 200;
   private static int JAIL_FEE = 50;
   private static boolean FREE_PARKING = false;
   private static int GO_AMOUNT;
   private static int INCOME_TAX_CASH;
   private static float INCOME_TAX_PERCENT;


   public Game(boolean debug)
   {
      this(debug, new Config());
   }

   public Game(boolean debug, Config config)
   {
      this.DEBUG = debug;
      this.config = config;
      this.board = new Board();
      this.dice = new Dice();
      gameInit();      
   }

   /**
    * Exposed method for Thread
    */
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

   /**
    * Initialize all parts of the game.
    */
   private void gameInit()
   {
      if (this.DEBUG) System.out.println("Initializing Game.");
      initRules();
      initPlayers();
      this.running = false;
      this.gamethread = new Thread(this);
      if (this.DEBUG) System.out.println("Game initialized.");
   }

   /**
    * Start the game (loop).
    */
   public void gameStart()
   {
      //start game; loop it
      this.running = true;
      if (this.DEBUG) System.out.println("Game started.");
      this.gamethread.start();
   }

   /**
    * End the game
    */
   private void gameShutdown()
   {
      //end game
      this.running = false;
   }

   /**
    * Update the game state
    */
   private void gameUpdate()
   {
      moveCurrentPlayer();
      
      // pass go
   }

   /**
    * Using the Config, set the game rules before starting the game.
    */
   private void initRules()
   {
      if (this.DEBUG) System.out.println("Initializing rules.");
      this.SPACES = Integer.parseInt(config.get("SPACES"));
      this.NUM_PLAYERS = Integer.parseInt(config.get("NUM_PLAYERS"));
      this.STARTING_CASH = Integer.parseInt(config.get("STARTING_CASH"));
      this.GO_AMOUNT = Integer.parseInt(config.get("GO_AMOUNT"));
      this.INCOME_TAX_CASH = Integer.parseInt(config.get("INCOME_TAX_CASH"));
      this.INCOME_TAX_PERCENT = Float.parseFloat(config.get("INCOME_TAX_PERCENT"));
      this.JAIL_FINE = Integer.parseInt(config.get("JAIL_FINE"));
      this.JAIL_FEE = Integer.parseInt(config.get("JAIL_FEE"));
      this.FREE_PARKING = Boolean.parseBoolean(config.get("FREE_PARKING"));
      if (this.DEBUG) System.out.println("Rules initialiazed.");
      if (this.DEBUG) System.out.println(config);
   }

   /** Initialize NUM_PLAYERS on the board.
    * 
    */
   private void initPlayers()
   {
      this.players = new LinkedList<Player>();
      this.players_iter = players.listIterator(0);
      for (int p = 0; p < this.NUM_PLAYERS; p++)
      {
         Player player = new Player();
         players_iter.add(player);
      }
      if (this.DEBUG) System.out.println(this.NUM_PLAYERS+" added.");
      this.current_player = players_iter.next();
   }

   /**
    * Roll dice, move player.
    */
   private void moveCurrentPlayer()
   {
      int current_index = this.current_player.getPosition();
      Space current_space = this.board.spaces.get(current_index);

      dice.roll();
      int new_position = dice.total + this.current_player.getPosition();

      if (new_position > 39)
      // Passed Go!
      {
         this.current_player.addMoney(this.GO_AMOUNT);
         new_position = this.board.getNumberOfSpaces() - new_position;
      }
      
      board.movePlayer(this.current_player, new_position);

      if (!dice.isDoubles())
      {
         this.current_player = players_iter.next();
      }
   }
   
}
