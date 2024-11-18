package me.prytkovd.numberplate.infrastructure.output.retry;

import me.prytkovd.numberplate.application.output.RetryableOperationExecutor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class AnnotationBasedRetryableOperationExecutor implements RetryableOperationExecutor {
    @Override
    @Retryable
    public <T> T executeWithRetry(RetryableOperation<T> operation) {
        return operation.execute();
    }
}
