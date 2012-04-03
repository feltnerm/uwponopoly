
class Game {

    public Config config;

    public Game() {

        this.config = new Config(); 
    }

    public static void main(String[] args) {

        Game g = new Game();
        g.config.load();

        System.out.println(g.config.get("PLAYERS"));
        g.config.put("PLAYERS", "2");
        g.config.save();

    }
}

