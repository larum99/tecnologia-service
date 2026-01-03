package com.onclass.tecnologia.domain.usecase;

import com.onclass.tecnologia.domain.api.TecnologiaServicePort;
import com.onclass.tecnologia.domain.constants.Constants;
import com.onclass.tecnologia.domain.enums.TechnicalMessage;
import com.onclass.tecnologia.domain.exceptions.BusinessException;
import com.onclass.tecnologia.domain.model.Tecnologia;
import com.onclass.tecnologia.domain.spi.TecnologiaPersistencePort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TecnologiaUseCase implements TecnologiaServicePort {

    private final TecnologiaPersistencePort tecnologiaPersistencePort;

    public TecnologiaUseCase(TecnologiaPersistencePort tecnologiaPersistencePort) {
        this.tecnologiaPersistencePort = tecnologiaPersistencePort;
    }

    @Override
    public Mono<Tecnologia> registrarTecnologia(Tecnologia tecnologia, String messageId) {
        return validarTecnologia(tecnologia)
                .then(verificarTecnologiaNoExiste(tecnologia.nombre()))
                .then(tecnologiaPersistencePort.saveTecnologia(tecnologia));
    }

    @Override
    public Mono<Void> eliminarTecnologia(Long tecnologiaId, String messageId) {
        return validarTecnologiaId(tecnologiaId)
                .then(verificarTecnologiaExiste(tecnologiaId))
                .then(tecnologiaPersistencePort.deleteById(tecnologiaId));
    }

    private Mono<Void> validarTecnologia(Tecnologia tecnologia) {
        if (tecnologia.nombre() == null || tecnologia.nombre().isBlank()) {
            return Mono.error(new BusinessException(TechnicalMessage.TECNOLOGIA_NOMBRE_REQUIRED));
        }
        if (tecnologia.descripcion() == null || tecnologia.descripcion().isBlank()) {
            return Mono.error(new BusinessException(TechnicalMessage.TECNOLOGIA_DESCRIPCION_REQUIRED));
        }
        if (tecnologia.nombre().length() > Constants.MAX_NOMBRE_TECNOLOGIA) {
            return Mono.error(new BusinessException(TechnicalMessage.TECNOLOGIA_NOMBRE_TOO_LONG));
        }
        if (tecnologia.descripcion().length() > Constants.MAX_DESCRIPCION_TECNOLOGIA) {
            return Mono.error(new BusinessException(TechnicalMessage.TECNOLOGIA_DESCRIPCION_TOO_LONG));
        }
        return Mono.empty();
    }

    private Mono<Void> verificarTecnologiaNoExiste(String nombre) {
        return tecnologiaPersistencePort.existByNombre(nombre)
                .flatMap(exists -> exists 
                    ? Mono.error(new BusinessException(TechnicalMessage.TECNOLOGIA_ALREADY_EXISTS))
                    : Mono.empty());
    }

    private Mono<Void> validarTecnologiaId(Long tecnologiaId) {
        if (tecnologiaId == null || tecnologiaId <= 0) {
            return Mono.error(new BusinessException(TechnicalMessage.TECNOLOGIA_ID_INVALID));
        }
        return Mono.empty();
    }

    private Mono<Void> verificarTecnologiaExiste(Long tecnologiaId) {
        return tecnologiaPersistencePort.existsById(tecnologiaId)
                .flatMap(exists -> exists 
                    ? Mono.empty() 
                    : Mono.error(new BusinessException(TechnicalMessage.TECNOLOGIA_NOT_FOUND)));
    }
}
