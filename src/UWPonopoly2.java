/**  

 @author UWP_User 
*/

import Config.Config;
import Game.Game;
import GUIGame.GUIGame;

public class UWPonopoly2 
{

   public UWPonopoly2(boolean gui, boolean debug) {
      
      if (gui)
      {
         GUIGame game = new GUIGame(debug);
      } else {
         Game game = new Game(debug);
         //game.gameStart();
      }
      game.gameStart();
   }

   public static void main(String[] args)
   {

      boolean gui = false;
      boolean debug = false;

      if (args[0] == "help")
      {
         System.out.println("% java UWPonopoly2 [gui] [debug]");
      } else
      {
         if (args[0] == "gui")
         {
            gui = true;
         }

         if (args[1] == "debug")
         {
            debug = true;
         }
      }

      UWPonopoly2 uwponopoly = new UWPonopoly2(gui, debug); 
}
