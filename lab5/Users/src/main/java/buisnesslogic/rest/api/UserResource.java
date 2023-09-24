package buisnesslogic.rest.api;

import buisnesslogic.interfaces.UserDetailService;
import buisnesslogic.interfaces.UserService;
import buisnesslogic.models.User;
import buisnesslogic.rest.models.CreateUserRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/carModels")

public class UserResource {
    private CreateUserRequest createUserRequest;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(CreateUserRequest createCarModelRequest) throws URISyntaxException {
        createUserRequest.validate();

        var user = new User(createUserRequest.getId(), createUserRequest.getUsername(), createUserRequest.getPassword(), createUserRequest.getRoles());

        var createdUser = UserService.saveUser(user);

        return Response.created(new URI(String.format("User/%s", createdUser.getId()))).build();

    }
}
