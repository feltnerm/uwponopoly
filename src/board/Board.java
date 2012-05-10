package board;

import java.util.ArrayList;
import java.util.ListIterator;

import player.Player;

/**
 * Board: Contains and displays Spaces in a board configuration.
 * Creates the Spaces from a file.
 */
public class Board {
	
    // defaults
    private boolean DEBUG;
    //private static int NUM_SPACES = 40; // standard monopoly board
    
    private JSONBoard jsonboard; 

    public ArrayList<Space> spaces;
    public ListIterator<Space> spaces_iter;

    private int selected_space; // index location of the currently selected
                                                            // space

    /**
     * Generate a Board (loaded from a JSON file)
     *
     * @param   debug Output debug statements if true.
     **/
    public Board(boolean debug) 
    {
        this.DEBUG = debug;
        jsonboard = new JSONBoard();
        this.spaces = jsonboard.getSpaces();

        // this.spaces_iter = spaces.listIterator(0);
    }

    /**
     * Move a player on the board.
     * 
     * @param   player  A player to move.
     * @param   index   The position on the board to move the player to.
     */
    public void movePlayer(Player player, int index) 
    {
        if (this.isValidPosition(index)) {
            player.setPosition(index);
            if (this.DEBUG) {
                System.out.println("MOVED PLAYER:" + player + "TO SPACE:"
                            + index);
            }
        }
    }

    /**
     * Basic sanity-checking on position numbers
     */
    private boolean isValidPosition(int position) {
        return position >= 0 && position < getNumSpaces();
    }

    /**
     * Takes a position number and maps it onto the board.
     *
     * @param   position_num    A position to look up
     */
    private int returnValidPosition(int position_num) {
        return position_num % getNumSpaces(); // rollover
    }

    /**
     * Returns the space at index.
     *
     * @param   index   The index of the {@link Space}
     **/
    public Space getSpace(int index) {
        if (!isValidPosition(index))
            return null;
        return this.spaces.get(index);
    }

    /**
     * Returns the total number of spaces on the board.
     **/
    public int getNumSpaces() {
        return spaces.size();
    }

    /**
     * Return the index of the currently selected space.
     **/
    public int getSelectedSpace() {
            return this.selected_space;
    }

    /**
     * Sets the currently selected space to the space at the provided index.
     * 
     * @param   space   The index of the space to set as currently selected.
     **/
    public void setSelectedSpace(int space) {
            selected_space = space;
    }

    private void printSpaces() 
    {
       System.out.println("Spaces in Board");
       System.out.println("#--------------");
       for(int i = 0; i < spaces.size(); i++)
       {
          if( spaces.get(i) == null )
             System.out.println(i+" is null");
          System.out.println(i+": "+spaces.get(i));
       }
       System.out.println("");
    }

    @Override
    public String toString() 
    {
            return ""
            + "Number of Spaces:     " + spaces.size() + "\n"
            + "Selected Space:       " + getSelectedSpace();
    }

    /**
     * Testbed main
     */
    public static void main(String[] args)
    {
       System.out.println("Testing Board");
       System.out.println("=============");
       Board b = new Board( true );
       System.out.println( b.toString() );
       System.out.println("Spaces by index, not order");
       for( int i = 0; i < b.spaces.size(); i++)
       {
          System.out.println(i + ": " + b.spaces.get(i).getTitle() );
       }
       //System.out.println("All tests passed.");
    }

}
