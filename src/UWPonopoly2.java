/**  

 @author UWP_User 
*/

import game.Game;
import gui.GUIGame;

public class UWPonopoly2 
{
	private boolean GUI;
	private boolean DEBUG;

   public UWPonopoly2(boolean gui, boolean debug) {
      
	   this.GUI = gui;
	   this.DEBUG = debug;      
   }
   
   public void play()
   {
	   if (this.GUI)
	   {
		   GUIGame game = new GUIGame(this.DEBUG);
		   game.initGame();
		   game.startGame();
	   } else {
		   Game game = new Game(this.DEBUG);
		   game.startGame();
	   }

   }

   public static int main(String[] args)
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
      uwponopoly.play();
      
      return 0;
   }
}