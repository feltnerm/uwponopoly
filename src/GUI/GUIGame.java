package GUI;

import Config.Config;

public class GUIGame 
{
    private static boolean DEBUG;

    Config config;

    public GUIGame(boolean debug)
    {
        this(deubg, new Config());
    }

    public GUIGame(boolean debug, Config config)
    {
        this.DEBUG = debug;
        this.config = Config;
    }

    public void gameStart()
    {
        // start the game!
    }
}