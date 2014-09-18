package dao;

import java.util.List;
import model.Transaccion;
import org.hibernate.Session;
import util.HibernateUtil;

public class TransaccionDaoImpl implements TransaccionDao{

    @Override
    public Transaccion findByTransaccion(Transaccion transaccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transaccion> selectItems() {
        List<Transaccion> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Transaccion";
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
    public List<Transaccion> findAll() {
        List<Transaccion> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Transaccion";
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
    public boolean create(Transaccion transaccion) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(transaccion);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Transaccion transaccion) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Transaccion transacciondb = (Transaccion) sesion.load(Transaccion.class, transaccion.getId());
            transacciondb.setClave(transaccion.getClave());
	    transacciondb.setNombre(transaccion.getNombre());
            transacciondb.setDescripcion(transaccion.getDescripcion());
            transacciondb.setTipo(transaccion.getTipo());
            sesion.update(transacciondb);
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
            Transaccion transaccion = (Transaccion) sesion.load(Transaccion.class, id);
            sesion.delete(transaccion);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }
    
}
