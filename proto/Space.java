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
   private static int DESIRED_WIDTH  = 75;
   private static int DESIRED_HEIGHT = 75;
   private static int TITLE_FONT_SIZE = 10;
   private static float COLOR_STRIP_HEIGHT_RATIO = 0.2F;
   protected static int BORDER_THICKNESS = 2;
   private static Color BORDER_COLOR_DEFAULT = Color.BLACK;
   private static Color BORDER_COLOR_HIGHLIGHT = Color.YELLOW;

   private Color border_color;
   private String title;

   public Space() // no-parameter testing constructor
   {
      super( DESIRED_WIDTH,DESIRED_HEIGHT, Color.WHITE );
      setPreferredSize( new Dimension(DESIRED_WIDTH,DESIRED_HEIGHT) );
      title=("Default");

   }
   public Space( GameBuffer gbuffer )
   {
      super( gbuffer );
      border_color = BORDER_COLOR_DEFAULT;
   }

   public Space( int width, int height, Color color)
   {
      super( width, height, color );
   }

   // Implements highlighting on mouse-over
   @Override
   protected boolean handleMouseEntered(MouseEvent e)
   {
      border_color = BORDER_COLOR_HIGHLIGHT;
      return true;
   }

   // Implements highlighting on mouse-over
   @Override
   protected boolean handleMouseExited(MouseEvent e)
   {
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
      g.setColor(Color.BLUE);
      g.fillRect(BORDER_THICKNESS/2,BORDER_THICKNESS/2, gbuffer.getWidth(), (int)(gbuffer.getHeight() * COLOR_STRIP_HEIGHT_RATIO));

      // Draw Property title
      Font font = new Font("Helvetica", Font.PLAIN, TITLE_FONT_SIZE);
      FontMetrics fm   = g.getFontMetrics(font);
      java.awt.geom.Rectangle2D rect = fm.getStringBounds(title, g);
      g.setColor(Color.BLACK);
      g.setFont(font);
      g.drawString( title, (int)(gbuffer.getWidth()/2) - (int)(rect.getWidth()/2) , 
                           (int)(gbuffer.getHeight() * COLOR_STRIP_HEIGHT_RATIO) + (int)(rect.getHeight()) );
   }
   
   public void setTitle( String title)
   {
      this.title = title;
   }

}
