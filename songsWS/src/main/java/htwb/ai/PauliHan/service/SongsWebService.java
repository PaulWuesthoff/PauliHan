package htwb.ai.PauliHan.service;

import htwb.ai.PauliHan.dao.ISongDAO;
import htwb.ai.PauliHan.model.Song;


import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.Collection;



@Path("/songs")
public class SongsWebService {
    @Inject
    private ISongDAO dao;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Song> getAllSongs() {
        Collection<Song> songCollection = dao.getSongs();
        return dao.getSongs();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSongById(@PathParam("id") Integer id) {
        Song song = null;
        try {
            song = dao.getSong(id);
        } catch (PersistenceException e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }

        if (song != null) {
            return Response.status(Response.Status.OK).entity(song).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("ID not found").build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createSong(Song song, @Context UriInfo uriInfo) {
        try {


            if (song == null) {
                Response.status(Response.Status.BAD_REQUEST).build();
            }
            if (!song.isValid()) {
                Response.status(Response.Status.BAD_REQUEST).build();
            }

            Integer newId = dao.addSong(song);
            if (newId == null) {
                Response.status(Response.Status.BAD_REQUEST).build();
            }
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(newId));
            return Response.created(uriBuilder.build()).build();    //return 201?
        } catch (PersistenceException e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateSong(@PathParam("id") Integer id, Song song) {
        //id fore check if song exits
        if (!song.isValid()) return Response.status(Response.Status.BAD_REQUEST).entity("Payload is invalid.").build();
        if (song.getId() != id) return Response.status(Response.Status.BAD_REQUEST).entity("Ids dont match.").build();
        try {


            Song updatedSong = dao.updateSong(song);

            if (updatedSong != null) {
                return Response.status((Response.Status.NO_CONTENT)).entity("Updated").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("ID not found").build();
            }
        } catch (PersistenceException e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteContact(@PathParam("id") Integer id) {
        try {


            if (dao.deleteSong(id)) {
                return Response.status((Response.Status.NO_CONTENT)).entity(id).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("ID not found").build();
            }
        }catch (PersistenceException e){
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }

}
