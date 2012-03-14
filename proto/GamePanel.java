// Copyright Aaron Decker 2012

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.*;

/**
 * Extended JPanel to allow for drawing of graphics.
 * @author Aaron Decker
 */

class GamePanel extends JPanel
{
   GameBuffer gbuffer;
   //LinkedList <MouseEvent>mouse_events = new LinkedList<MouseEvent>();
  
   // Events
   Queue<MouseEvent> mouse_events;
   Queue<KeyEvent> key_events;
   MyMouseAdapter mouse_adapter;
   MyKeyboardAdapter keyboard_adapter;

   public GamePanel( GameBuffer gbuffer)
   {
      this.gbuffer = gbuffer;

      // mouse events
      mouse_events = new LinkedList<MouseEvent>();

      mouse_adapter = new MyMouseAdapter();
      addMouseListener(mouse_adapter);
      addMouseMotionListener(mouse_adapter);

      // keyboard events
      key_events = new LinkedList<KeyEvent>();

      keyboard_adapter = new MyKeyboardAdapter();
      addKeyListener(keyboard_adapter);
   }


   /**
    * Draws the GamePanel
    */
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.drawImage(gbuffer.getBuffer(),0,0,this);
   }

   public MouseEvent getNextMouseEvent()
   {
      return mouse_events.remove();
   }

   public KeyEvent getNextKeyEvent()
   {
      return key_events.remove();
   }

   public boolean areMouseEventsLeft()
   {
      if( mouse_events.size() > 0 )
         return true;
      return false;
   }
   
   class MyMouseAdapter extends MouseAdapter
   {
      public void mouseDragged(MouseEvent e)
      {
         mouse_events.offer(e);
      }

      public void mousePressed(MouseEvent e)
      {
         mouse_events.offer(e);
      }

      public void mouseReleased(MouseEvent e)
      {
         mouse_events.offer(e);
      }

   };

   class MyKeyboardAdapter implements KeyListener
   {
      public void keyPressed(KeyEvent e)
      {
         key_events.offer(e);
         System.out.println( e.getKeyCode() );
      }

      public void keyReleased(KeyEvent e)  
      {
         key_events.offer(e);
      }

      public void keyTyped(KeyEvent e)
      {
         key_events.offer(e);
      }
   };
};
