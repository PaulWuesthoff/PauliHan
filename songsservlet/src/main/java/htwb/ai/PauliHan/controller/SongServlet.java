package htwb.ai.PauliHan.controller;


import model.ReadJson;
import model.Song;
import model.WriteJson;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@WebServlet(urlPatterns = "/songsservlet-PauliHan", name = "songservlet")
public class SongServlet extends HttpServlet {
    //concurrentHasmap
    private List<Song> songList;
    String message;


    public void init(ServletConfig servletConfig) throws ServletException {
        message = "Hallo Welt";
        songList = new ArrayList<>();
        //alles konfig

        try {
            songList = ReadJson.readJSONToSongs(this.getClass().getClassLoader().getResourceAsStream("songs.json").toString());
            Collections.reverse(songList);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //range zwischen 1 und 10

        String parameter = request.getParameter("songId");
        try {
            int songId = Integer.parseInt(parameter);
            if (checkIfValueIsInRange(songId)) {
                //arbeite
            } else {
                response.setStatus(400);
            }
        } catch (NumberFormatException e) {

            response.setStatus(400);
        }


        //PrintWriter out = response.getWriter();
        //out.println(songList.get(songId - 1).toString());
        //der Request gibt mir alle infos mit
        //anhand der request ein song zurückgeben
    }

    private boolean checkIfValueIsInRange(int req) {
        if (req < 1 || req > 10) {
            return false;
        }
        return true;
    }


    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
// checke ob beide ids übereinstimmen
    }

    public void destroy() {
        WriteJson.writeSongsToJson(songList);
    }


}

