import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;

/** Abstracts a BufferedImage to allow
 * for conveniences such as clearing to a 
 * background color.
 */

class GameBuffer
{
   private BufferedImage buffer;
   private Graphics context;
   private int width, height;
   private Color color;

   public GameBuffer( int width, int height )
   {
      this.width = width;
      this.height = height;
      color = Color.RED;
      buffer = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
      context = buffer.createGraphics();
   }

   public GameBuffer( int width, int height, Color color )
   {
      this.width = width;
      this.height = height;
      this.color = color;
      buffer = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
      context = buffer.createGraphics();
   }

   public Graphics getGraphics()
   {
      return context;
   }

   public BufferedImage getBuffer()
   {
      return buffer;
   }

   public void clear_to_color( Color color)
   {
      context.setColor(color);
      context.fillRect( 0, 0, width, height);
   }

   public void clear()
   {
      clear_to_color( color );
   }
}
