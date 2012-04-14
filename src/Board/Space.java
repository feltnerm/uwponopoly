package Board;

import Player.Player;

/**
 * Abstracts a Space in UWPonopoly.
 */

public class Space
{
   // defaults
   public String title;
   public String type;
   public int board_index;
   public int price;
   public String property_color;
   private boolean selected;

   // Property attributes
   private Player owner = null;
   private int state = 0;
   public int mortgage;
   public int house_cost;
   public int hotel_cost;
   public int[] rents;

   /**
    * Constructor for generic Spaces
    */
   public Space(String title, String type, int board_index)
   {
      this.title = title;
      this.type = type;
      this.board_index = board_index;
   }

    /**
    * Constructor for Property Spaces
    */
   public Space(String title, String type, int board_index,
                int price, String property_color,
                int mortgage, int house_cost, int hotel_cost,
                int[] rents)
   {
      this.title = title;
      this.type = type;
      this.property_color = property_color;
      this.board_index = board_index;
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
    public Space(String title, String type, int board_index,
                int price, String property_color, int[] rents)
    {
      this.title = title;
      this.type = type;
      this.board_index = board_index;
      this.price = price;
      this.property_color = property_color;
      this.rents = rents;
    }

   public void setSelected(boolean s)
   {
      this.selected = s;
   }

   public void buy() {

   }

}
