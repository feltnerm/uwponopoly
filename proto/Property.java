import java.awt.Color;
import java.awt.Graphics;

/**
 * Property: Represents a UWPonopoly Property
 */

class Property extends Space
{
   public Property( GameBuffer gbuffer )
   {
      super( gbuffer );
      setSize(50,50);
   }

   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawImage(gbuffer.getBuffer(),0,0,this);
      g.setColor(Color.BLUE);
      g.fillRect(0,0, 25, 25);
   }
}
