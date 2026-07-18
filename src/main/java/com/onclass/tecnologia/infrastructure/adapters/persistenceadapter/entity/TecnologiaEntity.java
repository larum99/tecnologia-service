package com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.entity;

import com.onclass.tecnologia.infrastructure.adapters.persistenceadapter.util.EntityConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = EntityConstants.TABLE_TECNOLOGIAS)
@Getter
@Setter
@RequiredArgsConstructor
public class TecnologiaEntity {
    @Id
    private Long id;
    private String nombre;
    private String descripcion;
}
