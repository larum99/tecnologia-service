package com.onclass.tecnologia.infrastructure.entrypoints.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CapacidadTecnologiaDTO {
    private Long capacidadId;
    private Long tecnologiaId;
}
