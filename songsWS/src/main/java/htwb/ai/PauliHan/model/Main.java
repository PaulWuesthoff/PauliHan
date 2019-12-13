package htwb.ai.PauliHan.model;


import htwb.ai.PauliHan.service.JSONReader;

import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JSONReader reader = new JSONReader();
        SongDAOImpl dao = new SongDAOImpl(Persistence.createEntityManagerFactory("songDB-PU"));
        try {
            List<Song> songList = reader.readJSONToSongs("songs.json");
           for(Song song : songList){
               dao.addSong(song);
           }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dao.done();
        }

//        Song so = new Song(2,"sdf","vsg","dsfsdf",111);
//        System.out.println(dao.updateSong(so).toString());
//        System.out.println(dao.deleteSong(9));
//        List<Song> songList = dao.getSongs();
//        songList.forEach(song -> System.out.println(song.toString()));


    }

}

