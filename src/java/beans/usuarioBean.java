package beans;

import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Usuario;

@Named(value = "usuarioBean")
@RequestScoped
public class usuarioBean {

    private List<Usuario> usuarios;  
    private Usuario selectedUsuario;
    
    public usuarioBean() {
        this.usuarios = new ArrayList<Usuario>();
        this.selectedUsuario = new Usuario();
    }
    
    public List<Usuario> getUsuarios() {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        this.usuarios = usuarioDao.findAll();
        return usuarios;
    }
    
    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }
    
    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }
    
    public void btnCreateUsuario(ActionEvent actionEvent)
    {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg;
        
        if(usuarioDao.create(this.selectedUsuario)){
            msg = "Registro creado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en creación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnUpdateUsuario(ActionEvent actionEvent)
    {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg;
        
        if(usuarioDao.update(this.selectedUsuario)){
            msg = "Registro modificado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en modificación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnDeleteUsuario(ActionEvent actionEvent)
    {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg;
        
        if(usuarioDao.delete(this.selectedUsuario.getId())){
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