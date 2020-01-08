package htwb.ai.PauliHan.dao;

import htwb.ai.PauliHan.model.Song;

import java.util.Collection;


public interface ISongListDao {
    Collection<Song> getSongLists(String flag);
    Integer addSongList(Collection<Song> songCollection);
    boolean deleteSongList(Integer id);
}
