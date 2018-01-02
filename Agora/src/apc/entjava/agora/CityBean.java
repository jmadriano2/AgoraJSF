package apc.entjava.agora;

import apc.entjava.agora.dataobjects.CityDao;
import apc.entjava.agora.objects.Cities;
import apc.entjava.agora.services.CityService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class CityBean {
    private List<Cities> citiesList;
    private List<Cities> notChosen;
    private Cities chosenCity;

    private CityService cityService = new CityDao();

    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }

    @PostConstruct
    public void init() {
        citiesList = cityService.getCities();
    }

    public List<Cities> getCitiesList() {
        return citiesList;
    }

    public void setCitiesList(List<Cities> citiesList) {
        this.citiesList = citiesList;
    }

    public Cities getChosenCity() {
        return chosenCity;
    }

    public String chooseCity(int city_id){
        chosenCity = citiesList.get(city_id);
        return "CityChosen";
    }

    public String skip(){
        insertHomeCity();
        return "Skip";
    }

    public String moreCities(){
        insertHomeCity();
        return "MoreCities";
    }

    private void insertHomeCity(){
        System.out.println(authBean.getLoggedUser().getUser_name());
        String username = authBean.getLoggedUser().getUser_name();
        cityService.insertHomeCity(username, chosenCity.getCity_name());
    }
}
