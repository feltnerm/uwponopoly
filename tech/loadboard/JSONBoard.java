import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;

class JSONBoard {

    private static String PATH = "monopoly.json";
    Gson gson = new Gson();
    String json = null;

    public JSONBoard() {
        this.read();
    }

    private void read() {
       try {
            FileReader fd = new FileReader(PATH);  
            String data = "";
            while (fd.ready()){
                data += (char) fd.read();
            }
            this.json = data;
       } catch (IOException e) {
            System.out.println("Error reading board data "+PATH);
       }
    }
}

class Cell{
   private String type;
   private String position;
   private String name;
   private String[] pass_over;
   private String[] land_on;
   private String price;
   private String color;

   public String toString() {
       return String.format("Name: %s;Position: %s;Type: %s", name, position, type);
   }
}

class Board {
    private List<Cell> cells;

    public String toString() {
        return String.format("Properties: %s", cells);
    }
}

class Test {
    public static void main(String[] args){
        JSONBoard jb = new JSONBoard();
        Board b = new Gson().fromJson(jb.json, Board.class);
        System.out.println("TEST");
        System.out.println(b);
    }
}
