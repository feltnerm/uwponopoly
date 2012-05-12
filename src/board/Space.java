package board;

import java.util.Iterator;
import java.util.LinkedList;

import player.Player;

/**
 * Abstracts a Space in UWPonopoly.
 */
public class Space {
	// defaults
	private String title;
    private String wrap_title;
	private String type;
	private int position;
	public int price;
	private String property_color;
	public static int MAX_NUM_IMPROVEMENTS = 6;

	// Property attributes
	private int mortgage;
	private int house_cost;
	private int hotel_cost;
	// Level:
	// 0 => no houses
	// 1 => 1 house
	// ..
	// 6 => 1 hotel
	private int level = 0;
	public int[] rents;

	protected Player owner = null;
	protected LinkedList<Player> players;

	// Property states
	private boolean mortaged;

	private boolean selected;

	/**
	 * Constructor for Spaces
	 */
	public Space() {
		// no-args constructor
	}

	/**
	 * public Space(String title, String type, int position, int price, String
	 * property_color, int house_cost, int hotel_cost, int mortgage, int[]
	 * rents) { this.title = title; this.type = type; this.position = position;
	 * this.property_color = property_color;
	 * 
	 * if (type == 'property') { // create a property/railroad/anything
	 * buyable/upgradeable this.price = price; this.house_cost = house_cost;
	 * this.hotel_cost = hotel_cost; this.mortgage = mortgage; this.rents =
	 * rents; }
	 * 
	 * else { // create special space
	 * 
	 * } } }
	 */

    /**
     * Checks spaces for special actions
     * @return          True if space is special.
     *
     */
    public boolean isSpecial()
    {
        if (this.getPropertyColorString().equals("special"))
            return true;
        return false;
    }


    /**
     * Upgrade this space.
     */
    public void upgrade() {
		if (this.level < 5) {
			// add house
		} else if (this.level == 5) {

		}
	 }

    /** 
     * Downgrade this space.
     */
   public void downgrade() {
      if (this.level > 0) {
         this.level -= 1;
      }
   }

    /** 
     * Removed: Space no longer tracks players.
     * Add a player to the space.
     *
     * @param    player      The player to add.
     */
	/*public void addPlayer(Player player) {
		Iterator<Player> itr = players.iterator();
		while (itr.hasNext()) {
			if (itr.next().getTokenChar() == player.getTokenChar())
				return; // player is already on space, get out of here
		}
		players.add(player);
	}*/

    /**
     * Removed: Space no longer tracks players.
     * Remove a player from the space.
     *
     * @param   player  A {@link player.Player} to remove.
     */
	/*public void removePlayer(Player player) {
		Iterator<Player> itr = players.iterator();
		while (itr.hasNext()) {
			if (itr.next().getTokenChar() == player.getTokenChar())
				itr.remove();
		}
	}*/

    /**
     * Executed when the player lands on a space.
     * @TODO: Probably not needed.
     * @param   player          The player that has landed on the space.
	public void landsOn(Player player) {
		// when the player lands on the space
	}

    /**
     * Execute a special event when the player lands on a special space.
     * @param   player          The player that is acted upon ,or that acts.
     */
	public void executeSpecialEvent(Player player) {
		/**
		 * switch(special_action) { case GO: break; case JAIL: break; case
		 * FREE_PARKING: break; case GO_JAIL: break;
		 * 
		 * }
		 */
	}

    /** Set the owner of the space.
     *
     * @param   p   The new owner.
     */
	public void setOwner(Player p) {
		this.owner = p;
	}

    /**
     * Get the color of this property
     *
     * @return  A string representing the color.
     */
	public String getColor() {
		return this.property_color;
	}

    /**
     * Returns this property's rent.
     *
     * @return  the rent fo the property. -1 if no rent.
     */
	public int getRent() 
    {
        if(rents == null )
        {
           return -1;
        }
		return this.rents[this.level];
	}

	/**
	 * Get the amount of rent for a particular improvement level
	 * 
	 * @param    improvement_level   the level of improvement the property is 
     * at, "0" is the base improvement level, "1" is one house, etc.
     *
	 * @return the amount of rent, -1 if the improvement_level passed is invalid
	 */
	public int getRentAtLevel(int improvement_level) {
		if (0 <= improvement_level
				&& improvement_level < this.rents[this.rents.length - 1])
			return rents[improvement_level];
		return -1;
	}

    /** 
     * Return the position this space occupies on the board.
     *
     * @return  The index of the Space's position on the {@link board.Board}.
     */
	public int getPosition() {
		return this.position;
	}

    /*
     *
     * @return  The number of <b>Houses</b> on the space. 
     *          Returns 0 if there are hotels.
     */
	public int getNumHouses() {
		if (this.level == 6) {
			return 0;
		} else {
			return this.level;
		}
	}

    /** 
     *
     * @return  The number of <b>Hotels</b> on the space.
     */
	public int getNumHotels() {
		if (this.level == 6) {
			return 1;
		} else {
			return 0;
		}
	}

    /**
     * @return  The title of this space.
     */
	public String getTitle() {
		return this.title;
	}

    /**
     * @return  The word-wrapped title.
     */
    public String getWrappedTitle()
    {
       if( wrap_title == null )
          return title;
       if( !wrap_title.equals("") )
          return wrap_title;
       return title;
    }

    /**
     * @return  A string representing the property color.
     */
	public String getPropertyColorString() {
		return property_color;
	}

    /**
     * @return  The current {@link player.Player} owner.
     */
	public Player getOwner() {
		return owner;
	}

    /**
     * @return  The linked list of {@link player.Player}.
     */
	public LinkedList<Player> getPlayers() {
		return players;
	}

	@Override
	public String toString() {
		return "_" + this.getTitle() + "_\n" 
                + "Color:" + getPropertyColorString() + "\n"
                + "Position:" + getPosition() + "\n" 
				+ "Owner:" + getOwner() + "\n" 
                + "Current Rent:" + getRent() + "\n" 
                + "Current Level:" + this.level + "\n"
				+ "     Houses:" + this.getNumHouses() + "\n"
                + "     Hotels:" + this.getNumHotels();

	}
    
    public boolean isSelected() 
    {
		return selected;
	}

	public void setSelected(boolean selected) 
    {
		this.selected = selected;
	}
    
    // whether or not the Space is highlighted.
    // This sould really be in GUISpace, but that would be difficult since
    // we don't keep track of GUISpaces
    private boolean highlighted;
    public boolean isHighlighted() { return highlighted; }
    public void setHighlighted( boolean high ) { highlighted = high; }

}
