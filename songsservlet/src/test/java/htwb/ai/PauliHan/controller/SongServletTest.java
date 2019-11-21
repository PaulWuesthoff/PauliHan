package htwb.ai.PauliHan.controller;

import model.ReadJson;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SongServletTest {

    @Test
    void init() {
        List<Song> songList = new ArrayList<>();
        try {
            songList = ReadJson.readJSONToSongs("/Users/paul/Documents/GitHub/PauliHan/songsservlet/src/main/resources/songs.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(10,  songList.size());
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