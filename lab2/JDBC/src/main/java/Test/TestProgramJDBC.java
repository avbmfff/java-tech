package Test;


import DAO.CarBrandDAO;
import DAO.CarBrandJDBC;
import Entities.CarBrand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class TestProgramJDBC {
    @Test
    public void addCarBrand() throws SQLException, ClassNotFoundException {
        CarBrand carBrand = new CarBrand(456789, "vivi", "2003-12-01");
        CarBrandDAO carBrandDAO = new CarBrandJDBC("jdbc:mysql://localhost:3306/Car", "root", "root!");
        carBrandDAO.save(carBrand);

        for (CarBrand value :
             carBrandDAO.getAll()) {
            if(value.getId() == carBrand.getId()){
                Assertions.assertTrue(value.getId() == carBrand.getId());
                break;
            }
        }
    }

    @Test
    public void deleteByID() throws SQLException, ClassNotFoundException {
        CarBrandDAO carBrandDAO = new CarBrandJDBC("jdbc:mysql://localhost:3306/Car", "root", "Sdlkn1j1ijw!");
        carBrandDAO.deleteById(456789);
        ArrayList<CarBrand> carBrands = carBrandDAO.getAll();
        Assertions.assertTrue(carBrands.size() == 1);
    }


}
