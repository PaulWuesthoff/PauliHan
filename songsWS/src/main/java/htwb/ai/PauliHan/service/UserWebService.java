package htwb.ai.PauliHan.service;

import htwb.ai.PauliHan.model.IUserDao;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


//@Path("/auth")
public class UserWebService {
    @Inject
    private IUserDao dao;
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public Response authenticateUser(@FormParam("userId") String userId,
                                     @FormParam("key") String key)
    {
        if(dao.authenticate(userId,key)){
            RandomString token = new RandomString(15);
            return Response.ok(token.nextString()).build();
        }else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }

}
