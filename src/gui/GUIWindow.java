package gui;
/**  

 @author UWP_User 
*/

// Import Java Packages
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

// Import GUI Mechanics
import gui.GameFrame;

public class GUIWindow extends GameFrame
{
	private static String TITLE = "UWPonopoly";
	
    // Height & Width of the Window
    final int WINDOW_WIDTH = 550;
    final int WINDOW_HEIGHT = 350;
    //final FlowLayout layout = new FlowLayout();

	private JMenuBar menuBar;
	
	public GUIWindow()
	{
		super(TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.WINDOW_HEIGHT, this.WINDOW_WIDTH);
		this.setLayout( new FlowLayout() );
		
		this.menuBar = new JMenuBar();
			JMenu fileMenu = new JMenu("File");
			fileMenu.add(new JMenuItem("New"));
			fileMenu.add(new JMenuItem("Quit"));
			
			JMenu aboutMenu = new JMenu("About");
			aboutMenu.add(new JMenuItem("Credits"));
		this.menuBar.add(fileMenu);
		this.menuBar.add(aboutMenu);
        setJMenuBar(menuBar);
	}
	
}
