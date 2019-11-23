package model;

import com.google.gson.Gson;

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

public class JsonWriter {

    public void writeSongsToJson(ConcurrentHashMap<Integer, Song> songs) {

        //TODO: change file name to songs.json
//    	Gson gson = new Gson();
//        String s = gson.toJson(songs);
//
//        FileOutputStream outputStream = null;
//        try {
//            outputStream = new FileOutputStream("/Users/paul/Documents/GitHub/PauliHan/songsservlet/src/main/resources/test.json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            outputStream.write(s.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        Gson gson = new Gson();
        BufferedWriter writer;
        try {
            URL res = getClass().getClassLoader().getResource("songs.json");
            File file = Paths.get(res.toURI()).toFile();
            FileWriter wi = new FileWriter(file.getAbsolutePath());
            gson.toJson(songs,wi);
            //kp wie man den pfad allgemeing�ltig macht

            //System.out.println(file.getAbsolutePath());
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

//			gson.toJson(songs.getSongFromMapById(songId))

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
