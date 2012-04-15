package dice;

import GUI.GUIDice;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

class DiceTester
{
   // testbed main
   public static void main( String[] args )
   {
      JFrame window = new JFrame("Dice Tester");
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      window.setSize(250, 250);
      window.setVisible(true);
      window.setLayout( new BorderLayout() );
      
      final GUIDice dice = new GUIDice();
      window.add( dice, BorderLayout.NORTH );

      JButton roll_button = new JButton("Roll");
      roll_button.addActionListener
      (   new ActionListener()
          {
             @Override
             public void actionPerformed(ActionEvent e)
             {
                dice.roll(); // calls the method with code.
             }
          }
      );
      window.add( roll_button, BorderLayout.SOUTH );
      window.pack();
     
   }
}
