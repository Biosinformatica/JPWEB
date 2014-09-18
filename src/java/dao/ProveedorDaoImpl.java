package dao;

import java.util.List;
import model.Proveedor;
import org.hibernate.Session;
import util.HibernateUtil;

public class ProveedorDaoImpl implements ProveedorDao{

    @Override
    public Proveedor findByProveedor(Proveedor proveedor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Proveedor> selectItems() {
        List<Proveedor> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Proveedor";
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
    public List<Proveedor> findAll() {
        List<Proveedor> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Proveedor";
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
    public boolean create(Proveedor proveedor) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(proveedor);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Proveedor proveedor) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Proveedor proveedordb = (Proveedor) sesion.load(Proveedor.class, proveedor.getId());
            proveedordb.setNombre(proveedor.getNombre());
            proveedordb.setContacto(proveedor.getContacto());
            proveedordb.setDireccion(proveedor.getDireccion());
            proveedordb.setCiudad(proveedor.getCiudad());
            proveedordb.setEmail(proveedor.getEmail());
            proveedordb.setTelefono1(proveedor.getTelefono1());
            proveedordb.setTelefono2(proveedor.getTelefono2());
            proveedordb.setDetalles(proveedor.getDetalles());
            proveedordb.setRuc(proveedor.getRuc());
            proveedordb.setBanco(proveedor.getBanco());
            sesion.update(proveedordb);
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
            Proveedor proveedor = (Proveedor) sesion.load(Proveedor.class, id);
            sesion.delete(proveedor);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        
        return flag;
    }
}
