package htwb.ai.PauliHan.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBException;

import htwb.ai.PauliHan.model.InMemoryDatabase;
import htwb.ai.PauliHan.model.Song;

public class InMemoryDao {

	JSONReader jsonreader;
	JSONWriter jsonwriter;
	XMLReader xmlreader;
	XMLWriter xmlwriter;
	
	@SuppressWarnings("unchecked")
	public InMemoryDatabase readJSONToSongs(String filename) throws FileNotFoundException, IOException{
		jsonreader = new JSONReader();
		List<Song> songList = jsonreader.readJSONToSongs(filename);
		return new InMemoryDatabase(songList);
	}

	public void writeSongsToJSON(ConcurrentHashMap<Integer, Song> songs) {
		jsonwriter = new JSONWriter();
		jsonwriter.writeSongsToJson(songs);
	}
	
	public InMemoryDatabase readXMLToSongs(String filename) throws FileNotFoundException, JAXBException, IOException {
		xmlreader = new XMLReader();
		List<Song> songList = xmlreader.readXMLToSongs(filename);
		return new InMemoryDatabase(songList);
	}
	
	//idk weil der einen printwriter braucht?
	public void writeSongsToXML() {
		xmlwriter = new XMLWriter();
//		xmlwriter.writeSongsToXML(inMemoryDatabase, out);
	}
	
	
}
