package com.onclass.tecnologia.domain.usecase;

import com.onclass.tecnologia.domain.model.CapacidadTecnologia;
import com.onclass.tecnologia.domain.spi.CapacidadTecnologiaPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CapacidadTecnologiaUseCaseTest {

    private CapacidadTecnologiaPersistencePort persistencePort;
    private CapacidadTecnologiaUseCase useCase;

    @BeforeEach
    void setUp() {
        persistencePort = Mockito.mock(CapacidadTecnologiaPersistencePort.class);
        useCase = new CapacidadTecnologiaUseCase(persistencePort);
    }

    @Test
    void registrarCapacidadTecnologias_shouldSaveAllRelaciones() {
        CapacidadTecnologia relacion1 = new CapacidadTecnologia(null, 1L, 1L);
        CapacidadTecnologia relacion2 = new CapacidadTecnologia(null, 2L, 1L);

        // Mock del save
        when(persistencePort.save(any()))
                .thenAnswer(invocation -> {
                    CapacidadTecnologia arg = invocation.getArgument(0);
                    return Mono.just(new CapacidadTecnologia(1L, arg.capacidadId(), arg.tecnologiaId()));
                });

        StepVerifier.create(useCase.registrarCapacidadTecnologias(List.of(relacion1, relacion2), "msg-1"))
                .expectNextCount(2)
                .verifyComplete();

        verify(persistencePort, times(2)).save(any());
    }

    @Test
    void registrarCapacidadTecnologias_shouldHandleEmptyList() {
        StepVerifier.create(useCase.registrarCapacidadTecnologias(List.of(), "msg-2"))
                .verifyComplete();

        verify(persistencePort, never()).save(any());
    }
}
