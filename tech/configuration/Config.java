import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
    Config

    ~ This class provides methods with which to manage the configuration
    of the program.

    Features:
    - Read / Write settings to / from configuration file
    - Get more settings from command-line args

*/

class Config
{

    private String CONFIG_PATH = "uwponopoly.conf";
    Properties config = new Properties();

    public Config() {
        // Default constructor
        loadConfig(CONFIG_PATH);
    }

    public Config(String config_path) {
        // Parameterized constructor for custom configurations
        this.CONFIG_PATH = config_path;
        loadConfig(config_path);
    }

    public void loadConfig(String path) {
        try {
            this.config.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args){

        // Instantiate our configuration
        Config c = new Config();
        System.out.println(c.get("DEBUG"));
        System.out.println(c.get("PLAYERS"));

    }

}