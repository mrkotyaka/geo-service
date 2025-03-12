package ru.netology.sender;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;

public class GeoServiceMock implements GeoService {

    @Override
    public Location byIp(String ip) {
        return new Location(
                "Mock",
                ip.equals("1") ? Country.RUSSIA : Country.BRAZIL,
                "Mock", 0);
    }

    @Override
    public Location byCoordinates(double latitude, double longitude) {
        return null;
    }
}
