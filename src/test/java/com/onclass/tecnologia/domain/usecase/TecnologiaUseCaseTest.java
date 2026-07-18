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
    }

    @Test
    void registrarTecnologia_shouldReturnError_whenNombreIsBlank() {
        Tecnologia tecnologia = new Tecnologia(null, "  ", "Descripcion");

        when(tecnologiaPersistencePort.existByNombre(anyString())).thenReturn(Mono.just(false));
        when(tecnologiaPersistencePort.saveTecnologia(any())).thenReturn(Mono.empty());

        StepVerifier.create(tecnologiaUseCase.registrarTecnologia(tecnologia, "msg-1"))
                .expectErrorMatches(throwable ->
                        throwable instanceof BusinessException &&
                                ((BusinessException) throwable).getTechnicalMessage() == TechnicalMessage.TECNOLOGIA_NOMBRE_REQUIRED
                )
                .verify();
    }

    @Test
    void registrarTecnologia_shouldReturnError_whenDescripcionIsBlank() {
        Tecnologia tecnologia = new Tecnologia(null, "Java", "  ");

        when(tecnologiaPersistencePort.existByNombre(anyString())).thenReturn(Mono.just(false));
        when(tecnologiaPersistencePort.saveTecnologia(any())).thenReturn(Mono.empty());

        StepVerifier.create(tecnologiaUseCase.registrarTecnologia(tecnologia, "msg-2"))
                .expectErrorMatches(throwable ->
                        throwable instanceof BusinessException &&
                                ((BusinessException) throwable).getTechnicalMessage() == TechnicalMessage.TECNOLOGIA_DESCRIPCION_REQUIRED
                )
                .verify();
    }

    @Test
    void registrarTecnologia_shouldReturnError_whenNombreAlreadyExists() {
        Tecnologia tecnologia = new Tecnologia(null, "Java", "Descripcion");

        when(tecnologiaPersistencePort.existByNombre("Java")).thenReturn(Mono.just(true));
        when(tecnologiaPersistencePort.saveTecnologia(any())).thenReturn(Mono.empty());

        StepVerifier.create(tecnologiaUseCase.registrarTecnologia(tecnologia, "msg-3"))
                .expectErrorMatches(throwable ->
                        throwable instanceof BusinessException &&
                                ((BusinessException) throwable).getTechnicalMessage() == TechnicalMessage.TECNOLOGIA_ALREADY_EXISTS
                )
                .verify();
    }

    @Test
    void registrarTecnologia_shouldSave_whenValidData() {
        Tecnologia tecnologia = new Tecnologia(null, "Java", "Descripcion");

        when(tecnologiaPersistencePort.existByNombre("Java")).thenReturn(Mono.just(false));
        when(tecnologiaPersistencePort.saveTecnologia(tecnologia))
                .thenReturn(Mono.just(new Tecnologia(1L, "Java", "Descripcion")));

        StepVerifier.create(tecnologiaUseCase.registrarTecnologia(tecnologia, "msg-4"))
                .expectNextMatches(saved -> saved.id() != null && saved.nombre().equals("Java"))
                .verifyComplete();

        verify(tecnologiaPersistencePort, times(1)).saveTecnologia(tecnologia);
    }

    @Test
    void registrarTecnologia_shouldReturnError_whenNombreTooLong() {
        String nombreLargo = "A".repeat(Constants.MAX_NOMBRE_TECNOLOGIA + 1);
        Tecnologia tecnologia = new Tecnologia(null, nombreLargo, "Descripcion");

        when(tecnologiaPersistencePort.existByNombre(anyString())).thenReturn(Mono.just(false));
        when(tecnologiaPersistencePort.saveTecnologia(any())).thenReturn(Mono.empty());

        StepVerifier.create(tecnologiaUseCase.registrarTecnologia(tecnologia, "msg-5"))
                .expectErrorMatches(throwable ->
                        throwable instanceof BusinessException &&
                                ((BusinessException) throwable).getTechnicalMessage() == TechnicalMessage.TECNOLOGIA_NOMBRE_TOO_LONG
                )
                .verify();
    }

    @Test
    void registrarTecnologia_shouldReturnError_whenDescripcionTooLong() {
        String descripcionLarga = "A".repeat(Constants.MAX_DESCRIPCION_TECNOLOGIA + 1);
        Tecnologia tecnologia = new Tecnologia(null, "Java", descripcionLarga);

        when(tecnologiaPersistencePort.existByNombre(anyString())).thenReturn(Mono.just(false));
        when(tecnologiaPersistencePort.saveTecnologia(any())).thenReturn(Mono.empty());

        StepVerifier.create(tecnologiaUseCase.registrarTecnologia(tecnologia, "msg-6"))
                .expectErrorMatches(throwable ->
                        throwable instanceof BusinessException &&
                                ((BusinessException) throwable).getTechnicalMessage() == TechnicalMessage.TECNOLOGIA_DESCRIPCION_TOO_LONG
                )
                .verify();
    }

    @Test
    void eliminarTecnologia_shouldReturnError_whenIdIsNull() {
        when(tecnologiaPersistencePort.existsById(any())).thenReturn(Mono.just(false));
        when(tecnologiaPersistencePort.deleteById(any())).thenReturn(Mono.empty());

        StepVerifier.create(tecnologiaUseCase.eliminarTecnologia(null, "msg-7"))
                .expectErrorMatches(throwable ->
                        throwable instanceof BusinessException &&
                                ((BusinessException) throwable).getTechnicalMessage() == TechnicalMessage.TECNOLOGIA_ID_INVALID
                )
                .verify();
    }

    @Test
    void eliminarTecnologia_shouldReturnError_whenIdIsInvalid() {
        when(tecnologiaPersistencePort.existsById(any())).thenReturn(Mono.just(false));
        when(tecnologiaPersistencePort.deleteById(any())).thenReturn(Mono.empty());

        StepVerifier.create(tecnologiaUseCase.eliminarTecnologia(0L, "msg-8"))
                .expectErrorMatches(throwable ->
                        throwable instanceof BusinessException &&
                                ((BusinessException) throwable).getTechnicalMessage() == TechnicalMessage.TECNOLOGIA_ID_INVALID
                )
                .verify();
    }

    @Test
    void eliminarTecnologia_shouldReturnError_whenTecnologiaNotFound() {
        when(tecnologiaPersistencePort.existsById(1L)).thenReturn(Mono.just(false));
        when(tecnologiaPersistencePort.deleteById(any())).thenReturn(Mono.empty());

        StepVerifier.create(tecnologiaUseCase.eliminarTecnologia(1L, "msg-9"))
                .expectErrorMatches(throwable ->
                        throwable instanceof BusinessException &&
                                ((BusinessException) throwable).getTechnicalMessage() == TechnicalMessage.TECNOLOGIA_NOT_FOUND
                )
                .verify();
    }

    @Test
    void eliminarTecnologia_shouldDelete_whenValidId() {
        when(tecnologiaPersistencePort.existsById(1L)).thenReturn(Mono.just(true));
        when(tecnologiaPersistencePort.deleteById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(tecnologiaUseCase.eliminarTecnologia(1L, "msg-10"))
                .verifyComplete();

        verify(tecnologiaPersistencePort, times(1)).deleteById(1L);
    }
}
