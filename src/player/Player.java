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
	protected String name;
	protected int playerNumber;
	protected char token_char; // even with a custom GameBuffer,
								// the token_char is used for equals()

	protected ArrayList<Space> properties = new ArrayList<Space>();
   
    protected int lastTurn;
    protected boolean active;
    protected boolean jailed;
   
    public Player(int playerNumber) {
		// Default money
		this(200, playerNumber);
	}

   public Player(String name)
   {
      this(200, name);
   }

   public Player(int money, int playerNumber) {
		this.money = money;
		this.position = 0;
		this.playerNumber = playerNumber;
      this.active = true;
      this.jailed = false;
      this.name = "Hello";
	}

   public Player(int money, String name)
   {
      this.money = money;
      this.position = 0;
      this.lastTurn = 0;
      this.active = true;
      this.jailed = false;
      this.name = name;
   }

   public boolean active(){
    return this.active;
   }

   public void activate(){
        this.active = true;
   }

   public void deactivate(){
        this.active = false;
   }

   public boolean jailed(){
        return this.jailed;
   }

   public String getName() { return this.name; }
   
	public int getMoney() {
		return this.money;
	}

	public void addMoney(int amount) {
		this.money += amount;
	}

	public void subtractMoney(int amount) {
		if (this.money - amount > 0) {
			this.money -= amount;
		}
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int value) {
		position = value;
	}

	public void buyProperty(Space s) {
		if (s.price <= this.money) {
			s.setOwner(this);
			this.subtractMoney(s.price);
			this.properties.add(s);
		}
	}

	public void removeProperty(Space s) {
		this.properties.remove(s);
	}

	public char getTokenChar() {
		return token_char;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Player) {
			Player p = (Player) o;
			return p.token_char == token_char;
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
		return "<Player:" + this.playerNumber + "|Name:" + this.getName()
				+ "|Wealth:" + this.getMoney() + "|Position:"
				+ this.getPosition() + "|Token:" + this.getTokenChar();
	}

   public int getLastTurn()
   {
      return lastTurn;
   }

   public boolean bankrupt()
   {
        if (this.money <= 0) {
            return true;
        }
        return false;
   }
}
