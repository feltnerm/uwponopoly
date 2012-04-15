package board;

import java.io.FileReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import board.Space;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


class JSONBoard
{

    private static String PATH = "../etc/monopoly.json";

    private Gson gson = new Gson();
    private String json;

    public JSONBoard()
    {
       this.json = read();
    }

    private String read()
    {
       try {
          FileReader fd = new FileReader(PATH);
          String data = "";
          while (fd.ready()){
            data += (char) fd.read();
          }
          return (String) data;
       } catch (IOException e) {
          System.err.println(e);
       }
       return null;
    }

    public ArrayList<Space> getSpaces()
    {
       ArrayList<Space> spaces = gson.fromJson(this.json, new TypeToken<ArrayList<Space>>(){}.getType());
       return spaces;
    }

}
