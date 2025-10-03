package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "capacidad_tecnologias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CapacidadTecnologiaEntity {

    @Id
    private Long id;

    @Column("id_capacidad")
    private Long capacidadId;

    @Column("id_tecnologia")
    private Long tecnologiaId;
}
