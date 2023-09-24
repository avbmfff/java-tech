package Controllers;

import Hibernate.Entities.CarBrand;
import Services.CarBrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarBrandController {
    @Autowired
    private CarBrandService carBrandService;

    @RequestMapping("/read-carBrand")
    @ApiOperation(value = "Show for reading car brands", notes = "Car brand")
    public String showReadCarBrandPage(Model model) {
        model.addAttribute("carBrands", carBrandService.findAll());
        return "readCarBrand";
    }

    @RequestMapping("/create-contact")
    @ApiOperation(value = "Show for creating car brands", notes = "Car brand")
    public String showCreateCarBrand(Model model) {
        model.addAttribute("command", new CarBrand());
        return "createCarBrand";
    }

    @RequestMapping(value = "/create-CarBrand", method = RequestMethod.POST)
    @ApiOperation(value = "Create car brands", notes = "Car brand")
    public String createCarBrand(@ModelAttribute("CarBrand") CarBrand carBrand) {
        carBrandService.saveCarBrand(carBrand);
        return "redirect:/read-contact";
    }

    @RequestMapping(value = "/update-CarBrand/{id}")
    @ApiOperation(value = "Show for updating car brands", notes = "Car brand")
    public String showUpdateCarBrandPage(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("command", CarBrandService.findById(id).orElse(null));
        return "updateCarBrand";
    }

    @RequestMapping(value = "/update-CarBrand/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "Update car brands", notes = "Car brand")
    public String updateCarBrand(@PathVariable long id, @ModelAttribute("CarModel") CarBrand carBrand) {
        carBrandService.updateCarBrand(id, carBrand);
        return "redirect:/read-CarBrand";
    }

    @RequestMapping(value = "/delete-CarBrand/{id}")
    @ApiOperation(value = "Delete car brands", notes = "Car brand")
    public String deleteCarBrand(@PathVariable long id) {
        carBrandService.deleteById(id);
        return "redirect:/read-CarBrand";
    }

}
