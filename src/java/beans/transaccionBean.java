package beans;

import dao.TransaccionDao;
import dao.TransaccionDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Transaccion;

@Named(value = "transaccionBean")
@RequestScoped
public class transaccionBean {

    private List<SelectItem> selectOneItemsTransaccion;
    private List<Transaccion> transacciones;
    private Transaccion selectedTransaccion;
    
    public transaccionBean() {
        this.transacciones = new ArrayList<Transaccion>();
        this.selectedTransaccion = new Transaccion();
    }

    public List<SelectItem> getSelectOneItemsTransaccion() {
        this.selectOneItemsTransaccion = new ArrayList<SelectItem>();
        TransaccionDao transaccionDao = new TransaccionDaoImpl();
        List<Transaccion> transacciones = transaccionDao.selectItems();
        for (Transaccion transaccion : transacciones) {
            SelectItem selectItem = new SelectItem(transaccion.getId(), transaccion.getNombre());
            this.selectOneItemsTransaccion.add(selectItem);
        }
        return selectOneItemsTransaccion;
    }

    public List<Transaccion> getTransacciones() {
        TransaccionDao transaccionDao = new TransaccionDaoImpl();
        this.transacciones = transaccionDao.findAll();
        return transacciones;
    }

    public Transaccion getSelectedTransaccion() {
        return selectedTransaccion;
    }

    public void setSelectedTransaccion(Transaccion selectedTransaccion) {
        this.selectedTransaccion = selectedTransaccion;
    }
    
    public void btnCreateTransaccion(ActionEvent actionEvent){
        TransaccionDao transaccionDao = new TransaccionDaoImpl();
        String msg;
        if (transaccionDao.create(this.selectedTransaccion)) {
            msg = "Registro creado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en creación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnUpdateTransaccion(ActionEvent actionEvent){
        TransaccionDao transaccionDao = new TransaccionDaoImpl();
        String msg;
        if (transaccionDao.update(this.selectedTransaccion)) {
            msg = "Registro modificado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en modificación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnDeleteTransaccion(ActionEvent actionEvent){
        TransaccionDao transaccionDao = new TransaccionDaoImpl();
        String msg;
        if (transaccionDao.delete(this.selectedTransaccion.getId())) {
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
