package gui;

// Copyright Aaron Decker 2012

//import GUI.GameBuffer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;

/**
 * Extended JPanel to allow for drawing of graphics.
 * 
 * @author Aaron Decker
 */

public class GamePanel extends JPanel {
	GameBuffer gbuffer;
	// LinkedList <MouseEvent>mouse_events = new LinkedList<MouseEvent>();
	private boolean is_static; // true if image is not redrawn i.e. do not clear
								// on repaint()

	// Events
	Queue<MouseEvent> mouseEvents;
	Queue<KeyEvent> keyEvents;
	MyMouseAdapter mouseAdapter;
	MyKeyboardAdapter keyboardAdapter;

	public GamePanel(GameBuffer gbuffer) {
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

	public GamePanel(int width, int height, Color color) {
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

	public void setGameBuffer(GameBuffer gbuffer) {
		this.gbuffer = gbuffer;
	}

	/**
	 * Draws the GamePanel
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!is_static)
			gbuffer.clear();
		g.drawImage(gbuffer.getBuffer(), 0, 0, this);
	}

	public MouseEvent getNextMouseEvent() {
		return mouseEvents.remove();
	}

	public KeyEvent getNextKeyEvent() {
		return keyEvents.remove();
	}

	public boolean areMouseEventsLeft() {
		if (mouseEvents.size() > 0)
			return true;
		return false;
	}

	// The following classes are for overriding in subclasses
	// to handle the events that are wanted.
	// Return false from the method to have the event put on the queue.

	protected boolean handleMouseDragged(MouseEvent e) {
		return false;
	}

	protected boolean handleMousePressed(MouseEvent e) {
		return false;
	}

	protected boolean handleMouseReleased(MouseEvent e) {
		return false;
	}

	protected boolean handleMouseEntered(MouseEvent e) {
		return false;
	}

	protected boolean handleMouseExited(MouseEvent e) {
		return false;
	}

	protected boolean handleMouseClicked(MouseEvent e) {
		return false;
	}

    protected boolean handleMouseMoved(MouseEvent e) {
		return false;
	}

	class MyMouseAdapter extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent e)
        {
           handleMouseMoved(e);
        }

		@Override
		public void mouseDragged(MouseEvent e) {
			///if (!handleMouseDragged(e))
			//	mouseEvents.offer(e);
			handleMouseDragged(e);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			//if (!handleMousePressed(e))
			//	mouseEvents.offer(e);
			handleMousePressed(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			//if (!handleMouseReleased(e))
			//	mouseEvents.offer(e);
			handleMouseReleased(e);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			//if (!handleMouseEntered(e))
			//	mouseEvents.offer(e);
			handleMouseEntered(e);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			//if (!handleMouseExited(e))
				//mouseEvents.offer(e);
			handleMouseExited(e);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			//if (!handleMouseClicked(e))
				//mouseEvents.offer(e);
			handleMouseClicked(e);
		}

	};

	class MyKeyboardAdapter implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			//keyEvents.offer(e);
			//System.out.println(e.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent e) {
			//keyEvents.offer(e);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			//keyEvents.offer(e);
		}
	};

	public void setStatic(boolean is_static) {
		this.is_static = is_static;
	}
}
