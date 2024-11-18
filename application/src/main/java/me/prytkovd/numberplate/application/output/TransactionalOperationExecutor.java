package me.prytkovd.numberplate.application.output;

public interface TransactionalOperationExecutor {
    /**
     * Executes the given {@link TransactionalOperation} within a transaction.
     *
     * @param operation the {@code TransactionalOperation} to be executed
     * @param <T>       the type of the result returned by the operation
     * @return the result of the executed operation
     */
    <T> T executeInTransaction(TransactionalOperation<T> operation);

    /**
     * Represents an operation to be executed within a transaction.
     *
     * @param <T> the type of the result produced by this operation
     */
    @FunctionalInterface
    interface TransactionalOperation<T> {
        T execute();
    }
}
