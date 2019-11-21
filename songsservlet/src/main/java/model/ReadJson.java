package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.*;

import java.util.List;

public class ReadJson {

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


    public static List<Song> readJSONToSongs(String filename) throws FileNotFoundException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            return (List<Song>) objectMapper.readValue(is, new TypeReference<List<Song>>() {
            });
        }
    }

}
