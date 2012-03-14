// Copyright Aaron Decker

import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

/**
 * The main class of UWPonopoly
 * @author Aaron Decker
 */

class UWPonopoly implements Runnable
{

   private GameFrame window;
   private GameBuffer backbuffer;
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

      // setup gamepanel
      gamepanel = new GamePanel( backbuffer );
      window.add( gamepanel );
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

};
