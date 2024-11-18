package me.prytkovd.numberplate.application.usecases;

import lombok.AllArgsConstructor;
import me.prytkovd.numberplate.application.input.AllocatingNumberPlates;
import me.prytkovd.numberplate.application.output.NumberPlates;
import me.prytkovd.numberplate.application.output.RetryableOperationExecutor;
import me.prytkovd.numberplate.application.output.TransactionalOperationExecutor;
import me.prytkovd.numberplate.domain.model.NumberPlate;

import java.util.Optional;

@AllArgsConstructor
public class AllocatingNumberPlatesUseCase implements AllocatingNumberPlates {
    private final NumberPlates numberPlates;
    private final TransactionalOperationExecutor transactionalOperationExecutor;
    private final RetryableOperationExecutor retryableOperationExecutor;

    @Override
    public Optional<NumberPlate> allocateNextAvailable() {
        return retryableOperationExecutor.executeWithRetry(() ->
            transactionalOperationExecutor.executeInTransaction(numberPlates::allocateNextAvailable)
        );
    }

    @Override
    public Optional<NumberPlate> allocateRandomAvailable() {
        return retryableOperationExecutor.executeWithRetry(() ->
            transactionalOperationExecutor.executeInTransaction(numberPlates::allocateRandomAvailable)
        );
    }
}
