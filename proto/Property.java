import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

/**
 * Property: Represents a UWPonopoly Property
 */

class Property extends Space
{
   // magic numbers for drawing the property
   static float COLOR_STRIP_HEIGHT_RATIO = 0.2F;
   static int BORDER_THICKNESS = 2;
   
   private GameBuffer gbuffer;

   public Property( GameBuffer gbuffer )
   {
      super( gbuffer );
      this.gbuffer = gbuffer;
      setSize(50,50);
   }

   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      gbuffer.clear();
      Graphics2D g2d = (Graphics2D) g;
      g.drawImage(gbuffer.getBuffer(),0,0,this);
      g.setColor(Color.BLUE);
      g.fillRect(0,0, gbuffer.getWidth(), (int)(gbuffer.getHeight() * COLOR_STRIP_HEIGHT_RATIO));

      // draw outline
      g2d.setColor(Color.BLACK);
      BasicStroke bs1 = new BasicStroke(BORDER_THICKNESS);
      g2d.setStroke(bs1);
      g2d.drawRect(0,0, gbuffer.getWidth(), gbuffer.getHeight() );
   }
}
