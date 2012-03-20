import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.*; // this was laziness on my part, import should
                   // be fixed before release -- Aaron

/**
 * Property: Represents a UWPonopoly Property
 */

class Property extends Space
{
   // magic numbers for drawing the property
   private static int DESIRED_WIDTH  = 75;
   private static int DESIRED_HEIGHT = 75;
   private static int TITLE_FONT_SIZE = 10;
   private static float COLOR_STRIP_HEIGHT_RATIO = 0.2F;

   private String title;
   
   public Property( GameBuffer gbuffer )
   {
      super( gbuffer );
      setSize(50,50);
   }

   public Property()
   {
      super( DESIRED_WIDTH,DESIRED_HEIGHT, Color.WHITE );
      setPreferredSize( new Dimension(DESIRED_WIDTH,DESIRED_HEIGHT) );
      title=("Default");
   }

   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      //Graphics2D g2d = (Graphics2D) g;
      //g.drawImage(gbuffer.getBuffer(),0,0,this);

      // Draw the color strip
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

      // Moved to paintComponent in Space.java,, because all spaces have an outline -- Aaron
      // draw outline
      /*g2d.setColor(Color.BLACK);
      BasicStroke bs1 = new BasicStroke(BORDER_THICKNESS);
      g2d.setStroke(bs1);
      g2d.drawRect(0,0, gbuffer.getWidth(), gbuffer.getHeight() );*/
   }

   public void setTitle( String title)
   {
      this.title = title;
   }
}
