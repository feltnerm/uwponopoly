package Board;

/**
 * Board: Contains and displays Spaces in a board configuration.
 * Creates the Spaces from a file.
 */
import java.util.ArrayList;

import Board.JSONBoard;
import Board.Space;
import Player.Player;


class Board
{
   // defaults
   private JSONBoard jsonboard = new JSONBoard();

   private static int DEFAULT_NUMBER_SPACES = 40; // standard monopoly board
   public int num_spaces;
   private ArrayList<Space> spaces;

   private int selected_space; // index location of the currently selected space
   
   public Board()
   {
      this.num_spaces = DEFAULT_NUMBER_SPACES;
      this.spaces = jsonboard.getSpaces();
   }

   /**
    * Basic sanity-checking on position numbers
    */
   public boolean isValidPosition(int position)
   {
      return position >= 0 && position < this.num_spaces;
   }

   /**
    * Takes a position number and maps it onto the board.
    */
   public int returnValidPosition(int position_num)
   {
      return position_num % num_spaces; // rollover
   }

   public Space getSpace(int index )
   {
      if(!isValidPosition(index))
         return null;
      return spaces.get(index);
   }

   public int getNumberOfSpaces()
   {
      return num_spaces;
   }

}

