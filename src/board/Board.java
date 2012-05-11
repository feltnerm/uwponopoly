package board;

import java.util.ArrayList;
import java.util.ListIterator;

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
     * Generate a Board (loaded from a JSON file).
     * Sets the this Board's spaces up.
     *
     * @param   debug Output debug statements if true.
     **/
    public Board(boolean debug) 
    {
        this.DEBUG = debug;
        jsonboard = new JSONBoard();
        this.spaces = jsonboard.getSpaces();
        this.spaces_iter = spaces.listIterator(0);
    }

    /**
     * Returns the space at index.
     * If index is not valid, returns null.
     *
     * @param   index   An index on the list of spaces.
     * @return          The (@link board.Space} returned at this index.
     **/
    public Space getSpace(int index) {
        if (!isValidPosition(index))
            return null;
        return this.spaces.get(index);
    }

    /** 
     * Returns the index of space
     *
     * @param   space   The space to find the index of.
     * @return          The integer index of the space.
     */
    public int getIndex(Space space)
    {
        return spaces.indexOf(space);
    }

    /**
     * Returns the total number of spaces on the board.
     *
     * @return          The number of spaces.
     **/
    public int getNumSpaces() {
        return spaces.size();
    }

    /**
     * Return the index of the currently selected space.
     *
     * @return          The currently selected space.
     **/
    public int getSelectedSpace() {
            return this.selected_space;
    }

    /**
     * Basic sanity-checking on position numbers
     *
     * @param   position  A position on the board to check.
     * @return            True if is within the bounds of the board. False
     *                    otherwise.
     */
    public boolean isValidPosition(int position) {
        return position >= 0 && position < getNumSpaces();
    }

    /**
     * Takes a position number and maps it onto the board.
     *
     * @param   position_num    A position to look up
     * @return                  0 if the position is within the board's spaces.
     *                          >0 if the position is greater than the board's
     *                          spaces.
     */
    public int getValidPosition(int position_num) {
        return position_num % getNumSpaces(); // rollover
    }

    /**
     * Converts a position to the index in the array of Spaces
     * @param   pos             The position
     * @return                  The position of the index. -1 if invalid.
     */
    public int position2Index( int pos )
    {
       ListIterator<Space> itr = spaces.listIterator();
       int position = 0;
       while(itr.hasNext())
       {
          if( itr.next().getPosition() == pos)
             return position;
          position++;
       }
       return -1;

    }


    /**
     * Sets the currently selected space to the space at the provided index.
     * 
     * @param   space   The index of the space to set as currently selected.
     **/
    public void setSelectedSpace(int space) 
    {
       if( isValidPosition( space ) )
       {
          spaces.get( selected_space ).setSelected(false);
          selected_space = space;
          spaces.get( selected_space ).setSelected(true);
       }
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
       String s = "Spaces in Board\n"+
                  "#--------------\n";
       for(int i = 0; i < spaces.size(); i++)
       {
          if( spaces.get(i) == null )
            s += i + " is null\n";
          System.out.println(i+": "+spaces.get(i));
          s += i + ": "+spaces.get(i) + "\n";
       }
       s += "";
       return s;
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
