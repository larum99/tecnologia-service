package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity;

import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.util.EntityConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = EntityConstants.TABLE_CAPACIDAD_TECNOLOGIAS)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CapacidadTecnologiaEntity {

    @Id
    private Long id;

    @Column(EntityConstants.COLUMN_ID_CAPACIDAD)
    private Long capacidadId;

    @Column(EntityConstants.COLUMN_ID_TECNOLOGIA)
    private Long tecnologiaId;
}
