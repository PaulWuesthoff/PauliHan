package htwb.ai.PauliHan.dao;

import htwb.ai.PauliHan.model.Song;

import java.util.Collection;
import java.util.List;

public interface ISongDAO {

    Song getSong(Integer id);

    Collection<Song> getSongs();

    Integer addSong(Song song);

    Song updateSong(Song song);

    boolean deleteSong(Integer id);

}
