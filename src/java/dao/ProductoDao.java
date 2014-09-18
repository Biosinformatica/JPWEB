package dao;

import java.util.List;
import model.Producto;

public interface ProductoDao {
    public Producto findbyProducto(Producto producto);
    public List<Producto> findAll();
    public boolean create(Producto producto);
    public boolean update(Producto producto);
    public boolean delete(Integer id);
}
