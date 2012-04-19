package board;

/**
 * Board: Contains and displays Spaces in a board configuration.
 * Creates the Spaces from a file.
 */
import java.util.ArrayList;
import java.util.ListIterator;

import board.JSONBoard;
import board.Space;

import player.Player;



public class Board
{
   // defaults
   private JSONBoard jsonboard = new JSONBoard();

   private static int DEFAULT_NUMBER_SPACES = 40; // standard monopoly board
   public int num_spaces;
   public ArrayList<Space> spaces;
   public ListIterator<Space> spaces_iter;

   private int selected_space; // index location of the currently selected space
   
   public Board()
   {
      this.num_spaces = DEFAULT_NUMBER_SPACES;
      this.spaces = jsonboard.getSpaces();
      this.spaces_iter = spaces.listIterator(0);
   }

   /**
    * Move a player on the board.
    * @param player A player to move.
    * @param index The position on the board to move the player to.
    */
   public void movePlayer(Player player, int index)
   {
      if (this.isValidPosition(index))
      {
         player.setPosition(index);
      }
   }

   /**
    * Basic sanity-checking on position numbers
    */
   private boolean isValidPosition(int position)
   {
      return position >= 0 && position < this.num_spaces;
   }

   /**
    * Takes a position number and maps it onto the board.
    */
   private int returnValidPosition(int position_num)
   {
      return position_num % num_spaces; // rollover
   }

   public Space getSpace(int index)
   {
      if(!isValidPosition(index))
         return null;
      return spaces.get(index);
   }

   public int getNumberOfSpaces()
   {
      return num_spaces;
   }

   public int getSelectedSpace()
   {
      return selected_space;
   }

   public void setSelectedSpace( int space )
   {
      selected_space = space;
   }

}

