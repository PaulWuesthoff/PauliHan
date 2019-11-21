package htwb.ai.PauliHan.controller;


import model.ReadJson;
import model.Song;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SongServlet {
    private List<Song> songList;


    public void init(ServletConfig servletConfig) throws ServletException {
        //alles konfig

        try {
            songList = new ArrayList<>();
            songList = ReadJson.readJSONToSongs("songs.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // der Request gibt mir alle infos mit
        // anhand der request ein song zurückgeben
    }
}
