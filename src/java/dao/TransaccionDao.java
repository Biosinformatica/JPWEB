package dao;

import java.util.List;
import model.Transaccion;

public interface TransaccionDao {
    public Transaccion findByTransaccion(Transaccion transaccion);
    public List<Transaccion> selectItems();
    public List<Transaccion> findAll();
    public boolean create(Transaccion transaccion);
    public boolean update(Transaccion transaccion);
    public boolean delete(Integer id);
}
