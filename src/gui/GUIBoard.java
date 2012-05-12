package gui;

//import GamePanel;

// Import Java Packages
import java.util.LinkedList;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ListIterator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import board.Board;
import board.Space;
import player.Player;

/**
 * @author UWP_User
 */

class GUIBoard extends GamePanel implements Runnable {
	// Game elements
	private Board board;
	private Space space;

	// GUI Elements
	private GUISpace guiSpace;
	private GamePanel deedPanel;
    private LinkedList<GUIPlayer> guiPlayers;

	private int num_spaces;
	private static int DEFAULT_WIDTH = GUISpace.WIDTH * 40;
	private static int DEFAULT_HEIGHT = GUISpace.HEIGHT * 40;
	private static int SCALED_UP_SPACE_X = GUISpace.WIDTH + 50;
	private static int SCALED_UP_SPACE_Y = GUISpace.HEIGHT + 50;
    private static int TOKEN_PADDING = 8;
	private static Color DEFAULT_COLOR = Color.WHITE;


	// animation constants
	private static int FRAME_RATE = 15;
	private static int FRAME_SLEEP_MS = 1000 / FRAME_RATE;
	private static int ANIMATION_DELAY_MS = 150;

	// Animation variables
	private Thread animationThread;

    private int highlighted_space;	

	/*private void drawSpaces() {
		// Length, in GUISpaces, of a side of the board;
		int outerside_length = (this.num_spaces / 4) + 1;
		// Lenght, in GUISpaces, of the inner side
		int innerside_length_empty = outerside_length - 2;
		this.setLayout(new GridLayout(outerside_length, outerside_length, 1, 1));

		for (int i = 0; i < this.num_spaces; i++) {
			Space space = this.board.spaces.get(i);
			GUISpace guiSpace = new GUISpace(space);

		}
	}*/

	/**
	 * Testing constructor. Generates a standard board where each Space is
	 * individually numbered.
	 */
	public GUIBoard(Board board, LinkedList<Player> players) // {{{
	{
		// super(GUISpace.WIDTH * board.getNumSpaces(), GUISpace.HEIGHT *
		// board.getNumSpaces(), DEFAULT_COLOR);
		// setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		super(11*GUISpace.WIDTH, 11*GUISpace.HEIGHT, DEFAULT_COLOR); //sensible default, 11 spaces on side of standard board
        System.out.println("Constructing GUIBoard");
        if( board == null )
           System.out.println("Board passed to GUIBoard is null.");
        if( board.spaces == null )
           System.out.println("Spaces in Board is null.");

		this.board = board;

        this.guiPlayers = new LinkedList();
        for (Player p : players)
        {
            GUIPlayer gp = new GUIPlayer(p);
            this.guiPlayers.add(gp);
        }

		this.num_spaces = board.getNumSpaces();
		setPreferredSize(new Dimension((num_spaces/4 + 1) * GUISpace.WIDTH, (num_spaces/4 + 1) * GUISpace.HEIGHT));
		//drawSpaces();
		// board.spaces = new Space[num_spaces];

		setSelectedSpace(0);
        setHighlightedSpace( highlighted_space );

		animationThread = new Thread(this);
		animationThread.start();
	}// }}}

	/**
	 * run() is from the Runnable interface. animation_thread calls this
	 * constantly when it is running.
	 */
	@Override
	public void run() {
		while (animationThread != null)
        {
			repaint();

			try {
				Thread.sleep(FRAME_SLEEP_MS);
			} catch (InterruptedException e) { /* do nothing */
			}
		}
	}

	@Override
	public void paintComponent(Graphics g)// {{{
	{
		super.paintComponent(g);
		// draw spaces
		/*for (int i = 0; i < board.getNumSpaces(); i++) 
        {
			GUISpace gs = new GUISpace(board.spaces.get(board
					.getSelectedSpace()));
			gs.repaint();
            
			/*
			 * if( board.spaces[i] != null ) board.spaces[i].repaint(); else {
			 * System.out.println("spaces[ " + i + "] in Board is null"); }
			 */
		//}

		// draw strings in middle of board for testing
		Font font = new Font("Helvetica", Font.PLAIN, 24);
		g.drawString(Integer.toString(board.getSelectedSpace()), 250, 250);

		// draw the blown-up version of the space that is currently highlighted
		GUISpace gs = new GUISpace(board.spaces.get(board.getSelectedSpace()));
		g.drawImage(gs.drawScaledUp().getBuffer(), SCALED_UP_SPACE_X,
				SCALED_UP_SPACE_Y, this);

		// draw the board
		int side = (board.getNumSpaces() / 4) + 1; // length, in Spaces, of a
													// side of the board
		// System.out.println("Side: " + side);
		int side_empty = side - 2; // length, in Spaces, of a side of the
									// "donut hole" of the board
		//int space_number = 0;
		// int side_empty = side - 2; // length, in Spaces, of a side of the
		// "donut hole" of the board
		ListIterator<Space> spaces_iter = board.spaces.listIterator(0);
		while (spaces_iter.hasNext()) {
			GUISpace casted_space = new GUISpace(spaces_iter.next());
            casted_space.repaint();
			if (casted_space != null) {
				GameBuffer game_buffer = casted_space.paintOnBuffer();
				if (game_buffer != null) 
                {
                   Point p = getCoordinates( casted_space.getSpace().getPosition() );
                   g.drawImage(game_buffer.getBuffer(), (int)p.getX(), (int)p.getY(), this);
                   //space_number++;
                }
			}
		}

        // Draw Players
        ListIterator<GUIPlayer> guiPlayers_iter = this.guiPlayers.listIterator(0);
        ArrayList<Point> used_positions = new ArrayList<Point>();
        while (guiPlayers_iter.hasNext()) 
        {
            GUIPlayer p = guiPlayers_iter.next();
            Point coordinates = getCoordinates(p.getPosition());
            if( !used_positions.contains(coordinates) )
            {
               used_positions.add( coordinates );
               g.drawImage(p.getToken().getBuffer(), (int)coordinates.getX(), 
                (int)coordinates.getY(), this);

            }

        }
	}// }}}

    /**
     * Takes an index and maps the Space at that index onto the board coordinates
     * @author Aaron Decker
     */
    private Point getCoordinates( int index )
    {
       if(      index <= 1*board.getNumSpaces()/4 )
          return new Point( index * GUISpace.WIDTH, 0);
       else if( index <= 2*board.getNumSpaces()/4 )
          return new Point( GUISpace.WIDTH * board.getNumSpaces()/4, (index - (1*board.getNumSpaces()/4))*GUISpace.HEIGHT );
       else if( index <= 3*board.getNumSpaces()/4 )
          return new Point( ((3*board.getNumSpaces()/4) - index)*GUISpace.WIDTH, GUISpace.HEIGHT * board.getNumSpaces()/4 );
       else if( index <= 4*board.getNumSpaces()/4 )
          return new Point( 0, ((4*board.getNumSpaces()/4) - index)*GUISpace.HEIGHT );
       return new Point (0,0);
    }

    /**
     * Maps an point onto the Spaces of the board.
     * @return the index of the space the point is over, -1 if over none.
     * @author Aaron Decker
     */
    private int getIndexFromCoordinates( int x, int y )//{{{
    {
       if( 0 <= y && y <= GUISpace.HEIGHT )
       {
          return x / GUISpace.WIDTH ;
       }
       if( GUISpace.HEIGHT * ( board.getNumSpaces()/4) <= y && y <= GUISpace.HEIGHT * ( board.getNumSpaces()/4 + 1) )
       {
          return (int)((0.75)*board.getNumSpaces()) - ( x / GUISpace.WIDTH);
       }
       if( 0 <= x && x <= GUISpace.WIDTH )
       {
          return (4/4)*board.getNumSpaces() - ( y / GUISpace.HEIGHT );
       }
       if( GUISpace.WIDTH * ( board.getNumSpaces()/4) <= x && x <= GUISpace.WIDTH * ( board.getNumSpaces()/4 + 1) )
       {
          return (int)((0.25)*board.getNumSpaces()) + ( y / GUISpace.HEIGHT );
       }
       
       return -1;
    }

    /**
     * Maps an point onto the Spaces of the board.
     * @return the index of the space the point is over, -1 if over none.
     * @author Aaron Decker
     */
    private int getIndexFromCoordinates( Point p )
    {
       return getIndexFromCoordinates( (int)p.getX(), (int)p.getY() );
    }

    /**
     * Sets a selected space
     * @param space , the index of the space
     */
	public void setSelectedSpace(int space) {//{{{
		if (space >= 0 && space < board.getNumSpaces()) // check for validity
		{
			board.setSelectedSpace(space);
            GUISpace gs = new GUISpace(board.spaces.get(board.getSelectedSpace()));
            gs.repaint();
            repaint();
			if (deedPanel != null) 
            {
                gs.drawDeed();
				deedPanel.setGameBuffer(gs.getDeedBuffer());
				deedPanel.repaint();
			}
		}
	}

    private void setHighlightedSpace( int hspace )
    {
       //board.spaces.get( highlighted_space ).
       highlighted_space = hspace;
    }

	public void setDeedPanel(GamePanel panel) {
		deedPanel = panel;
		GUISpace gs = new GUISpace(board.spaces.get(board.getSelectedSpace()));
        gs.drawDeed();
		deedPanel.setGameBuffer(gs.getDeedBuffer());
	}

	/**
	 * Basic sanity-checking on position numbers
	 */
	public boolean isValidPosition(int position_num) {
		return position_num >= 0 && position_num < board.getNumSpaces();
	}

	/**
	 * Takes a position number and maps it onto the board.
	 */
	public int returnValidPosition(int position_num) {
		return position_num % board.getNumSpaces(); // rollover
	}

	/*
	 * public int getNumberOfSpaces() { return board.getNumSpaces(); }
	 */

	public Space getSpace(int position_num) {
		// TODO do real error handling
		if (!isValidPosition(position_num))
			return null;
		return board.spaces.get(position_num);
	}

    /**
	class BoardAnimation implements Runnable //{{{
    {
		private Thread animation_thread;
		private int final_token_space;
		private int current_token_space;
		private boolean final_is_lesser_than_current; // used for situations
														// when moving past the
														// zeroth space.

        private GUIPlayer current_animation_player;
        private GUISpace current_space;
        private GUISpace fromSpace;
        private GUISpace toSpace;

        BoardAnimation()
        {
        }

        void movePlayer(GUIPlayer p, GUISpace fromSpace, GUISpace toSpace)
        {
            this.fromSpace = fromSpace;
            this.toSpace = toSpace;
            this.currentSpace = p.;
            this.current_animation_player.setIsMoving(true);
            this.animation_thread = new Thread(this);
            this.animation_thread.start();
        }

		@Override
		public void run()// {{{
		{
			while (animation_thread != null) {
				if (current_animation_player == null)
					animation_thread = null;

                int start_position = this.fromSpace.getPosition();
                int current_position = this.currentSpace.getPosition();
                int final_position = this.toSpace.getPosition();

				// System.out.println("current: " + start_position +
				// "  final: " + final_token_space );
				if (current_position < final_token_space
						|| final_is_lesser_than_current) {

					board.spaces.get(start_position).removePlayer(
							current_animation_player.getPlayer());

					start_position++;
					start_position = returnValidPosition(current_token_space);
					if (current_token_space == 0)
						final_is_lesser_than_current = false;

					board.spaces.get(current_token_space).addPlayer(
							current_animation_player.getPlayer());
				}
				// if( current_token_space >= final_token_space &&
				// !final_is_lesser_than_current)
				else {
					board.spaces.get(final_token_space).addPlayer(
							current_animation_player.getPlayer());
					// current_animation_player = null;
					current_animation_player.setIsMoving(false);
					animation_thread = null;
				}

				try {
					Thread.sleep(ANIMATION_DELAY_MS);
				} catch (InterruptedException e) { // do nothing
				}
			}
		}

	}
    */

    @Override
    protected boolean handleMousePressed(MouseEvent e) 
    {
       setSelectedSpace( board.position2Index( getIndexFromCoordinates(e.getPoint()) ));
       return true;
	}

	// Implements highlighting on mouse-over
	@Override
	protected boolean handleMouseEntered(MouseEvent e) 
    {
		return true;
	}

	// Implements highlighting on mouse-over
	@Override
	protected boolean handleMouseExited(MouseEvent e) 
    {
		return true;
    }
    
    @Override
    protected boolean handleMouseMoved(MouseEvent e)
    {
       if( board.isValidPosition(board.position2Index( getIndexFromCoordinates(e.getPoint()))) )
       {
          board.spaces.get( highlighted_space ).setHighlighted( false );
          highlighted_space = board.position2Index( getIndexFromCoordinates(e.getPoint()) );
          board.spaces.get( highlighted_space ).setHighlighted( true );
       }
        //System.out.println("(" + e.getX() + "," + e.getY() + ")");
       // System.out.println( getIndexFromCoordinates(e.getPoint()) );
		return false;
	}

}
