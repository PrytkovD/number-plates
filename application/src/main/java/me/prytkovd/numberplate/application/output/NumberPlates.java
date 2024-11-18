package me.prytkovd.numberplate.application.output;

import me.prytkovd.numberplate.domain.model.NumberPlate;

import java.util.Optional;

public interface NumberPlates {
    /**
     * Allocates the next available number plate.
     *
     * @return an {@link Optional} containing a new {@link NumberPlate} if one could be allocated,
     * otherwise an empty {@link Optional}.
     */
    Optional<NumberPlate> allocateNextAvailable();

    /**
     * Allocates a random available number plate.
     *
     * @return an {@link Optional} containing a new {@link NumberPlate} if one could be allocated,
     * otherwise an empty {@link Optional}.
     */
    Optional<NumberPlate> allocateRandomAvailable();
}