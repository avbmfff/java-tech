package buisnesslogic.repository;

import buisnesslogic.models.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {
    void saveCarBrand(CarBrand carBrand);
    CarBrand getCarBrandById(String id);
}
