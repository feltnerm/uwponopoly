/**  

 @author UWP_User 
*/

package game;

import java.util.EventObject;
import java.util.EventListener;

public class Event extends EventObject
{
   public Event(Object source)
   {
      super(source);
   }

}

public interface EventHandler extends EventListener
{
   public void handleEven(Event e);
}
