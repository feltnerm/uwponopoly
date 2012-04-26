package board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import board.Space;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.reflect.TypeToken;


class JSONBoard
{

	// game /etc, system /etc, ~
    private static String PATH = "../etc/board.json";
 	private File JSONBoardFile;

    private Gson gson = new Gson();
    private String json;
    
    public ArrayList<Space> Spaces;

    public JSONBoard()
    {
    	this(PATH);
    }
    
    public JSONBoard(String filepath)
    {
    	this.PATH = filepath;
    	File JSONBoardFile = new File(this.PATH);
    }
    
    public ArrayList<Space> getSpaces()
    {
       ArrayList<Space> spaces = gson.fromJson(this.json, new TypeToken<ArrayList<Space>>(){}.getType());
       return spaces;
    }

    public static void main(String[] args) {
        JSONBoard j = new JSONBoard();
        ArrayList<Space> spaces = new ArrayList<Space>();
        spaces = j.getSpaces();
        spaces.toString();
    }

}
