package Test;


import DAO.CarBrandDAO;
import DAO.CarBrandHibernate;
import Entities.CarBrand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class TestProgramHibernate {
    @Test
    public void addCarBrand() throws SQLException, ClassNotFoundException {
        CarBrand carBrand = new CarBrand(456789, "vivi", "2003-12-01");
        CarBrandDAO carBrandDAO = new CarBrandHibernate();
        carBrandDAO.save(carBrand);

        for (CarBrand value :
                carBrandDAO.getAll()) {
            if (value.getId() == carBrand.getId()) {
                Assertions.assertTrue(value.getId() == carBrand.getId());
                break;
            }
        }
    }

    @Test
    public void deleteByID() throws SQLException, ClassNotFoundException {
        CarBrand carBrand = new CarBrand(456789, "vivi", "2003-12-01");
        CarBrandDAO carBrandDAO = new CarBrandHibernate();
        carBrandDAO.save(carBrand);
        List<CarBrand> carBrands = carBrandDAO.getAll();
        Assertions.assertTrue(carBrands.size() == 1);
    }

}
