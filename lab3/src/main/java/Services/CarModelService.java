package Services;

import Hibernate.Entities.CarModel;
import Repositories.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarModelService {
    @Autowired
    private CarModelRepository carModelRepository;

    public List<CarModel> findAll() {
        return carModelRepository.findAll();
    }

    public Optional<CarModel> findById(long id) {
        return carModelRepository.findById(id);
    }

    public CarModel saveCarModel(CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    public CarModel updateCarModel(long id, CarModel carModel) {
        CarModel updatedCarModel = carModelRepository.findById(id).orElse(null);
        updatedCarModel.setBrand(carModel.getBrand());
        updatedCarModel.setModelName(carModel.getModelName());
        updatedCarModel.setId(carModel.getId());
        updatedCarModel.setWidth(carModel.getWidth());
        updatedCarModel.setLength(carModel.getLength());
        updatedCarModel.setBodyType(carModel.getBodyType());
        updatedCarModel.setHigher(carModel.getHigher());
        return carModelRepository.save(updatedCarModel);
    }

    public void deleteById(long id) {
        carModelRepository.deleteById(id);
    }

}
