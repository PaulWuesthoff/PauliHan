package model;

import com.google.gson.Gson;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class WriteJson {

    public static void writeSongsToJson(List<Song> songs) {

        //TODO: change file name to songs.json
        Gson gson = new Gson();
        String s = gson.toJson(songs);

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("/Users/paul/Documents/GitHub/PauliHan/songsservlet/src/main/resources/test.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {

            outputStream.write(s.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
