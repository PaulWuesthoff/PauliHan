package model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


import java.io.*;


import java.util.List;


public class JsonReader {



    public List<Song> readJSONToSongs(String filename) throws FileNotFoundException, IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
        ObjectMapper objectMapper = new ObjectMapper();
        return (List<Song>) objectMapper.readValue(in, new TypeReference<List<Song>>() {});
    }

}
