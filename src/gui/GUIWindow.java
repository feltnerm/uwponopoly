/**  

 @author UWP_User 
*/

import gui.GameFrame;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GUIWindow extends GameFrame
{
	
	private FlowLayout LAYOUT;
	private JMenuBar menuBar;
	
	public GUIWindow(String title, int height, int width)
	{
		super(title);
		init(height, width);

	}
	
	private void init(int height, int width)
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(height, width);
		this.setLayout(this.LAYOUT);
		
		this.menuBar = new JMenuBar();
			JMenu fileMenu = new JMenu("File");
			fileMenu.add(new JMenuItem("New"));
			fileMenu.add(new JMenuItem("Quit"));
			
			JMenu aboutMenu = new JMenu("About");
			aboutMenu.add(new JMenuItem("Credits"));
		this.menuBar.add(fileMenu);
		this.menuBar.add(aboutMenu);
	}
	
}
