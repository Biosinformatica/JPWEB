package dao;

import java.util.List;
import model.Lineaproducto;
import org.hibernate.Session;
import util.HibernateUtil;

public class LineaproductoDaoImpl implements LineaproductoDao{

    @Override
    public Lineaproducto findByLineaproducto(Lineaproducto lineaproducto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Lineaproducto> selectItems() {
        List<Lineaproducto> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Lineaproducto";
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
    public List<Lineaproducto> findAll() {
        List<Lineaproducto> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Lineaproducto";
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
    public boolean create(Lineaproducto lineaproducto) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(lineaproducto);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Lineaproducto lineaproducto) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Lineaproducto lineaproductodb = (Lineaproducto) sesion.load(Lineaproducto.class, lineaproducto.getId());
            lineaproductodb.setNombre(lineaproducto.getNombre());
            lineaproductodb.setDescripcion(lineaproducto.getDescripcion());
            sesion.update(lineaproductodb);
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
            Lineaproducto lineaproducto = (Lineaproducto) sesion.load(Lineaproducto.class, id);
            sesion.delete(lineaproducto);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }
    
}
