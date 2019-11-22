package htwb.ai.PauliHan.controller;


import com.google.gson.Gson;
import model.Database;
import model.JsonReader;
import model.Song;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/songsservlet-PauliHan", name = "songservlet")
public class SongServlet extends HttpServlet {
    private JsonReader reader;
    private Database database;
    private Gson gson;
    private PrintWriter out;


    public void init(ServletConfig servletConfig) throws ServletException {
        //String filename = servletConfig.getInitParameter("song");
        reader = new JsonReader();
        try {
            database = new Database(reader.readJSONToSongs("songs.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String checkHttpHeader(HttpServletRequest request) {
        if (request.getHeader("Accept").contains("application/json")) {
            return "json";

        }
        if (request.getHeader("Accept").contains("application/xml")) {
            return "xml";

        }
        if (request.getHeader("Accept").contains("*/*")) {
            return "*/*";
        }
        if (request.getHeader("Accept").isEmpty()) {
            return "empty";
        }
        if (request.getHeader("Accept").equals(null)) {
            return "null";
        }
        return "error";
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        out = response.getWriter();

        if (request.getParameterMap().containsKey("songId")) {
            String parameter = request.getParameter("songId");
            int songId = Integer.parseInt(parameter);
            if (checkIfValueIsInRange(songId)) {
                switch (checkHttpHeader(request)) {
                    case "json": {
                        gson = new Gson();
                        out.write(gson.toJson(database.getSongFromMapById(songId)));
                        out.flush();
                        break;
                    }
                    case "xml": {
                        JAXBContext context = null;
                        try {
                            context = JAXBContext.newInstance(Song.class);
                        } catch (JAXBException e) {
                            response.setStatus(400);
                            e.printStackTrace();
                        }
                        Marshaller marshaller = null;
                        try {
                            marshaller = context.createMarshaller();
                        } catch (JAXBException e) {
                            response.setStatus(400);
                            e.printStackTrace();
                        }
                        try {
                            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                        } catch (PropertyException e) {
                            response.setStatus(400);
                            e.printStackTrace();
                        }
                        Song song = database.getSongFromMapById(songId);
                        try {
                            marshaller.marshal(song, out);
                        } catch (JAXBException e) {
                            response.setStatus(400);
                            e.printStackTrace();
                        }
                        out.flush();
                        break;
                    }
                    case "*/*": {
                        gson = new Gson();
                        out.write(gson.toJson(database.getSongFromMapById(songId)));
                        out.flush();
                        break;
                    }
                    case "empty": {
                        gson = new Gson();
                        out.write(gson.toJson(database.getSongFromMapById(songId)));
                        out.flush();
                        break;
                    }
                    case "null": {
                        gson = new Gson();
                        out.write(gson.toJson(database.getSongFromMapById(songId)));
                        out.flush();
                        break;
                    }
                    case "error": {
                        response.setStatus(406);
                        break;
                    }
                    default: {
                        response.setStatus(400);
                    }
                }
            } else {
                response.setStatus(404);
            }
        } else {
            response.setStatus(400);
        }
        //range zwischen 1 und 10
//        PrintWriter out = response.getWriter();
//        if (!request.getHeader("Accept").equals("application.json")
//                || !request.getHeader("Accept").equals("application.xml"))
//            response.setStatus(406);
//        if (request.getParameterMap().containsKey("songId")) {
//            String parameter = request.getParameter("songId");
//            try {
//
//                int songId = Integer.parseInt(parameter);
//                if (checkIfValueIsInRange(songId)) {
//                    if (request.getHeader("Accept").equals("application/json")
//                            || request.getHeader("Accept").contains("*/*")
//                            || request.getHeader("Accept").equals(null)) {
//                        response.setContentType("application/json");
//
//                        gson = new Gson();
//                        out.write(gson.toJson(database.getSongFromMapById(songId)));
//                        out.flush();
//                    }
//                    if (request.getHeader("Accept").equals("application/xml")) {
//                        JAXBContext context = JAXBContext.newInstance(Song.class);
//                        Marshaller marshaller = context.createMarshaller();
//                        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//                        Song song = database.getSongFromMapById(songId);
//                        marshaller.marshal(song, out);
//                        out.flush();
//                    }
//
//                } else {
//                    response.setStatus(404);
//                }
//            } catch (NumberFormatException e) {
//
//                response.setStatus(400);
//            } catch (JAXBException e) {
//                response.setStatus(400);
//            }
//        } else {
//            response.setStatus(400);
//        }


        //PrintWriter out = response.getWriter();
        //out.println(songList.get(songId - 1).toString());
        //der Request gibt mir alle infos mit
        //anhand der request ein song zurückgeben
    }

    private boolean checkIfValueIsInRange(int req) {
        if (req < 1 || req > 10) return false;
        else return true;
    }

    private boolean checkIfParameterContainsString(String parameter) {
        if (parameter.contains("songId")) return true;
        else return false;
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
// checke ob beide ids übereinstimmen
    }

    public void destroy() {

    }


}

