package gui;
/**  

 @author UWP_User 
*/

// Import Java Packages
import java.awt.FlowLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gui.GameFrame;


public class GUIWindow extends GameFrame implements ActionListener
{
	private static String TITLE = "UWPonopoly";
	private JMenuBar menuBar;
	private JMenuItem menuItem;
	
    // Height & Width of the Window
    final int WINDOW_WIDTH = 550;
    final int WINDOW_HEIGHT = 350;
    //final FlowLayout layout = new FlowLayout();
	
	public GUIWindow()
	{
		super(TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.WINDOW_HEIGHT, this.WINDOW_WIDTH);
		this.setLayout( new FlowLayout() );
		
		this.menuBar = new JMenuBar();
			JMenu fileMenu = new JMenu("File");
			menuItem = new JMenuItem("New");
			menuItem.setActionCommand("new");
			menuItem.addActionListener(this);
			fileMenu.add(menuItem);
			
			menuItem = new JMenuItem("Quit");
			menuItem.setActionCommand("quit");
			menuItem.addActionListener(this);
			fileMenu.add(menuItem);
			
			
			JMenu editMenu = new JMenu("Edit");
			menuItem = new JMenuItem("Board");
			menuItem.setActionCommand("edit_board");
			menuItem.addActionListener(this);
			editMenu.add(menuItem);
			
			menuItem = new JMenuItem("Rules");
			menuItem.setActionCommand("edit_rules");
			menuItem.addActionListener(this);
			editMenu.add(menuItem);
			
			JMenu aboutMenu = new JMenu("About");
			menuItem.setActionCommand("about");
			menuItem.addActionListener(this);
			menuItem = new JMenuItem("Credits");
			menuItem.setActionCommand("credits");
			menuItem.addActionListener(this);
			aboutMenu.add(menuItem);
		
			this.menuBar.add(fileMenu);
		this.menuBar.add(editMenu);
		this.menuBar.add(aboutMenu);
        this.setJMenuBar(menuBar);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if ("new".equals(e.getActionCommand()))
		{
			System.out.print("New");
		} else if ("quit".equals(e.getActionCommand()))
		{
			System.exit(0);
		} else if ("edit_board".equals(e.getActionCommand()))
		{
			System.out.print("Edit Board");
		} else if ("edit_rules".equals(e.getActionCommand()))
		{
			System.out.print("Edit Rules");
		} else if ("about".equals(e.getActionCommand()))
		{
			System.out.print("About");
		} else if ("credits".equals(e.getActionCommand()))
		{
			System.out.print("Credits");
		} else {
			
		}
		
	}
	
}
