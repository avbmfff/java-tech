package buisnesslogic.rest.api;
import buisnesslogic.models.CarModel;
import buisnesslogic.rest.models.CreateCarModelRequest;
import buisnesslogic.rest.models.ErrorModel;
import jakarta.xml.bind.ValidationException;

import javax.ws.rs.Consumes;
import buisnesslogic.interfaces.CarModelService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/carModels")

public class CarModelResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCarModel(CreateCarModelRequest createCarModelRequest) throws URISyntaxException {
        createCarModelRequest.validate();

        var carModel = new CarModel(createCarModelRequest.getId(), createCarModelRequest.getLength(), createCarModelRequest.getWidth(),
                createCarModelRequest.getHigher(), createCarModelRequest.getModelName(), createCarModelRequest.getBrand(), createCarModelRequest.getBodyType());

        var createdCarModel = CarModelService.saveCarModel(carModel);

        return Response.created(new URI(String.format("carModel/%s", createdCarModel.getId()))).build();

    }
}
