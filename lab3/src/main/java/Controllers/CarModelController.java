package Controllers;

import Hibernate.Entities.CarBrand;
import Hibernate.Entities.CarModel;
import Services.CarBrandService;
import Services.CarModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Users")
public class CarModelController {
    @Autowired
    private CarModelService carModelService;

    @RequestMapping("/read-carModel")
    @ApiOperation(value = "Show for reading car models", notes = "Car model")
    public String showReadCarModelPage(@NotNull Model model) {
        model.addAttribute("carModels", carModelService.findAll());
        return "readCarModel";
    }

    @RequestMapping("/create-CarModel")
    @ApiOperation(value = "Show for creating car models", notes = "Car model")
    public String showCreateCarModelPage(Model model) {
        model.addAttribute("command", new CarBrand());
        return "createCarBrand";
    }

    @RequestMapping(value = "/create-CarModel", method = RequestMethod.POST)
    @ApiOperation(value = "Creating car models", notes = "Car model")
    public String createCarModel(@ModelAttribute("CarModel") CarModel carModel) {
        carModelService.saveCarModel(carModel);
        return "redirect:/read-contact";
    }

    @RequestMapping(value = "/update-CarBrand/{id}")
    @ApiOperation(value = "Show for updating car models", notes = "Car model")
    public String showUpdateCarModelPage(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("command", CarBrandService.findById(id).orElse(null));
        return "updateCarBrand";
    }

    @RequestMapping(value = "/update-CarBrand/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "Update car models", notes = "Car model")
    public String updateCarModel(@PathVariable long id, @ModelAttribute("CarModel") CarModel carModel) {
        carModelService.updateCarModel(id, carModel);
        return "redirect:/read-CarModel";
    }

    @RequestMapping(value = "/delete-CarModel/{id}")
    @ApiOperation(value = "Delete car models", notes = "Car model")
    public String deleteCarModel(@PathVariable long id) {
        carModelService.deleteById(id);
        return "redirect:/read-CarModel";
    }

}
