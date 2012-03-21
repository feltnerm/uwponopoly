/**
 * Board: Contains and displays Spaces in a board configuration.
 * Creates the Spaces from a file.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;

class Board extends GamePanel
{
   // defaults
   private static int DEFAULT_NUMBER_SPACES = 40; // standard monopoly board
   private static int DEFAULT_WIDTH = Space.SPACE_WIDTH * DEFAULT_NUMBER_SPACES;
   private static int DEFAULT_HEIGHT = Space.SPACE_HEIGHT * DEFAULT_NUMBER_SPACES;
   private static Color DEFAULT_COLOR = Color.WHITE;
   //private static int DEFAULT_NUMBER_SPACES = 40; // standard monopoly board

   private int num_spaces;
   private Space[] spaces;

   private int selected_space; // index location of the currently selected space

   /**
    * Testing constructor.
    * Generates a standard board where each Space is individually numbered.
    */
   Board()
   {
      super( DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR );
      num_spaces = DEFAULT_NUMBER_SPACES;
      spaces = new Space[num_spaces];

      /*for(int i = 0; i < DEFAULT_NUMBER_SPACES; i++)
      {
         if( i == 4)
         {
            add( new JLabel("")  );// empty cell
         }
         spaces[i] = new Space();
         spaces[i].setTitle("Property " + i);
         add( spaces[i] );
      }*/
      int side = (num_spaces/4) + 1; // length, in Spaces, of a side of the board
      int side_empty = side - 2; // length, in Spaces, of a side of the "donut hole" of the board
      setLayout(new GridLayout(side,side));
      
      // construct the spaces
      for( int i = 0; i < num_spaces; i++)
      {
         spaces[i] = new Space();
         spaces[i].setTitle("Property " + i);
      }
      for( int i = 0; i < side; i++) // draw the top row
      {
         add( spaces[i] );
      }
      for( int i = side; i < side + side_empty; i++) // draw everything in the middle
      {
         add( spaces[ num_spaces - (i - side) - 1 ] );

         // fill in the gaps with empty JLabels
         for( int j = 0; j < side_empty; j++)
            add( new JLabel("") );

         // add Space to the other side
         //i++;
         add( spaces[i] );
      }
      for( int i = num_spaces - side_empty - 1; i >= side + side_empty;  i--) // draw bottom row
      {
         add( spaces[i] );
      }

   }

   Board( String filename )
   {
      super( DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR );
   }

   /**
    * Takes the milliseconds since the last time it was called as a parameter.
    * Useful for animation, things that need updated, etc.
    */
   public void tick( int milliseconds ) // like a run() function for board
   {
      // update which space is selected
      //setSeletedSpace(-1); // clear the selected space
      for( int i = 0; i < num_spaces; i++)
      {
         // new selection?
         if( spaces[i].isSelected() && selected_space != i )
            setSeletedSpace(i);
      }
      for( int i = 0; i < num_spaces; i++)
      {
         if( i != selected_space )
            spaces[i].setSelected( false );
      }
   }
   
   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      //draw spaces
      for( int i = 0; i < num_spaces - 1; i++)
      {
         if( spaces[i] != null )
            spaces[i].repaint();
         else
         {
            System.out.println("spaces[ " + i + "] in Board is null");
         }
      }



      // draw strings in middle of board for testing
      Font font = new Font("Helvetica", Font.PLAIN, 24);
      g.drawString( Integer.toString(selected_space),250,250);
   }

   void setSeletedSpace( int space )
   {
      selected_space = space;
   }

}

