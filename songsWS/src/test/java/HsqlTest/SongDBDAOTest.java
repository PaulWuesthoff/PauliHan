//package HsqlTest;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//
//import java.util.Collection;
//
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//import htwb.ai.PauliHan.dao.SongDAOImpl;
//import htwb.ai.PauliHan.model.Song;
//
//class SongDBDAOTest {
//
//	private static EntityManagerFactory emf;
//
//	private SongDAOImpl dao;
//
//    @BeforeAll
//    public static void initEMF () {
//        emf = Persistence
//                .createEntityManagerFactory("Song-TEST-PU");
//    }
//
//    @BeforeEach
//    public void setUpDao() {
//        dao = new SongDAOImpl (emf);
//    }
//
//
//    @Test
//    public void getSongTest() {
//    	Song song = dao.getSong(1);
//    	songMatchesExpected(song);
//    }
//
//    @Test
//    public void getAllSongsTest() {
//    	Collection<Song> songs = dao.getSongs();
//    	assertTrue(songs.size() >=1);
//    	 Song songFromDB = songs.stream().findFirst().get();
//         songMatchesExpected(songFromDB);
//    }
//
//    @Test
//    @Order(1)
//    public void addSongTest() {
//    	Song song = new Song("NewTitle", "NewArtist", "NewLabel", 1000);
//    	int id = dao.addSong(song);
////        System.out.println("Add: " + dao.getSong(id));
////        assertEquals(11, id);
//
//    	Song dbSong = dao.getSong(id);
//
//    	assertEquals(dbSong.getTitle(), song.getTitle());
//    	assertEquals(dbSong.getTitle(), song.getTitle());
//    }
//
//    @Test
//    @Order(2)
//    public void deleteSongTest() {
//    	Song song = new Song("NewTitle", "NewArtist", "NewLabel", 1000);
//        Integer id = dao.addSong(song);
////        System.out.println("Del: " + dao.getSong(id));
//
//        dao.deleteSong(id);
//
//        assertNull(dao.getSong(id));
//    }
//
//    private void songMatchesExpected(Song song) {
//        assertEquals(1 , song.getId());
//        assertEquals("MacArthur Park", song.getTitle());
//        assertEquals("Richard Harris", song.getArtist());
//        assertEquals("Dunhill Records", song.getLabel());
//        assertEquals(1968, song.getReleased());
//
////        Collection<Address> addresses = song.getAddressSet();
////        assertEquals(2, addresses.size());
//        // ...
//    }
//
//
//
//}
