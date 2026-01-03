package com.onclass.tecnologia.domain.usecase;

import com.onclass.tecnologia.domain.model.CapacidadTecnologia;
import com.onclass.tecnologia.domain.model.Tecnologia;
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
        Tecnologia t1 = new Tecnologia(1L, "Java", "Descripcion Java");
        Tecnologia t2 = new Tecnologia(2L, "Spring", "Descripcion Spring");

        when(persistencePort.findTecnologiasByCapacidadId(capacidadId))
                .thenReturn(Flux.just(t1, t2));

        StepVerifier.create(useCase.listarTecnologiasPorCapacidad(capacidadId))
                .expectNextMatches(dto -> dto.id().equals(1L) && dto.nombre().equals("Java"))
                .expectNextMatches(dto -> dto.id().equals(2L) && dto.nombre().equals("Spring"))
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

    @Test
    void deleteTecnologiasByCapacidades_shouldDeleteRelaciones() {
        List<Long> capacidadIds = List.of(1L, 2L, 3L);

        when(persistencePort.deleteByCapacidadIds(capacidadIds))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.deleteTecnologiasByCapacidades(capacidadIds))
                .verifyComplete();

        verify(persistencePort).deleteByCapacidadIds(capacidadIds);
    }

    @Test
    void findTecnologiaIdsByCapacidades_shouldReturnTecnologiaIds() {
        List<Long> capacidadIds = List.of(1L, 2L);
        
        when(persistencePort.findTecnologiaIdsByCapacidades(capacidadIds))
                .thenReturn(Flux.just(1L, 2L, 3L));

        StepVerifier.create(useCase.findTecnologiaIdsByCapacidades(capacidadIds))
                .expectNext(1L, 2L, 3L)
                .verifyComplete();

        verify(persistencePort).findTecnologiaIdsByCapacidades(capacidadIds);
    }

    @Test
    void findTecnologiaIdsByCapacidades_shouldReturnEmptyFluxIfNone() {
        List<Long> capacidadIds = List.of(99L);
        
        when(persistencePort.findTecnologiaIdsByCapacidades(capacidadIds))
                .thenReturn(Flux.empty());

        StepVerifier.create(useCase.findTecnologiaIdsByCapacidades(capacidadIds))
                .verifyComplete();

        verify(persistencePort).findTecnologiaIdsByCapacidades(capacidadIds);
    }

    @Test
    void countCapacidadesByTecnologiaId_shouldReturnCount() {
        Long tecnologiaId = 1L;
        
        when(persistencePort.countCapacidadesByTecnologiaId(tecnologiaId))
                .thenReturn(Mono.just(5L));

        StepVerifier.create(useCase.countCapacidadesByTecnologiaId(tecnologiaId))
                .expectNext(5L)
                .verifyComplete();

        verify(persistencePort).countCapacidadesByTecnologiaId(tecnologiaId);
    }

    @Test
    void countCapacidadesByTecnologiaId_shouldReturnZeroIfNone() {
        Long tecnologiaId = 99L;
        
        when(persistencePort.countCapacidadesByTecnologiaId(tecnologiaId))
                .thenReturn(Mono.just(0L));

        StepVerifier.create(useCase.countCapacidadesByTecnologiaId(tecnologiaId))
                .expectNext(0L)
                .verifyComplete();

        verify(persistencePort).countCapacidadesByTecnologiaId(tecnologiaId);
    }
}
