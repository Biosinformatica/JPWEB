package beans;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;


/**
 *
 * @Named(value = "uploadBean")
 * @author Martincho
 */
@ManagedBean
@SessionScoped
public class uploadBean implements Serializable {

    private Part file;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    
    public String upload() throws IOException{
        file.write("C:\\data\\"+getFileName(file));
        return "success";
    }

    private String getFileName(Part file) {
        for (String cd : file.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
               String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
               return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
