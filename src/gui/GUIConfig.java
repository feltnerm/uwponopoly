package gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import config.Config;

public class GUIConfig extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenuItem menuItem;

	private JFileChooser fileChooser;
	private SpinnerModel spinnerModel;
	private JSpinner spinner;

	private Config config;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Config c = new Config(false);
					GUIConfig frame = new GUIConfig(c);
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
	public GUIConfig(Config c) {
		this.config = c;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(1, 1));
		setContentPane(contentPane);

		// Set the menu bar
		this.setJMenuBar(setMenuBar());
		this.setContent();
	}

	private JMenuBar setMenuBar() {
		this.menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuItem = new JMenuItem("Load Defaults");
		menuItem.setActionCommand("load_defaults");
		fileMenu.add(menuItem);
		menuItem = new JMenuItem("Load");
		menuItem.setActionCommand("load");
		fileMenu.add(menuItem);
		menuItem = new JMenuItem("Save");
		menuItem.setActionCommand("save");
		fileMenu.add(menuItem);
		menuItem = new JMenuItem("Save As...");
		menuItem.setActionCommand("save_as");
		fileMenu.add(menuItem);
		menuItem = new JMenuItem("Quit");
		menuItem.setActionCommand("quit");
		fileMenu.add(menuItem);

		this.menuBar.add(fileMenu);

		return this.menuBar;
	}

	private Object get(String value)
	{
		return this.config.get(value);
	}

	private void set(String value, String key)
	{
		this.config.set(value, key);
	}

	private void setContent() {
		// Num Players chooser
		spinnerModel = new SpinnerNumberModel(2, 2, 50, 1);
		this.spinner = new JSpinner(spinnerModel);
		this.spinner.setName("Players");
		contentPane.add(this.spinner);

		// Starting Cash chooser
		spinnerModel = new SpinnerNumberModel(150000, 0, 1000000000, 500);
		this.spinner = new JSpinner(spinnerModel);
		this.spinner.setName("Starting Cash");
		contentPane.add(this.spinner);

		// GO Amount Chooser
		spinnerModel = new SpinnerNumberModel(200, 0, 1000000, 100);
		this.spinner = new JSpinner(spinnerModel);
		this.spinner.setName("Reward for GO!");
		contentPane.add(this.spinner);

		// Jail Fine Chooser
		spinnerModel = new SpinnerNumberModel(50, 0, 10000, 50);
		this.spinner = new JSpinner(spinnerModel);
		this.spinner.setName("Jail Fine");
		contentPane.add(this.spinner);

	}

	public void actionPerformed(ActionEvent e) {
		if ("load_defaults".equals(e.getActionCommand()))
		{
			this.config.loadDefaults();
		}
		if ("save".equals(e.getActionCommand()))
		{
			this.config.save();
		}
		if ("save_as".equals(e.getActionCommand()))
		{
			//this.config.save(path)
		}
		if ("load".equals(e.getActionCommand()))
		{
			//this.config.load(path);
		}


	}

}
