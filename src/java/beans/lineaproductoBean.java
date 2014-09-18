package beans;

import dao.LineaproductoDao;
import dao.LineaproductoDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Lineaproducto;

@Named(value = "lineaproductoBean")
@RequestScoped
public class lineaproductoBean {

    private List<SelectItem> selectOneItemsLineaproducto;
    private List<Lineaproducto> lineaproductos;
    private Lineaproducto selectedLineaproducto;
    
    public lineaproductoBean() {
        this.lineaproductos = new ArrayList<Lineaproducto>();
        this.selectedLineaproducto = new Lineaproducto();
    }

    public List<Lineaproducto> getLineaproductos() {
        LineaproductoDao lineaproductoDao = new LineaproductoDaoImpl();
        this.lineaproductos = lineaproductoDao.findAll();
        return lineaproductos;
    }

    public Lineaproducto getSelectedLineaproducto() {
        return selectedLineaproducto;
    }

    public void setSelectedLineaproducto(Lineaproducto selectedLineaproducto) {
        this.selectedLineaproducto = selectedLineaproducto;
    }
    
    public List<SelectItem> getSelectOneItemsLineaproducto() {
        this.selectOneItemsLineaproducto = new ArrayList<SelectItem>();
        LineaproductoDao lineaproductoDao = new LineaproductoDaoImpl();
        List<Lineaproducto> lineaproductos = lineaproductoDao.selectItems();
        for (Lineaproducto lineaproducto : lineaproductos) {
            SelectItem selectItem = new SelectItem(lineaproducto.getId(), lineaproducto.getNombre());
            this.selectOneItemsLineaproducto.add(selectItem);
        }
        return selectOneItemsLineaproducto;
    }
    
    public void btnCreateLineaproducto(ActionEvent actionEvent){
        LineaproductoDao lineaproductoDao = new LineaproductoDaoImpl();
        String msg;
        if (lineaproductoDao.create(this.selectedLineaproducto)) {
            msg = "Registro creado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en creación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnUpdateLineaproducto(ActionEvent actionEvent){
        LineaproductoDao lineaproductoDao = new LineaproductoDaoImpl();
        String msg;
        if (lineaproductoDao.update(this.selectedLineaproducto)) {
            msg = "Registro modificado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en modificación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnDeleteLineaproducto(ActionEvent actionEvent){
        LineaproductoDao lineaproductoDao = new LineaproductoDaoImpl();
        String msg;
        if (lineaproductoDao.delete(this.selectedLineaproducto.getId())) {
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
