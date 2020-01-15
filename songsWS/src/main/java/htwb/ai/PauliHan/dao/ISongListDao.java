package htwb.ai.PauliHan.dao;

import htwb.ai.PauliHan.model.Song;
import htwb.ai.PauliHan.model.SongList;
import htwb.ai.PauliHan.model.User;

import java.util.Collection;


public interface ISongListDao {
    Collection<SongList> getSongLists(String flag);
    Collection<SongList> getAll();
    Integer addSongList(SongList songList);
    boolean deleteSongList(Integer id);
}
