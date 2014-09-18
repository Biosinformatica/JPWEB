package dao;

import java.util.List;
import model.Rol;

public interface RolDao {
    public Rol findByRol(Rol rol);
    public List<Rol> selectItems();
    public List<Rol> findAll();
    public boolean create(Rol rol);
    public boolean update(Rol rol);
    public boolean delete(Integer id);
}
