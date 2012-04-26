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

   public static void main(String[] args)
   {
      boolean gui = false;
      boolean debug = false;
      for( int i = 0; i < args.length; i++)
         System.out.println( args[i] );

      if( args.length > 0)
      {
         if (args[0].equals("help") )
         {
            System.out.println("% java UWPonopoly2 [gui] [debug]");
         } 
         else
         {
            if (args[0].equals("gui") )
            {
               gui = true;
            }
            if( args.length > 1)
            {
               if (args[1].equals("debug") )
               {
                  debug = true;
               }
            }
         }
      }

      UWPonopoly2 uwponopoly = new UWPonopoly2(gui, debug); 
      uwponopoly.play();
   }
}
