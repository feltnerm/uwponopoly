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



public class Game
{
   private static boolean GUI = false;
   private static boolean DEBUG = false;

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
	  this.running = false;
      this.config = config;
      this.config.load();
      this.board = new Board();
      this.dice = new Dice(); 
   }

   public void initGame()
   {
	  initRules();
      initPlayers();
   }

   private void initRules()
   {
      /*
      this.SPACES = Integer.parseInt(config.get("SPACES"));
      this.NUM_PLAYERS = Integer.parseInt(config.get("NUM_PLAYERS"));
      this.STARTING_CASH = Integer.parseInt(config.get("STARTING_CASH"));
      this.GO_AMOUNT = Integer.parseInt(config.get("GO_AMOUNT"));
      this.INCOME_TAX_CASH = Integer.parseInt(config.get("INCOME_TAX_CASH"));
      this.INCOME_TAX_PERCENT = Float.parseFloat(config.get("INCOME_TAX_PERCENT"));
      this.JAIL_FINE = Integer.parseInt(config.get("JAIL_FINE"));
      this.JAIL_FEE = Integer.parseInt(config.get("JAIL_FEE"));
      this.FREE_PARKING = Boolean.parseBoolean(config.get("FREE_PARKING"));
      */
   }

   private void initPlayers()
   {
      this.players = new LinkedList<Player>();
      for (int p = 0; p < this.NUM_PLAYERS; p++)
      {
         Player player = new Player();
         players.add(player);
      }
      this.players_iter = players.listIterator(0);
      this.current_player = players_iter.next();
   }
   
   public void startGame()
   {
      //start game; loop it
      this.running = true;
   }

   public void shutdownGame()
   {
      //end game
      this.running = false;
   }

   public void updateGame()
   {
      // RULES!
      //update game state
      if (this.players.size() == 1)
      {
         // winner!
      }
      Space current_space = this.board.getSpace(this.current_player.getPosition());
         
      roll();

   }

   public void roll()
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
