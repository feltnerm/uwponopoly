package gui;

// Copyright Aaron Decker 2012

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;

/**
 * Extended JFrame to allow for handling of keyboard input. This file was based
 * off of GamePanel.java. The mouse handling has been commented out since it is
 * redundant.
 * 
 * @author Aaron Decker
 */

class GameFrame extends JFrame {
	// LinkedList <MouseEvent>mouse_events = new LinkedList<MouseEvent>();
	// Queue<MouseEvent> mouse_events;
	Queue<KeyEvent> key_events;
	// /MyMouseAdapter mouse_adapter;
	MyKeyboardAdapter keyboard_adapter;

	public GameFrame(String frame_title) {
		super(frame_title);
		// mouse events
		// mouse_events = new LinkedList<MouseEvent>();

		/*
		 * mouse_adapter = new MyMouseAdapter();
		 * addMouseListener(mouse_adapter);
		 * addMouseMotionListener(mouse_adapter);
		 */

		// keyboard events
		key_events = new LinkedList<KeyEvent>();

		keyboard_adapter = new MyKeyboardAdapter();
		addKeyListener(keyboard_adapter);
	}

	/*
	 * public MouseEvent getNextMouseEvent() { return mouse_events.remove(); }
	 */

	public KeyEvent getNextKeyEvent() {
		return key_events.remove();
	}

	/*
	 * public boolean areMouseEventsLeft() { if( mouse_events.size() > 0 )
	 * return true; return false; }
	 */

	/*
	 * class MyMouseAdapter extends MouseAdapter { public void
	 * mouseDragged(MouseEvent e) { mouse_events.offer(e); }
	 * 
	 * public void mousePressed(MouseEvent e) { mouse_events.offer(e); }
	 * 
	 * public void mouseReleased(MouseEvent e) { mouse_events.offer(e); }
	 * 
	 * };
	 */

	class MyKeyboardAdapter implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			key_events.offer(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			key_events.offer(e);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			key_events.offer(e);
		}
	}
}
