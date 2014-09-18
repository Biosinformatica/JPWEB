package com.corejsf;

import java.io.Serializable;
import javax.inject.Named; 
import javax.enterprise.context.SessionScoped; 

@Named("user")
@SessionScoped
public class UserBean implements Serializable {
   private String id;

    public String getId() {
        return id;
    }

    public void setId(String newValue) {
        id = newValue;
    }

   private String fileText;

    public String getFileText() {
        return fileText;
    }

    public void setFileText(String fileText) {
        this.fileText = fileText;
    }

}