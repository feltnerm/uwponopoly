/**
 * The Player class describes one player in a game of UWPonopoly
 * Copyright Aaron Decker 2012
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

class Player
{
   private static int TOKEN_SIZE = 15;
   private static int TOKEN_FONT_SIZE = 15;
   private int money;
   private int position;
   private GameBuffer token;

   public Player()
   {
   }

   public Player( char token_char )
   {
      token = generateTokenFromChar( token_char );
   }

   /* I don't think that this method is appropriate -- Aaron
   public void advance( int num_spaces )
   {
      // can't implement this until the Config is available
   }
   */

   public int getAmountOfMoney( ) { return money; }
   public void creditMoney( int amount ) { money += amount; }

   /**
    * If an amount of money is charged which causes the player to go bankrupt,
    * the full amount is not paid.
    * Instead, all of the remaining money is paid.
    */
   public int debitMoney( int amount )
   {
      if( money - amount > 0 )
         return money;
      else
         return money - amount;
   }

   private GameBuffer generateTokenFromChar( char token_char )
   {
      GameBuffer gbuffer = new GameBuffer( TOKEN_SIZE, TOKEN_SIZE, Color.WHITE);
      gbuffer.clear();
      Graphics g = gbuffer.getGraphics();
      g.setColor( Color.BLACK );
      Font font = new Font("Helvetica", Font.PLAIN, TOKEN_FONT_SIZE);
      g.setFont( font );
      g.drawString( Character.toString(token_char), 0, 10);
      //g.drawString( "Banana", 0, 10);
      g.fillRect( 0,0,1,1);
      return gbuffer;
   }

   public int getPosition() { return position; }
   public void setPosition( int new_positition ) { position = new_positition; }
   public GameBuffer getToken() { return token; }

}
