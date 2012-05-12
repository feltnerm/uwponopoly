package config;

import game.Game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ArrayList;
import java.util.Arrays;

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
	private static String DEFAULT_CONFIG_PATH = "etc/uwponopoly.conf";
    private File configFile;
    private File[] configPaths = new File[] {
                                    new File("etc/uwponpoly.conf"),
                                    new File("/etc/uwponopoly.conf"),
                                    new File("../etc/uwponopoly.conf"),
                                    new File("~/uwponopoly.conf")
                                 };
	private static FileOutputStream config_file_writer;
	private boolean DEBUG;

	/**
    * constructor
    *
    * @param    debug   Output debug statements or not.
    */
   public Config(boolean debug) {
		super();
		this.DEBUG = debug;
        this.chooseConfig();
	}

    public void setFile(String path)
    {
        this.configFile = new File(path);
    }

    private void chooseConfig()
    {
        for (File f : this.configPaths)
        {
            if (f.canRead())
            {
                this.configFile = f;
            }
        }

        if (this.configFile == null || !this.configFile.canRead())
        {
            this.loadDefaults();
        }
    }


    /**
    * Load a configuration file.
    */
    public void load() throws IOException
    {
        try
        {
            FileReader config_file_reader = new FileReader(this.configFile.getAbsolutePath());
            super.load(config_file_reader);
        } catch (IOException e)
        {
            System.out.println("Config file could not be loaded at:"+
                this.configFile.getAbsolutePath());
            throw e;
        }
    }

    public void loadDefaults()
    {

    }

   /**
    * Save a file.
    */
	public void save() 
    {
		try 
        {
			FileOutputStream config_file_writer = 
                new FileOutputStream(this.configFile.getAbsolutePath());
			super.store(config_file_writer, "");
		} 
        catch (IOException e) {
			System.out.println("Could not write to config file at "
					+ this.configFile.getAbsolutePath());
		}
	}

   /**
    * Save a configuration file.
    *
    * @param    path    The absolute path to save the file to.
    */
	public void save(String path) {
        File f = new File(path);
        if (f.canWrite())
        {
            this.configFile = f;
            save();    
        }
		
	}

   /**
    * Get a configuration value
    *
    * @param    key to lookup (see {@link game.Game}'s default settings.
    * 
    * @return   the value of key.
    */
	public String get(String key) {
		return super.getProperty(key);
	}

   /**
    * Set a configuration value
    *
    * @param    key     The option being set
    * @param    value   The value of the option
    * @return   The objec that was created.
    */
	public Object set(String key, String value) {
		return super.setProperty(key, value);
	}

   /**
    * Print the entire configuration.
    */
	public void print() {
		Enumeration keys = this.keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = this.get(key);
			System.out.println(key + ":" + value);
		}

	}

}
