package beans;

import dao.RolDao;
import dao.RolDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Rol;

@Named(value = "rolBean")
@RequestScoped
public class rolBean {

    private List<SelectItem> selectOneItemsRol;
    private List<Rol> roles;
    private Rol selectedRol;
    
    public rolBean() {
        this.roles = new ArrayList<Rol>();
        this.selectedRol = new Rol();
    }

    public List<Rol> getRoles() {
        RolDao rolDao = new RolDaoImpl();
        this.roles = rolDao.findAll();
        return roles;
    }

    public Rol getSelectedRol() {
        return selectedRol;
    }
    
    public void setSelectedRol(Rol selectedRol) {
        this.selectedRol = selectedRol;
    }

    public List<SelectItem> getSelectOneItemsRol() {
        this.selectOneItemsRol = new ArrayList<SelectItem>();
        RolDao rolDao = new RolDaoImpl();
        List<Rol> roles = rolDao.selectItems();
        for (Rol rol : roles) {
            SelectItem selectItem = new SelectItem(rol.getId(), rol.getNombre());
            this.selectOneItemsRol.add(selectItem);
        }
        return selectOneItemsRol;
    }
    
    public void btnCreateRol(ActionEvent actionEvent){
        RolDao rolDao = new RolDaoImpl();
        String msg;
        if (rolDao.create(this.selectedRol)) {
            msg = "Registro creado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en creación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnUpdateRol(ActionEvent actionEvent){
        RolDao rolDao = new RolDaoImpl();
        String msg;
        if (rolDao.update(this.selectedRol)) {
            msg = "Registro modificado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
           msg = "Error en modificación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message); 
        }
    }
    
    public void btnDeleteRol(ActionEvent actionEvent){
        RolDao rolDao = new RolDaoImpl();
        String msg;
        if (rolDao.delete(this.selectedRol.getId())) {
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
