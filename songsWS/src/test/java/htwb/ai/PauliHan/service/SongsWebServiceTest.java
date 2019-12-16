package htwb.ai.PauliHan.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import htwb.ai.PauliHan.config.DependencyBinder;
import htwb.ai.PauliHan.config.DependencyBinderTest;
import htwb.ai.PauliHan.config.ResourceConfigTest;
import htwb.ai.PauliHan.model.InMemoryDatabase;
import htwb.ai.PauliHan.model.Song;


class SongsWebServiceTest extends JerseyTest{

	String baseUrl = "/songs";
	
	@Override
	protected Application configure() {
//		return new ResourceConfigTest
//		return new ResourceConfig(InMemoryDatabase.class);
		return new ResourceConfig(SongsWebService.class).register(new DependencyBinderTest());
	}
	
	@BeforeEach
	@Override
	public void setUp() throws Exception{
		super.setUp();
	}
	
	@AfterEach
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
	
	@Test
	public void getAllSongsJsonTest() {
		Response response = target("/songs").request(MediaType.APPLICATION_JSON).get();
		List<Song> songList = response.readEntity(List.class);
		System.out.println(songList);
		assertEquals(10,songList.size());
	}
	
//	@Test
//	public void addSongTest() {
//		Song song = new Song();
//        song.setId(11);
//        song.setTitle("Testsong");
//        song.setArtist("Testartist");
//        song.setLabel("Testlabel");
//        song.setReleased(2000);
//        
//		Response response = target("/songs").request().post(Entity.json(song));
//		assertEquals(201, response.getStatus());
//	}
	
	@Test
	public void updateSongTest() {
		Song song = new Song();
		song.setId(1);
		song.setArtist("Ich Bins");
		song.setLabel("as");
		song.setReleased(123);
		song.setTitle("qwe");
		
        Response response = target("/songs/1").request().put(Entity.json(song));
//        Song updatedSong = response.readEntity(Song.class);

		assertEquals(204, response.getStatus());
	}

}




