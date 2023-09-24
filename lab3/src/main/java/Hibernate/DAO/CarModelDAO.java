package Hibernate.DAO;

import Hibernate.Entities.CarModel;

import java.sql.SQLException;
import java.util.List;

public interface CarModelDAO {
    CarModel save(CarModel entity) throws SQLException;
    void deleteById(long id) throws SQLException;
    void deleteByEntity(CarModel entity) throws SQLException;
    void deleteAll() throws SQLException;
    CarModel update(CarModel entity) throws SQLException;
    CarModel getById(long id) throws SQLException;
    List<CarModel> getAll() throws SQLException;
}
