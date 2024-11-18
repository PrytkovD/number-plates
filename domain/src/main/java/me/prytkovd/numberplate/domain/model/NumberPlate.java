package me.prytkovd.numberplate.domain.model;

import java.util.regex.Pattern;

public record NumberPlate(
    String code
) {
    private static final Pattern PATTERN = Pattern.compile("^[АВЕКМНОРСТУХ][0-9]{3}[АВЕКМНОРСТУХ]{2} 116 RUS$");

    public NumberPlate {
        if (code == null) {
            throw new NullPointerException("code must not be null");
        }

        if (!PATTERN.matcher(code).matches()) {
            throw new IllegalArgumentException("code must match pattern");
        }
    }
}