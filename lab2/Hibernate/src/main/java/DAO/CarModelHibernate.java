package DAO;

import Entities.CarModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class CarModelHibernate implements CarModelDAO {

    private SessionFactory sessionFactory;

    public CarModelHibernate() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    @Override
    public CarModel save(CarModel entity) throws SQLException {
        Session session = sessionFactory.openSession();
        session.save(entity);
        session.close();
        return entity;
    }

    @Override
    public void deleteById(long id) throws SQLException {
        Session session = sessionFactory.openSession();
        String hql = "delete from CarModel where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
        session.close();
    }

    @Override
    public void deleteByEntity(CarModel entity) throws SQLException {
        Session session = sessionFactory.openSession();
        session.delete(entity);
        session.close();
    }

    @Override
    public void deleteAll() throws SQLException {
        Session session = sessionFactory.openSession();
        String hql = "delete from CarModel";
        Query query = session.createQuery(hql);
        session.close();
        query.executeUpdate();
        session.close();
    }

    @Override
    public CarModel update(CarModel entity) throws SQLException {
        Session session = sessionFactory.openSession();
        session.update(entity);
        session.close();
        return entity;
    }

    @Override
    public CarModel getById(long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from CarModel where id = :id");
        query.setParameter("id", id);
        List<CarModel> carModels = query.list();
        session.close();
        return carModels.get(0);
    }

    @Override
    public List<CarModel> getAll() throws SQLException {
        Session session = sessionFactory.openSession();
        List<CarModel> carModels = session.createQuery("FROM CarModel ", CarModel.class).list();
        session.close();
        return carModels;
    }
}
