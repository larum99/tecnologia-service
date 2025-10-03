package com.onclass.tecnologia.domain.usecase;

import com.onclass.tecnologia.domain.api.TecnologiaServicePort;
import com.onclass.tecnologia.domain.constants.Constants;
import com.onclass.tecnologia.domain.enums.TechnicalMessage;
import com.onclass.tecnologia.domain.exceptions.BusinessException;
import com.onclass.tecnologia.domain.model.Tecnologia;
import com.onclass.tecnologia.domain.spi.TecnologiaPersistencePort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TecnologiaUseCase implements TecnologiaServicePort {

    private final TecnologiaPersistencePort tecnologiaPersistencePort;

    public TecnologiaUseCase(TecnologiaPersistencePort tecnologiaPersistencePort) {
        this.tecnologiaPersistencePort = tecnologiaPersistencePort;
    }

    @Override
    public Mono<Tecnologia> registrarTecnologia(Tecnologia tecnologia, String messageId) {
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

        return tecnologiaPersistencePort.existByNombre(tecnologia.nombre())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new BusinessException(TechnicalMessage.TECNOLOGIA_ALREADY_EXISTS));
                    }
                    return tecnologiaPersistencePort.save(tecnologia);
                });
    }
}
