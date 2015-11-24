package com.grinyov.dao;


        import java.sql.SQLException;
        import java.util.Collection;
        import java.util.ArrayList;
        import java.util.List;
        import javax.swing.*;

        import com.grinyov.entity.Bus;
        import com.grinyov.entity.Driver;
        import com.grinyov.entity.Route;
        import com.grinyov.util.HibernateUtil;
        import org.hibernate.Session;
        import org.hibernate.Query;
/**
 * Created by green on 08/24/15.
 */
public class BusDAOImpl implements BusDAO {
    public void addBus(Bus bus) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(bus);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public void updateBus(Long bus_id, Bus bus) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(bus);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Bus getBusById(Long bus_id) throws SQLException {
        Session session = null;
        Bus bus = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            bus = (Bus) session.load(Bus.class, bus_id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return bus;
    }

    public Collection getAllBusses() throws SQLException {
        Session session = null;
        List busses = new ArrayList<Bus>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            busses = session.createCriteria(Bus.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return busses;
    }

    public void deleteBus(Bus bus) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(bus);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Collection getBussesByDriver(Driver driver) throws SQLException {
        Session session = null;
        List busses = new ArrayList<Bus>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long driver_id = driver.getId();
            Query query = session.createQuery(
                    " select b "
                            + " from Bus b INNER JOIN b.drivers driver"
                            + " where driver.id = :driverId "
            )
                    .setLong("driverId", driver_id);
            busses = (List<Bus>) query.list();
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return busses;
    }

    @Override
    public Collection getBussesByRoute(Route route){
        Session session = null;
        List busses = new ArrayList<Bus>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long route_id = route.getId();
            Query query = session.createQuery("from Bus where route_id = :routeId ").setLong("routeId", route_id);
            busses = (List<Bus>) query.list();
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return busses;
    }

}
