/**  

 @author UWP_User 
*/
import java.util.LinkedList;

import Config.Config;
import Player.Player;

public class Game 
{
   private static int SPACES = 40;
   private static int NUM_PLAYERS = 2;
   private static int STARTING_CASH = 200;
   private static int JAIL_FINE = 200;
   private static int JAIL_FEE = 50;
   private static boolean FREE_PARKING = false;
   private static int GO_AMOUNT;
   private static int INCOME_TAX_CASH;
   private static float INCOME_TAX_PERCENT;

   private boolean running = false;

   private LinkedList<Player> players;

   Config config;

   public Game()
   {
      initPlayers();
   }

   public Game(Config config)
   {
      // Get configuration
      this.config = config;

      // Set defaults
      this.SPACES = Integer.parseInt(config.get("SPACES"));
      this.NUM_PLAYERS = Integer.parseInt(config.get("NUM_PLAYERS"));
      this.STARTING_CASH = Integer.parseInt(config.get("STARTING_CASH"));
      this.GO_AMOUNT = Integer.parseInt(config.get("GO_AMOUNT"));
      this.INCOME_TAX_CASH = Integer.parseInt(config.get("INCOME_TAX_CASH"));
      this.INCOME_TAX_PERCENT = Float.parseFloat(config.get("INCOME_TAX_PERCENT"));
      this.JAIL_FINE = Integer.parseInt(config.get("JAIL_FINE"));
      this.JAIL_FEE = Integer.parseInt(config.get("JAIL_FEE"));
      this.FREE_PARKING = Boolean.parseBoolean(config.get("FREE_PARKING"));

      initPlayers();
   }

   private void initPlayers()
   {
      for (int p = 0; p < this.NUM_PLAYERS; p++)
      {
         Player player = new Player();
         this.players.add(p, player);
      }
   }

   public void run() {
      if (!this.running)
      {
         // run the game!
      }
   }

   public void run_console() {

   }

   public void run_gui() {
      
   }
}
