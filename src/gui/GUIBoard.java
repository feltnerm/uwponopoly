package gui;

//import GamePanel;

// Import Java Packages
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ListIterator;
import java.awt.Dimension;

// Import Game Mechanics
import board.Board;
import board.Space;
import player.Player;

// Import GUI Mechanics
import gui.GamePanel;
import gui.GUISpace;
import gui.GameBuffer;

/** 

 @author UWP_User
 */


class GUIBoard extends GamePanel implements Runnable
{
	// Game elements
	private Board board;
	private Space space;
	
	// GUI Elements
	private GUISpace guiSpace;
	private GamePanel deedPanel;
	
	private int num_spaces;
	private static int DEFAULT_WIDTH = GUISpace.WIDTH * 40;
	private static int DEFAULT_HEIGHT = GUISpace.HEIGHT * 40;
	private static int SCALED_UP_SPACE_X = GUISpace.WIDTH + 50;
	private static int SCALED_UP_SPACE_Y = GUISpace.HEIGHT + 50;
	private static Color DEFAULT_COLOR = Color.WHITE;

	// animation constants
	private static int FRAME_RATE = 15;
	private static int FRAME_SLEEP_MS = 1000 / FRAME_RATE;
	private static int ANIMATION_DELAY_MS = 150;

	// Animation variables
	private Thread animationThread;
	
	// Token-moving animation
	//private Thread move_player_thread;
	//private int final_token_space;
	//private int current_token_space;
	//private Player current_animation_player;
	
	private void drawSpaces()
	{
		// Length, in GUISpaces, of a side of the board;
		int outerside_length = (this.num_spaces/4) + 1;
		// Lenght, in GUISpaces, of the inner side
		int innerside_length_empty = outerside_length - 2;
		this.setLayout(new GridLayout(outerside_length, outerside_length, 1, 1));
		
		for (int i = 0; i < this.num_spaces; i++)
		{
			Space space = this.board.spaces.get(i);
			GUISpace guiSpace = new GUISpace(space);
			
		}
	}

	/**
	 * Testing constructor.
	 * Generates a standard board where each Space is individually numbered.
	 */
	public GUIBoard(Board board) //{{{
	{
		//super(GUISpace.WIDTH * board.getNumSpaces(), GUISpace.HEIGHT * board.getNumSpaces(), DEFAULT_COLOR);
    	//setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        super(800, 800, DEFAULT_COLOR);
    	setPreferredSize(new Dimension(800, 800));

		this.board = board;
		this.num_spaces = board.getNumSpaces();
		drawSpaces();
		//board.spaces = new Space[num_spaces];

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
		//int side = (num_spaces/4) + 1; // length, in Spaces, of a side of the board
		//int side_empty = side - 2; // length, in Spaces, of a side of the "donut hole" of the board
		//setLayout(new GridLayout(side,side,1,1));

        /*ListIterator<Space> spaces_iter = board.spaces.listIterator(0);
        while( spaces_iter.hasNext() ) // add me back in
        {
           add( new GUISpace( spaces_iter.next() ) );
        }*/
        
		// construct the spaces
        //ListIterator<Space> spaces_iter = board.spaces.listIterator(0);
		/*for( int i = 0; i < num_spaces; i++)
		{
			board.spaces[i] = new GUISpace();
			board.spaces[i].setTitle("Space: " + i);
			board.spaces[i].setBoard(this);
			board.spaces[i].setBoardIndex(i);
			for( int j = 0; j < Space.MAX_NUM_IMPROVEMENTS; j++) // fill in some random rents
				board.spaces[i].setRent( 1 + i*(j+1), j);
		}*/
        /*for( int i = 0; i < num_spaces; i++)
		{
			board.spaces[i] = new GUISpace();
			board.spaces[i].setTitle("Space: " + i);
			board.spaces[i].setBoard(this);
			board.spaces[i].setBoardIndex(i);
			for( int j = 0; j < Space.MAX_NUM_IMPROVEMENTS; j++) // fill in some random rents
				board.spaces[i].setRent( 1 + i*(j+1), j);
		}*/
		/*for( int i = 0; i < side; i++) // draw the top row
		{
			add( board.spaces[i] );
		}
		for( int i = side; i < side + side_empty; i++) // draw everything in the middle
		{
			add( board.spaces[ num_spaces - (i - side) - 1 ] );

			// fill in the gaps with empty JLabels
			for( int j = 0; j < side_empty; j++)
				add( new JLabel("") );

			// add Space to the other side
			//i++;
			add( board.spaces[i] );
		}
		for( int i = num_spaces - side_empty - 1; i >= side + side_empty;  i--) // draw bottom row
		{
			add( board.spaces[i] );
		}*/

		setSelectedSpace(0);

		animationThread = new Thread(this);
		animationThread.start();
	}//}}}

	/**
	 * run() is from the Runnable interface.
	 * animation_thread calls this constantly when it is running.
	 */
	public void run()
	{
		while( animationThread != null )
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
		for( int i = 0; i < board.getNumSpaces() - 1; i++)
		{
            GUISpace gs = new GUISpace( board.spaces.get( board.getSelectedSpace() ) );
            gs.repaint();
			/*if( board.spaces[i] != null )
				board.spaces[i].repaint();
			else
			{
				System.out.println("spaces[ " + i + "] in Board is null");
			}*/
		}

		// draw strings in middle of board for testing
		Font font = new Font("Helvetica", Font.PLAIN, 24);
		g.drawString( Integer.toString(board.getSelectedSpace()),250,250);

		// draw the blown-up version of the space that is currently highlighted
        GUISpace gs = new GUISpace( board.spaces.get( board.getSelectedSpace() ) );
		g.drawImage(gs.drawScaledUp().getBuffer() ,SCALED_UP_SPACE_X, SCALED_UP_SPACE_Y,this);

        // draw the board
        int side = (board.getNumSpaces()/4) + 1; // length, in Spaces, of a side of the board
		int side_empty = side - 2; // length, in Spaces, of a side of the "donut hole" of the board
        int space_number = 0;
		//int side_empty = side - 2; // length, in Spaces, of a side of the "donut hole" of the board
        ListIterator<Space> spaces_iter = board.spaces.listIterator(0);
        while( spaces_iter.hasNext() )
        {
           GUISpace casted_space = new GUISpace( spaces_iter.next() );
           if( casted_space != null )
           {
              GameBuffer game_buffer = casted_space.paintOnBuffer();
              if( game_buffer != null )
              {
                 //g.drawImage( casted_space.paintOnBuffer(), GUISpace.WIDTH, GUISpace.HEIGHT, g);
                 if( space_number < side )
                    g.drawImage( game_buffer.getBuffer(), space_number * GUISpace.WIDTH, 0, this);
                 else if( space_number < board.getNumSpaces() - 1 )
                 {
                    if( space_number % 2 == 0 )
                       g.drawImage( game_buffer.getBuffer(), 0, ((space_number - side)/2 + 1) * GUISpace.HEIGHT , this);
                    else
                       g.drawImage( game_buffer.getBuffer(), side * GUISpace.WIDTH, (space_number-side)/2 * GUISpace.HEIGHT , this);
                 }
                 else
                    g.drawImage( game_buffer.getBuffer(), (space_number - (board.getNumSpaces() - side) ) * GUISpace.WIDTH, side * GUISpace.HEIGHT, this);
                 space_number++;
              }
           }
        }
	}//}}}

	public void setSelectedSpace( int space )
	{
		if( space >= 0 && space < board.getNumSpaces() ) // check for validity
		{
            GUISpace gs = new GUISpace( board.spaces.get( board.getSelectedSpace() ) );
			gs.setSelected( false ); // turn last selected space off
			board.setSelectedSpace(space);
			gs.setSelected( true ); // turn new selection on
			if( deedPanel != null )
			{
               //if( board.spaces.get( board.getSelectedSpace() ) instanceof GUISpace )
                //{
				   //deedPanel.setGameBuffer( board.spaces.get(board.getSelectedSpace()).getDeedBuffer() );
				   deedPanel.setGameBuffer( gs.getDeedBuffer() );
                   deedPanel.repaint();
                //}
			}
		}
	}

	public void setDeedPanel( GamePanel panel)
	{
		deedPanel = panel;
        GUISpace gs = new GUISpace( board.spaces.get( board.getSelectedSpace() ) );
		deedPanel.setGameBuffer( gs.getDeedBuffer() );
	}

	/**
	 * WARNING! SIDE EFFECT: the player's position is set to the position of the space.
	 * This means that the position may get rolled over.
	 */
	public void addPlayerToSpace( int space, GUIPlayer player )
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
		if( space < 0 || space >= board.getNumSpaces() )
			return;
		//board.spaces[space].removePlayer();
	}

	/**
	 * Basic sanity-checking on position numbers
	 */
	public boolean isValidPosition( int position_num )
	{
		return position_num >= 0 && position_num < board.getNumSpaces();
	}

	/**
	 * Takes a position number and maps it onto the board.
	 */
	public int returnValidPosition( int position_num )
	{
		return position_num % board.getNumSpaces(); // rollover
	}

	/*public int getNumberOfSpaces()
	{
		return board.getNumSpaces();
	}*/

	public Space getSpace( int position_num )
	{
		// TODO do real error handling
		if( !isValidPosition( position_num ) )
			return null;
		return board.spaces.get(position_num);
	}

	class BoardAnimation implements Runnable
	{
		private Thread move_player_thread;
		private int final_token_space;
		private int current_token_space;
		private GUIPlayer current_animation_player;
		private boolean final_is_lesser_than_current; // used for situations when moving past the zeroth space.

		BoardAnimation( int current_token_space, int final_token_space, GUIPlayer current_animation_player )
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

					board.spaces.get(current_token_space).removePlayer( current_animation_player );

					current_token_space++;
					current_token_space = returnValidPosition( current_token_space );
					if( current_token_space == 0 )
						final_is_lesser_than_current = false;

					board.spaces.get(current_token_space).addPlayer( current_animation_player );
				}
				//if( current_token_space >= final_token_space && !final_is_lesser_than_current)
				else
				{
					board.spaces.get(final_token_space).addPlayer( current_animation_player );
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
