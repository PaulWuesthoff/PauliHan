package htwb.ai.PauliHan.services;

import com.google.gson.Gson;
import htwb.ai.PauliHan.model.Song;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class JSONWriter {
    public void writeSongsToJson(ConcurrentHashMap<Integer, Song> songs) {

        Gson gson = new Gson();
        BufferedWriter writer;
        try {
            URL res = getClass().getClassLoader().getResource("songs.json");
            File file = Paths.get(res.toURI()).toFile();
            FileWriter wi = new FileWriter(file.getAbsolutePath());
            gson.toJson(songs,wi);


            writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            writer.write("[");
            Set entrySet = songs.entrySet();
            Iterator it = entrySet.iterator();

            while (it.hasNext()) {
                Map.Entry me = (Map.Entry) it.next();
                writer.append(gson.toJson(me.getValue()));
                if (it.hasNext())
                    writer.append(",");
            }
            writer.write("]");
            writer.close();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
