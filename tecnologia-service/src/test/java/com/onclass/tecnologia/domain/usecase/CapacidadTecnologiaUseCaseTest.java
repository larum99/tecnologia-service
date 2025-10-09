package com.onclass.tecnologia.domain.usecase;

import com.onclass.tecnologia.domain.model.CapacidadTecnologia;
import com.onclass.tecnologia.domain.spi.CapacidadTecnologiaPersistencePort;
import com.onclass.tecnologia.infrastructure.entrypoints.dto.TecnologiaSummaryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
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

        when(persistencePort.saveCapacidadTecnologia(any()))
                .thenAnswer(invocation -> {
                    CapacidadTecnologia arg = invocation.getArgument(0);
                    return Mono.just(new CapacidadTecnologia(1L, arg.capacidadId(), arg.tecnologiaId()));
                });

        StepVerifier.create(useCase.registrarCapacidadTecnologias(List.of(relacion1, relacion2), "msg-1"))
                .expectNextCount(2)
                .verifyComplete();

        verify(persistencePort, times(2)).saveCapacidadTecnologia(any());
    }

    @Test
    void registrarCapacidadTecnologias_shouldHandleEmptyList() {
        StepVerifier.create(useCase.registrarCapacidadTecnologias(List.of(), "msg-2"))
                .verifyComplete();

        verify(persistencePort, never()).saveCapacidadTecnologia(any());
    }

    @Test
    void listarTecnologiasPorCapacidad_shouldReturnTecnologias() {
        Long capacidadId = 1L;
        TecnologiaSummaryDTO t1 = new TecnologiaSummaryDTO(1L, "Java");
        TecnologiaSummaryDTO t2 = new TecnologiaSummaryDTO(2L, "Spring");

        when(persistencePort.findTecnologiasByCapacidadId(capacidadId))
                .thenReturn(Flux.just(t1, t2));

        StepVerifier.create(useCase.listarTecnologiasPorCapacidad(capacidadId))
                .expectNext(t1, t2)
                .verifyComplete();

        verify(persistencePort).findTecnologiasByCapacidadId(capacidadId);
    }

    @Test
    void listarTecnologiasPorCapacidad_shouldReturnEmptyFluxIfNone() {
        Long capacidadId = 2L;

        when(persistencePort.findTecnologiasByCapacidadId(capacidadId))
                .thenReturn(Flux.empty());

        StepVerifier.create(useCase.listarTecnologiasPorCapacidad(capacidadId))
                .verifyComplete();

        verify(persistencePort).findTecnologiasByCapacidadId(capacidadId);
    }
}
