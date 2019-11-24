package htwb.ai.PauliHan.controller;

import model.Database;
import model.JsonReader;
import model.Song;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class SongServletTest {

    @Test
    public void init() {
        JsonReader reader = new JsonReader();
        Database database = null;

        try {
             database = new Database(reader.readJSONToSongs("songs.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        database.toString();
    }

    @Test
    void checkHttpHeaderContentTypeisJson(){
    	SongServlet s = new SongServlet();
    	MockHttpServletRequest request = new MockHttpServletRequest();
    	request.addHeader("Content-Type", "application/json");
    	assertEquals("json", s.checkHttpHeaderContentType(request));
    }
    
    @Test
    void checkHttpHeaderContentTypeisError(){
    	SongServlet s = new SongServlet();
    	MockHttpServletRequest request = new MockHttpServletRequest();
    	request.addHeader("Content-Type", "application/xml");
    	assertEquals("error", s.checkHttpHeaderContentType(request));
    }
    
    @Test
    void checkHttpHeaderIsJson(){
    	SongServlet s = new SongServlet();
    	MockHttpServletRequest request = new MockHttpServletRequest();
    	request.addHeader("Accept", "application/json");
    	assertEquals("json", s.checkHttpHeader(request));
    }
    
    @Test
    void checkHttpHeaderIsXml(){
    	SongServlet s = new SongServlet();
    	MockHttpServletRequest request = new MockHttpServletRequest();
    	request.addHeader("Accept", "application/xml");
    	assertEquals("xml", s.checkHttpHeader(request));
    }
    
    @Test
    void checkIfValueIsInRangeTest() {
    	SongServlet s = new SongServlet();
    	assertEquals(true, s.checkIfValueIsInRange(6));
    }
    
    @Test
    void checkIfParameterIsSongIdTest() {
    	SongServlet s = new SongServlet();
    	assertEquals(true,s.checkIfParameterContainsString("songId"));
    }

    @Test
    void doGet() {
    }

    @Test
    void doPut() {
    }

    @Test
    void destroy() {
    }
}