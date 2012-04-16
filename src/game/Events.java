/**  

 @author UWP_User 
*/

package game;

import java.awt.AWTEvent;
import java.util.EventListener;

public class Events extends AWTEvent
{
   public Roll(Player p, Dice d)
   {
      super();
   }

}

public interface EventHandler extends EventListener
{
   public void handleEvent(Events e);
}
