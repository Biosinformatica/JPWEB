package dao;

import java.util.List;
import model.Categoria;

public interface CategoriaDao {
    public Categoria findByCategoria(Categoria categoria);
    public List<Categoria> selectItems();
    public List<Categoria> findAll();
    public boolean create(Categoria categoria);
    public boolean update(Categoria categoria);
    public boolean delete(Integer id);
}