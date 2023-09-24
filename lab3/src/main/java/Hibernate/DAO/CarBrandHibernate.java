package Hibernate.DAO;

import Hibernate.Entities.CarBrand;
import Hibernate.Entities.CarModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class CarBrandHibernate implements CarBrandDAO {

    private SessionFactory sessionFactory;

    public CarBrandHibernate() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public CarBrand save(CarBrand entity) throws SQLException {
        Session session = sessionFactory.openSession();
        session.save(entity);
        session.close();
        return entity;
    }

    @Override
    public void deleteById(double id) throws SQLException {
        Session session = sessionFactory.openSession();
        String hql = "delete from CarBrand where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
        session.close();
    }

    @Override
    public void deleteByEntity(CarBrand entity) throws SQLException {
        Session session = sessionFactory.openSession();
        session.delete(entity);
        session.close();
    }

    @Override
    public void deleteAll() throws SQLException {
        Session session = sessionFactory.openSession();
        String hql = "delete from CarBrand";
        Query query = session.createQuery(hql);
        session.close();
        query.executeUpdate();
        session.close();
    }

    @Override
    public CarBrand update(CarBrand entity) throws SQLException {
        Session session = sessionFactory.openSession();
        session.update(entity);
        session.close();
        return entity;
    }

    @Override
    public CarBrand getById(double id) throws SQLException {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from CarBrand where id = :id");
        query.setParameter("id", id);
        List<CarBrand> carBrands = query.list();
        session.close();
        return carBrands.get(0);
    }

    @Override
    public List<CarBrand> getAll() throws SQLException {
        Session session = sessionFactory.openSession();
        List<CarBrand> carBrands = session.createQuery("FROM CarBrand", CarBrand.class).list();
        session.close();
        return carBrands;
    }

    @Override
    public List<CarModel> getAllByCarBrandId(String brand) throws SQLException {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from CarModel where brand = :Brand");
        query.setParameter("Brand", brand);
        List<CarModel> carModels = query.list();
        session.close();
        return carModels;
    }
}
