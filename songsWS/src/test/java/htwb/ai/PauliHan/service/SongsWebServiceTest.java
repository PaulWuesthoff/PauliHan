package htwb.ai.PauliHan.service;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
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


class SongsWebServiceTest extends JerseyTest {

    String baseUrl = "/songs";

    @Override
    protected Application configure() {
        return new ResourceConfigTest();
//		return new ResourceConfigTest
//		return new ResourceConfig(InMemoryDatabase.class);
//      return new ResourceConfig(SongsWebService.class).register(new DependencyBinderTest());
    }

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @AfterEach
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void getAllSongsTest() {
        Response response = target("/songs").request(MediaType.APPLICATION_JSON).get();
        List<Song> songList = response.readEntity(List.class);
        System.out.println(songList);
        assertEquals(10, songList.size());
    }
    @Test
    public void addSongTestShouldReturn201() {
        Response response = target("/songs").request().post(Entity.json("{\n" +
                "        \"id\": 11,\n" +
                "        \"title\": \"Testsong\",\n" +
                "        \"artist\": \"TestArtist\",\n" +
                "        \"label\": \"TestLabel\",\n" +
                "        \"released\": 2\n" +
                "    }"));
        assertEquals(201, response.getStatus());
    }

    @Test
    public void addSongTestShouldReturn400() {
        Response response = target("/songs").request().post(Entity.json("{\n" +
                "        \"id\": \n" +
                "        \"title\": \"Testsong\",\n" +
                "        \"artist\": \"TestArtist\",\n" +
                "        \"label\": \"TestLabel\",\n" +
                "        \"released\": 2\n" +
                "    }"));
        assertEquals(400, response.getStatus());
    }

    @Test
    public void updateSongTestShouldReturn200() {

        Response response = target("/songs/1").request().put(Entity.json("{\n" +
                "        \"id\": 1,\n" +
                "        \"title\": \"Hallo\",\n" +
                "        \"artist\": \"TestArtist\",\n" +
                "        \"label\": \"TestLabel\",\n" +
                "        \"released\": 2\n" +
                "    }"));

        assertEquals(204, response.getStatus());
    }

    @Test
    public void updateSongTestShouldReturn400() {

        Response response = target("/songs/1").request().put(Entity.json("{\n" +
                "        \"id\": 1,\n" +
                "        \"title\": \\n" +
                "        \"artist\": \"TestArtist\",\n" +
                "        \"label\": \"TestLabel\",\n" +
                "        \"released\": 2\n" +
                "    }"));

        assertEquals(400, response.getStatus());
    }

    @Test
    void deleteSongTest() {
        Response response = target("/songs/1").request().delete();
        assertEquals(204, response.getStatus());
    }

    @Test
    void deleteSongFailTest() {
        Response response = target("/songs/13").request().delete();
        assertEquals(404, response.getStatus());
    }

    @Test
    void deleteSongFailStringTest() {
        Response response = target("/songs/asd").request().delete();
        assertEquals(404, response.getStatus());
    }

}




