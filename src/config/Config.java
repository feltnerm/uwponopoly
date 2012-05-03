package config;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Config
 * 
 * ~ This class provides methods with which to manage the configuration of the
 * program.
 * 
 * Features: - Read / Write settings to / from configuration file - Get more
 * settings from command-line args
 */

public class Config extends Properties {

	// Path to default configuration file.
	private static String CONFIG_PATH = "etc/uwponopoly.conf";
	private static FileReader config_file_reader;
	private static FileOutputStream config_file_writer;
	private boolean DEBUG;

	public Config(boolean debug) {
		super();
		this.DEBUG = debug;
	}

	public void load() throws IOException{
		// Default loader of a configuration file
		try {
			config_file_reader = new FileReader(CONFIG_PATH);
			super.load(config_file_reader);
		} catch (IOException e) {
			System.out.println("Configuration file at " + CONFIG_PATH
					+ " not found");
            throw e;
		}
	}

	public void load(String path) throws IOException{
		CONFIG_PATH = path;
		load();
	}

	public void save() {
		try {
			config_file_writer = new FileOutputStream(CONFIG_PATH);
			super.store(config_file_writer, "");
		} catch (IOException e) {
			System.out.println("Could not write to config file at "
					+ CONFIG_PATH);
		}
	}

	public void save(String path) {
		CONFIG_PATH = path;
		save();
	}

	public String get(String key) {
		return super.getProperty(key);
	}

	public Object set(String key, String value) {
		return super.setProperty(key, value);
	}

	public void print() {
		Enumeration keys = this.keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = this.get(key);
			System.out.println(key + ":" + value);
		}

	}

	public static void main(String[] args) {
		Config config = new Config(true);
        try
        {
           config.load();
        }
        catch( IOException e )
        { }
		config.print();
	}
}
