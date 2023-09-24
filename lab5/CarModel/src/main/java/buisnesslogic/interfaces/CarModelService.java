package buisnesslogic.interfaces;

import buisnesslogic.repository.CarModelRepository;
import buisnesslogic.models.CarModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarModelService {

    private static CarModelRepository carModelRepository;

    public List<CarModel> findAll() {
        return carModelRepository.findAll();
    }

    public Optional<CarModel> findById(long id) {
        return carModelRepository.findById(id);
    }

    public static CarModel saveCarModel(CarModel carModel) {
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