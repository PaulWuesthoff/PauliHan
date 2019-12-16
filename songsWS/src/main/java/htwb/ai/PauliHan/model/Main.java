//package htwb.ai.PauliHan.model;
//
//
//import htwb.ai.PauliHan.dao.SongDAOImpl;
//import htwb.ai.PauliHan.dao.UserDaoImpl;
//import htwb.ai.PauliHan.services.JSONReader;
//
//import javax.persistence.Persistence;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        JSONReader reader = new JSONReader();
//        SongDAOImpl dao = new SongDAOImpl(Persistence.createEntityManagerFactory("songDB-PU"));
//        UserDaoImpl userDao = new UserDaoImpl(Persistence.createEntityManagerFactory("songDB-PU"));
//
//        try {
//            List<Song> songList = reader.readJSONToSongs("songs.json");
//            Collections.reverse(songList);
//           for(Song s : songList){
//               dao.addSong(s);
//           }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            dao.done();
//        }
//
//        Song so = new Song(1,"MacArthur ","Richard Harris","Dunhill Records",1986);
//        System.out.println(dao.updateSong(so).toString());
//        System.out.println(dao.deleteSong(9));
//        List<Song> songList = dao.getSongs();
//        songList.forEach(song -> System.out.println(song.toString()));
//
//
//
//    }
//
//}
//
