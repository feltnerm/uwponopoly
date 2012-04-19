/**  

 @author UWP_User 
*/
package gui;

import board.Space;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.*;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Iterator;

//import GUI.GamePanel;
//import GUI.GameBuffer;

public class GUISpace extends GamePanel
{
   // defaults and magic numbers
   public static int SPACE_WIDTH  = 55; // Board uses this
   public static int SPACE_HEIGHT = 55; // Board uses this
   public static int DEED_WIDTH = 250;
   public static int DEED_HEIGHT = 250;

   private static int DEED_FONT_SIZE = 12;
   private static int TITLE_FONT_SIZE = 10;
   private static int DEED_TEXT_Y_INCREMENT = (int)(DEED_FONT_SIZE * 1.2);

   public static int SCALED_UP_SCALE = 2;
   
   private static float COLOR_STRIP_HEIGHT_RATIO = 0.2F;
   protected static int BORDER_THICKNESS = 2;
   private static Color BORDER_COLOR_DEFAULT = Color.BLACK;
   private static Color BORDER_COLOR_HIGHLIGHT = Color.YELLOW;
   private static Color BORDER_COLOR_SELECTED = Color.RED;
   private Color border_color;
   private boolean selected;
   private Space space;

   // Moved to Space
   //public static int MAX_NUM_IMPROVEMENTS = 6; // maximum number of building improvements minus one, for the base case.

   private static int TOKEN_PADDING = 8; // distance from tokens to sides of space
   private static int TOKEN_BETWEEN_PADDING = 4; // distance between tokens

   private int x_coor,y_coor; // x and y coordinates for placing on board
   private LinkedList<GUIPlayer> players;
   private GameBuffer deed_buffer;

   public GUISpace()
   {
      super(SPACE_WIDTH, SPACE_HEIGHT, )
      space = new Space();

      property_color = Color.YELLOW; // senseless default
      border_color = BORDER_COLOR_DEFAULT;
      setPreferredSize( new Dimension(SPACE_WIDTH,SPACE_HEIGHT) );
      title=("Default");
      rent = new int[MAX_NUM_IMPROVEMENTS + 1];

      deed_buffer = new GameBuffer( DEED_WIDTH, DEED_HEIGHT, Color.WHITE);
      players = new LinkedList<GUIPlayer>();
      //drawDeed();
   }

      @Override
   protected boolean handleMousePressed(MouseEvent e)
   {
      setSelected(true);
      board.setSelectedSpace( board_index );
      return true;
   }

   // Implements highlighting on mouse-over
   @Override
   protected boolean handleMouseEntered(MouseEvent e)
   {
      if( !isSelected() )
         border_color = BORDER_COLOR_HIGHLIGHT;
      return true;
   }

   // Implements highlighting on mouse-over
   @Override
   protected boolean handleMouseExited(MouseEvent e)
   {
      if( !isSelected() )
         border_color = BORDER_COLOR_DEFAULT;
      return true;
   }

   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      // draw outline
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(border_color);
      BasicStroke bs1 = new BasicStroke(BORDER_THICKNESS);
      g2d.setStroke(bs1);
      g2d.drawRect(0,0, gbuffer.getWidth(), gbuffer.getHeight() );

      // draw color strip
      g.setColor(property_color);
      g.fillRect(BORDER_THICKNESS/2,BORDER_THICKNESS/2, gbuffer.getWidth()-BORDER_THICKNESS, (int)(gbuffer.getHeight() * COLOR_STRIP_HEIGHT_RATIO));

      // Draw Property title
      Font font = new Font("Helvetica", Font.PLAIN, TITLE_FONT_SIZE);
      FontMetrics fm   = g.getFontMetrics(font);
      java.awt.geom.Rectangle2D rect = fm.getStringBounds(title, g);
      g.setColor(Color.BLACK);
      g.setFont(font);
      g.drawString( title, (int)(gbuffer.getWidth()/2) - (int)(rect.getWidth()/2) ,
                           (int)(gbuffer.getHeight() * COLOR_STRIP_HEIGHT_RATIO) + (int)(rect.getHeight()) );

      // Draw Player tokens
      Iterator<Player> itr = players.iterator();
      // positioning for tokens
      int token_x = TOKEN_PADDING;
      int token_y = (int)(SPACE_HEIGHT * COLOR_STRIP_HEIGHT_RATIO) + TITLE_FONT_SIZE + TOKEN_PADDING;
      while( itr.hasNext() )
      {
         Player p = itr.next();
         if( token_x + Player.TOKEN_SIZE + TOKEN_PADDING >= SPACE_WIDTH )
         {
            token_x = TOKEN_PADDING;
            token_y += TOKEN_BETWEEN_PADDING + Player.TOKEN_SIZE;
         }
         g.drawImage( p.getToken().getBuffer(), token_x, token_y, this);
         token_x += TOKEN_BETWEEN_PADDING+ Player.TOKEN_SIZE;
      }

   }

   private void drawDeed()//{{{
   {
      if(deed_buffer == null)
         return;
      Graphics g = deed_buffer.getGraphics();


      // dummy testing code
      //clear the buffer
      deed_buffer.clear();

      // draw outline
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(BORDER_COLOR_DEFAULT);
      BasicStroke bs1 = new BasicStroke(BORDER_THICKNESS);
      g2d.setStroke(bs1);
      g2d.drawRect(0,0, deed_buffer.getWidth(), deed_buffer.getHeight() );

      // draw color strip
      g.setColor(property_color);
      g.fillRect(BORDER_THICKNESS/2,BORDER_THICKNESS/2, deed_buffer.getWidth()-BORDER_THICKNESS, (int)(deed_buffer.getHeight() * COLOR_STRIP_HEIGHT_RATIO));

      // draw Property title
      int previous_y = 0; // what was the y value of the previous line of text?

      // font set-up
      g.setColor(Color.BLACK);
      Font font = new Font("Helvetica", Font.PLAIN, TITLE_FONT_SIZE);
      FontMetrics fm   = g.getFontMetrics(font);

      // deed title
      java.awt.geom.Rectangle2D title_rect = fm.getStringBounds(title + " Deed", g);
      previous_y = (int)(deed_buffer.getHeight() * COLOR_STRIP_HEIGHT_RATIO) + (int)(title_rect.getHeight());
      g.drawString( title + " Deed", (int)(deed_buffer.getWidth()/2) - (int)(title_rect.getWidth()/2), previous_y);

      // rent
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
      //deed_buffer.repaint();
   }//}}}

   /**
    * Returns a GameBuffer containing a larger version of a Space
    */
   public GameBuffer drawScaledUp()//{{{
   {
      GameBuffer scaledUpBuffer = new GameBuffer(SPACE_WIDTH * SCALED_UP_SCALE, SPACE_HEIGHT * SCALED_UP_SCALE, Color.WHITE);
      scaledUpBuffer.clear();
      Graphics g = scaledUpBuffer.getGraphics();

      // draw outline
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(BORDER_COLOR_DEFAULT);
      BasicStroke bs1 = new BasicStroke(BORDER_THICKNESS);
      g2d.setStroke(bs1);
      g2d.drawRect(0,0, SPACE_WIDTH * SCALED_UP_SCALE, SPACE_HEIGHT * SCALED_UP_SCALE);

      // draw color strip
      g.setColor(property_color);
      g.fillRect(BORDER_THICKNESS/2,BORDER_THICKNESS/2, SPACE_WIDTH * SCALED_UP_SCALE - BORDER_THICKNESS, (int)(SPACE_HEIGHT * SCALED_UP_SCALE * COLOR_STRIP_HEIGHT_RATIO));

      // Draw Property title
      Font font = new Font("Helvetica", Font.PLAIN, TITLE_FONT_SIZE * SCALED_UP_SCALE);
      FontMetrics fm   = g.getFontMetrics(font);
      java.awt.geom.Rectangle2D rect = fm.getStringBounds(title, g);
      g.setColor(Color.BLACK);
      g.setFont(font);
      g.drawString( title, (int)(SPACE_WIDTH * SCALED_UP_SCALE/2) - (int)(rect.getWidth()/2) ,
                           (int)(SPACE_HEIGHT * SCALED_UP_SCALE * COLOR_STRIP_HEIGHT_RATIO) + (int)(rect.getHeight()) );

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
         border_color = BORDER_COLOR_SELECTED;
      }
      else
         border_color = BORDER_COLOR_DEFAULT;
   }

   //public void setBoard( Board_new board ) { this.board = board; }
   //public void setBoardIndex( int index ) { board_index = index; }

   public GameBuffer getDeedBuffer() { return deed_buffer; }

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


   public void addPlayer( Player player )
   {
      Iterator<GUIPlayer> itr = players.iterator();
      while( itr.hasNext() )
      {
         if( itr.next().getTokenChar() == player.getTokenChar() )
            return; // player is already on space, get out of here
      }
      players.add(player);
   }

   public void removePlayer( Player player )
   {
      Iterator<GUIPlayer> itr = players.iterator();
      while( itr.hasNext() )
      {
         if( itr.next().getTokenChar() == player.getTokenChar() )
            itr.remove();
      }
   }

}
