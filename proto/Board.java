/**
 * Board: Contains and displays Spaces in a board configuration.
 * Creates the Spaces from a file.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JLabel;

class Board extends GamePanel
{
   // defaults
   private static int DEFAULT_WIDTH = 500;
   private static int DEFAULT_HEIGHT = 500;
   private static Color DEFAULT_COLOR = Color.WHITE;
   //private static int DEFAULT_NUMBER_SPACES = 40; // standard monopoly board
   private static int DEFAULT_NUMBER_SPACES = 8; // standard monopoly board

   private int num_spaces;
   private Space[] spaces;

   /**
    * Testing constructor.
    * Generates a standard board where each Space is individually numbered.
    */
   Board()
   {
      super( DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR );
      setLayout(new GridLayout(3,3));
      num_spaces = DEFAULT_NUMBER_SPACES;
      spaces = new Space[num_spaces];
      for(int i = 0; i < DEFAULT_NUMBER_SPACES; i++)
      {
         if( i == 4)
         {
            add( new JLabel("")  );// empty cell
         }
         spaces[i] = new Space();
         spaces[i].setTitle("Property " + i);
         add( spaces[i] );
      }
   }

   Board( String filename )
   {
      super( DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR );
   }
   
   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      for( int i = 0; i < num_spaces - 1; i++)
      {
         if( spaces[i] != null )
            spaces[i].repaint();
         else
         {
            System.out.println("spaces[ " + i + "] in Board is null");
         }
      }
   }

}

