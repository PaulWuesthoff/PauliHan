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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Path("/songLists")
public class SongListsWebService {
// GET, POST DELETE implementieren

    @Inject
    ISongListDao songListDao;

    @Inject
    IAuthenticator authenticator;

    @Inject
    ISongDAO songDAO;


    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUserSongLists(@QueryParam("userId") String flag, @Context HttpHeaders header) {
        Response response = userAuthorization(header);
        if (response != null) {
            return response;
        }
        String token = header.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
        User user = getUserByAuthorizationToken(token);       //authenticator.getTokenMap().get(token);
        Collection<SongList> songListCollection = songListDao.getSongLists(flag);
        songListCollection = controllList(user, songListCollection);
        if (songListCollection != null) {
            for (SongList songList : songListCollection) {
                songList.getUser().setKey("*********");
            }
            GenericEntity<Collection<SongList>> entity = new GenericEntity<Collection<SongList>>(songListCollection) {
            };
            return Response.status(Response.Status.OK).entity(entity).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("UserId not found").build();
        }
    }

    @GET
    @Path("{songListId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getOneSongList(@PathParam("songListId") int flag, @Context HttpHeaders header) {
        Response response = userAuthorization(header);
        if (response != null) {
            return response;
        }
        String token = header.getRequestHeader(HttpHeaders.AUTHORIZATION).get(0);
        User user = getUserByAuthorizationToken(token);
        Collection<SongList> songListCollection = songListDao.getSongLists(Integer.toString(flag));
        if (songListCollection == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("SongListId not found").build();
        }
        songListCollection = controllList(user, songListCollection);
        if (songListCollection == null || songListCollection.size() == 0) {
            return Response.status(Response.Status.FORBIDDEN).entity("SongList is private").build();
        }
        for (SongList songList : songListCollection) {
            songList.getUser().setKey("*********");
        }
        return Response.status(Response.Status.OK).entity(songListCollection).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createNewSongList(SongList songList, @HeaderParam("Authorization") String authorizationToken, @Context UriInfo uriInfo) {
        User user = getUserByAuthorizationToken(authorizationToken);
        if (songList == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (songList.getSongList().isEmpty() || songList.getSongList() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (songList.getIsPrivate() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Collection<Song> songsFromPayload = songList.getSongList();
        for (Song song : songsFromPayload) {
            Song temp = songDAO.getSong(song.getId());
            if (temp == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }

        //songList.setId(getNewIndex());
        songList.setUser(user);
        try {
            System.out.println(songList.toString());
            Integer newId = songListDao.addSongList(songList);
            if (newId == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(newId));
            return Response.created(uriBuilder.build()).build();
        } catch (PersistenceException e) {
            e.printStackTrace();
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
            if (!songListCollection.stream().findFirst().get().getUser().getUserId().equals(user.getUserId())) {
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

    //gibt eine neue Liste zurueck die den anforderungen passt. zb mmuster kriegt nur public listen von eschuler
    private Collection<SongList> controllList(User user, Collection<SongList> songListCollection) {
        if (songListCollection == null) {
            return null;
        }
        String userId = user.getUserId();
        Collection<SongList> filteredList = new ArrayList<>();

        //wenn selber name dann alles zur�ckgeben
        //wenn unterschieldicher name dann auf public achten
        for (SongList sl : songListCollection) {
            if (userId.equals(sl.getUser().getUserId())) {
                return songListCollection;
            } else {
                if (sl.getIsPrivate() == false) { //wenn liste jmd anderes geh�rt und public ist
                    filteredList.add(sl);
                }
            }
        }
        return filteredList;
    }

    private Response userAuthorization(HttpHeaders header) {
        List<String> authList = header.getRequestHeader(HttpHeaders.AUTHORIZATION);
        if (authList.get(0) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No authorization-key").build();
        }
        String token = authList.get(0);
        boolean isTokenRight = authenticator.authenticate(token);
        if (!isTokenRight) {
            return Response.status(Response.Status.NOT_FOUND).entity("Wrong authorization-key").build();
        }
        return null;
    }

//    private Integer getNewIndex() {
//        Collection<SongList> songList = songListDao.getAll();
//        Set<Integer> intSet = IntStream.rangeClosed(1, songList.size() + 1).boxed().collect(Collectors.toSet());
//        Set<Integer> alreadyUsedIds = songList.stream().map(SongList::getId).collect(Collectors.toSet());
//        intSet.removeAll(alreadyUsedIds);
//        return intSet.stream().findFirst().orElse(-1);
//    }
}







