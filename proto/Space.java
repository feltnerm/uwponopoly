import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.*;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;

/**
 * Abstracts a space in UWPonopoly.
 */

class Space extends GamePanel
{
   // defaults and magic numbers
   public static int SPACE_WIDTH  = 50; // Board uses this
   public static int SPACE_HEIGHT = 50; // Board uses this
   public static int DEED_WIDTH = 250;
   public static int DEED_HEIGHT = 250;
   private static int TITLE_FONT_SIZE = 10;
   private static float COLOR_STRIP_HEIGHT_RATIO = 0.2F;
   protected static int BORDER_THICKNESS = 2;
   private static Color BORDER_COLOR_DEFAULT = Color.BLACK;
   private static Color BORDER_COLOR_HIGHLIGHT = Color.YELLOW;
   private static Color BORDER_COLOR_SELECTED = Color.RED;

   private Color border_color;
   private Color property_color;
   private String title;
   private int x_coor,y_coor; // x and y coordinates for placing on board
   private boolean selected;
   private Board board;
   private int board_index;
   private GameBuffer deed_buffer;

   public Space() // no-parameter testing constructor
   {
      super( SPACE_WIDTH,SPACE_HEIGHT, Color.WHITE );
      property_color = Color.YELLOW; // senseless default
      border_color = BORDER_COLOR_DEFAULT;
      setPreferredSize( new Dimension(SPACE_WIDTH,SPACE_HEIGHT) );
      title=("Default");

      deed_buffer = new GameBuffer( DEED_WIDTH, DEED_HEIGHT, Color.WHITE);
      //drawDeed();
   }

   /*public Space( GameBuffer gbuffer )
   {
      super( gbuffer );
      deed_buffer = new GameBuffer( DEED_WIDTH, DEED_HEIGHT, Color.WHITE);
      drawDeed();
      border_color = BORDER_COLOR_DEFAULT;
   }

   public Space( int width, int height, Color color)
   {
      super( width, height, color );
      deed_buffer = new GameBuffer( DEED_WIDTH, DEED_HEIGHT, Color.WHITE);
      drawDeed();
   }*/

   // Implement begin selected
   @Override
   protected boolean handleMouseClicked(MouseEvent e)
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

   }

   private void drawDeed()
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
      g.setColor(Color.BLACK);
      Font font = new Font("Helvetica", Font.PLAIN, TITLE_FONT_SIZE);
      FontMetrics fm   = g.getFontMetrics(font);
      java.awt.geom.Rectangle2D rect = fm.getStringBounds(title + " Deed", g);
      g.drawString( title + " Deed", (int)(deed_buffer.getWidth()/2) - (int)(rect.getWidth()/2) , 
                                     (int)(deed_buffer.getHeight() * COLOR_STRIP_HEIGHT_RATIO) + (int)(rect.getHeight()) );
      //deed_buffer.repaint();
   }
   
   public void setTitle( String title)
   {
      this.title = title;
   }
   
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

   public void setBoard( Board board ) { this.board = board; }
   public void setBoardIndex( int index ) { board_index = index; }

   public GameBuffer getDeedBuffer() { return deed_buffer; }

}
