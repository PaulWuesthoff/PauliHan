package htwb.ai.PauliHan.controller;

import model.Database;
import model.JsonReader;
import model.Song;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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
        assertTrue(database.getSize() > 0);
    }


    @Test
    void checkHttpHeaderContentTypeIsJson() {
        SongServlet s = new SongServlet();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Content-Type", "application/json");
        assertEquals("json", s.checkHttpHeaderContentType(request));
    }

    @Test
    void checkHttpHeaderContentTypeIsError() {
        SongServlet s = new SongServlet();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Content-Type", "application/xml");
        assertEquals("error", s.checkHttpHeaderContentType(request));
    }

    @Test
    void checkHttpHeaderIsJson() {
        SongServlet s = new SongServlet();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Accept", "application/json");
        assertEquals("json", s.checkHttpHeader(request));
    }

    @Test
    void checkHttpHeaderIsXml() {
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
    void checkIfValueIsLowerThanZero() {
        SongServlet s = new SongServlet();
        assertEquals(false, s.checkIfValueIsInRange(-1));
    }


    @Test
    void checkIfValueIsNotInRangeTest() {
        SongServlet s = new SongServlet();
        assertEquals(false, s.checkIfValueIsInRange(11));
    }

    @Test
    void checkIfParameterContainsSongIdTrue() {
        SongServlet s = new SongServlet();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("songId", "6");
        assertEquals(true, s.checkIfParameterIsSongId(request));
    }

    @Test
    void checkIfParameterContainsSongIdFalse() {
        SongServlet s = new SongServlet();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("song", "6");
        assertEquals(false, s.checkIfParameterIsSongId(request));
    }

    @Test
    void checkIfParameterIsNull() {
        SongServlet s = new SongServlet();
        assertEquals(false, s.checkIfParameterIsSongId(null));
    }

    @Test
    void doGetUseCase() {
        SongServlet songServlet = new SongServlet();
        MockServletConfig servletConfig = new MockServletConfig();
        servletConfig.addInitParameter("songs.json", "testSongs.json");
        try {
            songServlet.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.setContextPath("/songsservlet-PauliHan");
        request.setPathInfo("/songs");
        request.addParameter("songId", "6");
        request.addHeader("Accept", "application/json");

        try {
            songServlet.doGet(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(200, response.getStatus());
    }

    @Test
    void doGetWrongParameterName() {
        SongServlet songServlet = new SongServlet();
        MockServletConfig servletConfig = new MockServletConfig();
        servletConfig.addInitParameter("songs.json", "testSongs.json");
        try {
            songServlet.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.setContextPath("/songsservlet-PauliHan");
        request.setPathInfo("/songs");
        request.addParameter("song", "6");
        request.addHeader("Accept", "application/json");

        try {
            songServlet.doGet(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(400, response.getStatus());
    }

    @Test
    void doGetParameterValueGreaterThen10() {
        SongServlet songServlet = new SongServlet();
        MockServletConfig servletConfig = new MockServletConfig();
        servletConfig.addInitParameter("songs.json", "testSongs.json");
        try {
            songServlet.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.setContextPath("/songsservlet-PauliHan");
        request.setPathInfo("/songs");
        request.addParameter("songId", "11");
        request.addHeader("Accept", "application/json");

        try {
            songServlet.doGet(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(404, response.getStatus());
    }

    @Test
    void doGetParameterValueSmallerThen0() {
        SongServlet songServlet = new SongServlet();
        MockServletConfig servletConfig = new MockServletConfig();
        servletConfig.addInitParameter("songs.json", "testSongs.json");
        try {
            songServlet.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.setContextPath("/songsservlet-PauliHan");
        request.setPathInfo("/songs");
        request.addParameter("songId", "-1");
        request.addHeader("Accept", "application/json");

        try {
            songServlet.doGet(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(404, response.getStatus());
    }


    @Test
    void doPut() {
    }

    @Test
    void destroy() {
    }
}