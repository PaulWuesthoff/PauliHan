package htwb.ai.PauliHan.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import htwb.ai.PauliHan.model.Song;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JSONReader {

    public List<Song> readJSONToSongs(String filename) throws FileNotFoundException, IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
        ObjectMapper objectMapper = new ObjectMapper();
        return (List<Song>) objectMapper.readValue(in, new TypeReference<List<Song>>() {});
    }

}
