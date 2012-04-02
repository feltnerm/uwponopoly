import java.util.Random;

/**
 * Simulates rolling two dice, as used in Monopoly.
 * This gives it a range of 2-12 for possible values.
 */
class Dice
{
   private int dice1, dice2;


   public Dice()
   {
   }

   public void roll()
   {
   }

   // returns true if the roll is doubles
   public boolean isDoubles() { return dice1 == dice2; }

   // testbed main
   public static void main( String[] args )
   {
      System.out.println("Dice Testbed.");
   }
}
