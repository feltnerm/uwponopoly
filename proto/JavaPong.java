// Copyright Aaron Decker

import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

/**
 * The main class of JavaPong
 * @author Aaron Decker
 */

class JavaPong implements Runnable
{

   private GameFrame window;
   private GamePanel gamepanel;
   private GameBuffer backbuffer;
   private Thread thread;

   static final int TICK_LENGTH_MS = 10;

   // desired height and width for the window
   static final int DESIRED_WIDTH = 550;
   static final int DESIRED_HEIGHT = 350;

   // testing code
   Sprite test;

   public static void main(String[] args)
   {
      JavaPong javapong = new JavaPong();
      // goes to JavaPong() constructor from here
   }

   JavaPong()
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

      // testing code
     test = new Sprite( backbuffer.getGraphics() );

     // game code
     ball = new Sprite( backbuffer.getGraphics() );


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

         // tick sprites 
         test.tick(TICK_LENGTH_MS);

         handleMouseEvents();
         handleKeyEvents();

         // draw
         backbuffer.clear();
         test.draw();
         gamepanel.repaint();

         //check for bounds
         if( !test.isInsideRectangle( 0, 0, DESIRED_WIDTH, DESIRED_HEIGHT) )
         {
            test.inverseXVel();
            test.inverseYVel();
         }
      }
   }

   public void handleMouseEvents()
   {
      try
      {
         MouseEvent e = gamepanel.getNextMouseEvent();
         test.setX( e.getX() );
         test.setY( e.getY() );
         if( e.getID() == MouseEvent.MOUSE_RELEASED )
         {
            // just a sample of how to test the event type
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
                  test.setXAccel(-150);
                  break;
               case KeyEvent.VK_RIGHT:
                  test.setXAccel(150);
                  break;
               case KeyEvent.VK_DOWN:
                  test.setYAccel(150);
                  break;
               case KeyEvent.VK_UP:
                  test.setYAccel(-150);
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
                  test.setXAccel(0);
                  break; 
               case KeyEvent.VK_UP:
               case KeyEvent.VK_DOWN:
                  test.setYAccel(0);
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
