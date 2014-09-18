package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import util.MyUtil;

@ManagedBean
@ApplicationScoped
public class appBean {

    public appBean() {
    }
    
    public String getBaseUrl()
    {
        return MyUtil.baseurl();
    }
    
    public String getBasePath()
    {
       return MyUtil.basepath();
    }
}