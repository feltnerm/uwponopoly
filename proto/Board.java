/**
 * Board: Contains and displays Spaces in a board configuration.
 * Creates the Spaces from a file.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;

class Board extends GamePanel implements Runnable
{
   // defaults
   private static int DEFAULT_NUMBER_SPACES = 40; // standard monopoly board
   private static int DEFAULT_WIDTH = Space.SPACE_WIDTH * DEFAULT_NUMBER_SPACES;
   private static int DEFAULT_HEIGHT = Space.SPACE_HEIGHT * DEFAULT_NUMBER_SPACES;
   private static int SCALED_UP_SPACE_X = Space.SPACE_WIDTH + 50;
   private static int SCALED_UP_SPACE_Y = Space.SPACE_HEIGHT + 50;
   private static Color DEFAULT_COLOR = Color.WHITE;
   //private static int DEFAULT_NUMBER_SPACES = 40; // standard monopoly board

   // animation constants
   private static int FRAME_RATE = 15;
   private static int FRAME_SLEEP_MS = 1000 / FRAME_RATE;

   private int num_spaces;
   private Space[] spaces;

   private int selected_space; // index location of the currently selected space
   private GamePanel deed_panel;

   private Thread animation_thread;

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
         for( int j = 0; j < Space.MAX_NUM_IMPROVEMENTS; j++) // fill in some random rents
            spaces[i].setRent( 1 + i*(j+1), j);
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

      animation_thread = new Thread(this);
      animation_thread.start();
   }

   Board( String filename )
   {
      super( DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR );
   }

   /**
    * run() is from the Runnable interface.
    * animation_thread calls this constantly when it is running.
    */
   public void run()
   {
      while( animation_thread != null )
      {
         repaint();

         try { Thread.sleep(FRAME_SLEEP_MS); }
         catch ( InterruptedException e ) { /* do nothing */ }
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

      // draw the blown-up version of the space that is currently highlighted
      g.drawImage(spaces[selected_space].drawScaledUp().getBuffer() ,SCALED_UP_SPACE_X, SCALED_UP_SPACE_Y,this);
   }

   void setSelectedSpace( int space )
   {
      if( space >= 0 && space < num_spaces ) // check for validity
      {
         spaces[selected_space].setSelected( false ); // turn last selected space off
         selected_space = space;
         spaces[selected_space].setSelected( true ); // turn new selection on
         if( deed_panel != null )
         {
            deed_panel.setGameBuffer( spaces[selected_space].getDeedBuffer() );
            deed_panel.repaint();
         }
      }
   }

   void setDeedPanel( GamePanel panel) 
   { 
      deed_panel = panel; 
      deed_panel.setGameBuffer( spaces[selected_space].getDeedBuffer() );
   }

}

