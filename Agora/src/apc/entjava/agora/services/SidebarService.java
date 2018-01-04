package apc.entjava.agora.services;

import java.util.List;

public interface SidebarService {
    List<String> selectMyCities(String username);

    List<String> selectNearbyCities(String city_name, String username);

    List<String> selectMyNeighbours(String city_name, String username);
}
