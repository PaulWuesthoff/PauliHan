package model;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Database {
    private ConcurrentHashMap<Integer,Song> songMap;

    public Database(List<Song> songsList) {
        this.songMap = new ConcurrentHashMap<>();
        songsList.forEach(song -> this.songMap.put(song.getId(),song));

    }

    public Song getSongFromMapById(int id){
        return songMap.get(id);
    }

    public Song update(int id){
        return null;
    }
   public int getSize(){
        return songMap.size();
   }

}
