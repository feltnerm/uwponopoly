// Copyright Aaron Decker

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.*;
import java.util.*;

/**
 * The main class of UWPonopoly
 * @author Aaron Decker
 */

class UWPonopoly implements Runnable
{

   // widgets
   private GameFrame window;
   private GameBuffer backbuffer;
   private JPanel dashboard_panel;
   private JPanel dice_panel;
   private JPanel player_stats_panel;
   private JPanel property_context_panel;

   private JButton sell_button;


   private Thread thread;

   static final int TICK_LENGTH_MS = 10;

   // desired height and width for the window
   static final int DESIRED_WIDTH = 550;
   static final int DESIRED_HEIGHT = 350;

   // testing code
   private GamePanel gamepanel;
   private Property testproperty;

   public static void main(String[] args)
   {
      UWPonopoly uwponopoly = new UWPonopoly();
   }

   UWPonopoly()
   {
      backbuffer = new GameBuffer(DESIRED_WIDTH,DESIRED_HEIGHT, Color.ORANGE);
      // setup window
      window = new GameFrame("Java Pong");
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      window.setSize(DESIRED_WIDTH,DESIRED_HEIGHT);
      //window.setResizable(false);
      window.setVisible(true);
      //window.setLayout( new BoxLayout(window.getContentPane(), BoxLayout.PAGE_AXIS) );
      window.setLayout( new BorderLayout() );

      // setup gamepanel
      gamepanel = new GamePanel( backbuffer );
      //window.getContentPane().add( gamepanel );

      // PROPERTY CONTEXT PANEL - test code
      // @todo: Separate module
      // @todo: make it work (?)
      property_context_panel = new JPanel();
      property_context_panel.setLayout( new BorderLayout());
      //property_context_panel.add( new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.NORTH);
      property_context_panel.add( new JLabel("Connecticut Ave."), BorderLayout.NORTH);
      property_context_panel.add( new JButton("Buy"), BorderLayout.WEST);
      sell_button = new JButton("Sell");
      sell_button.setEnabled(false);
      property_context_panel.add( sell_button, BorderLayout.EAST ); 

      dashboard_panel = new JPanel();
      dashboard_panel.setLayout( new FlowLayout() );
      dice_panel = new JPanel();
      dice_panel.setLayout( new BorderLayout() );
      player_stats_panel = new JPanel();
      player_stats_panel.setLayout( new BorderLayout() );
      player_stats_panel.add( new JLabel("Money: $ 314,159,265"), BorderLayout.NORTH );
      player_stats_panel.add( new JLabel("Current Player: Pat the Pioneer"), BorderLayout.SOUTH );

      // test code
      testproperty = new Property( backbuffer );
      /*window.getContentPane().add( testproperty );
      window.getContentPane().add( new JSeparator(SwingConstants.HORIZONTAL) );
      window.getContentPane().add( dashboard_panel );*/
      //window.getContentPane().add( testproperty , BorderLayout.NORTH);
      ImageIcon board_icon = createImageIcon("images/monopoly-board.png","board");
      window.getContentPane().add( new JLabel(board_icon), BorderLayout.NORTH );
      window.getContentPane().add( new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.SOUTH );
      window.getContentPane().add( dashboard_panel, BorderLayout.SOUTH );
      //dashboard_panel.add( new JLabel("Current Player: Pat the Pioneer") );
      dashboard_panel.add( property_context_panel );
      dashboard_panel.add( player_stats_panel );
      dashboard_panel.add( dice_panel );
      ImageIcon icon = createImageIcon("images/dice.jpg", "dice");
      //dice_panel.add( new JLabel("Dice", icon, JLabel.CENTER) , BorderLayout.NORTH);
      dice_panel.add( new JLabel(icon), BorderLayout.NORTH );
      dice_panel.add( new JButton("Roll!") , BorderLayout.SOUTH);
      //window.getContentPane().add( new JProgressBar(5,10) );


      window.pack();

      thread = new Thread(this);
      thread.start();
   }

   /** Implemented from Runnable
    *
    */
   public void run()
   {
      Thread current = Thread.currentThread();

      while( current == thread )
      {
         // throttle frame rate
         try
         {
            Thread.sleep(TICK_LENGTH_MS);
         }
         catch( InterruptedException e )
         {
            e.printStackTrace();
         }

         handleMouseEvents();
         handleKeyEvents();

         // draw
         backbuffer.clear();
         gamepanel.repaint();
         testproperty.repaint();

      }
   }

   public void handleMouseEvents()
   {
      try
      {
         MouseEvent e = gamepanel.getNextMouseEvent();
         if( e.getID() == MouseEvent.MOUSE_RELEASED )
         {
         }

      }
      catch ( java.util.NoSuchElementException exception )
      {
         // no events on queue, so nothing to do
      }
   }

   public void handleKeyEvents()
   {
      try
      {
         KeyEvent e = window.getNextKeyEvent();
         if( e.getID() == KeyEvent.KEY_PRESSED )
         {
            switch( e.getKeyCode() )
            {
               case KeyEvent.VK_LEFT:
                  break;
               case KeyEvent.VK_RIGHT:
                  break;
               case KeyEvent.VK_DOWN:
                  break;
               case KeyEvent.VK_UP:
                  break;
               default:
                  break;
            }
         }
         if( e.getID() == KeyEvent.KEY_RELEASED )
         {
            switch( e.getKeyCode() )
            {
               case KeyEvent.VK_LEFT:
               case KeyEvent.VK_RIGHT:
                  break; 
               case KeyEvent.VK_UP:
               case KeyEvent.VK_DOWN:
               default:
                  break;
            }
         }
      }
      catch ( java.util.NoSuchElementException exception )
      {
         // no events on queue, so nothing to do
      }
   }

   // the following function is copyrighted!
   // source: http://docs.oracle.com/javase/tutorial/uiswing/examples/components/LabelDemoProject/src/components/LabelDemo.java
   /** Returns an ImageIcon, or null if the path was invalid. */
   protected static ImageIcon createImageIcon(String path,
         String description) {
      java.net.URL imgURL = UWPonopoly.class.getResource(path);
      if (imgURL != null) {
         return new ImageIcon(imgURL, description);
      } else {
         System.err.println("Couldn't find file: " + path);
         return null;
      }
   }

};
