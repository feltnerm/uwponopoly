package board;

import player.Player;

/**
 * Two types of properties: Properties & Improvements.
 * The only difference is that you cannot upgrade improvements (such
 * as railroads).
 * @author mark
 *
 */
public class Property extends Space {

   private int price;
   private String color;

   private int mortgage;
   private int house_cost;
   private int hotel_cost;
   // Level:
   // 0 => no houses
   // 1 => 1 house
   // ..
   // 6 => 1 hotel
   private int level = 0;
   private int[] rents;

   private Player owner = null;
   private boolean mortgaged = false;


   public Property(String title, String type, int position,
                   int price, String color, int mortgage,
                   int house_cost, int hotel_cost, int[] rents)
   {
      super(title, type, position);
      this.price = price;
      this.color = color;
      this.mortgage = mortgage;
      this.house_cost = house_cost;
      this.rents = rents;
      
   }

   public Player getOwner()
   {
      return this.owner;
   }

   public void setOwner(Player p)
   {
      this.owner = p;
   }

   public void removeOwner()
   {
      this.owner = null;
   }

   public void upgrade()
   {
      if (this.level < 5)
      {
         // add house
         this.level += 1;
      }
      else if (this.level == 5)
      {
         // add hotel
         this.level += 1;
      }
      else {
         // upgrades not possible
      }
   }

   public void downgrade()
   {
      if (this.level == 6)
      {
         this.level -= 1;
         // remove hotel
      }
      else if (this.level > 1 && this.level < 6)
      {
         this.level -= 1;
         // remove house
      }
      else {
         // do nothing
      }
   }

   public int getRent()
   {
      return this.rents[this.level];
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

   public int numHotels()
   {
      if (this.level ==6)
      {
         return 1;
      }

      return 0;

   }

}
