package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.output.FileWriterWithEncoding;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class writeJson {

    public static void writeSongsToJson(List<Song> songs) {
        try {
            Writer writer = new FileWriter("test.json");
            Gson gson = new GsonBuilder().create();
            gson.toJson(songs,writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
