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

   // PRIVATE OBJECTS

   private Thread thread;

   // Game Board
   private GameFrame window;
   private GameBuffer backbuffer;
   private GamePanel gamepanel;
   private Property testproperty;

   // Player Stats Panel
   private JPanel dashboard_panel;
   private JPanel dice_panel;
   private JPanel player_stats_panel;
   private JPanel property_context_panel;

   private JButton sell_button;

   static final int TICK_LENGTH_MS = 10;

   // Height & Width of the Board
   static final int DESIRED_WIDTH = 550;
   static final int DESIRED_HEIGHT = 350;

   public static void main(String[] args)
   {
      UWPonopoly uwponopoly = new UWPonopoly();
   }

   UWPonopoly()
   {
      backbuffer = new GameBuffer(DESIRED_WIDTH, DESIRED_HEIGHT, Color.ORANGE);
      
      // GAME WINDOW
      window = new GameFrame("UWPonopoly");
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      window.setSize(DESIRED_WIDTH, DESIRED_HEIGHT);
      //window.setResizable(false);
      window.setVisible(true);
      //window.setLayout( new BoxLayout(window.getContentPane(), BoxLayout.PAGE_AXIS) );
      window.setLayout( new BorderLayout() );

      // GAME PANEL
      gamepanel = new GamePanel( backbuffer );
      //window.getContentPane().add( gamepanel );

      // DASHBOARD
      dashboard_panel = new JPanel();
      dashboard_panel.setLayout( new BoxLayout(dashboard_panel, BoxLayout.PAGE_AXIS) );

      // Property Context Panel
      property_context_panel = new JPanel();
      property_context_panel.setLayout( new BorderLayout());

      ImageIcon property_icon = createImageIcon("images/boardwalk.jpg", "deed");
      
      JButton improve_button  = new JButton("Improve");
      improve_button.setEnabled(false);
      JButton buy_button      = new JButton("Buy"); 
      JButton sell_button     = new JButton("Sell");
      sell_button.setEnabled(false);

      property_context_panel.add( new JLabel(property_icon), BorderLayout.NORTH);
      property_context_panel.add( buy_button, BorderLayout.WEST);
      property_context_panel.add( improve_button, BorderLayout.CENTER);
      property_context_panel.add( sell_button, BorderLayout.EAST );
      
      // Dice Control
      dice_panel = new JPanel();
      dice_panel.setLayout( new BorderLayout() );
      ImageIcon icon = createImageIcon("images/dice.jpg", "dice");
      dice_panel.add( new JLabel(icon), BorderLayout.NORTH );
      dice_panel.add( new JButton("Roll!") , BorderLayout.SOUTH);

      // Player Stats
      player_stats_panel = new JPanel();
      player_stats_panel.setLayout( new BorderLayout() );
      player_stats_panel.add( new JLabel("Money: $ 314,159,265"), BorderLayout.NORTH );
      player_stats_panel.add( new JLabel("Current Player: Pat the Pioneer"), BorderLayout.SOUTH );


      // Add Panels to Dashboard
      dashboard_panel.add( property_context_panel );
      dashboard_panel.add( player_stats_panel );
      dashboard_panel.add( dice_panel );

      // GAME BOARD
      //ImageIcon board_icon = createImageIcon("images/monopoly-board.png","board");
      //window.getContentPane().add( new JLabel(board_icon), BorderLayout.WEST);

      // Properties
      testproperty = new Property( backbuffer );
      window.getContentPane().add( testproperty );
      /*
      window.getContentPane().add( testproperty );
      window.getContentPane().add( new JSeparator(SwingConstants.HORIZONTAL) );
      window.getContentPane().add( dashboard_panel );
      */
      //window.getContentPane().add( testproperty , BorderLayout.NORTH);
      
      // Dashboard Panel
      //window.getContentPane().add( new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.SOUTH );
      window.getContentPane().add( dashboard_panel, BorderLayout.EAST );
      //dashboard_panel.add( new JLabel("Current Player: Pat the Pioneer") );
      //dice_panel.add( new JLabel("Dice", icon, JLabel.CENTER) , BorderLayout.NORTH);

      //window.getContentPane().add( new JProgressBar(5,10) );

      // Pack it and Start the Thread
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
