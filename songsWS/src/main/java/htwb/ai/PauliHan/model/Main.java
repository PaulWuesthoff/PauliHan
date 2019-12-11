package htwb.ai.PauliHan.model;


import htwb.ai.PauliHan.service.JSONReader;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JSONReader reader = new JSONReader();
        SongDAOImpl dao = new SongDAOImpl("songDB-PU");
//        try {
//            List<Song> songList = reader.readJSONToSongs("songs.json");
//            songList.get(0).setId(null);
//           for(Song song : songList){
//               song.setId(null);
//               dao.addSong(song);
//           }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            dao.done();
//        }
        System.out.println(dao.getSong(1).toString());

    }

}

