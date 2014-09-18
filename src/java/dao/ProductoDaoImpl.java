package dao;

import java.util.List;
import model.Producto;
import org.hibernate.Session;
import util.HibernateUtil;

public class ProductoDaoImpl implements ProductoDao{

    @Override
    public Producto findbyProducto(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Producto p inner join fetch p.lineaproducto left join fetch p.categoria left join fetch p.proveedor left join fetch p.sucursal";
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
    public boolean create(Producto producto) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(producto);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }

    @Override
    public boolean update(Producto producto) {
        boolean flag;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            Producto productodb = (Producto) sesion.load(Producto.class, producto.getId());
            productodb.setNombre(producto.getNombre());
            productodb.setPreciocompra(producto.getPreciocompra());
            productodb.setPrecioventa(producto.getPrecioventa());
            productodb.setDescripcion(producto.getDescripcion());
            productodb.setNumeroserie(producto.getNumeroserie());
            productodb.setCodigobarra(producto.getCodigobarra());
            productodb.setEstado(producto.getEstado());
            productodb.setUnidadmedida(producto.getUnidadmedida());
            productodb.setCantidad(producto.getCantidad());
            productodb.setMedida(producto.getMedida());
            productodb.setImagen(producto.getImagen());
            productodb.setCategoria(producto.getCategoria());
            productodb.setLineaproducto(producto.getLineaproducto());
            productodb.setProveedor(producto.getProveedor());
            productodb.setSucursal(producto.getSucursal());
            sesion.update(productodb);
            sesion.beginTransaction().commit();
            flag = true;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
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
            Producto producto = (Producto) sesion.load(Producto.class, id);
            sesion.delete(producto);
            sesion.beginTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            sesion.beginTransaction().rollback();
        }
        return flag;
    }
    
}
