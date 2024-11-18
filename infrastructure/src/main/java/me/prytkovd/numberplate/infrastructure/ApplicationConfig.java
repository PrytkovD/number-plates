package me.prytkovd.numberplate.infrastructure;

import me.prytkovd.numberplate.application.output.NumberPlates;
import me.prytkovd.numberplate.application.output.RetryableOperationExecutor;
import me.prytkovd.numberplate.application.output.TransactionalOperationExecutor;
import me.prytkovd.numberplate.application.usecases.AllocatingNumberPlatesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
public class ApplicationConfig {
    @Bean
    public AllocatingNumberPlatesUseCase numberPlatesService(
        NumberPlates numberPlates,
        TransactionalOperationExecutor transactionalOperationExecutor,
        RetryableOperationExecutor retryableOperationExecutor
    ) {
        return new AllocatingNumberPlatesUseCase(
            numberPlates,
            transactionalOperationExecutor,
            retryableOperationExecutor
        );
    }
}
