package me.prytkovd.numberplate.infrastructure.output.jdbc;

import lombok.NonNull;
import me.prytkovd.numberplate.domain.model.NumberPlate;
import org.springframework.stereotype.Component;

@Component
public class AvailableNumberMapper {
    public static final String letters = "АВЕКМНОРСТУХ";

    public NumberPlate toNumberPlate(@NonNull AvailableNumber availableNumber) {
        int id = availableNumber.getNumber();
        int number = id % 1000;
        id /= 1000;
        Character letter3 = letters.charAt(id % 12);
        id /= 12;
        Character letter2 = letters.charAt(id % 12);
        id /= 12;
        Character letter1 = letters.charAt(id % 12);
        String code = "%c%03d%c%c116".formatted(letter1, number, letter2, letter3);
        return new NumberPlate(code);
    }

    public AvailableNumber toAvailableNumber(@NonNull NumberPlate numberPlate) {
        String code = numberPlate.code();
        int letter1 = letters.indexOf(code.charAt(0)) * 1000 * 12 * 12;
        int letter2 = letters.indexOf(code.charAt(4)) * 1000 * 12;
        int letter3 = letters.indexOf(code.charAt(5)) * 1000;
        int number = Integer.parseInt(code.substring(1, 4));
        int id = letter1 + letter2 + letter3 + number;
        return new AvailableNumber(id);
    }
}
