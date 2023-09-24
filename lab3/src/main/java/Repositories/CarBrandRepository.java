package Repositories;

import Hibernate.Entities.CarBrand;
import Hibernate.Entities.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {
    List<CarModel> findByModelName(String name);
}
