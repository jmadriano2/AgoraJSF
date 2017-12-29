package apc.entjava.agora;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class DetailBean {
    private Projects detail;

    public Projects getDetail() {
        return detail;
    }

    public void setDetail(Projects detail) {
        this.detail = detail;
    }
}
