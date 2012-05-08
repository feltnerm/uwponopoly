/**  

 @author UWP_User 
 */
package game;

import java.util.LinkedList;
import java.util.ListIterator;
import java.io.IOException;

import player.Player;
import board.Board;
import board.Space;
import config.Config;
import dice.Dice;

import gui.GameOverSplash;

public class Game {
	private static boolean GUI = false;
	private static boolean DEBUG = false;

	private boolean running = false;

	private Board board;
	private Dice dice;

	private LinkedList<Player> players;
	private ListIterator<Player> players_iter;
	private Player current_player;

	private Config config;

	private static int NUM_PLAYERS = 2;
	private static int STARTING_CASH = 200;
	private static int JAIL_FINE = 200;
	private static int JAIL_FEE = 50;
	private static boolean FREE_PARKING = false;
	private static int GO_AMOUNT;
	private static int INCOME_TAX_CASH;
	private static float INCOME_TAX_PERCENT;

	public Game(boolean debug) {
		this(debug, new Config(debug));
	}

	public Game(boolean debug, Config config) {
		Game.DEBUG = debug;
		if (Game.DEBUG) {
			System.out.println("DEBUG: ON");
		}
		this.running = false;
		this.config = config;
		this.initRules();

		this.board = new Board(Game.DEBUG);
		this.dice = new Dice();
	}

	public void initGame() {
		initPlayers();
	}

	private void initRules() 
    {
        try 
        {
           this.config.load();
           Game.NUM_PLAYERS = Integer.parseInt(config.get("NUM_PLAYERS"));
           Game.STARTING_CASH = Integer.parseInt(config.get("STARTING_CASH"));
           Game.GO_AMOUNT = Integer.parseInt(config.get("GO_AMOUNT"));
           Game.INCOME_TAX_CASH = Integer.parseInt(config.get("INCOME_TAX_CASH"));
           Game.INCOME_TAX_PERCENT = Float.parseFloat(config
                 .get("INCOME_TAX_PERCENT"));
           Game.JAIL_FINE = Integer.parseInt(config.get("JAIL_FINE"));
           Game.JAIL_FEE = Integer.parseInt(config.get("JAIL_FEE"));
           Game.FREE_PARKING = Boolean.parseBoolean(config.get("FREE_PARKING"));
           if (Game.DEBUG) {
              System.out.println("RULES LOADED...");
              this.config.print();
           }
		}
        catch (IOException e)
        { }
	}

	private void initPlayers() {
		this.players = new LinkedList<Player>();
		for (int p = 0; p < Game.NUM_PLAYERS; p++) {
			Player player = new Player(p);
			player.activate();
            players.add(player);
		}
		this.players_iter = players.listIterator(0);
		this.current_player = players_iter.next();
	}

	public void startGame() {
		// start game; loop it
		this.running = true;
	}

	public void shutdownGame()
   {
      //end game
      this.endGame();
   }

	public void updateGame() {
		// RULES!
		
        // Check Massive Game Settings
		if (this.players.size() == 1) {
			// winner!
         this.endGame();
		}

        // Check state of current player
        if (this.current_player.active())
        {   
            // Bankrupt?
            if (this.current_player.bankrupt())
            {
                this.current_player.deactivate();
            }

            if (this.current_player.jailed())
            {
                // skip player; do something
            } else {
                Space current_space = this.board.getSpace(this.current_player
                    .getPosition());

                if (current_space.getOwner() != current_player)
                {
                    current_player.subtractMoney(current_space.getRent());
                    current_space.getOwner().addMoney(current_space.getRent());
                }
            }
        }

        this.current_player = this.players_iter.next(); 
    }

        

	public void roll() {
		dice.roll();
		int new_position = dice.total + this.current_player.getPosition();
		board.movePlayer(this.current_player, new_position);

		if (!dice.isDoubles()) {
			this.current_player = players_iter.next();
		}
	}
   
   public void endGame()
   {
      this.running = false;
   }
}
