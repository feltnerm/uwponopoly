// Copyright Aaron Decker

import GUI.GamePanel;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.*;
import java.util.*;
import GUI.GameFrame;
import javax.swing.*;

import board.Board;
import board.Space;

import player.Player;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
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
   private Board board;
   private GUIDice dice;

   private JMenuBar menuBar;
   private JMenu fileMenu, aboutMenu;
   private JMenuItem newMenuItem, quitMenuItem, creditsMenuItem;

   // Player Stats Panel
   private JPanel dashboard_panel;
   private JPanel dice_panel;
   private JPanel player_stats_panel;
   private JPanel property_context_panel;
   private GamePanel deed_panel;

   private JButton sell_button;
   private JButton roll_button;
   private JButton done_with_turn_button;

   static final int TICK_LENGTH_MS = 10;

   // Height & Width of the Window
   static final int DESIRED_WIDTH = 550;
   static final int DESIRED_HEIGHT = 350;

   // Event and game state variables
   Player current_player;

   // Testing code
   Player test_player;
   Player test_b;
   Player test_c;
   Player test_d;
   Player test_e;
   Player test_f;

   public static void main(String[] args)
   {
      UWPonopoly uwponopoly = new UWPonopoly();
   }

   UWPonopoly()//{{{
   {
      
      // GAME WINDOW
      window = new GameFrame("UWPonopoly");
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      window.setSize(DESIRED_WIDTH, DESIRED_HEIGHT);
      //window.setResizable(false);
      window.setVisible(true);
      //window.setLayout( new BoxLayout(window.getContentPane(), BoxLayout.PAGE_AXIS) );
      //window.setLayout( new BorderLayout() );
      window.setLayout( new FlowLayout() );

      // Game Board
      board = new Board();

      // Dice
      dice = new GUIDice();

      // Menu Bar
      menuBar = new JMenuBar();
      fileMenu = new JMenu("File");
      newMenuItem = new JMenuItem("New");
      quitMenuItem = new JMenuItem("Quit");
      fileMenu.add(newMenuItem);
      fileMenu.add(quitMenuItem);
      aboutMenu = new JMenu("About");
      creditsMenuItem = new JMenuItem("Credits");
      aboutMenu.add(creditsMenuItem);
      menuBar.add(fileMenu);
      menuBar.add(aboutMenu);

      window.setJMenuBar(menuBar);

      // DASHBOARD
      dashboard_panel = new JPanel();
      dashboard_panel.setLayout( new BoxLayout(dashboard_panel, BoxLayout.PAGE_AXIS) );

      // Property Context Panel
      property_context_panel = new JPanel();
      property_context_panel.setLayout( new BorderLayout());

      //ImageIcon property_icon = createImageIcon("images/boardwalk.jpg", "deed");
      deed_panel = new GamePanel(500,100, Color.WHITE); // we don't need the default GameBuffer
      deed_panel.setStatic(true); // no clear() on redraw
      deed_panel.setPreferredSize( new Dimension(Space.DEED_WIDTH,Space.DEED_HEIGHT) );
      board.setDeedPanel( deed_panel );
      
      JButton improve_button  = new JButton("Improve");
      improve_button.setEnabled(false);
      JButton buy_button      = new JButton("Buy"); 
      JButton sell_button     = new JButton("Sell");
      sell_button.setEnabled(false);

      //property_context_panel.add( new JLabel(property_icon), BorderLayout.NORTH);
      property_context_panel.add( deed_panel, BorderLayout.NORTH);
      property_context_panel.add( buy_button, BorderLayout.WEST);
      property_context_panel.add( improve_button, BorderLayout.CENTER);
      property_context_panel.add( sell_button, BorderLayout.EAST );
      
      // Dice Control
      dice_panel = new JPanel();
      dice_panel.setLayout( new BorderLayout() );
      //ImageIcon icon = createImageIcon("images/dice.jpg", "dice");
      //dice_panel.add( new JLabel(icon), BorderLayout.NORTH );
      dice_panel.add( dice, BorderLayout.NORTH );
      roll_button = new JButton("Roll!");
      roll_button.setPreferredSize(new Dimension(25,50));
      roll_button.addActionListener // make the roll button roll the dice
         (   new ActionListener()
             {
             @Override
             public void actionPerformed(ActionEvent e)
             {
                doRoll(); 
             }
             }
         );
      dice_panel.add(roll_button, BorderLayout.SOUTH);

      // Player Stats
      player_stats_panel = new JPanel();
      player_stats_panel.setLayout( new BorderLayout() );
      player_stats_panel.add( new JLabel("Money: $ 314,159,265"), BorderLayout.NORTH );
      player_stats_panel.add( new JLabel("Current Player: Pat the Pioneer"), BorderLayout.SOUTH );

      // Done with turn button


      // Add Panels to Dashboard
      dashboard_panel.add( property_context_panel );
      dashboard_panel.add( player_stats_panel );
      dashboard_panel.add( dice_panel );

      window.getContentPane().add( board );
      window.getContentPane().add( dashboard_panel );

      // Pack it and Start the Thread
      window.pack();
      thread = new Thread(this);
      thread.start();

      // testing code
      test_player = new Player('A');
      test_b = new Player('B');
      test_c = new Player('C');
      test_d = new Player('D');
      test_e = new Player('E');
      test_f = new Player('F');
      board.addPlayerToSpace( 0, test_player );
      board.addPlayerToSpace( 0, test_b );
      board.addPlayerToSpace( 0, test_c );
      board.addPlayerToSpace( 0, test_d );
      board.addPlayerToSpace( 0, test_e );
      board.addPlayerToSpace( 0, test_f );

      // testing code
      current_player = test_player;
   }//}}}

   /**
    * Event handler called whenever the dice roll button is pushed.
    */
   private void doRoll()
   {
      dice.roll();

      // remove the player from current location
      if( !board.isValidPosition( current_player.getPosition() ) )
      {
         System.out.println("Player " + current_player.getTokenChar() + " has an invalid position");
         return;
      }
      board.getSpace( current_player.getPosition() ).removePlayer( current_player );

      // calculate the new position and add the player to it
      int new_position = current_player.getPosition() + dice.getTotal();
      if( new_position >= board.getNumberOfSpaces() ) // handle board wrap-around
      {
         // TODO credit the player with the "Passing Go" salary
      }
      new_position = board.returnValidPosition( new_position );
      //current_player.setPosition( new_position );
      //board.getSpace( current_player.getPosition() ).addPlayer( current_player );
      board.addPlayerToSpace( new_position, current_player);
   }

   /** Implemented from Runnable
    *
    */
   public void run()//{{{
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

         //board.tick( TICK_LENGTH_MS );
   //      board.repaint();
      //   deed_panel.repaint();

      }
   }//}}}

   public void handleMouseEvents()//{{{
   {
      try
      {
         MouseEvent e = board.getNextMouseEvent();
         if( e.getID() == MouseEvent.MOUSE_RELEASED )
         {
         }

      }
      catch ( java.util.NoSuchElementException exception )
      {
         // no events on queue, so nothing to do
      }
   }//}}}

   public void handleKeyEvents()//{{{
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
   }//}}}

};
