package dao;

import java.util.List;
import model.Rol;
import org.hibernate.Session;
import util.HibernateUtil;

public class RolDaoImpl implements RolDao{

    @Override
    public List<Rol> selectItems() {
        List<Rol> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Rol";
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
    public Rol findByRol(Rol rol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rol> findAll() {
        List<Rol> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Rol";
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
    public boolean create(Rol rol) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(rol);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Rol rol) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Rol roldb = (Rol) sesion.load(Rol.class, rol.getId());
            roldb.setNombre(rol.getNombre());
            roldb.setDescripcion(rol.getDescripcion());
            roldb.setPermiso(rol.getPermiso());
            sesion.update(roldb);
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
            Rol rol = (Rol) sesion.load(Rol.class, id);
            sesion.delete(rol);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }
}