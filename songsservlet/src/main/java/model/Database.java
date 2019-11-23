package model;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Database {
    private ConcurrentHashMap<Integer, Song> songMap;

    public Database(List<Song> songsList) {
        this.songMap = new ConcurrentHashMap<>();
        songsList.forEach(song -> this.songMap.put(song.getId(), song));

    }

    public Song getSongFromMapById(int id) {
        return songMap.get(id);
    }

    public boolean update(int id, Song update) {
        //hier für put ids aktualisieren

    	boolean updated = true;
    	try {
    		songMap.get(id).setTitle(update.getTitle());
        	songMap.get(id).setArtist(update.getArtist()); 
        	songMap.get(id).setLabel(update.getLabel());
        	songMap.get(id).setReleased(update.getReleased());
		} catch (Exception e) {
			updated = false;
		}	
        return updated;
    }

    public int getSize() {
        return songMap.size();
    }

    public ConcurrentHashMap<Integer, Song> getSongMap() {
        return songMap;
    }
}
