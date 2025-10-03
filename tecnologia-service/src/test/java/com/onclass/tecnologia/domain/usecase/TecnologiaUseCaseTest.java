package com.onclass.tecnologia.domain.usecase;

import com.onclass.tecnologia.domain.constants.Constants;
import com.onclass.tecnologia.domain.enums.TechnicalMessage;
import com.onclass.tecnologia.domain.exceptions.BusinessException;
import com.onclass.tecnologia.domain.model.Tecnologia;
import com.onclass.tecnologia.domain.spi.TecnologiaPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class TecnologiaUseCaseTest {

    private TecnologiaPersistencePort tecnologiaPersistencePort;
    private TecnologiaUseCase tecnologiaUseCase;

    @BeforeEach
    void setUp() {
        tecnologiaPersistencePort = Mockito.mock(TecnologiaPersistencePort.class);
        tecnologiaUseCase = new TecnologiaUseCase(tecnologiaPersistencePort);

        // Mock genérico: si no se especifica, se asume que el nombre NO existe
        when(tecnologiaPersistencePort.existByNombre(anyString())).thenReturn(Mono.just(false));
    }

    @Test
    void registrarTecnologia_shouldReturnError_whenNombreIsBlank() {
        Tecnologia tecnologia = new Tecnologia(null, "  ", "Descripcion");

        StepVerifier.create(tecnologiaUseCase.registrarTecnologia(tecnologia, "msg-1"))
                .expectErrorMatches(throwable ->
                        throwable instanceof BusinessException &&
                                ((BusinessException) throwable).getTechnicalMessage() == TechnicalMessage.TECNOLOGIA_NOMBRE_REQUIRED
                )
                .verify();

        verify(tecnologiaPersistencePort, never()).save(any());
    }

    @Test
    void registrarTecnologia_shouldReturnError_whenDescripcionIsBlank() {
        Tecnologia tecnologia = new Tecnologia(null, "Java", "  ");

        StepVerifier.create(tecnologiaUseCase.registrarTecnologia(tecnologia, "msg-2"))
                .expectErrorMatches(throwable ->
                        throwable instanceof BusinessException &&
                                ((BusinessException) throwable).getTechnicalMessage() == TechnicalMessage.TECNOLOGIA_DESCRIPCION_REQUIRED
                )
                .verify();

        verify(tecnologiaPersistencePort, never()).save(any());
    }

    @Test
    void registrarTecnologia_shouldReturnError_whenNombreAlreadyExists() {
        Tecnologia tecnologia = new Tecnologia(null, "Java", "Descripcion");

        when(tecnologiaPersistencePort.existByNombre("Java")).thenReturn(Mono.just(true));

        StepVerifier.create(tecnologiaUseCase.registrarTecnologia(tecnologia, "msg-3"))
                .expectErrorMatches(throwable ->
                        throwable instanceof BusinessException &&
                                ((BusinessException) throwable).getTechnicalMessage() == TechnicalMessage.TECNOLOGIA_ALREADY_EXISTS
                )
                .verify();

        verify(tecnologiaPersistencePort, never()).save(any());
    }

    @Test
    void registrarTecnologia_shouldSave_whenValidData() {
        Tecnologia tecnologia = new Tecnologia(null, "Java", "Descripcion");

        when(tecnologiaPersistencePort.save(tecnologia))
                .thenReturn(Mono.just(new Tecnologia(1L, "Java", "Descripcion")));

        StepVerifier.create(tecnologiaUseCase.registrarTecnologia(tecnologia, "msg-4"))
                .expectNextMatches(saved -> saved.id() != null && saved.nombre().equals("Java"))
                .verifyComplete();

        verify(tecnologiaPersistencePort, times(1)).save(tecnologia);
    }

    @Test
    void registrarTecnologia_shouldReturnError_whenNombreTooLong() {
        String nombreLargo = "A".repeat(Constants.MAX_NOMBRE_TECNOLOGIA + 1);
        Tecnologia tecnologia = new Tecnologia(null, nombreLargo, "Descripcion");

        StepVerifier.create(tecnologiaUseCase.registrarTecnologia(tecnologia, "msg-5"))
                .expectErrorMatches(throwable ->
                        throwable instanceof BusinessException &&
                                ((BusinessException) throwable).getTechnicalMessage() == TechnicalMessage.TECNOLOGIA_NOMBRE_TOO_LONG
                )
                .verify();

        verify(tecnologiaPersistencePort, never()).save(any());
    }

    @Test
    void registrarTecnologia_shouldReturnError_whenDescripcionTooLong() {
        String descripcionLarga = "A".repeat(Constants.MAX_DESCRIPCION_TECNOLOGIA + 1);
        Tecnologia tecnologia = new Tecnologia(null, "Java", descripcionLarga);

        StepVerifier.create(tecnologiaUseCase.registrarTecnologia(tecnologia, "msg-6"))
                .expectErrorMatches(throwable ->
                        throwable instanceof BusinessException &&
                                ((BusinessException) throwable).getTechnicalMessage() == TechnicalMessage.TECNOLOGIA_DESCRIPCION_TOO_LONG
                )
                .verify();

        verify(tecnologiaPersistencePort, never()).save(any());
    }
}
