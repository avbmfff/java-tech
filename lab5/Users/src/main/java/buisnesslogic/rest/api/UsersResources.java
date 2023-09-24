package buisnesslogic.rest.api;

import buisnesslogic.interfaces.UserService;
import buisnesslogic.rest.UserServiceFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("user/{id}")
public class UsersResources {
    private UserService userService;

    public UsersResources(){
        userService = new UserServiceFactory().getService();
    }

    @GET
    public Response getUser(@PathParam("id") String id){
        var user = this.userService.findById(Long.parseLong(id));
        if(user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }
}
