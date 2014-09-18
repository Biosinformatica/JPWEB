package beans;

import dao.MovimientoencDao;
import dao.MovimientoencDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Movimientoenc;

@Named(value = "movimientoencBean")
@RequestScoped
public class movimientoencBean {

    private List<Movimientoenc> movimientoencs;
    private Movimientoenc selectedMovimientoenc;
    
    public movimientoencBean() {
        this.movimientoencs = new ArrayList<Movimientoenc>();
        this.selectedMovimientoenc = new Movimientoenc();
    }

    public List<Movimientoenc> getMovimientoencs() {
        MovimientoencDao movimientoencDao = new MovimientoencDaoImpl();
        this.movimientoencs = movimientoencDao.findAll();
        return movimientoencs;
    }

    public Movimientoenc getSelectedMovimientoenc() {
        return selectedMovimientoenc;
    }

    public void setSelectedMovimientoenc(Movimientoenc selectedMovimientoenc) {
        this.selectedMovimientoenc = selectedMovimientoenc;
    }
    
    public void btnCreateMovimientoenc(ActionEvent actionEvent){
        MovimientoencDao movimientoencDao = new MovimientoencDaoImpl();
        String msg;
        
        if(movimientoencDao.create(this.selectedMovimientoenc)){
            msg = "Registro creado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en creación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } 
    }
    
    public void btnUpdateMovimientoenc(ActionEvent actionEvent){
        MovimientoencDao movimientoencDao = new MovimientoencDaoImpl();
        String msg;
        
        if(movimientoencDao.update(this.selectedMovimientoenc)){
            msg = "Registro creado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en creación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }      
    }

    public void btnDeleteMovimientoenc(ActionEvent actionEvent){
        MovimientoencDao movimientoencDao = new MovimientoencDaoImpl();
        String msg;
        
        if(movimientoencDao.delete(this.selectedMovimientoenc.getId())){
            msg = "Registro creado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en creación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }    
    }
}
