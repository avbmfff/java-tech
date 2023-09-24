package DAO;

import Entities.CarModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CarModelDAO {
    CarModel save(CarModel entity) throws SQLException;
    void deleteById(long id) throws SQLException;
    void deleteByEntity(CarModel entity) throws SQLException;
    void deleteAll() throws SQLException;
    CarModel update(CarModel entity) throws SQLException;
    CarModel getById(long id) throws SQLException;
    ArrayList<CarModel> getAll() throws SQLException;
}
