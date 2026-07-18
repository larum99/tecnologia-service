CREATE TABLE IF NOT EXISTS tecnologias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS capacidad_tecnologias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_capacidad BIGINT NOT NULL,
    id_tecnologia BIGINT NOT NULL
);
