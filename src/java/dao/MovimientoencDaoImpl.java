package dao;

import java.util.List;
import model.Movimientoenc;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

public class MovimientoencDaoImpl implements MovimientoencDao{

    @Override
    public Movimientoenc findbyMovimientoenc(Movimientoenc movimientoenc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movimientoenc> findAll() {
        List<Movimientoenc> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "";
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
    public boolean create(Movimientoenc movimientoenc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Movimientoenc movimientoenc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
	public int obtenerUltimoEncabezado() {
    	Integer resultado;
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	Query query = session.createQuery("SELECT MAX(id)+1 AS id FROM Movimientoenc");
    	if (query.uniqueResult() != null){
    		resultado = new Integer(query.uniqueResult().toString());
        }
    	else{
    		resultado = 1; //Valor por defecto
    	}
    	session.flush();
    	session.close();
    	return resultado;
    }
}
