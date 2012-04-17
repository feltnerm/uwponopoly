package gui;

//import GamePanel;
import player.Player;
import board.Space;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;

/**

 @author UWP_User
*/


class GUIBoard extends GamePanel implements Runnable
{
   // defaults
   private static int DEFAULT_WIDTH = Space.SPACE_WIDTH * DEFAULT_NUMBER_SPACES;
   private static int DEFAULT_HEIGHT = Space.SPACE_HEIGHT * DEFAULT_NUMBER_SPACES;
   private static int SCALED_UP_SPACE_X = Space.SPACE_WIDTH + 50;
   private static int SCALED_UP_SPACE_Y = Space.SPACE_HEIGHT + 50;
   private static Color DEFAULT_COLOR = Color.WHITE;

   // animation constants
   private static int FRAME_RATE = 15;
   private static int FRAME_SLEEP_MS = 1000 / FRAME_RATE;
   private static int ANIMATION_DELAY_MS = 150;

   private GamePanel deed_panel;

   // Animation variables
   private Thread animation_thread;
   // Token-moving animation
   //private Thread move_player_thread;
   //private int final_token_space;
   //private int current_token_space;
   //private Player current_animation_player;

   /**
    * Testing constructor.
    * Generates a standard board where each Space is individually numbered.
    */
   public GUIBoard() //{{{
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
   }//}}}

   GUIBoard( String filename )
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
   public void paintComponent(Graphics g)//{{{
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
   }//}}}

   public void setSelectedSpace( int space )
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

   public void setDeedPanel( GamePanel panel)
   {
      deed_panel = panel;
      deed_panel.setGameBuffer( spaces[selected_space].getDeedBuffer() );
   }

   /**
    * WARNING! SIDE EFFECT: the player's position is set to the position of the space.
    * This means that the position may get rolled over.
    */
   public void addPlayerToSpace( int space, Player player )
   {
      if( space < 0 )
         return;
      space = returnValidPosition( space );
      int current_token_space = player.getPosition();
      int final_token_space = space;
      player.setPosition( space ); // SIDE EFFECT
      //spaces[current_token_space].removePlayer( player );
      //spaces[final_token_space].addPlayer( player );
      /*current_animation_player = player;
      final_token_space = space;
      move_player_thread = new Thread( this );
      move_player_thread.start();*/
      BoardAnimation b = new BoardAnimation( current_token_space, final_token_space, player);
   }

   public void removePlayerFromSpace( int space )
   {
      if( space < 0 || space >= num_spaces )
         return;
      //spaces[space].removePlayer();
   }

   /**
    * Basic sanity-checking on position numbers
    */
   public boolean isValidPosition( int position_num )
   {
      return position_num >= 0 && position_num < num_spaces;
   }

   /**
    * Takes a position number and maps it onto the board.
    */
   public int returnValidPosition( int position_num )
   {
      return position_num % num_spaces; // rollover
   }

   public int getNumberOfSpaces()
   {
      return num_spaces;
   }

   public Space getSpace( int position_num )
   {
      // TODO do real error handling
      if( !isValidPosition( position_num ) )
         return null;
      return spaces[position_num];
   }

   class BoardAnimation implements Runnable
   {
      private Thread move_player_thread;
      private int final_token_space;
      private int current_token_space;
      private Player current_animation_player;
      private boolean final_is_lesser_than_current; // used for situations when moving past the zeroth space.

      BoardAnimation( int current_token_space, int final_token_space, Player current_animation_player )
      {
         this.current_token_space = current_token_space;
         this.final_token_space = final_token_space;
         this.current_animation_player = current_animation_player;
         final_is_lesser_than_current = ( final_token_space < current_token_space );

         current_animation_player.setIsMoving( true );

         //System.out.println("Animate: " + current_token_space + " " + final_token_space);

         move_player_thread = new Thread( this );
         move_player_thread.start();
      }

      public void run()//{{{
      {
         while( move_player_thread != null)
         {
            if( current_animation_player == null )
               move_player_thread = null;

            //System.out.println("current: " + current_token_space + "  final: " + final_token_space );
            if( current_token_space < final_token_space || final_is_lesser_than_current )
            {

               spaces[current_token_space].removePlayer( current_animation_player );

               current_token_space++;
               current_token_space = returnValidPosition( current_token_space );
               if( current_token_space == 0 )
                  final_is_lesser_than_current = false;

               spaces[current_token_space].addPlayer( current_animation_player );
            }
            //if( current_token_space >= final_token_space && !final_is_lesser_than_current)
            else
            {
               spaces[final_token_space].addPlayer( current_animation_player );
               //current_animation_player = null;
               current_animation_player.setIsMoving( false );
               move_player_thread = null;
            }


            try { Thread.sleep( ANIMATION_DELAY_MS ); }
            catch ( InterruptedException e ) { /* do nothing */ }
         }
      }//}}}
   }

}
