import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


class Game extends JPanel implements Runnable {

    public Config config;

    private Thread thread;

    static final int FRAME_RATE = 15;
    private int current_frame;

    public Game() {
        super();

        this.config = new Config();

        thread = null;
        frame = new JFrame();
        menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        frame.pack();
        thread = new Thread(this);
        thread.start();
    }

    public void run()
    {
        Thread current = Thread.currentThread();

        while (thread != null) {
            
            repaint();
            current_frame++;

            try {
                Thread.sleep(FRAME_RATE);
            } catch (InterruptedException e) {} // do nothing

        }

    }

    public static void main(String[] args) {

        JFrame window = new JFrame("Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setLayout(new BorderLayout());

        Game g = new Game();
        g.config.load();
        window.add(g, BorderLayout.NORTH);
        window.pack();

    }
}

