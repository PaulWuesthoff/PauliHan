package htwb.ai.PauliHan.service;

import htwb.ai.PauliHan.dao.ISongListDao;
import htwb.ai.PauliHan.model.Song;
import htwb.ai.PauliHan.model.SongList;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Collection;
import java.util.List;

@Path("/songLists")
public class SongListsWebService {
// GET, POST DELETE implementieren
	
    @Inject
    ISongListDao dao;

    @GET
    @Path("{userId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})		//alle listen vom user kriegen
    public Response getUserSongLists(@PathParam("userId") String flag, @Context HttpHeaders header) {
    	List<String> authList = header.getRequestHeader(HttpHeaders.AUTHORIZATION);
    	String authentifikator = authList.get(0);	//eig noch checken ob da überhaupt was vorhanden ist
    	Collection<SongList> songListCollection = dao.getSongLists(flag);
    	
    	if (songListCollection != null) {		//public check noch nicht drin
            return Response.status(Response.Status.OK).entity(songListCollection).build();	//ohne status?
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("UserId not found").build();
        }
    }
    
    @GET
    @Path("{songListId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getOneSongList(@PathParam("songListId") int flag){
        Collection <SongList> songListCollection = dao.getSongLists(""+flag);
        return null;
    }

    
}
