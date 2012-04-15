/**
 * 
 */
package board;

/**
 * An action space has an 'Action' associated with it. This is so we can
 * have Insurance Tax, Free Parking, Jail, etc.
 * @author mark
 *
 */
public class ActionSpace extends Space {

   public ActionSpace(String title, String type, int positon)
   {
      super(title, type, position);
   }
}
