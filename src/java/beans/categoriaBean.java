package beans;

import dao.CategoriaDao;
import dao.CategoriaDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Categoria;

@Named(value = "categoriaBean")
@RequestScoped
public class categoriaBean {

    private List<SelectItem> selectOneItemsCategoria;
    private List<Categoria> categorias;
    private Categoria selectedCategoria;
    
    public categoriaBean() {
        this.categorias = new ArrayList<Categoria>();
        this.selectedCategoria = new Categoria();
    }

    public List<Categoria> getCategorias() {
        CategoriaDao categoriaDao = new CategoriaDaoImpl();
        this.categorias = categoriaDao.findAll();
        return categorias;
    }

    public Categoria getSelectedCategoria() {
        return selectedCategoria;
    }

    public void setSelectedCategoria(Categoria selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }
    
    public List<SelectItem> getSelectOneItemsCategoria() {
        this.selectOneItemsCategoria = new ArrayList<SelectItem>();
        CategoriaDao categoriaDao = new CategoriaDaoImpl();
        List<Categoria> categorias = categoriaDao.selectItems();
        for (Categoria categoria : categorias) {
            SelectItem selectItem = new SelectItem(categoria.getId(), categoria.getNombre());
            this.selectOneItemsCategoria.add(selectItem);
        }
        return selectOneItemsCategoria;
    }
    
    public void btnCreateCategoria(ActionEvent actionEvent){
        CategoriaDao categoriaDao = new CategoriaDaoImpl();
        String msg;
        if (categoriaDao.create(this.selectedCategoria)) {
            msg = "Registro creado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en creación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnUpdateCategoria(ActionEvent actionEvent){
        CategoriaDao categoriaDao = new CategoriaDaoImpl();
        String msg;
        if (categoriaDao.update(this.selectedCategoria)) {
            msg = "Registro modificado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
           msg = "Error en modificación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message); 
        }
    }
    
    public void btnDeleteCategoria(ActionEvent actionEvent){
        CategoriaDao categoriaDao = new CategoriaDaoImpl();
        String msg;
        if (categoriaDao.delete(this.selectedCategoria.getId())) {
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
