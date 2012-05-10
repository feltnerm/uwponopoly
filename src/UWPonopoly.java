/**   
 */

import game.Game;
import gui.GUIGame;

public class UWPonopoly {
	private boolean GUI;
	private boolean DEBUG;
        
        /**
         * The runner for the {@link gui.GUIGame} and {@link game.Game}
         *
         * @param   gui     Whether to run as a GUI app or not
         * @param   debug   Output debug statements if true.
         */
	public UWPonopoly(boolean gui, boolean debug) {

		this.GUI = gui;
		this.DEBUG = debug;
	}

        /**
         * Initializes and starts the game
         */
	public void play() {
		if (this.GUI) {
			GUIGame game = new GUIGame(this.DEBUG);
			game.initGame();
			game.startGame();
		} else {
			Game game = new Game(this.DEBUG);
			game.initGame();
			game.startGame();
            game.loop();
		}

	}

	public static void main(String[] args) {
		boolean gui = false;
		boolean debug = false;
		for (int i = 0; i < args.length; i++)
			System.out.println(args[i]);

		if (args.length > 0) {
			if (args[0].equals("help")) {
				System.out.println("% java UWPonopoly2 [gui] [debug]");
			} else if (args[0].equals("gui")) {
					gui = true;
			} else if (args[0].equals("debug")) {
					debug = true;
			}
		}

		UWPonopoly uwponopoly = new UWPonopoly(gui, debug);
		uwponopoly.play();
	}
}
