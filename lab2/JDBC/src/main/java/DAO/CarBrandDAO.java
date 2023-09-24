package DAO;

import Entities.CarBrand;
import Entities.CarModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CarBrandDAO {

    CarBrand save(CarBrand entity) throws SQLException;
    void deleteById(double id) throws SQLException;
    void deleteByEntity(CarBrand entity) throws SQLException;
    void deleteAll() throws SQLException;
    CarBrand update(CarBrand entity) throws SQLException;
    CarBrand getById(double id) throws SQLException;
    ArrayList<CarBrand> getAll() throws SQLException;
    ArrayList<CarModel> getAllByCarBrandId(String brand) throws SQLException;

}
