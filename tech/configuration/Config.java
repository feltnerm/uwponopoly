import java.util.Enumeration;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Hashtable;

/**
    Config

    ~ This class provides methods with which to manage the configuration
    of the program.

    Features:
    - Read / Write settings to / from configuration file
    - Get more settings from command-line args

*/

class Config extends Properties
{

    // Path to default configuration file.
    private String CONFIG_PATH = "uwponopoly.conf";
    private FileReader config_file;

    public Config() {
        // Default constructor
        super();
        this.load(CONFIG_PATH);
    }

    public Config(String config_path) {
        // Parameterized constructor for custom configurations
        super();
        this.CONFIG_PATH = config_path;
        this.load(config_path);
    }

    /**
    public Config(String config_path, Map<String k, String v> config) {
        super();
        this.CONFIG_PATH = config_path;
        this.putAll(config);
        FileWriter file = new FileWriter(this.CONFIG_PATH);
        this.store(file, "DERP");
    }
    */

    private void load(String path){
        try {
            this.config_file = new FileReader(path);
            super.load(this.config_file);
        } catch (IOException e) {
            System.out.println(path + " not found.");
        }
    }

    public void print() {
        Enumeration keys = this.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = this.get(key);
            System.out.println(key + ":" + value);
        }

    }

    public static void main(String[] args){

        // Instantiate our configuration
        Config c = new Config();
        
        // Print the Configuration
        c.print();

        // Get values
        System.out.println("DEBUG: "+c.get("DEBUG"));
        System.out.println("PLAYERS: "+c.get("PLAYERS"));

        // Declare custom configuration
        Config c1 = new Config("uwponopoly.conf");
        c1.print();

        // Error handling
        Config c2 = new Config("derp");
        System.out.println("DERP: "+c.get("HERP"));

        // Saving a new config
        Hashtable opts = new Hashtable();
        opts.put("DEBUG", "True");
        opts.put("PLAYERS", "2");
        Config c3 = new Config("test.conf", opts);
    }

}
