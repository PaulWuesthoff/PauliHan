package htwb.ai.PauliHan.service;

import htwb.ai.PauliHan.dao.ISongListDao;
import htwb.ai.PauliHan.model.Song;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/songLists")
public class SongListsWebService {

    @Inject
    ISongListDao dao;

    @GET
    @Path("{userId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Song> getAllSongs(@PathParam("userId") String flag) {
        Collection<Song> songListCollection = dao.getSongLists(flag);
        return songListCollection;
    }

}
