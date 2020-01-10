package htwb.ai.PauliHan.service;

import htwb.ai.PauliHan.authentication.IAuthenticator;
import htwb.ai.PauliHan.dao.ISongDAO;
import htwb.ai.PauliHan.dao.ISongListDao;
import htwb.ai.PauliHan.model.Song;
import htwb.ai.PauliHan.model.SongList;
import htwb.ai.PauliHan.model.User;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Path("/songLists")
public class SongListsWebService {
// GET, POST DELETE implementieren

    @Inject
    ISongListDao songListDao;

    @Inject
    IAuthenticator authenticator;

    @Inject
    ISongDAO songDAO;

    //alle songlisten vom user kriegen
    @GET
    @Path("{userId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUserSongLists(@PathParam("userId") String flag, @Context HttpHeaders header) {

        List<String> authList = header.getRequestHeader(HttpHeaders.AUTHORIZATION);
        String token = authList.get(0);	//eig noch checken ob da überhaupt was vorhanden ist

        boolean isTokenRight = authenticator.authenticate(token);
        if (isTokenRight)
            // vielleicht auch über die getUserByAuthorizationToken() methode ? 
            authenticator.getTokenMap().get(token);

        Collection<SongList> songListCollection = songListDao.getSongLists(flag);

        if (songListCollection != null) {		//public check noch nicht drin
            return Response.status(Response.Status.OK).entity(songListCollection).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("UserId not found").build();
        }
    }

    //eine songliste über id kriegen
    @GET
    @Path("{songListId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getOneSongList(@PathParam("songListId") int flag){
        Collection <SongList> songListCollection = songListDao.getSongLists(""+flag);
        return null;
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createNewSongList(SongList songList, @HeaderParam("Authorization") String authorizationToken, @Context UriInfo uriInfo) {
        User user = getUserByAuthorizationToken(authorizationToken);
        try {
            if (songList == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            if (songList.getSongCollection().isEmpty() || songList.getSongCollection() == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            if (songList.getState() == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            Collection<Song> songsFromPayload = songList.getSongCollection();
            for (Song song : songsFromPayload) {
                Song temp = songDAO.getSong(song.getId());
                if (temp == null) {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
            }

            songList.setOwnerId(user.getUserId());
            Integer newId = songListDao.addSongList(songList);
            if (newId == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(newId));
            return Response.created(uriBuilder.build()).build();

        } catch (PersistenceException e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteSongList(@PathParam("id") Integer id, @HeaderParam("Authorization") String authorizationToken) {
        User user = getUserByAuthorizationToken(authorizationToken);
        try {

            Collection<SongList> songListCollection = songListDao.getSongLists(String.valueOf(id));
            if (id == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            if (!songListCollection.stream().findFirst().get().getOwnerId().equals(user.getUserId())) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
            if (songListDao.deleteSongList(id)) {
                return Response.status(Response.Status.OK).build();
            } else {
                Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (PersistenceException e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
    }

    @PUT
    public Response putSongList() {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
    }

    private User getUserByAuthorizationToken(String autorizationToken) {
        Set<User> userSet = authenticator.getTokenMap().keySet();
        return userSet.stream().filter(user -> autorizationToken.equals(authenticator.getTokenMap().get(user))).findFirst().orElse(null);
    }
}
