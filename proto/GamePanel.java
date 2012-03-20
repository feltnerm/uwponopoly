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

   public GamePanel( int width, int height, Color color )
   {
      gbuffer = new GameBuffer(width, height, color);

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
      gbuffer.clear();
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

   // The following classes are for overriding in subclasses
   // to handle the events that are wanted.
   // Return false from the method to have the event put on the queue.

   protected boolean handleMouseDragged(MouseEvent e) { return false; }
   protected boolean handleMousePressed(MouseEvent e) { return false; }
   protected boolean handleMouseReleased(MouseEvent e) { return false; }
   protected boolean handleMouseEntered(MouseEvent e) { return false; }
   protected boolean handleMouseExited(MouseEvent e) { return false; }
   protected boolean handleMouseClicked(MouseEvent e) { return false; }
   
   class MyMouseAdapter extends MouseAdapter
   {
      public void mouseDragged(MouseEvent e)
      {
         if( !handleMouseDragged( e ) )
               mouse_events.offer(e);
      }

      public void mousePressed(MouseEvent e)
      {
         if( !handleMousePressed( e ) )
            mouse_events.offer(e);
      }

      public void mouseReleased(MouseEvent e)
      {
         if( !handleMouseReleased( e ) )
            mouse_events.offer(e);
      }

      public void mouseEntered(MouseEvent e)
      {
         if( !handleMouseEntered( e ) )
               mouse_events.offer(e);
      }

      public void mouseExited(MouseEvent e)
      {
         if( !handleMouseExited( e ) )
               mouse_events.offer(e);
      }

      public void mouseClicked(MouseEvent e)
      {
         if( !handleMouseClicked( e ) )
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
