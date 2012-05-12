package player;

import gui.GameBuffer;

import java.util.ArrayList;

import board.Space;

/**
 * The Player class describes one player in a game of UWPonopoly Copyright Aaron
 * Decker 2012
 */

public class Player {
	protected int money = 1500; // might be a bug, do you really want it
								// constant? --Aaron
	protected int position;
	protected int playerNumber;
	//protected char token_char; // even with a custom GameBuffer,
								// the token_char is used for equals()

	protected ArrayList<Space> properties = new ArrayList<Space>();
   
    protected int lastTurn;
    protected boolean active;
    protected boolean jailed;
   
    public Player(int playerNumber) {
		// Default money
		this(200, playerNumber);
	}

   public Player(int money, int playerNumber) {
        this.money = money;
		this.position = 0;
		this.playerNumber = playerNumber;
        this.active = true;
        this.jailed = false;
	}

    /**
     * Check if this player is active (e.g., not bankrupt or jailed.)
     * @return True if is active.
     */
    public boolean active(){
        return this.active;
    }

    /**
     * Activate the player so they can perform actions.
     */
    public void activate(){
        this.active = true;
    }

    /**
     * Deactivate the player's ability to perform actions.
     */
    public void deactivate(){
        this.active = false;
    }

    /**
     * Is this player jailed?
     */
    public boolean jailed(){
        return this.jailed;
    }

    /**
     * Set this player as jailed or not.
     * @param j     True sets to jailed.
     */
    public void setJailed(boolean j)
    {
        this.jailed = j;
    }


    /**
     * @return  True if player has <=0 wealth.
     */
    public boolean bankrupt()
    {    
        if (this.money <= 0) {
            return true;
        }
        return false;
    }

    //public String getName() { return this.name; }
   
    /**
     * Get the player's wealth
     * @return  This player's money.
     */
	public int getMoney() {
		return this.money;
	}

    /**
     * Credit player with money.
     * @param   amount  the amount to credit them with.
     */
	public void addMoney(int amount) {
		this.money += amount;
	}

    /**
     * Debit money from player's account.
     * @param   amount  The amount to subtract.
     */
	public void subtractMoney(int amount) {
		if (this.money - amount > 0) {
			this.money -= amount;
		}
	}

    /**
     * Get this player's position on the board.
     * @return  this player's positon on the board.
     */
	public int getPosition() {
		return this.position;
	}

    /**
     * Set this player's board positon.
     * @param   value   The position to set.
     */
	public void setPosition(int value) {
		position = value;
	}

    /**
     * Purchase a {@link board.Space} if the player can afford it.
     * @param   s   A space to purchase.
     */
	public void buyProperty(Space s) {
		if (s.price <= this.money) {
			s.setOwner(this);
			this.subtractMoney(s.price);
			this.properties.add(s);
		}
	}

    /**
     * Remove a property from this player's properties.
     * @param   s   The space to remove.
     */
	public void removeProperty(Space s) {
		this.properties.remove(s);
	}

    /**
     * Get this player's token as a char.
     * @return  the player's token_char.
     */
    /**
     * @DEPRECATED: Token is now an int.
	public char getTokenChar() {
		return token_char;
	}
    */

    /**
     * Get the player's number (order in the game: Player1, Player2, etc.)
     * @return  This player's number.
     */
    public int getPlayerNum(){
        return this.playerNumber;
    }

    /**
    * Get the player's number (order in the game: Player1, Player2, etc.)
    * @return  This player's number.
    */
    public int getPlayerNumber()
    {
        return this.playerNumber;
    }

    public int getLastTurn()
    {
      return lastTurn;
    } 

	@Override
	public boolean equals(Object o) {
		if (o instanceof Player) {
			Player p = (Player) o;
			return p.getPlayerNumber() == playerNumber;
		}
		return false;
	}

	/**
	 * Why introduce a GameBuffer into Player? Because each Space has a list of
	 * Players, But GUISpace needs to be able to get an image. Therefore, Player
	 * must have the methods that GUIPlayer needs. --Aaron
	 */
	public GameBuffer getToken() {
		return null;
	}

	@Override
	public String toString() {
		return "<Player:" + this.playerNumber
				+ "|Wealth:" + this.getMoney() + "|Position:"
				+ this.getPosition();
	}


}
