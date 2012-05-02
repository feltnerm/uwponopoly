package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.border.EmptyBorder;

import config.Config;

public class GUIConfig extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	
	private Config config;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIConfig frame = new GUIConfig();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIConfig() {
		this.config = config;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// Set the menu bar
		this.setJMenuBar(setMenuBar());
	}
	
	private JMenuBar setMenuBar()
	{
		this.menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(new JMenuItem("New"));
		fileMenu.add(new JMenuItem("Save"));
		fileMenu.add(new JMenuItem("Save As..."));
		fileMenu.add(new JMenuItem("Quit"));

		this.menuBar.add(fileMenu);
	
		return this.menuBar;
	}

}
