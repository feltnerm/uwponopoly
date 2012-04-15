package board;

import player.Player;

/**
 * Abstracts a Space in UWPonopoly.
 */


public abstract class Space
{
   // defaults
   private String title;
   private String type;
   private int position;

   /**
    * Constructor for generic Spaces
    */
   public Space(String title, String type, int position)
   {
      this.title = title;
      this.type = type;
      this.position = position;
   }

   public int getPosition()
   {
      return this.position;
   }

   public String getTitle()
   {
      return this.title;
   }

   public String getType()
   {
      return this.type;
   }

   public String toString()
   {
      return this.title +
            " (" + this.type +") " +
            " ["+ this.position + "] ";
   }

}
