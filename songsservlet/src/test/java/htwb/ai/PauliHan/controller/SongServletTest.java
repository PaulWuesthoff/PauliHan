package htwb.ai.PauliHan.controller;

import model.Database;
import model.JsonReader;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class SongServletTest {

    @Test
    void init() {
        JsonReader reader = new JsonReader();
        Database database = null;
        try {
             database = new Database(reader.readJSONToSongs("songs.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        database.toString();
    }




    @Test
    void doGet() {
    }

    @Test
    void doPut() {
    }

    @Test
    void destroy() {
    }
}