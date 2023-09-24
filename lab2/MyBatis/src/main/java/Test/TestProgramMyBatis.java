package Test;

import DAO.CarBrandDAO;
import Entities.CarBrand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TestProgramMyBatis {

    @Test
    public void addCarBrand() throws SQLException, ClassNotFoundException {
        SqlSessionFactory sessionFactory;

        {
            try {
                sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        SqlSession session = sessionFactory.openSession();
        CarBrandDAO carBrandDAO = session.getMapper(CarBrandDAO.class);

        CarBrand carBrand = new CarBrand(456789, "vivi", "2003-12-01");

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
        SqlSessionFactory sessionFactory;

        {
            try {
                sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        SqlSession session = sessionFactory.openSession();
        CarBrandDAO carBrandDAO = session.getMapper(CarBrandDAO.class);

        CarBrand carBrand = new CarBrand(456789, "lklk", "2003-12-01");
        carBrandDAO.save(carBrand);
        List<CarBrand> carBrands = carBrandDAO.getAll();
        Assertions.assertTrue(carBrands.size() == 2);
    }

}
