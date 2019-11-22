package htwb.ai.PauliHan.controller;


import model.Database;
import model.JsonReader;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/songsservlet-PauliHan", name = "songservlet")
public class SongServlet extends HttpServlet {
    private JsonReader reader;
    private Database database;



    public void init(ServletConfig servletConfig) throws ServletException {
        //String filename = servletConfig.getInitParameter("song");
        reader = new JsonReader();
        try {
            database = new Database(reader.readJSONToSongs("songs.json"));
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
                PrintWriter out = response.getWriter();
                out.println(database.getSongFromMapById(songId).toString());

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

    }


}

