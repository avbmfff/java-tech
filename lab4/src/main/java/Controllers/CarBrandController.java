package Controllers;

import Entities.CarBrand;
import Entities.CarModel;
import Services.CarBrandService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarBrandController {

    private CarBrandService carBrandService;

    @GetMapping("/car-brands")
    @ApiOperation(value = "Get car brands", notes = "Car brands")
    public List<CarBrand> getCarBrands() {
        List<CarBrand> carBrands = carBrandService.findAll();
        return carBrands;
    }

    @ApiOperation(value = "Update car brand", notes = "Car brand")
    @PutMapping("/car-brand/{id}")
    public CarBrand updateCarBrand (@PathVariable Long id, @RequestBody CarBrand carBrand) {
        carBrandService.updateCarBrand(id, carBrand);
        return carBrand;
    }


    @ApiOperation(value = "Save car brands", notes = "Car brand")
    @PostMapping("/car-brand")
    public CarBrand createCarBrand(@RequestBody CarBrand carBrand) {
        carBrandService.saveCarBrand(carBrand);
        return carBrand;
    }

    @ApiOperation(value = "Delete car brands", notes = "Car brand")
    @DeleteMapping("/car-brand/{id}")
    public void deleteCarBrand(@PathVariable Long id) {
        carBrandService.deleteById(id);
    }

}