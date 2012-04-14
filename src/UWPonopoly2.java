/**  

 @author UWP_User 
*/

import Config.Config;

public class UWPonopoly2 
{
   public Config config;

   public UWPonopoly2() {
      this.config = new Config();
      Game game = new Game(this.config);
      game.run();
   }

   public static void main(String[] args)
   {
      // Get configuration
      UWPonopoly2 uwponpoly = new UWPonopoly2();
   }
}
