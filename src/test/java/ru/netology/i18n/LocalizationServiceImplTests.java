package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;

public class LocalizationServiceImplTests {
    @ParameterizedTest
    @EnumSource(Country.class)
    public void testLocale(Country country) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        String greetings = localizationService.locale(country);

        if (country == Country.RUSSIA) {
            Assertions.assertEquals("Добро пожаловать", greetings);
        } else {
            Assertions.assertEquals("Welcome", greetings);
        }
    }
}
