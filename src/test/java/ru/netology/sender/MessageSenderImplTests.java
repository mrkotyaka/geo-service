package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderImplTests {

    @ParameterizedTest
    @MethodSource
    public void testSend(String name, String ip, String expected) {

        GeoServiceMock geoServiceMock = new GeoServiceMock();
        LocalizationServiceMock localizationServiceMock = new LocalizationServiceMock();
        MessageSenderImpl messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(name, ip);
        String result = messageSender.send(headers);

        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> testSend() {
        return Stream.of(
                Arguments.of("x-real-ip", "1", "Добро пожаловать"),
                Arguments.of("x-real-ip", "2", "Ola"),
                Arguments.of("x-real-ip", "", "Welcome")
        );
    }

}
