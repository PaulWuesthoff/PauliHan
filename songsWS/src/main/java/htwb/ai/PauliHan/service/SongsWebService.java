package htwb.ai.PauliHan.service;

import htwb.ai.PauliHan.model.ISongDAO;
import htwb.ai.PauliHan.model.Song;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.Collection;


@Path("/songs")
public class SongsWebService {
    @Inject
    private ISongDAO dao;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Song> getAllContacts() {
        return dao.getSongs();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSongById(@PathParam("id") Integer id) {
        Song song = dao.getSong(id);
        if (song != null) {
            return Response.status(Response.Status.OK).entity(song).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("ID not found").build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createSong(Song song, @Context UriInfo uriInfo) {
        Integer newId = dao.addSong(song);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(newId));
        return Response.created(uriBuilder.build()).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateSong(@PathParam("id") Integer id, Song song) {
        //id fore check if song exits
        Song updatedSong = dao.updateSong(song);

        if (updatedSong != null) {
            return Response.status((Response.Status.OK)).entity("Updated").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("ID not found").build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteContact(@PathParam("id") Integer id) {
        if (dao.deleteSong(id)) {
            return Response.status((Response.Status.OK)).entity(id).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("ID not found").build();
        }
    }

}
