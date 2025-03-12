package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

public class GeoServiceImplTests {

    @ParameterizedTest
    @MethodSource
    public void ParameterizedTestByIp(String ip, String city, Country country, String street, int builing) {
        GeoServiceImpl geoServiceImpl = new GeoServiceImpl();
        Location chekedLocation = ip.isEmpty() ? null : new Location(city, country, street, builing);

        Location resultLocation = geoServiceImpl.byIp(ip);

        Assertions.assertEquals(chekedLocation, resultLocation);
    }

    public static Stream<Arguments> ParameterizedTestByIp() {
        return Stream.of(
                Arguments.of("127.0.0.1", null, null, null, 0),
                Arguments.of("172.0.32.11", "Moscow", Country.RUSSIA, "Lenina", 15),
                Arguments.of("172.", "Moscow", Country.RUSSIA, null, 0),
                Arguments.of("96.44.183.149", "New York", Country.USA, "10th Avenue", 32),
                Arguments.of("96.", "New York", Country.USA, null, 0),
                Arguments.of("", null, null, null, 0)
        );
    }

    @Test
    public void testByCoordinates() {
        GeoServiceImpl geoServiceImpl = new GeoServiceImpl();
        double latitude = 0, longitude = 0;

        try {
            geoServiceImpl.byCoordinates(latitude, longitude);
        } catch (RuntimeException e) {
            Assertions.assertEquals("Not implemented", e.getMessage());
            System.out.println("Everything is fine, method is not implemented");
        }
    }
}
