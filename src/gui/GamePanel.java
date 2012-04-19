package gui;

// Copyright Aaron Decker 2012

//import GUI.GameBuffer;
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

public class GamePanel extends JPanel
{
   GameBuffer gbuffer;
   //LinkedList <MouseEvent>mouse_events = new LinkedList<MouseEvent>();
   private boolean is_static; // true if image is not redrawn i.e. do not clear on repaint()
  
   // Events
   Queue<MouseEvent> mouseEvents;
   Queue<KeyEvent> keyEvents;
   MyMouseAdapter mouseAdapter;
   MyKeyboardAdapter keyboardAdapter;

   public GamePanel(GameBuffer gbuffer)
   {
      this.gbuffer = gbuffer;

      // mouse events
      mouseEvents = new LinkedList<MouseEvent>();

      mouseAdapter = new MyMouseAdapter();
      addMouseListener(mouseAdapter);
      addMouseMotionListener(mouseAdapter);

      // keyboard events
      keyEvents = new LinkedList<KeyEvent>();

      keyboardAdapter = new MyKeyboardAdapter();
      addKeyListener(keyboardAdapter);
      is_static = false;
   }

   public GamePanel(int width, int height, Color color )
   {
      gbuffer = new GameBuffer(width, height, color);

      // mouse events
      mouseEvents = new LinkedList<MouseEvent>();

      mouseAdapter = new MyMouseAdapter();
      addMouseListener(mouseAdapter);
      addMouseMotionListener(mouseAdapter);

      // keyboard events
      keyEvents = new LinkedList<KeyEvent>();

      keyboardAdapter = new MyKeyboardAdapter();
      addKeyListener(keyboardAdapter);
      is_static = false;
   }

   public void setGameBuffer( GameBuffer gbuffer ) 
   { 
	   this.gbuffer = gbuffer; 
   }


   /**
    * Draws the GamePanel
    */
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if(!is_static)
         gbuffer.clear();
      g.drawImage(gbuffer.getBuffer(),0,0,this);
   }

   public MouseEvent getNextMouseEvent()
   {
      return mouseEvents.remove();
   }

   public KeyEvent getNextKeyEvent()
   {
      return keyEvents.remove();
   }

   public boolean areMouseEventsLeft()
   {
      if( mouseEvents.size() > 0 )
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
               mouseEvents.offer(e);
      }

      public void mousePressed(MouseEvent e)
      {
         if( !handleMousePressed( e ) )
            mouseEvents.offer(e);
      }

      public void mouseReleased(MouseEvent e)
      {
         if( !handleMouseReleased( e ) )
            mouseEvents.offer(e);
      }

      public void mouseEntered(MouseEvent e)
      {
         if( !handleMouseEntered( e ) )
               mouseEvents.offer(e);
      }

      public void mouseExited(MouseEvent e)
      {
         if( !handleMouseExited( e ) )
               mouseEvents.offer(e);
      }

      public void mouseClicked(MouseEvent e)
      {
         if( !handleMouseClicked( e ) )
               mouseEvents.offer(e);
      }

   };

   class MyKeyboardAdapter implements KeyListener
   {
      public void keyPressed(KeyEvent e)
      {
         keyEvents.offer(e);
         System.out.println( e.getKeyCode() );
      }

      public void keyReleased(KeyEvent e)  
      {
         keyEvents.offer(e);
      }

      public void keyTyped(KeyEvent e)
      {
         keyEvents.offer(e);
      }
   };

   public void setStatic( boolean is_static )
   {
      this.is_static = is_static;
   }
}
