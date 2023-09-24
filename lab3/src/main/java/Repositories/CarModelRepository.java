package Repositories;

import Hibernate.Entities.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    List<CarModel> findCarModelByBrand(String brand);

    List<CarModel> findByModelName(String name);
}
