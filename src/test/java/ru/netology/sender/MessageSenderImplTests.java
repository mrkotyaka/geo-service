package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTests {

    @Test
    public void testRuSend() {

        String ipAddress = "172.";
        String expected = "Добро пожаловать";

        GeoService geoServiceMock = Mockito.mock(GeoService.class);
        Mockito.when(geoServiceMock.byIp(ipAddress)).thenReturn(new Location("Mock", Country.RUSSIA,"Mock",0));

        LocalizationService localizationServiceMock = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationServiceMock.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);
        String result = messageSender.send(headers);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testUSAOrElseSend() {

        String ipAddress = "127.0.0.1";
        String expected = "Welcome";

        GeoService geoServiceMock = Mockito.mock(GeoService.class);
        Mockito.when(geoServiceMock.byIp(ipAddress)).thenReturn(new Location("Mock", Country.USA,"Mock", 0));

        LocalizationService localizationServiceMock = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationServiceMock.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);
        String result = messageSender.send(headers);

        Assertions.assertEquals(expected, result);
    }
}
