package Repositories;

import Entities.CarBrand;
import Entities.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {
    List<CarModel> findByModelName(String name);
}
