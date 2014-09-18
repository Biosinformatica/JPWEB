package dao;

import java.util.List;
import model.Sucursal;
import org.hibernate.Session;
import util.HibernateUtil;

public class SucursalDaoImpl implements SucursalDao{

    @Override
    public List<Sucursal> selectItems() {
        List<Sucursal> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Sucursal";
        try {
            sesion.beginTransaction();
            listado = sesion.createQuery(sql).list();
            sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        }
        return listado;
    }

    @Override
    public Sucursal findBySucursal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sucursal> findAll() {
        List<Sucursal> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Sucursal";
        try {
            sesion.beginTransaction();
            listado = sesion.createQuery(sql).list();
            sesion.beginTransaction().commit();
        } catch (Exception e) {
            sesion.beginTransaction().rollback();
        }
        return listado;
    }

    @Override
    public boolean create(Sucursal sucursal) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(sucursal);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Sucursal sucursal) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Sucursal sucursaldb = (Sucursal) sesion.load(Sucursal.class, sucursal.getId());
            sucursaldb.setNombre(sucursal.getNombre());
            sucursaldb.setDescripcion(sucursal.getDescripcion());
            sucursaldb.setTelefono(sucursal.getTelefono());
            sesion.update(sucursaldb);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }

    @Override
    public boolean delete(Integer id) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Sucursal sucursal = (Sucursal) sesion.load(Sucursal.class, id);
            sesion.delete(sucursal);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }
}