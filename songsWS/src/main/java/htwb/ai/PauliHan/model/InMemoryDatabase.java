package htwb.ai.PauliHan.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import htwb.ai.PauliHan.dao.ISongDAO;
import htwb.ai.PauliHan.services.JSONReader;

public class InMemoryDatabase implements ISongDAO {
//    private ConcurrentHashMap<Integer, Song> songMap;
	private ArrayList<Song> songList = new ArrayList<Song>();
    
	public InMemoryDatabase() throws FileNotFoundException, IOException {
		JSONReader jsonreader = new JSONReader();
		songList = (ArrayList<Song>) jsonreader.readJSONToSongs("songs.json");
	}
	
//    public InMemoryDatabase(List<Song> songList) {
//    	this.songList = songList;
//        this.songMap = new ConcurrentHashMap<>();
//        songList.forEach(song -> this.songMap.put(song.getId(), song));
//    }

//    public Song getSongFromMapById(int id) {
//        return songMap.get(id);
//    }
//    
//    public Collection<Song> getSongs(){
//    	return songMap.values();
//    }
//
//    public boolean update(int id, Song update) {
//        //hier für put ids aktualisieren
//
//        boolean updated = true;
//        try {
//            songMap.get(id).setTitle(update.getTitle());
//            songMap.get(id).setArtist(update.getArtist());
//            songMap.get(id).setLabel(update.getLabel());
//            songMap.get(id).setReleased(update.getReleased());
//        } catch (Exception e) {
//            updated = false;
//        }
//        return updated;
//    }

//    public int getSize() {
//        return songMap.size();
//    }
//
//    public ConcurrentHashMap<Integer, Song> getSongMap() {
//        return songMap;
//    }

	@Override
	public Song getSong(Integer id) {
		for(Song song : songList) {
			if(song.getId() == id) {
				return song;
			}
		}
        return null;
	}

	@Override
	public List<Song> getSongs() {
		return songList;
		}

	@Override
	public Integer addSong(Song s) {
		for(Song song : songList) {
			if(song.getId() == s.getId()) {
				return null;
			}
		}
		songList.add(s);
		return s.getId();
	}

	@Override
	public Song updateSong(Song s) {
		for(Song song : songList) {
			if(song.getId() == s.getId()) {
				song.setId(s.getId());
				song.setTitle(s.getTitle());
				song.setLabel(s.getLabel());
				song.setArtist(s.getArtist());
				song.setReleased(s.getReleased());
				return song;
			}
		}
		return null;
	}

	@Override
	public boolean deleteSong(Integer id) {
		for(Song song : songList) {
			if(song.getId() == id) {
				songList.remove(song);
				return true;
			}
		}
		return false;
	}

}
