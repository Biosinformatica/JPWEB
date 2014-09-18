package beans;

import dao.ProveedorDao;
import dao.ProveedorDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Proveedor;

@Named(value = "proveedorBean")
@RequestScoped
public class proveedorBean {

    
    private List<SelectItem> selectOneItemsProveedor;
    private List<Proveedor> proveedores;
    private Proveedor selectedProveedor;
    
    public proveedorBean() {
        this.proveedores = new ArrayList<Proveedor>();
        this.selectedProveedor = new Proveedor();
    }

    public List<Proveedor> getProveedores() {
        ProveedorDao proveedorDao = new ProveedorDaoImpl();
        this.proveedores = proveedorDao.findAll();
        return proveedores;
    }

    public Proveedor getSelectedProveedor() {
        return selectedProveedor;
    }

    public void setSelectedProveedor(Proveedor selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }

    public List<SelectItem> getSelectOneItemsProveedor() {
        this.selectOneItemsProveedor = new ArrayList<SelectItem>();
        ProveedorDao proveedorDao = new ProveedorDaoImpl();
        List<Proveedor> proveedores = proveedorDao.selectItems();
        for (Proveedor proveedor : proveedores) {
            SelectItem selectItem = new SelectItem(proveedor.getId(), proveedor.getNombre());
            this.selectOneItemsProveedor.add(selectItem);
        }
        return selectOneItemsProveedor;
    }
    
    public void btnCreateProveedor(ActionEvent actionEvent){
        ProveedorDao proveedorDao = new ProveedorDaoImpl();
        String msg;
        if (proveedorDao.create(this.selectedProveedor)) {
            msg = "Registro creado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en creación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnUpdateProveedor(ActionEvent actionEvent){
        ProveedorDao proveedorDao = new ProveedorDaoImpl();
        String msg;
        if (proveedorDao.update(this.selectedProveedor)) {
            msg = "Registro modificado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en modificación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnDeleteProveedor(ActionEvent actionEvent){
        ProveedorDao proveedorDao = new ProveedorDaoImpl();
        String msg;
        if (proveedorDao.delete(this.selectedProveedor.getId())) {
            msg = "Registro eliminado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en eliminación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
}
