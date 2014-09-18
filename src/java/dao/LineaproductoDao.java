package dao;

import java.util.List;
import model.Lineaproducto;

public interface LineaproductoDao {
    public Lineaproducto findByLineaproducto(Lineaproducto lineaproducto);
    public List<Lineaproducto> selectItems();
    public List<Lineaproducto> findAll();
    public boolean create(Lineaproducto lineaproducto);
    public boolean update(Lineaproducto lineaproducto);
    public boolean delete(Integer id);
}
