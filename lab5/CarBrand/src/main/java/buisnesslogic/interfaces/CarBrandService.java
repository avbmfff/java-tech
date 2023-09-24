package buisnesslogic.interfaces;

import buisnesslogic.repository.CarBrandRepository;
import buisnesslogic.models.CarBrand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CarBrandService {

    private static CarBrandRepository carBrandRepository;

    public List<CarBrand> findAll() {
        return carBrandRepository.findAll();
    }

    public static Optional<CarBrand> findById(long id) {
        return carBrandRepository.findById(id);
    }

    public static CarBrand saveCarBrand(CarBrand carBrand) {
        return carBrandRepository.save(carBrand);
    }

    public CarBrand updateCarBrand(long id, CarBrand carBrand) {
        CarBrand updatedCarBrand = carBrandRepository.findById(id).orElse(null);
        updatedCarBrand.setBrand(carBrand.getBrand());
        updatedCarBrand.setId(carBrand.getId());
        updatedCarBrand.setDate(carBrand.getDate());
        return carBrandRepository.save(updatedCarBrand);
    }

    public void deleteById(long id) {
        carBrandRepository.deleteById(id);
    }
}