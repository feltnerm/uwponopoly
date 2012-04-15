package Board;

import Player.Player;

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

   private Player owner;

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

   public int getRent()
   {
      return this.rents[this.level];
   }

   public void downgrade()
   {
      if (this.level > 0)
      {
         this.level -= 1;
      }
   }
   
   private int numHouses()
   {
      if (this.level == 6)
      {
         return 0;
      } else {
         return this.level;
      }
   }

}
