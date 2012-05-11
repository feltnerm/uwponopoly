/**  

 */
package game;

import java.util.LinkedList;
import java.util.ListIterator;
import java.io.IOException;
import java.util.Scanner;

import board.Board;
import board.Space;
import config.Config;
import dice.Dice;
import player.Player;

/**
 * Contains the logic (how to play) and game elements such as:
 * {@link board.Board} with {@link board.Space}s, a {@link LinkedList} 
 * of {@link player.Player}s, and {@link dice.Dice}.
 * <p>
 * Uses {@link config.Config} to hold game rules.
 * <p>
 * The methods in this class should be abstract and not rely on any GUI
 * elements
 **/
public class Game {
    private static boolean GUI = false;
    private static boolean DEBUG = false;

    private boolean running = false;

    // Game Elements
    private Config config;
    private Board board;
    private Dice dice;
    private Player current_player;
    private LinkedList<Player> players;
    private ListIterator<Player> players_iter;
    private Space current_space;

    // Default Game Rules
    private static int NUM_PLAYERS = 2;
    private static int STARTING_CASH = 200;
    private static int JAIL_FINE = 200;
    private static int JAIL_FEE = 50;
    private static boolean FREE_PARKING = false;
    private static int GO_AMOUNT;
    private static int INCOME_TAX_CASH;
    private static float INCOME_TAX_PERCENT;

    /**
     * Constructs a game
     *
     * @param   debug   Output debug statements if true.
     **/
    public Game(boolean debug) {
            this(debug, new Config(debug));
    }

    /** 
     * Constructs a game with a custom configuration
     *
     * @param   config A {@link config.Config}
     **/
    public Game(boolean debug, Config config) {
            Game.DEBUG = debug;
            if (Game.DEBUG) {
                    System.out.println("DEBUG: ON");
            }
            this.running = false;
            if (Game.DEBUG) { System.out.println("Game running: False"); }
            this.config = config;
            this.initRules();

            this.board = new Board(Game.DEBUG);
            this.dice = new Dice();

    }

    /**
     * Run <b>before</b> the game is started. Initalizes the starting
     * players.
     **/
    public void initGame() {
            initPlayers();
    }

    /**
     * Loads the rules from the specified configuration file.
     **/
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

    /**
     * Instantiates and sets each {@link player.Player} as active
     * and adds them to {@link this.players}
     **/
    private void initPlayers() {
            if (Game.DEBUG) {
                    System.out.println("Creating _"+this.NUM_PLAYERS+"_ Players");
            }
            this.players = new LinkedList<Player>();
            for (int p = 0; p < Game.NUM_PLAYERS; p++) {
                    Player player = new Player(p);
                    player.activate();
                    players.add(player);
            }
       
    }

    /**
     * Begin the game loop
     **/
    public void startGame() {
            // start game; loop it
            this.running = true;
            this.current_space = this.board.getSpace(0);
            this.players_iter = players.listIterator(0);
            this.current_player = players_iter.next();
            if (this.DEBUG) {
                    System.out.println("Game running: True");
            }
    }

    public void loop()
    {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine())
        {
            System.out.println("**************************************");
            System.out.println(this.current_player);
            System.out.println(this.current_space);
            System.out.println("");
            this.updateGame();
            System.out.println(this.current_space);
            String line = in.nextLine();
        }
    }

    /**
     * Called when the game is over.
     **/
    public void shutdownGame()
    {
        this.endGame();
    }


    /**
     * Updates the state of the game, looks for possible endgame
     * scenarios.
     **/
    public void updateGame() 
    {
        // RULES!
            
        // Check Massive Game Settings
        if (this.players.size() == 1) {
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

                this.dice.roll();
                System.out.println("You rolled a: "+this.dice.getTotal());
                int num_spaces = this.dice.getTotal();
                this.move(current_player, num_spaces);

                int index = this.board.position2Index(this.current_player.getPosition());
                this.current_space = this.board.getSpace(index);

                if (this.current_space.getOwner() != this.current_player)
                {
                    if (this.current_space.isSpecial())
                    {

                    } else {
                        //current_player.subtractMoney(current_space.getRent());
                        //current_space.getOwner().addMoney(current_space.getRent());
                    }
                }
            }

            if (!dice.isDoubles())
            { 
                this.nextPlayer();
            } else {
                System.out.println(" DOUBLES!");
            }

        } else {
            this.nextPlayer();
        }
    
    }

    /**
     * Move a player on the board.
     * 
     * @param p A player to move.
     * @param num_spaces   The number of spaces to move the player forward.
     */
    public void move(Player p, int num_spaces) 
    {
        int current_position = p.getPosition();
        int new_position = current_position + num_spaces;
        if (new_position >= this.board.getNumSpaces())
        {
            new_position = new_position % this.board.getNumSpaces();
        }
        System.out.println("New Position: "+new_position);
        p.setPosition(new_position);
    }

    private void nextPlayer(){
        if (!this.players_iter.hasNext())
        {
            this.current_player = this.players.getFirst();
        } else {
            this.current_player = this.players_iter.next();
        }
    }

    
    /**
     * Sets the game running state to false.
     **/
    public void endGame()
    {
        this.running = false;
    }
}
