package dao;

import java.util.List;
import model.Proveedor;

public interface ProveedorDao {
    public Proveedor findByProveedor(Proveedor proveedor);
    public List<Proveedor> selectItems();
    public List<Proveedor> findAll();
    public boolean create(Proveedor proveedor);
    public boolean update(Proveedor proveedor);
    public boolean delete(Integer id);
}