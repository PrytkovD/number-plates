package me.prytkovd.numberplate.application.output;

public interface RetryableOperationExecutor {
    /**
     * Executes the given {@link RetryableOperation} with retry logic.
     *
     * @param operation the {@code RetriableOperation} to be executed
     * @param <T>       the type of the result returned by the operation
     * @return the result of the executed operation
     */
    <T> T executeWithRetry(RetryableOperation<T> operation);

    /**
     * Represents an operation that can be retried upon failure.
     *
     * @param <T> the type of the result produced by this operation
     */
    @FunctionalInterface
    interface RetryableOperation<T> {
        T execute();
    }
}
