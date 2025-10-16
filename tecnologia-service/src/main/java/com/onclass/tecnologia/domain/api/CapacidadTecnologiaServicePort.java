package com.onclass.tecnologia.domain.api;

import com.onclass.tecnologia.domain.model.CapacidadTecnologia;
import com.onclass.tecnologia.domain.model.Tecnologia;
import reactor.core.publisher.Flux;

import java.util.List;

public interface CapacidadTecnologiaServicePort {

    Flux<CapacidadTecnologia> registrarCapacidadTecnologias(List<CapacidadTecnologia> relaciones, String messageId);

    Flux<Tecnologia> listarTecnologiasPorCapacidad(Long capacidadId);
}
