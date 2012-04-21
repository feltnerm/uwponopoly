package board;

import player.Player;
import java.util.LinkedList;

/**
 * Abstracts a Space in UWPonopoly.
 */


public class Space
{
   // defaults
   private String title;
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

   protected Player owner;
   protected LinkedList<Player> players;

   // Property states
   private boolean mortaged;

   /**
    * Constructor for generic Spaces
    */
   public Space(String title, String type, int position)
   {
      this.title = title;
      this.type = type;
      this.position = position;
   }

    /**
    * Constructor for Property Spaces
    */
   public Space(String title, String type, int position,
                int price, String property_color,
                int mortgage, int house_cost, int hotel_cost,
                int[] rents)
   {
      this.title = title;
      this.type = type;
      this.property_color = property_color;
      this.position = position;
      this.price = price;
      this.mortgage = mortgage;
      this.house_cost = house_cost;
      this.hotel_cost = hotel_cost;
      this.rents = rents;
   }

    /**
     * Constructor for non-improvement Spaces
     * (e.g., Electric Co., Railroad, etc.)
     */
    public Space(String title, String type, int position,
                int price, String property_color, int[] rents)
    {
      this.title = title;
      this.type = type;
      this.position = position;
      this.price = price;
      this.property_color = property_color;
      this.rents = rents;
    }

   public void setOwner(Player p)
   {
      this.owner = p;
   }

   public void upgrade()
   {
      if (this.level < 5)
      {
         // add house
      }
      else if (this.level == 5)
      {

      }
   }
   
   public String getColor()
   {
	   return this.property_color;
   }

   public int getRent()
   {
      return this.rents[this.level];
   }

   /**
    * Get the amount of rent for a particular improvement level
    * @param improvement_level , the level of improvement the property is at,
    * "0" is the base improvement level, "1" is one house, etc.
    * @return the amount of rent, -1 if the improvement_level passed is invalid
    */
   public int getRentAtLevel( int improvement_level )
   {
      if( 0 <= improvement_level && improvement_level < this.rents[this.rents.length - 1] )
         return rents[improvement_level];
      return -1;
   }
   
   public int getPosition()
   {
	   return this.position;
   }
   
   public void downgrade()
   {
      if (this.level > 0)
      {
         this.level -= 1;
      }
   }
   
   public int numHouses()
   {
      if (this.level == 6)
      {
         return 0;
      } else {
         return this.level;
      }
   }

   public String getTitle() { return this.title; }
   public String getPropertyColorString() { return property_color; }

   public void addPlayer(Player player )
   {
      Iterator<Player> itr = players.iterator();
      while( itr.hasNext() )
      {
         if( itr.next().getTokenChar() == player.getTokenChar() )
            return; // player is already on space, get out of here
      }
      players.add(player);
   }

   public void removePlayer(Player player )
   {
      Iterator<Player> itr = players.iterator();
      while( itr.hasNext() )
      {
         if( itr.next().getTokenChar() == player.getTokenChar() )
            itr.remove();
      }
   }

   public Player getOwner() { return owner; }
   public LinkedList<Players> getPlayers() { return players; }
}
