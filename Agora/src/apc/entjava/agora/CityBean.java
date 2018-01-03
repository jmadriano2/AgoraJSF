package apc.entjava.agora;

import apc.entjava.agora.dataobjects.CityDao;
import apc.entjava.agora.objects.Cities;
import apc.entjava.agora.services.CityService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class CityBean {
    private List<Cities> citiesList;
    private List<Cities> notChosen;
    private Cities chosenCity;
    private Cities addedCity;

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
        String username = authBean.getLoggedUser().getUser_name();
        System.out.println("CityBean PostConstruct Username: " + username);
        citiesList = cityService.selectCities();
    }

    public List<Cities> getCitiesList() {
        return citiesList;
    }

    public void setCitiesList(List<Cities> citiesList) {
        this.citiesList = citiesList;
    }

    public List<Cities> getNotChosen() {
        return notChosen;
    }

    public Cities getChosenCity() {
        return chosenCity;
    }

    public Cities getAddedCity() {
        return addedCity;
    }

    //Methods
    public String chooseCity(int city_id){
        chosenCity = citiesList.get(city_id);
        return "CityChosen";
    }

    public String addCity(int city_id){
        addedCity = notChosen.get(city_id);
        return "AddedCity";
    }

    public String skip(){
        insertHomeCity();
        return "Skip";
    }

    public String moreCities(){
        insertHomeCity();
        return "MoreCities";
    }

    public String skipAdd(){
        insertCity();
        return "SkipAdd";
    }

    public String addMoreCities(){
        insertCity();
        return "AddMoreCities";
    }

    private void insertHomeCity(){
        String username = authBean.getLoggedUser().getUser_name();
        cityService.insertHomeCity(username, chosenCity.getCity_name());
        notChosen = cityService.selectChooseCities(username);
    }

    private void insertCity(){
        String username = authBean.getLoggedUser().getUser_name();
        cityService.insertCity(username, addedCity.getCity_name());
        notChosen = cityService.selectChooseCities(username);
    }
}
