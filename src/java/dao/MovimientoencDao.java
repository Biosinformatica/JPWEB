package dao;

import java.util.List;
import model.Movimientoenc;


public interface MovimientoencDao {
    public Movimientoenc findbyMovimientoenc(Movimientoenc movimientoenc);
    public List<Movimientoenc> findAll();
    public boolean create(Movimientoenc movimientoenc);
    public boolean update(Movimientoenc movimientoenc);
    public boolean delete(Integer id);
    public int obtenerUltimoEncabezado();
}