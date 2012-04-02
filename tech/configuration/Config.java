import java.io.File;
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
        this.readConfig(CONFIG_PATH);
    }

    public Config(String config_path) {
        this.CONFIG_PATH = config_path;
        this.readConfig(config_path);
    }

    public void readConfig(String path) {

        File config_file = new File(CONFIG_PATH);

        if !(config_file.canRead()) {
            System.out.println('Cannot find configuration file.')''
        }
        else {
            
        }

    }

    public String get(String value) {
    /** Retrive a single configuration value. */

        return "";
    }

    public static void main(String[] args){

        // Instantiate our configuration
        Config c = new Config();
        System.out.println(c.CONFIG_PATH);

    }

}