/**  

 @author UWP_User 
*/

import Config.Config;
import Game.Game;

public class UWPonopoly2 
{

   public UWPonopoly2(boolean gui) {
      if (gui)
      {
         // do things
         // Game game = new Game(gui);
      } else {
         Game game = new Game();
         //game.gameStart();
      }
   }

   public static void main(String[] args)
   {
      UWPonopoly2 uwponpoly = new UWPonopoly2(false);
   }
}
