import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Dimension;

class GUIDice extends JPanel
{
   private static int DICE_SIZE = 50; // size of one side of the square dice in pixels
   private static int BORDER_THICKNESS = 2; // border thickness of dice in pixels
   private static int DICE_PADDING = 15; // space between dice in pixels
   private static int WIDGET_PADDING = 5; // padding around the dice as a whole in pixels
   private static int DOT_RADIUS = 5; // radius of the dots on the dice in pixels

   GUIDice()
   {
      super();
      setPreferredSize( new Dimension( DICE_SIZE*2 + DICE_PADDING + 2*WIDGET_PADDING, DICE_SIZE + 2*WIDGET_PADDING) );
   }

   /**
    * The ugly paint routine that draws the dice
    */
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      // draw outlines
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor( Color.BLACK );
      BasicStroke bs1 = new BasicStroke(BORDER_THICKNESS);
      g2d.setStroke(bs1);

      g2d.drawRect(0 + WIDGET_PADDING, 0 + WIDGET_PADDING, DICE_SIZE, DICE_SIZE);
      g2d.drawRect(0 + DICE_SIZE + DICE_PADDING + WIDGET_PADDING, 0 + WIDGET_PADDING, DICE_SIZE, DICE_SIZE);

      drawDiceNumber( 1, WIDGET_PADDING, WIDGET_PADDING, g);

   }

   /**
    * Draws the specified side of the dice, with the upper-left corner of the die at (x,y) onto Graphics g.
    */
   public void drawDiceNumber( int num, int x, int y, Graphics g )
   {
      g.setColor( Color.BLACK );
      int center_x = x + DICE_SIZE/2;
      int center_y = y + DICE_SIZE/2;
      if( num == 1 )
         g.drawOval( center_x - DOT_RADIUS/2, center_y - DOT_RADIUS/2, center_x + DOT_RADIUS/2, center_y + DOT_RADIUS/2);
   }
}
