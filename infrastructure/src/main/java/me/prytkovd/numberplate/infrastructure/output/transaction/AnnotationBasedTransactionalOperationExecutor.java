package me.prytkovd.numberplate.infrastructure.output.transaction;

import me.prytkovd.numberplate.application.output.TransactionalOperationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AnnotationBasedTransactionalOperationExecutor implements TransactionalOperationExecutor {
    @Override
    @Transactional
    public <T> T executeInTransaction(TransactionalOperation<T> operation) {
        return operation.execute();
    }
}
