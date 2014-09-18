package beans;

import dao.SucursalDao;
import dao.SucursalDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Sucursal;

@Named(value = "sucursalBean")
@RequestScoped
public class sucursalBean {
    private List<SelectItem> selectOneItemsSucursal;
    private List<Sucursal> sucursales;
    private Sucursal selectedSucursal;

    
    
    public sucursalBean() {
        this.sucursales = new ArrayList<Sucursal>();
        this.selectedSucursal = new Sucursal();
    }
    
    public List<Sucursal> getSucursales(){
        SucursalDao sucursalDao = new SucursalDaoImpl();
        this.sucursales = sucursalDao.findAll();
        return sucursales;
    }

    public Sucursal getSelectedSucursal() {
        return selectedSucursal;
    }

    public void setSelectedSucursal(Sucursal selectedSucursal) {
        this.selectedSucursal = selectedSucursal;
    }
    
    public List<SelectItem> getSelectOneItemsSucursal(){
        this.selectOneItemsSucursal = new ArrayList<SelectItem>();
        SucursalDao sucursalDao = new SucursalDaoImpl();
        List<Sucursal> sucursales = sucursalDao.selectItems();
        for (Sucursal sucursal : sucursales) {
            SelectItem selectItem = new SelectItem(sucursal.getId(), sucursal.getNombre());
            this.selectOneItemsSucursal.add(selectItem);
        }
        return selectOneItemsSucursal;
    }
    
    public void btnCreateSucursal(ActionEvent actionEvent){
        SucursalDao sucursalDao = new SucursalDaoImpl();
        String msg;
        if (sucursalDao.create(this.selectedSucursal)) {
            msg = "Registro creado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en creación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnUpdateSucursal(ActionEvent actionEvent){
        SucursalDao sucursalDao = new SucursalDaoImpl();
        String msg;
        if (sucursalDao.update(this.selectedSucursal)) {
            msg = "Registro modificado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
           msg = "Error en modificación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message); 
        }
    }
    
    public void btnDeleteSucursal(ActionEvent actionEvent){
        SucursalDao sucursalDao = new SucursalDaoImpl();
        String msg;
        if (sucursalDao.delete(this.selectedSucursal.getId())) {
            msg = "Registro modificado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
           msg = "Error en modificación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message); 
        }
    }
}
