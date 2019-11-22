package model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


import java.io.*;


import java.util.List;


public class JsonReader {

//    public static void main(String[] args) {
//
//        try {
//            List<Song> readSongs = readJSONToSongs("src/main/resources/songs.json");
//            readSongs.forEach(s -> {
//
//               // System.out.println(s.toString());
//            });
//            readSongs.remove(1);
//            WriteJson.writeSongsToJson(readSongs);
//            //System.out.println("------------------");
//            readSongs.forEach(s -> {
//
//                //System.out.println(s.toString());
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


    public List<Song> readJSONToSongs(String filename) throws FileNotFoundException, IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
        ObjectMapper objectMapper = new ObjectMapper();
        return (List<Song>) objectMapper.readValue(in, new TypeReference<List<Song>>() {});
    }

}
