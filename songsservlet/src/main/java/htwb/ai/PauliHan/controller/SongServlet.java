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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/songsservlet-PauliHan",name ="songservlet")
public class SongServlet extends HttpServlet {
    private List<Song> songList;
    String message;


    public void init(ServletConfig servletConfig) throws ServletException {
        message = "Hallo Welt";
        //alles konfig
        try {
            songList = new ArrayList<>();
            songList = ReadJson.readJSONToSongs("songs.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
        // der Request gibt mir alle infos mit
        // anhand der request ein song zurückgeben
    }


    public void doPut(HttpServletRequest request, HttpServletResponse response)throws IOException{

    }
    public void destroy(){
        WriteJson.writeSongsToJson(songList);
    }


}

