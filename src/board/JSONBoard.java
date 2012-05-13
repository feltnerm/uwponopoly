package board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This class loads a Monopoly board represented in JSON.
 */
class JSONBoard {

	// game /etc, system /etc, ~
	private String PATH = "../etc/board.json";
	private File JSONBoardFile;

	private Gson gson = new Gson();
	private String json;

	public ArrayList<Space> Spaces;

   /**
    * Constructor, reads file when instantiated.
    */
	public JSONBoard() {
		try {
			this.json = readFile();
		} catch (IOException e) {
			System.out.println("JSONBoard file not found.");
		}
	}

    public JSONBoard( String path )
    {
        PATH = path;
		try {
			this.json = readFile();
		} catch (IOException e) {
			System.out.println("JSONBoard file not found.");
		}
	}

   /**
    * Read the config file.
    */
	private String readFile() throws IOException {
		FileInputStream stream = new FileInputStream(new File(this.PATH));
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0,
					fc.size());
			return Charset.defaultCharset().decode(bb).toString();
		} catch (IOException e) {
            throw e;
        } 
        finally {
			stream.close();
		}
	}

    public String readFile(String path) throws IOException
    {
        File f = new File(path);
        if (f.canRead())
        {
            this.PATH = path;
        }
        return this.readFile();
    }

   /**
    * deserialze JSON into classes.
    */
	public ArrayList<Space> getSpaces() {
		ArrayList<Space> spaces = gson.fromJson(this.json,
				new TypeToken<ArrayList<Space>>() {
				}.getType());
		return spaces;
	}

}
