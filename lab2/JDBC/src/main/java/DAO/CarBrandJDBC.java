package DAO;

import Entities.BodyType;
import Entities.CarBrand;
import Entities.CarModel;

import java.sql.*;
import java.util.ArrayList;

public class CarBrandJDBC implements CarBrandDAO {

    private Connection connection;

    public CarBrandJDBC(String url, String user, String password) throws ClassNotFoundException, SQLException{
        this.connection = DriverManager.getConnection(url, user, password);
    }
    @Override
    public CarBrand save(CarBrand entity) throws SQLException {
        String sql = "insert into car_brand (ID, Brand, Date)" + "values (?,?,?)";
        PreparedStatement ps = this.connection.prepareStatement(sql);

        ps.setDouble(1, entity.getId());
        ps.setString(2, entity.getBrand());
        ps.setDate(3, java.sql.Date.valueOf(entity.getDate()));
        ps.executeUpdate();
        return entity;
    }

    @Override
    public void deleteById(double id) throws SQLException {
        String sql = "delete from car_brand where id = ?";
        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.setDouble(1, id);
        ps.executeUpdate();
    }

    @Override
    public void deleteByEntity(CarBrand entity) throws SQLException {
        String sql = "delete from car_brand where ID = ? and Brand = ? and Date = ?";
        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.setDouble(1, entity.getId());
        ps.setString(2, entity.getBrand());
        ps.setDate(2, java.sql.Date.valueOf(entity.getDate()));
        ps.executeUpdate();
    }

    @Override
    public void deleteAll() throws SQLException {
        String sql = "delete * from car_brand";
        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.executeUpdate();
    }

    @Override
    public CarBrand update(CarBrand entity) throws SQLException {
        String sql = "update car.car_brand set Brand = ? where ID = ?";
        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.setString(1, entity.getBrand());
        ps.setDouble(2, entity.getId());
        ps.executeUpdate();
        return entity;
    }

    @Override
    public CarBrand getById(double id) throws SQLException {
        ArrayList<CarBrand> array = getAll();
        for (CarBrand carBrand : array) {
            if(carBrand.getId() == id) {
                return carBrand;
            }
        }
        return null;
    }

    @Override
    public ArrayList<CarBrand> getAll() throws SQLException {

        ArrayList<CarBrand> array = new ArrayList<CarBrand>();

        ResultSet result = this.connection.prepareStatement("select * from car_brand").executeQuery();
        while(result.next()) {
            CarBrand carBrand = new CarBrand();
            carBrand.setId(result.getDouble("ID"));
            carBrand.setBrand(result.getString("Brand"));
            carBrand.setDate(result.getString("Date"));
            array.add(carBrand);
        }
        result.close();
        return array;
    }

    @Override
    public ArrayList<CarModel> getAllByCarBrandId(String brand) throws SQLException {
        ArrayList<CarModel> array = new ArrayList<CarModel>();
        ResultSet result = this.connection.prepareStatement("select * from car_model").executeQuery();
        while(result.next()) {
            CarModel carModel = new CarModel();
            carModel.setId(result.getDouble("ID"));
            carModel.setBrand(result.getString("Brand"));
            carModel.setBodyType(BodyType.valueOf(result.getString("Body")));
            carModel.setModelName(result.getString("ModelName"));
            carModel.setLength(result.getInt("Length"));
            carModel.setWidth(result.getInt("Width"));
            if (carModel.getBrand().equals(brand)){
                array.add(carModel);
            }
        }
        result.close();
        return array;
    }
}
