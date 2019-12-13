package htwb.ai.PauliHan.model;

import java.util.List;

public interface ISongDAO {

    Song getSong(Integer id);

    List<Song> getSongs();

    Integer addSong(Song song);

    Song updateSong(Integer id, Song song);

    boolean deleteSong(Integer id);

}
