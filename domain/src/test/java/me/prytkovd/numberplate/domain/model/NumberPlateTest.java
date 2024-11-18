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
            "Z123ВЕ 116 RUS",
            "А123ВЕ 117 RUS",
            "А12ВЕ 116 RUS",
            "А123ВЕ 1167 RUS",
            "А123В 116 RUS",
            " А123ВЕ 116 RUS",
            "А123ВЕ 116 RUS ",
            "А123ВЕ116",
            "А123ВЕ116 RUS",
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
            "А123ВЕ 116 RUS",
            "А000АА 116 RUS",
            "Х999ХХ 116 RUS"
        );

        //WHEN
        for (String validCode : validCodes) {
            //THEN
            assertDoesNotThrow(() -> new NumberPlate(validCode));
        }
    }
}