package apc.entjava.agora;

import apc.entjava.agora.dataobjects.SidebarDao;
import apc.entjava.agora.services.SidebarService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

@ManagedBean
public class SidebarBean {
    private List<String> myCities;
    private List<String> nearbyCities;
    private List<String> myNeighbours;


    private SidebarService sidebarService = new SidebarDao();

    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;

    @PostConstruct
    public void init(){
        String username = authBean.getLoggedUser().getUser_name();
        myCities = sidebarService.selectMyCities(username);
        String home = myCities.get(0);
        nearbyCities = sidebarService.selectNearbyCities(home, username);
        myNeighbours = sidebarService.selectMyNeighbours(home, username);
    }

    public List<String> getMyCities() {
        return myCities;
    }

    public void setMyCities(List<String> myCities) {
        this.myCities = myCities;
    }

    public List<String> getNearbyCities() {
        return nearbyCities;
    }

    public void setNearbyCities(List<String> nearbyCities) {
        this.nearbyCities = nearbyCities;
    }

    public List<String> getMyNeighbours() {
        return myNeighbours;
    }

    public void setMyNeighbours(List<String> myNeighbours) {
        this.myNeighbours = myNeighbours;
    }

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }
}
