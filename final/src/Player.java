/**
 * The Player class describes one player in a game of UWPonopoly
 * Copyright Aaron Decker 2012
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;

class Player
{
   public static int TOKEN_SIZE = 10;
   private static int TOKEN_FONT_SIZE = 10;
   private int money;
   private int position;
   private GameBuffer token;
   private char token_char; // even with a custom GameBuffer,
                            // the token_char is used for equals()

   public Player()
   {
   }

   public Player( char token_char )
   {
      this.token_char = token_char;
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

      // generate font
      Font font = new Font("Helvetica", Font.PLAIN, TOKEN_FONT_SIZE);
      FontMetrics fm = g.getFontMetrics(font);
      java.awt.geom.Rectangle2D rect = fm.getStringBounds( Character.toString(token_char), g);
      g.setFont( font );

      g.drawString( Character.toString(token_char), 0, (int)(3*rect.getHeight()/4) );
      return gbuffer;
   }

   public int getPosition() { return position; }
   public void setPosition( int new_position ) { position = new_position; }
   public GameBuffer getToken() { return token; }

   @Override
   public boolean equals( Object o )
   { 
      if( o instanceof  Player )
      {
         Player p = (Player) o;
         return p.token_char == token_char; 
      }
      return false;
   }

   public char getTokenChar() { return token_char; }

}
