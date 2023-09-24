package buisnesslogic.rest.api;

import buisnesslogic.interfaces.CarBrandService;
import buisnesslogic.rest.CarBrandServiceFactory;
import lombok.Getter;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("carBrands/{id}")
public class CarBrandsResources {
    private CarBrandService carBrandService;

    public CarBrandsResources(){
        carBrandService = new CarBrandServiceFactory().getService();
    }

    @GET
    public Response getCatBrand(@PathParam("id") String id){
        var carBrand = this.carBrandService.findById(Long.parseLong(id));
        if(carBrand == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(carBrand).build();
    }
}
