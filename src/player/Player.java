package player;

import java.util.ArrayList;

import board.Space;
import gui.GameBuffer;


/**
 * The Player class describes one player in a game of UWPonopoly
 * Copyright Aaron Decker 2012
 */

public class Player
{
   private int money = 1500;
   private int position;
   private String name;
   private char token_char; // even with a custom GameBuffer,
                            // the token_char is used for equals()

   private ArrayList<Space> properties = new ArrayList<Space>();

   public Player()
   {
      // Default money
      this(200);
   }

   public Player(int money)
   {
      this.money = money;
      this.position = 0;
   }

   public int getMoney(){ return this.money; }
   public void addMoney(int amount){ this.money += amount; }
   public void subtractMoney(int amount)
   {
      if (this.money - amount > 0)
      {
         this.money -= amount;
      }
   }

   public int getPosition(){ return this.position; }
   public void setPosition(int value) { position = value; }

   public void buyProperty(Space s)
   {
      if (s.price <= this.money)
      {
         s.setOwner(this);
         this.subtractMoney(s.price);
         this.properties.add(s);
      }
   }

   public void removeProperty(Space s)
   {
      this.properties.remove(s);
   }

   public char getTokenChar() { return token_char; }
   
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

   /**
    * Why introduce a GameBuffer into Player?
    * Because each Space has a list of Players,
    * But GUISpace needs to be able to get an image.
    * Therefore, Player must have the methods that GUIPlayer needs.
    * --Aaron
    */
   public GameBuffer getToken(){ return null; }

}
