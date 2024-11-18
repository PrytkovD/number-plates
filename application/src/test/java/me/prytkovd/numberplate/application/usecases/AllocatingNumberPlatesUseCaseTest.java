package me.prytkovd.numberplate.application.usecases;

import me.prytkovd.numberplate.application.output.NumberPlates;
import me.prytkovd.numberplate.application.output.RetryableOperationExecutor;
import me.prytkovd.numberplate.application.output.RetryableOperationExecutor.RetryableOperation;
import me.prytkovd.numberplate.application.output.TransactionalOperationExecutor;
import me.prytkovd.numberplate.application.output.TransactionalOperationExecutor.TransactionalOperation;
import me.prytkovd.numberplate.domain.model.NumberPlate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AllocatingNumberPlatesUseCaseTest {
    @Mock
    private NumberPlates numberPlates;

    @Mock
    private TransactionalOperationExecutor transactionalOperationExecutor;

    @Mock
    private RetryableOperationExecutor retryableOperationExecutor;

    @InjectMocks
    private AllocatingNumberPlatesUseCase allocatingNumberPlatesUseCase;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void allocateNextAvailable_Should_Return_NumberPlate_When_Available() {
        //GIVEN
        NumberPlate expectedPlate = new NumberPlate("А000АА 116 RUS");

        when(numberPlates.allocateNextAvailable())
            .thenReturn(Optional.of(expectedPlate));

        when(transactionalOperationExecutor.executeInTransaction(any()))
            .thenAnswer(invocation -> invocation.getArgument(0, TransactionalOperation.class).execute());

        when(retryableOperationExecutor.executeWithRetry(any()))
            .thenAnswer(invocation -> invocation.getArgument(0, RetryableOperation.class).execute());

        //WHEN
        Optional<NumberPlate> result = allocatingNumberPlatesUseCase.allocateNextAvailable();

        //THEN
        assertTrue(result.isPresent());
        assertEquals(expectedPlate, result.get());

        verify(retryableOperationExecutor).executeWithRetry(any());
        verify(transactionalOperationExecutor).executeInTransaction(any());
        verify(numberPlates).allocateNextAvailable();
    }

    @Test
    public void allocateNextAvailable_Should_Return_Empty_Optional_When_Not_Available() {
        //GIVEN
        when(numberPlates.allocateNextAvailable())
            .thenReturn(Optional.empty());

        when(transactionalOperationExecutor.executeInTransaction(any()))
            .thenAnswer(invocation -> invocation.getArgument(0, TransactionalOperation.class).execute());

        when(retryableOperationExecutor.executeWithRetry(any()))
            .thenAnswer(invocation -> invocation.getArgument(0, RetryableOperation.class).execute());

        //WHEN
        Optional<NumberPlate> result = allocatingNumberPlatesUseCase.allocateNextAvailable();

        //THEN
        assertTrue(result.isEmpty());

        verify(retryableOperationExecutor).executeWithRetry(any());
        verify(transactionalOperationExecutor).executeInTransaction(any());
        verify(numberPlates).allocateNextAvailable();
    }

    @Test
    public void allocateRandomAvailable_Should_Return_NumberPlate_When_Available() {
        //GIVEN
        NumberPlate expectedPlate = new NumberPlate("А000АА 116 RUS");

        when(numberPlates.allocateRandomAvailable())
            .thenReturn(Optional.of(expectedPlate));

        when(transactionalOperationExecutor.executeInTransaction(any()))
            .thenAnswer(invocation -> invocation.getArgument(0, TransactionalOperation.class).execute());

        when(retryableOperationExecutor.executeWithRetry(any()))
            .thenAnswer(invocation -> invocation.getArgument(0, RetryableOperation.class).execute());

        //WHEN
        Optional<NumberPlate> result = allocatingNumberPlatesUseCase.allocateRandomAvailable();

        //THEN
        assertTrue(result.isPresent());
        assertEquals(expectedPlate, result.get());

        verify(retryableOperationExecutor).executeWithRetry(any());
        verify(transactionalOperationExecutor).executeInTransaction(any());
        verify(numberPlates).allocateRandomAvailable();
    }

    @Test
    public void allocateRandomAvailable_Should_Return_Empty_Optional_When_Not_Available() {
        //GIVEN
        when(numberPlates.allocateRandomAvailable())
            .thenReturn(Optional.empty());

        when(transactionalOperationExecutor.executeInTransaction(any()))
            .thenAnswer(invocation -> invocation.getArgument(0, TransactionalOperation.class).execute());

        when(retryableOperationExecutor.executeWithRetry(any()))
            .thenAnswer(invocation -> invocation.getArgument(0, RetryableOperation.class).execute());

        //WHEN
        Optional<NumberPlate> result = allocatingNumberPlatesUseCase.allocateRandomAvailable();

        //THEN
        assertTrue(result.isEmpty());

        verify(retryableOperationExecutor).executeWithRetry(any());
        verify(transactionalOperationExecutor).executeInTransaction(any());
        verify(numberPlates).allocateRandomAvailable();
    }
}
