package htwb.ai.PauliHan.dao;

import htwb.ai.PauliHan.model.Song;
import htwb.ai.PauliHan.model.SongList;

import java.util.Collection;


public interface ISongListDao {
    Collection<SongList> getSongLists(String flag);
    Integer addSongList(SongList songList);
    boolean deleteSongList(Integer id);
}
