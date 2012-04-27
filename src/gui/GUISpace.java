/**  

 @author UWP_User 
 */
package gui;

// Import Java Packages
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.*;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.lang.Class;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Iterator;


// Import Game Mechanics
import board.Space;

// Import GUI Mechanics
import gui.GamePanel;
import gui.GUIPlayer;
import player.Player;

public class GUISpace extends GamePanel
{
	// Sizing
	public static int WIDTH  = 55; // GUIBoard uses this
	public static int HEIGHT = 55; // GUIBoard uses this
	public static int DEED_WIDTH = 250;
	public static int DEED_HEIGHT = 250;

	// Fonts
	private static int DEED_FONT_SIZE = 12;
	private static int TITLE_FONT_SIZE = 10;
	private static int DEED_TEXT_Y_INCREMENT = (int)(DEED_FONT_SIZE * 1.2);

	public static int SCALED_UP_SCALE = 2;

	// Colors
	private static float COLOR_STRIP_HEIGHT_RATIO = 0.2F;
	private static Color BACKGROUND_COLOR = Color.WHITE;

	// Border
	protected static int BORDER_THICKNESS = 2;
	private static Color BORDER_COLOR_DEFAULT = Color.BLACK;
	private static Color BORDER_COLOR_HIGHLIGHT = Color.YELLOW;
	private static Color BORDER_COLOR_SELECTED = Color.RED;
	private Color borderColor;
	private boolean selected;

	private Space space;
	private Color spaceColor;

	private static int TOKEN_PADDING = 8; // distance from tokens to sides of space
	private static int TOKEN_BETWEEN_PADDING = 4; // distance between tokens

	private int x_coor,y_coor; // x and y coordinates for placing on board
	private GameBuffer deedBuffer;

	public GUISpace(Space space)
	{
		super(WIDTH, HEIGHT, BACKGROUND_COLOR);
		this.space = space;

		Color color;
		try {
			Field field = Class.forName("java.awt.Color").getField(this.space.getColor());
			color = (Color)field.get(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			color = null;
		}
		this.spaceColor = color;
		this.borderColor = BORDER_COLOR_DEFAULT;
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));

		deedBuffer = new GameBuffer(DEED_WIDTH, DEED_HEIGHT, BACKGROUND_COLOR);
	}

	@Override
	protected boolean handleMousePressed(MouseEvent e)
	{
		setSelected(true);
		//this.guiBoard.setSelectedSpace(this.space.getPosition());
		return true;
	}

	// Implements highlighting on mouse-over
	@Override
	protected boolean handleMouseEntered(MouseEvent e)
	{
		if( !isSelected() )
			this.borderColor = BORDER_COLOR_HIGHLIGHT;
		return true;
	}

	// Implements highlighting on mouse-over
	@Override
	protected boolean handleMouseExited(MouseEvent e)
	{
		if( !isSelected() )
			this.borderColor = BORDER_COLOR_DEFAULT;
		return true;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// draw outline
		// drawOutline(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(this.borderColor);
		BasicStroke bs1 = new BasicStroke(BORDER_THICKNESS);
		g2d.setStroke(bs1);
		g2d.drawRect(0,0, gbuffer.getWidth(), gbuffer.getHeight() );

		// draw color strip
		// drawColor(g);
		g.setColor(this.spaceColor);
		g.fillRect(BORDER_THICKNESS/2,BORDER_THICKNESS/2, gbuffer.getWidth()-BORDER_THICKNESS, (int)(gbuffer.getHeight() * COLOR_STRIP_HEIGHT_RATIO));

		// Draw Property title
		// drawTitle(g);
		Font font = new Font("Helvetica", Font.PLAIN, TITLE_FONT_SIZE);
		FontMetrics fm   = g.getFontMetrics(font);
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(this.space.getTitle(), g);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString( this.space.getTitle(), (int)(gbuffer.getWidth()/2) - (int)(rect.getWidth()/2) ,
				(int)(gbuffer.getHeight() * COLOR_STRIP_HEIGHT_RATIO) + (int)(rect.getHeight()) );

		// Draw Player tokens
		// drawTokens();
        if( space.getPlayers() != null )
        {
           Iterator<Player> itr = space.getPlayers().iterator();
           // positioning for tokens
           int token_x = TOKEN_PADDING;
           int token_y = (int)(HEIGHT * COLOR_STRIP_HEIGHT_RATIO) + TITLE_FONT_SIZE + TOKEN_PADDING;
           while( itr.hasNext() )
           {
              Player p = itr.next();
              if( token_x + GUIPlayer.TOKEN_SIZE + TOKEN_PADDING >= WIDTH )
              {
                 token_x = TOKEN_PADDING;
                 token_y += TOKEN_BETWEEN_PADDING + GUIPlayer.TOKEN_SIZE;
              }
              g.drawImage( p.getToken().getBuffer(), token_x, token_y, this);
              token_x += TOKEN_BETWEEN_PADDING + GUIPlayer.TOKEN_SIZE;
           }
        }

	}

	private void drawDeed()//{{{
	{
		if(this.deedBuffer == null)
			return;
		Graphics g = this.deedBuffer.getGraphics();


		// dummy testing code
		//clear the buffer
		this.deedBuffer.clear();

		// draw outline
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(BORDER_COLOR_DEFAULT);
		BasicStroke bs1 = new BasicStroke(BORDER_THICKNESS);
		g2d.setStroke(bs1);
		g2d.drawRect(0,0, this.deedBuffer.getWidth(), this.deedBuffer.getHeight() );

		// draw color strip
		g.setColor(this.spaceColor);
		g.fillRect(BORDER_THICKNESS/2,BORDER_THICKNESS/2, this.deedBuffer.getWidth()-BORDER_THICKNESS, (int)(this.deedBuffer.getHeight() * COLOR_STRIP_HEIGHT_RATIO));

		// draw Property title
		int previous_y = 0; // what was the y value of the previous line of text?

		// font set-up
		g.setColor(Color.BLACK);
		Font font = new Font("Helvetica", Font.PLAIN, TITLE_FONT_SIZE);
		FontMetrics fm   = g.getFontMetrics(font);

		// deed title
		java.awt.geom.Rectangle2D title_rect = fm.getStringBounds(this.space.getTitle() + " Deed", g);
		previous_y = (int)(this.deedBuffer.getHeight() * COLOR_STRIP_HEIGHT_RATIO) + (int)(title_rect.getHeight());
		g.drawString( space.getTitle() + " Deed", (int)(this.deedBuffer.getWidth()/2) - (int)(title_rect.getWidth()/2), previous_y);

		// rent
		/**
		for( int i = 0; i < MAX_NUM_IMPROVEMENTS; i++)
		{
			previous_y += DEED_TEXT_Y_INCREMENT;
			String rent_string;
			if( i == 0)
				rent_string = "Rent: $" + Integer.toString( getRent(i) );
			else if ( i == MAX_NUM_IMPROVEMENTS - 1)
				rent_string = "Hotel: $" + Integer.toString( getRent(i) );
			else
				rent_string = i + " houses: $" + Integer.toString( getRent(i) );
			java.awt.geom.Rectangle2D rent_rect = fm.getStringBounds(rent_string, g);
			g.drawString( rent_string, (int)(deed_buffer.getWidth()/2) - (int)(rent_rect.getWidth()/2), previous_y);
		}
		*/
		//deed_buffer.repaint();
	}//}}}

	/**
	 * Returns a GameBuffer containing a larger version of a Space
	 */
	public GameBuffer drawScaledUp()//{{{
	{
		GameBuffer scaledUpBuffer = new GameBuffer(WIDTH * SCALED_UP_SCALE, HEIGHT * SCALED_UP_SCALE, Color.WHITE);
		scaledUpBuffer.clear();
		Graphics g = scaledUpBuffer.getGraphics();

		// draw outline
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(BORDER_COLOR_DEFAULT);
		BasicStroke bs1 = new BasicStroke(BORDER_THICKNESS);
		g2d.setStroke(bs1);
		g2d.drawRect(0,0, WIDTH * SCALED_UP_SCALE, HEIGHT * SCALED_UP_SCALE);

		// draw color strip
		g.setColor( getPropertyColor() );
		g.fillRect(BORDER_THICKNESS/2,BORDER_THICKNESS/2, WIDTH * SCALED_UP_SCALE - BORDER_THICKNESS, (int)(HEIGHT * SCALED_UP_SCALE * COLOR_STRIP_HEIGHT_RATIO));

		// Draw Property title
		Font font = new Font("Helvetica", Font.PLAIN, TITLE_FONT_SIZE * SCALED_UP_SCALE);
		FontMetrics fm   = g.getFontMetrics(font);
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(space.getTitle(), g);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString( space.getTitle(), (int)(WIDTH * SCALED_UP_SCALE/2) - (int)(rect.getWidth()/2) ,
				(int)(HEIGHT * SCALED_UP_SCALE * COLOR_STRIP_HEIGHT_RATIO) + (int)(rect.getHeight()) );

		return scaledUpBuffer;
	}//}}}

	/*public void setTitle( String title)
   {
      this.title = title;
   }*/

	public void setXCoor( int x ) { x_coor = x; }
	public void setYCoor( int y ) { y_coor = y; }
	public int getXCoor() { return x_coor; }
	public int getYCoor() { return y_coor; }

	public boolean isSelected() { return selected; }
	public void setSelected( boolean selected )
	{
		this.selected = selected;
		if( selected )
		{
			drawDeed(); // regenerate the deed
			borderColor = BORDER_COLOR_SELECTED;
		}
		else
			borderColor = BORDER_COLOR_DEFAULT;
	}

	//public void setBoard( Board_new board ) { this.board = board; }
	//public void setBoardIndex( int index ) { board_index = index; }

	public GameBuffer getDeedBuffer() { return deedBuffer; }

	/**
	 * Set the amount of rent for a particular improvement level
	 * @param rent_amount , the monetary value of the rent
	 * @param improvement_level , the level of improvement the property is at,
	 * "0" is the base improvement level, "1" is one house, etc.
	 */
	/*public void setRent( int rent_amount, int improvement_level)
   {
      if( 0 <= improvement_level && improvement_level < rent.length )
         rent[improvement_level] = rent_amount;
   }*/


	

    public Color getPropertyColor()
    {
       String str_color = space.getPropertyColorString();
       if( str_color == null )
          return Color.BLACK;
       if( str_color.equals("RED") )
          return Color.RED;
       if( str_color.equals("YELLOW") )
          return Color.YELLOW;
       if( str_color.equals("BLACK") )
          return Color.BLACK;
       if( str_color.equals("WHITE") )
          return Color.WHITE;
       return Color.BLACK;
    }

}
