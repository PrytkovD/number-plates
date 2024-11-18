package me.prytkovd.numberplate.infrastructure.output.jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@AllArgsConstructor
@Table
public class AvailableNumber {
    @Id
    private Integer number;
}
