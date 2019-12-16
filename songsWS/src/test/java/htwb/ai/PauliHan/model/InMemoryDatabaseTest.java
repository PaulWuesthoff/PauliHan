package htwb.ai.PauliHan.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryDatabaseTest {

	InMemoryDatabase data;
	
	@BeforeEach
	public void init() throws FileNotFoundException, IOException {
		data = new InMemoryDatabase();
	}
	
	@Test
	void getSongTest() {
		Song song = data.getSong(10);
		assertEquals("Chinese Food", song.getTitle());
		song = data.getSong(1);
		assertEquals(1, song.getId());
	}
	
	@Test
	void getSongsTest() {
		List<Song> songList = data.getSongs();
		assertEquals(10, songList.size());
	}
	
	@Test
	void addSongTest() {
		Song song = new Song();
		song.setId(11);
		song.setTitle("BesterSong");
		assertEquals(11, data.addSong(song));
		assertEquals(11, data.getSongs().size());
	}
	
	@Test
	void addSongFailTest() {
		Song song = new Song();
		song.setId(1);
		song.setTitle("BesterSong");
		assertEquals(null, data.addSong(song));
		assertEquals(10, data.getSongs().size());
	}
	
	@Test
	void updateSongTest() {
		Song song = new Song();
		song.setId(1);
		song.setTitle("BesterSong");
		assertEquals(song.toString(), data.updateSong(song).toString());
	}
	
	@Test
	void updateSongFailTest() {
		Song song = new Song();
		song.setId(12);
		song.setTitle("BesterSong");
		assertEquals(null, data.updateSong(song));
	}
	
	@Test
	void deleteSongTest() {
		boolean check = data.deleteSong(1);
		assertEquals(true, check);
	}
	
	@Test
	void deleteSongFailTest() {
		boolean check = data.deleteSong(12);
		assertEquals(false, check);
	}

}
