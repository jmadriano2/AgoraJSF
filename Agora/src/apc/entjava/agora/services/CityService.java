package apc.entjava.agora.services;

import apc.entjava.agora.objects.Cities;

import java.util.List;

public interface CityService {
    List<Cities> selectCities();

    List<Cities> selectChooseCities(String username);

    boolean userHasCity(String username);

    void insertHomeCity(String username, String city_name);

    void insertCity(String username, String city_name);
}
