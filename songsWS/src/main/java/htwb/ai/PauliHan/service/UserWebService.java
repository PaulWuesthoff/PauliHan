package htwb.ai.PauliHan.service;

import htwb.ai.PauliHan.authentication.IAuthenticator;
import htwb.ai.PauliHan.dao.IUserDao;
import htwb.ai.PauliHan.model.User;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path ("/auth")
public class UserWebService {
    @Inject
    private IUserDao dao;

    @Inject
    private IAuthenticator authenticator;

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public Response authenticateUser(@QueryParam("userId") String userId,
                                     @QueryParam("key") String key) {


        if (userId == null || userId.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).entity("Could not find userID").build();
        if (key == null || key.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).entity("Could not find key").build();
        User user = dao.getUser(userId, key);
        try {
            if (user != null) {
                String token = authenticator.createToken(user);
                return Response.status(Response.Status.OK).type(MediaType.TEXT_PLAIN_TYPE).entity(token).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).type(MediaType.TEXT_PLAIN_TYPE).entity(dao.getUser(key, userId).toString()).build();
            }
        } catch (PersistenceException | NullPointerException e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).type(MediaType.TEXT_PLAIN_TYPE).entity(userId + key).build();
        }

    }

}
