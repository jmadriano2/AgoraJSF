package apc.entjava.agora;

import apc.entjava.agora.dataobjects.CityDao;
import apc.entjava.agora.objects.Cities;
import apc.entjava.agora.services.CityService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean
public class CityBean {
    private List<Cities> citiesList;
    private Cities chosenCity;

    private CityService cityService = new CityDao();

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
}
