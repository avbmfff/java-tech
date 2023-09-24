package buisnesslogic.rest.api;

import buisnesslogic.interfaces.CarModelService;
import buisnesslogic.rest.CarModelServiceFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("carBrands/{id}")
public class CarModelsResources {
    private CarModelService carModelService;

    public CarModelsResources(){
        carModelService = new CarModelServiceFactory().getService();
    }

    @GET
    public Response getCatModel(@PathParam("id") String id){
        var carModel = this.carModelService.findById(Long.parseLong(id));
        if(carModel == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(carModel).build();
    }
}
