package DAO;

import Entities.CarBrand;
import Entities.CarModel;

import java.sql.SQLException;
import java.util.List;

public interface CarBrandDAO {

    CarBrand save(CarBrand entity) throws SQLException;
    void deleteById(double id) throws SQLException;
    void deleteByEntity(CarBrand entity) throws SQLException;
    void deleteAll() throws SQLException;
    CarBrand update(CarBrand entity) throws SQLException;
    CarBrand getById(double id) throws SQLException;
    List<CarBrand> getAll() throws SQLException;
    List<CarModel> getAllByCarBrandId(String brand) throws SQLException;

}
