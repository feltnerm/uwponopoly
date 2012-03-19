import java.awt.Graphics;
import java.awt.Color;

/**
 * Abstracts a space in UWPonopoly.
 */

class Space extends GamePanel
{
   
   public Space( GameBuffer gbuffer )
   {
      super( gbuffer );
   }

   public Space( int width, int height, Color color)
   {
      super( width, height, color );
   }

   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
   }

}
