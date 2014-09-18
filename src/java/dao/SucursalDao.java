/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Sucursal;

/**
 *
 * @author Martincho
 */
public interface SucursalDao {
    public Sucursal findBySucursal();
    public List<Sucursal> selectItems();
    public List<Sucursal> findAll();
    public boolean create(Sucursal sucursal);
    public boolean update(Sucursal sucursal);
    public boolean delete(Integer id);
}