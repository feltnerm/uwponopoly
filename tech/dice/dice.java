import java.util.Random;

/**
 * Simulates rolling two dice, as used in Monopoly.
 * This gives it a range of 2-12 for possible values.
 */
class Dice
{
   private static int HIGH_NUMBER = 6; // highest number on die
   private int dice1, dice2;
   private Random random;

   public Dice()
   {
      random = new Random();
      roll(); // otherwise both die would be zeroes.
   }

   public void roll()
   {
      // A note on the method used:
      // According to http://docs.oracle.com/javase/6/docs/api/java/util/Random.html,
      //    nextInt( n ) "returns a pseudorandom, uniformly distributed int value between 0 (inclusive) 
      //    and the specified value (exclusive), drawn from this random number generator's sequence."
      // Thus we add one to nextInt( 6 ) ( for a six-sided die ) to get a random number 1-6.
      dice1 = random.nextInt( HIGH_NUMBER ) + 1;
      dice2 = random.nextInt( HIGH_NUMBER ) + 1;
   }

   public int getTotal()
   {
      return dice1 + dice2;
   }

   // formats the dice as a Cartesian coordinate (x,y)
   @Override
   public String toString()
   {
      return "(" + dice1 + "," + dice2 + ")";
   }

   // returns true if the roll is doubles
   public boolean isDoubles() { return dice1 == dice2; }

   public int getFirstDie() { return dice1; }
   public int getSecondDie() { return dice2; }

   // testbed main
   public static void main( String[] args )
   {
      // declare the test object
      Dice d = new Dice();

      // print a banner
      System.out.println("Dice Testbed");
      System.out.println("============");

      // roll the dice and print the output
      for( int i = 0; i < 10; i++ )
      {
         d.roll();
         System.out.format( d.toString() + " | %2d | " + d.isDoubles() + "\n", d.getTotal() );
      }

   }
}
