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

class JSONBoard {

	// game /etc, system /etc, ~
	private String PATH = "etc/board.json";
	private File JSONBoardFile;

	private Gson gson = new Gson();
	private String json;

	public ArrayList<Space> Spaces;

	public JSONBoard() {
		try {
			this.json = readFile();
		} catch (IOException e) {
			System.out.println("JSONBoard file not found.");
		}
	}

	private String readFile() throws IOException {
		FileInputStream stream = new FileInputStream(new File(this.PATH));
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0,
					fc.size());
			return Charset.defaultCharset().decode(bb).toString();
		} finally {
			stream.close();
		}
	}

	public ArrayList<Space> getSpaces() {
		ArrayList<Space> spaces = gson.fromJson(this.json,
				new TypeToken<ArrayList<Space>>() {
				}.getType());
		return spaces;
	}

}
