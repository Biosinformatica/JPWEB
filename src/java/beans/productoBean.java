package beans;

import dao.MovimientoencDao;
import dao.MovimientoencDaoImpl;
import dao.ProductoDao;
import dao.ProductoDaoImpl;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import model.Producto;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


@Named(value = "productoBean")
@RequestScoped
public class productoBean {

    private List<Producto> productos;
    private Producto selectedProducto;
    private MovimientoencDao movimientoEncDao = new MovimientoencDaoImpl();
    
    private UploadedFile avatar;
    
    public productoBean() {
        this.productos = new ArrayList<Producto>();
        this.selectedProducto = new Producto();
    }

    public List<Producto> getProductos() {
        ProductoDao productoDao = new ProductoDaoImpl();
        this.productos = productoDao.findAll();
        return productos;
    }

    public Producto getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(Producto selectedProducto) {
        this.selectedProducto = selectedProducto;
    }
    
    

    public void btnCreateProducto(ActionEvent actionEvent)
    {
        ProductoDao productoDao = new ProductoDaoImpl();
        String msg;
        
        if(productoDao.create(this.selectedProducto)){
            msg = "Producto creado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en creación de registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnUpdateProducto(ActionEvent actionEvent)
    {
        ProductoDao productoDao = new ProductoDaoImpl();
        String msg;
        
        if(productoDao.update(this.selectedProducto)){
            msg = "Producto modificado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
    } else{
          msg = "Error en eliminación de producto";
          FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
          FacesContext.getCurrentInstance().addMessage(null, message);  
        }
    }
    public void btnDeleteProducto(ActionEvent actionEvent)
    {
        ProductoDao productoDao = new ProductoDaoImpl();
        String msg;
        
        if(productoDao.delete(this.selectedProducto.getId())){
            msg = "Producto eliminado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error en eliminación de producto";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void actualizarAvatar(FileUploadEvent event) throws IOException
    {
       InputStream inputStream=null;
       OutputStream outputStream=null;
        try 
        {
            ServletContext servletContext=(ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
            String carpetaAvatar=(String)servletContext.getRealPath("/avatar");
            
            inputStream = event.getFile().getInputstream();
            
            outputStream=new FileOutputStream(new File(carpetaAvatar+"/"+this.selectedProducto.getId()+".png"));
            
            int read=0;
            byte[] bytes=new byte[1024];
            
            while ((read=inputStream.read(bytes))!=-1) 
            {                
               outputStream.write(bytes, 0, read);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto;", "Imagen actualizada correctamente"));
        } 
        catch (Exception ex) 
        {
            System.out.println("Error:"+ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Contacte con el admin"+ex.getMessage()));
        }
        finally
        {
        if(inputStream!=null)
        {
            inputStream.close();
        }
        if(outputStream!=null)
        {
            outputStream.close();
        }
        }
    }
    
    public void agregarProducto()
    {
                ProductoDao productoDao = new ProductoDaoImpl();
		String msg;
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,Object> sessionParams = fc.getExternalContext().getSessionMap();
		
		productos = (ArrayList<Producto>) sessionParams.get("productos");
		if(productos == null){
			productos = new ArrayList<Producto>();
		}
        try {
        	selectedProducto.setId(movimientoEncDao.obtenerUltimoEncabezado());
        	productos.add(selectedProducto);
        	sessionParams.put("productos", productos);
            msg = "Producto agregado correctamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
            msg = "Error al agregar producto";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
		}
    }
    
    public UploadedFile getAvatar() {
        return avatar;
    }

    public void setAvatar(UploadedFile avatar) {
        this.avatar = avatar;
    }
    
    
}