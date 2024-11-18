package me.prytkovd.numberplate.infrastructure.output.jdbc;

import lombok.AllArgsConstructor;
import me.prytkovd.numberplate.application.output.NumberPlates;
import me.prytkovd.numberplate.domain.model.NumberPlate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class JdbcNumberPlates implements NumberPlates {
    private final AvailableNumberRepository repository;
    private final AvailableNumberMapper mapper;

    @Override
    public Optional<NumberPlate> allocateNextAvailable() {
        return repository
            .deleteFirstReturning()
            .map(mapper::toNumberPlate);
    }

    @Override
    public Optional<NumberPlate> allocateRandomAvailable() {
        return repository
            .deleteRandomReturning()
            .map(mapper::toNumberPlate);
    }
}
