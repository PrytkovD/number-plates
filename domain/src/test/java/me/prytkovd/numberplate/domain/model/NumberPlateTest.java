package me.prytkovd.numberplate.domain.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberPlateTest {
    @Test
    public void Constructor_Should_Throw_NullPointerException_When_Code_Is_Null() {
        //WHEN
        String nullCode = null;

        //THEN
        assertThrows(NullPointerException.class, () -> new NumberPlate(nullCode));
    }

    @Test
    public void Constructor_Should_Throw_IllegalArgumentException_When_Code_Is_Invalid() {
        //GIVEN
        List<String> invalidCodes = List.of(
            "Z123ВЕ116",
            "А123ВЕ117",
            "А12ВЕ116",
            "А123ВЕ1167",
            "А123В116",
            " А123ВЕ116",
            "А123ВЕ116 ",
            ""
        );

        //WHEN
        for (String invalidCode : invalidCodes) {
            //THEN
            assertThrows(IllegalArgumentException.class, () -> new NumberPlate(invalidCode));
        }
    }

    @Test
    public void Should_Construct_When_Code_Is_Valid() {
        //GIVEN
        List<String> validCodes = List.of(
            "А123ВЕ116",
            "А000АА116",
            "Х999ХХ116"
        );

        //WHEN
        for (String validCode : validCodes) {
            //THEN
            assertDoesNotThrow(() -> new NumberPlate(validCode));
        }
    }
}