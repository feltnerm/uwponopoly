package board;

import player.Player;
//import gui.GameBuffer;
import java.util.LinkedList;
import java.util.Iterator;

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

   protected Player owner = null;
   protected LinkedList<Player> players;

   // Property states
   private boolean mortaged;

   /**
    * Constructor for Spaces
    */
   public Space()
   {
      // no-args constructor
   }

   /**
   public Space(String title, String type, int position, int price,
      String property_color, int house_cost, int hotel_cost, int mortgage,
      int[] rents)
   {
      this.title = title;
      this.type = type;
      this.position = position;
      this.property_color = property_color;

      if (type == 'property') {
         // create a property/railroad/anything buyable/upgradeable
         this.price = price;
         this.house_cost = house_cost;
         this.hotel_cost = hotel_cost;
         this.mortgage = mortgage;
         this.rents = rents;
      }
   
      else 
      {
         // create special space

      }
      }
   }
   */

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

   public void downgrade()
   {
      if (this.level > 0)
      {
         this.level -= 1;
      }
   }

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

   public void landsOn(Player player)
   {
      //when the player lands on the space
   }

   public void executeSpecialEvent(Player player)
   {
      /**
      switch(special_action)
      {
         case GO:
            break;
         case JAIL:
            break;
         case FREE_PARKING:
            break;
         case GO_JAIL:
            break;

      }
      */
   }

   public void setOwner(Player p)
   {
      this.owner = p;
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
   
   public int getNumHouses()
   {
      if (this.level == 6)
      {
         return 0;
      } else {
         return this.level;
      }
   }
   
   public int getNumHotels()
   {
	   if (this.level == 6)
	   {
		   return 1;
	   } else {
		   return 0;
	   }
   }

   public String getTitle() { return this.title; }
   public String getPropertyColorString() { return property_color; }
   public Player getOwner() { return owner; }
   public LinkedList<Player> getPlayers() { return players; }
   
   public String toString()
   {
	   return  "_"+this.getTitle()+"_\n"+
			   "Color:"+getPropertyColorString()+"\n"+
			   "Position:"+getPosition()+"\n"+
			   "Owner:"+getOwner()+"\n"+
			   "Current Rent:"+getRent()+"\n"+
			   "Current Level:"+this.level+"\n"+
			   "	Houses:"+this.getNumHouses()+"\n"+
			   "    Hotels:"+this.getNumHotels()+"\n";
			   
   }
}
