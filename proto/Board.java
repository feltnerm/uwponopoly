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
   private GamePanel deed_panel;

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
      setLayout(new GridLayout(side,side,1,1));
      
      // construct the spaces
      for( int i = 0; i < num_spaces; i++)
      {
         spaces[i] = new Space();
         spaces[i].setTitle("Space: " + i);
         spaces[i].setBoard(this);
         spaces[i].setBoardIndex(i);
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
     
      setSelectedSpace(0);
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

   void setSelectedSpace( int space )
   {
      if( space >= 0 && space < num_spaces ) // check for validity
      {
         spaces[selected_space].setSelected( false ); // turn last selected space off
         selected_space = space;
         spaces[selected_space].setSelected( true ); // turn new selection on
         if( deed_panel != null )
            deed_panel.setGameBuffer( spaces[selected_space].getDeedBuffer() );
      }
   }

   void setDeedPanel( GamePanel panel) 
   { 
      deed_panel = panel; 
      deed_panel.setGameBuffer( spaces[selected_space].getDeedBuffer() );
   }

}

