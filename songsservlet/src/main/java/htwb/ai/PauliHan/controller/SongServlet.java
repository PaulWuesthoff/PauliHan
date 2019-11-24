package htwb.ai.PauliHan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import model.Database;
import model.JsonReader;
import model.JsonWriter;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/songsservlet-PauliHan", name = "songservlet")
public class SongServlet extends HttpServlet {
	private JsonReader reader;
	private Database database;
	private Gson gson;
	private PrintWriter out;

	public void init(ServletConfig servletConfig) throws ServletException {
		reader = new JsonReader();
		try {

			database = new Database(reader.readJSONToSongs("songs.json"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		out = response.getWriter();

		if (checkIfParamterIsSongId(request)) {
			String parameter = request.getParameter("songId");
			int songId = Integer.parseInt(parameter);

			if (checkIfValueIsInRange(songId)) {
				switch (checkHttpHeader(request)) {
				case "json": {
					doGetHeaderJson(response, songId);
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
					doGetHeaderJson(response, songId);
					break;
				}
				case "empty": {
					doGetHeaderJson(response, songId);
					break;
				}
				case "null": {
					doGetHeaderJson(response, songId);

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
		// range zwischen 1 und 10
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

		// PrintWriter out = response.getWriter();
		// out.println(songList.get(songId - 1).toString());
		// der Request gibt mir alle infos mit
		// anhand der request ein song zurückgeben
	}

	public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		out = response.getWriter();
		if (checkIfParamterIsSongId(request)) {
			String parameter = request.getParameter("songId");
			int songId = Integer.parseInt(parameter);

			if (checkIfValueIsInRange(songId)) {
				switch (checkHttpHeaderContentType(request)) {

				case "json":
					doPutHeaderJson(request, response, songId);
					break;

				default:
					response.setStatus(400);
					break;
				}
			}
		}
	}

	public void destroy() {
		JsonWriter writer = new JsonWriter();
		writer.writeSongsToJson(database.getSongMap());
	}

	public void doGetHeaderJson(HttpServletResponse response, int songId) throws IOException {
		out = response.getWriter();
		gson = new Gson();
		out.write(gson.toJson(database.getSongFromMapById(songId)));
		out.flush();
	}

	public void doPutHeaderJson(HttpServletRequest request, HttpServletResponse response, int songId) throws IOException {
		BufferedReader in = request.getReader();
		ObjectMapper objectMapper = new ObjectMapper();
		Song updatedSong = objectMapper.readValue(in, Song.class);

		if (updatedSong.getId() == songId) {
			if (database.update(songId, updatedSong)) {
				response.setStatus(204);

			} else {
				response.setStatus(400);
			}
		} else {
			response.setStatus(400);
		}
	}

	public String checkHttpHeaderContentType(HttpServletRequest request) {
		if (request.getHeader("Content-Type").contains("application/json")) {
			return "json";
		}
		return "error";
	}

	public String checkHttpHeader(HttpServletRequest request) {
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

	public boolean checkIfValueIsInRange(int req) {
		if (req < 1 || req > 10)
			return false;
		else
			return true;
	}

	public boolean checkIfParamterIsSongId(HttpServletRequest request) {
		if (request.getParameterMap().containsKey("songId")) {
			return true;
		}
		return false;
	}

	public boolean checkIfParameterContainsString(String parameter) { // wird gar nicht benutzt
		if (parameter.contains("songId"))
			return true;
		else
			return false;
	}

}
