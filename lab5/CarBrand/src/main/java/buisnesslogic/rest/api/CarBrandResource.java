package buisnesslogic.rest.api;
import buisnesslogic.interfaces.CarBrandService;
import buisnesslogic.models.CarBrand;
import buisnesslogic.rest.models.CreateCarBrandRequest;
import buisnesslogic.rest.models.ErrorModel;

import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/carBrands")

public class CarBrandResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCarBrand(CreateCarBrandRequest createCarBrandRequest) throws URISyntaxException {
        try {
            createCarBrandRequest.validate();

            var carBrand = new CarBrand(createCarBrandRequest.getId(), createCarBrandRequest.getBrand(), createCarBrandRequest.getDate());

            var createdCarBrand = CarBrandService.saveCarBrand(carBrand);

            return Response.created(new URI(String.format("carModel/%s", createdCarBrand.getId()))).build();

        } catch (ValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel(e.getMessage())).build();

    }
    }
}
