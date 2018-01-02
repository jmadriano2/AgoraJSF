package apc.entjava.agora.services;

import apc.entjava.agora.objects.Cities;

import java.util.List;

public interface CityService {
    List<Cities> getCities();
    boolean userHasCity(String username);
}
