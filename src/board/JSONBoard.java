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
    private static String PATH = "../etc/monopoly.json";
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
    
    public ArrayList<Space> getSpaces(InputStream in) throws IOException
    {
    	FileInputStream jsonBoardStream = new FileInputStream(this.JSONBoardFile.)
    }
    
    public ArrayList<Space> readJsonStream(InputStream in) throws IOException {
    	JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
    	try {
    		return readSpaceArray(reader);
    	} finally {
    		reader.close();
    	}
    	
    }
    
    
    public ArrayList<Space> readSpaceArray(JsonReader reader) throws IOException
    {
    	ArrayList<Space> Spaces = new ArrayList<Space>();
    	
    	reader.beginArray();
    	while (reader.hasNext())
    	{
    		Spaces.add(readSpace(reader));
    	}
    	reader.endArray();
    	return Spaces;
    }
    
    public ArrayList<Space> getSpaces(String json)
    {
    	Type spaceCollectionType = new TypeToken<ArrayList<Space>>(){}.getType();
    	ArrayList<Space> Spaces = gson.fromJson(json, spaceCollectionType);
		return Spaces;
    	
    }
    
    /**
    public ArrayList<Space> getSpaces()
    {
       ArrayList<Space> spaces = gson.fromJson(this.json, new TypeToken<ArrayList<Space>>(){}.getType());
       return spaces;
    }
    */

}
