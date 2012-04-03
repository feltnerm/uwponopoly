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
   private static int DOT_RADIUS = 10; // radius of the dots on the dice in pixels

   private Dice dice;

   GUIDice()
   {
      super();
      setPreferredSize( new Dimension( DICE_SIZE*2 + DICE_PADDING + 2*WIDGET_PADDING, DICE_SIZE + 2*WIDGET_PADDING) );
      dice = new Dice();
   }

   public void roll()
   {
      dice.roll();
   }

   /**
    * The ugly paint routine that draws the dice
    */
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      // draw white dice backgrounds
      g.setColor( Color.WHITE );
      g.fillRect( 0 + WIDGET_PADDING, 0 + WIDGET_PADDING, DICE_SIZE, DICE_SIZE);
      g.fillRect(0 + DICE_SIZE + DICE_PADDING + WIDGET_PADDING, 0 + WIDGET_PADDING, DICE_SIZE, DICE_SIZE);

      // draw outlines
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor( Color.BLACK );
      BasicStroke bs1 = new BasicStroke(BORDER_THICKNESS);
      g2d.setStroke(bs1);

      g2d.drawRect(0 + WIDGET_PADDING, 0 + WIDGET_PADDING, DICE_SIZE, DICE_SIZE);
      g2d.drawRect(0 + DICE_SIZE + DICE_PADDING + WIDGET_PADDING, 0 + WIDGET_PADDING, DICE_SIZE, DICE_SIZE);

      drawDiceNumber( dice.getFirstDie(), WIDGET_PADDING, WIDGET_PADDING, g);
      drawDiceNumber( dice.getSecondDie(), WIDGET_PADDING + DICE_SIZE + DICE_PADDING, WIDGET_PADDING, g);

   }

   /**
    * Draws the specified side of the dice, with the upper-left corner of the die at (x,y) onto Graphics g.
    */
   public void drawDiceNumber( int num, int x, int y, Graphics g )
   {
      g.setColor( Color.BLACK );
      int center_x = x + DICE_SIZE/2;
      int center_y = y + DICE_SIZE/2;
      /*int left_fourth_x = center_x/2;
      int right_fourth_x = center_x + center_x/2;
      int left_fourth_y = center_y/2;
      int right_fourth_y = center_y + center_y/2;*/
      int left_fourth_x = x + DICE_SIZE/4;
      int right_fourth_x = x + 3*DICE_SIZE/4;
      int left_fourth_y = y + DICE_SIZE/4;
      int right_fourth_y = y + 3*DICE_SIZE/4;

      if( num == 1 )
         g.fillOval( center_x - DOT_RADIUS/2, center_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
      if( num == 2 )
      {
         g.fillOval( left_fourth_x - DOT_RADIUS/2, left_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( right_fourth_x - DOT_RADIUS/2, right_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
      }
      if( num == 3 )
      {
         g.fillOval( left_fourth_x - DOT_RADIUS/2, left_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( center_x - DOT_RADIUS/2, center_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( right_fourth_x - DOT_RADIUS/2, right_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
      }
      if( num == 4 )
      {
         g.fillOval( left_fourth_x - DOT_RADIUS/2, left_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( left_fourth_x - DOT_RADIUS/2, right_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( right_fourth_x - DOT_RADIUS/2, left_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( right_fourth_x - DOT_RADIUS/2, right_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
      }
      if( num == 5 )
      {  
         g.fillOval( left_fourth_x - DOT_RADIUS/2, left_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( center_x - DOT_RADIUS/2, center_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( right_fourth_x - DOT_RADIUS/2, right_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( left_fourth_x - DOT_RADIUS/2, right_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( right_fourth_x - DOT_RADIUS/2, left_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
      }
      if( num == 6 )
      {
         g.fillOval( left_fourth_x - DOT_RADIUS/2, left_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( left_fourth_x - DOT_RADIUS/2, center_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( left_fourth_x - DOT_RADIUS/2, right_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( right_fourth_x - DOT_RADIUS/2, left_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( right_fourth_x - DOT_RADIUS/2, center_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
         g.fillOval( right_fourth_x - DOT_RADIUS/2, right_fourth_y - DOT_RADIUS/2, DOT_RADIUS, DOT_RADIUS);
      }

   }
}
