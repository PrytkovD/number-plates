package me.prytkovd.numberplate.infrastructure.output.jdbc;

import me.prytkovd.numberplate.domain.model.NumberPlate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvailableNumberMapperTest {
    private final AvailableNumberMapper mapper = new AvailableNumberMapper();

    @Test
    public void Should_Correctly_Map_To_Domain() {
        AvailableNumber firstEntity = new AvailableNumber(0);
        AvailableNumber someEntity = new AvailableNumber(1234);
        AvailableNumber lastEntity = new AvailableNumber(1727999);

        NumberPlate firstDomain = mapper.toNumberPlate(firstEntity);
        NumberPlate someDomain = mapper.toNumberPlate(someEntity);
        NumberPlate lastDomain = mapper.toNumberPlate(lastEntity);

        assertEquals("А000АА116", firstDomain.code());
        assertEquals("А234АВ116", someDomain.code());
        assertEquals("Х999ХХ116", lastDomain.code());
    }

    @Test
    public void Should_Correctly_Map_To_Entity() {
        NumberPlate firstDomain = new NumberPlate("А000АА116");
        NumberPlate someDomain = new NumberPlate("А234АВ116");
        NumberPlate lastDomain = new NumberPlate("Х999ХХ116");

        AvailableNumber firstEntity = mapper.toAvailableNumber(firstDomain);
        AvailableNumber someEntity = mapper.toAvailableNumber(someDomain);
        AvailableNumber lastEntity = mapper.toAvailableNumber(lastDomain);

        assertEquals(0, firstEntity.getNumber());
        assertEquals(1234, someEntity.getNumber());
        assertEquals(1727999, lastEntity.getNumber());
    }

    @Test
    public void Should_Correctly_Map_To_Entity_And_Back() {
        NumberPlate someDomain = new NumberPlate("А234АВ116");

        assertEquals(someDomain, mapper.toNumberPlate(mapper.toAvailableNumber(someDomain)));
    }
}