import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.*;

/**
 * Abstracts a space in UWPonopoly.
 */

class Space extends GamePanel
{
   protected static int BORDER_THICKNESS = 2;
   private static Color BORDER_COLOR_DEFAULT = Color.BLACK;
   private static Color BORDER_COLOR_HIGHLIGHT = Color.YELLOW;
   private Color border_color;
   
   public Space( GameBuffer gbuffer )
   {
      super( gbuffer );
      border_color = BORDER_COLOR_DEFAULT;
   }

   public Space( int width, int height, Color color)
   {
      super( width, height, color );
   }

   @Override
   protected boolean handleMouseEntered(MouseEvent e)
   {
      border_color = BORDER_COLOR_HIGHLIGHT;
      return true;
   }

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
   }

}
