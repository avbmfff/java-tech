package DAO;

import Entities.BodyType;
import Entities.CarModel;

import java.sql.*;
import java.util.ArrayList;

public class CarModelJDBC implements CarModelDAO {

    private Connection connection;
    public CarModelJDBC(String url, String user, String password) throws ClassNotFoundException, SQLException{
        this.connection = DriverManager.getConnection(url, user, password);
    }
    @Override
    public CarModel save(CarModel entity) throws SQLException {
    String sql = "insert into car_model (ID, ModelName, Length, Width, Body, Brand)" + "values (?,?,?,?,?,?)";
    PreparedStatement ps = this.connection.prepareStatement(sql);

        ps.setDouble(1, entity.getId());
        ps.setString(2, entity.getModelName());
        ps.setInt(3,entity.getLength());
        ps.setInt(4,entity.getWidth());
        ps.setString(5, String.valueOf(entity.getBodyType()));
        ps.setString(6, entity.getBrand());
        ps.executeUpdate();
        return entity;
}

    @Override
    public void deleteById(long id) throws SQLException {
        String sql = "delete from car_model where id = ?";
        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.setDouble(1, id);
        ps.executeUpdate();
    }

    @Override
    public void deleteByEntity(CarModel entity) throws SQLException {
        String sql = "delete from car_model where ID = ? and ModelName = ? and Length = ? and Width = ? and Body = ? and Brand = ?";
        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.setDouble(1, entity.getId());
        ps.setString(2, entity.getModelName());
        ps.setInt(3,entity.getLength());
        ps.setInt(4,entity.getWidth());
        ps.setString(5, String.valueOf(entity.getBodyType()));
        ps.setString(6, entity.getBrand());
        ps.executeUpdate();
    }

    @Override
    public void deleteAll() throws SQLException{
        String sql = "delete * from car_model";
        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.executeUpdate();
    }

    @Override
    public CarModel update(CarModel entity) throws SQLException {
        String sql = "update car_model set Brand = ? where ID = ?";
        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.setString(1, entity.getBrand());
        ps.setDouble(2, entity.getId());
        ps.executeUpdate();
        return entity;
    }

    @Override
    public CarModel getById(long id) throws SQLException {
        ArrayList<CarModel> array = getAll();
        for (CarModel carModel : array) {
            if(carModel.getId() == id) {
                return carModel;
            }
        }
        return null;
    }

    @Override
    public ArrayList<CarModel> getAll() throws SQLException {

        ArrayList<CarModel> array = new ArrayList<CarModel>();

        ResultSet result = this.connection.prepareStatement("select * from car_model").executeQuery();
        while(result.next()) {
            CarModel carModel = new CarModel();
            carModel.setId(result.getDouble("ID"));
            carModel.setModelName(result.getString("ModelName"));
            carModel.setWidth(result.getInt("Width"));
            carModel.setLength(result.getInt("Length"));
            carModel.setBodyType(BodyType.valueOf(result.getString("Body")));
            carModel.setBrand(result.getString("Brand"));
            array.add(carModel);
        }
        result.close();
        return array;
    }
}
