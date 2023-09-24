package Controllers;

import Entities.CarModel;
import Services.CarModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Users")
@RequiredArgsConstructor
public class CarModelController {

    private CarModelService carModelService;

    @GetMapping("/car-model")
    @ApiOperation(value = "Get car model", notes = "Car model")
    public List<CarModel> getCarModels() {
        List<CarModel> carModels = carModelService.findAll();
        return carModels;
    }

    @ApiOperation(value = "Update car model", notes = "Car model")
    @PutMapping("/car-model/{id}")
    public CarModel updateCarBModel (@PathVariable Long id, @RequestBody CarModel carModel) {
        carModelService.updateCarModel(id, carModel);
        return carModel;
    }


    @ApiOperation(value = "Save car model", notes = "Car Model")
    @PostMapping("/car-model")
    public CarModel createCarModel(@RequestBody CarModel carModel) {
        carModelService.saveCarModel(carModel);
        return carModel;
    }

    @ApiOperation(value = "Delete car model", notes = "Car Model")
    @DeleteMapping("/car-model/{id}")
    public void deleteCarModel(@PathVariable Long id) {
        carModelService.deleteById(id);
    }

}