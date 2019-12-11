package htwb.ai.PauliHan.model;


import htwb.ai.PauliHan.service.JSONReader;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JSONReader reader = new JSONReader();
        SongDAOImpl dao = new SongDAOImpl("songDB-PU");
        try {
            List<Song> songList = reader.readJSONToSongs("songs.json");
            songList.get(0).setId(null);
            Integer id = dao.addSong(songList.get(0));
            System.out.println(id);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dao.done();
        }


    }

}

