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
   private static boolean GUI = false;
   private static boolean DEBUG = false;

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
      this(gui, new Config());
   }

   public Game(boolean debug, Config config)
   {
      this.GUI = gui;
      this.config = config;
      this.board = new Board();
      this.dice = new Dice();
      gameInit();      
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

         if (GUI)
         {
            //
         }

         try {
            Thread.sleep(10);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }

   }

   private void gameInit()
   {
      initRules();
      initPlayers();
      this.running = false;
      this.gamethread = new Thread(this);
   }

   public void gameStart()
   {
      //start game; loop it
      this.running = true;
      this.gamethread.start();
   }

   private void gameShutdown()
   {
      //end game
      this.running = false;
      this.gamethread.stop();
   }

   private void gameUpdate()
   {
      // RULES!
      //update game state
      if (this.players.size() == 1)
      {
         // winner!
      }

      else {
         handlSpace(this.board.getSpace(this.current_player.position));
      }

   }
   
   private void initRules()
   {
      this.SPACES = Integer.parseInt(config.get("SPACES"));
      this.NUM_PLAYERS = Integer.parseInt(config.get("NUM_PLAYERS"));
      this.STARTING_CASH = Integer.parseInt(config.get("STARTING_CASH"));
      this.GO_AMOUNT = Integer.parseInt(config.get("GO_AMOUNT"));
      this.INCOME_TAX_CASH = Integer.parseInt(config.get("INCOME_TAX_CASH"));
      this.INCOME_TAX_PERCENT = Float.parseFloat(config.get("INCOME_TAX_PERCENT"));
      this.JAIL_FINE = Integer.parseInt(config.get("JAIL_FINE"));
      this.JAIL_FEE = Integer.parseInt(config.get("JAIL_FEE"));
      this.FREE_PARKING = Boolean.parseBoolean(config.get("FREE_PARKING"));
   }

   private void initPlayers()
   {
      this.players = new LinkedList<Player>();
      this.players_iter = players.listIterator(0);
      for (int p = 0; p < this.NUM_PLAYERS; p++)
      {
         Player player = new Player();
         players_iter.add(player);
      }
      this.current_player = players_iter.next();
   }

   private void roll()
   {
      dice.roll();
      int new_position = dice.total + this.current_player.getPosition();
      board.movePlayer(this.current_player, new_position);

      if (!dice.isDoubles())
      {
         this.current_player = players_iter.next();
      }
   }
   
}
