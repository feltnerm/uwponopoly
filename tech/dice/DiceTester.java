import javax.swing.JFrame;

class DiceTester
{
   // testbed main
   public static void main( String[] args )
   {
      JFrame window = new JFrame("Dice Tester");
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      window.setSize(250, 250);
      window.setVisible(true);
      
      GUIDice dice = new GUIDice();
   }
}
