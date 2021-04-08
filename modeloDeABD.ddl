-- Generado por Oracle SQL Developer Data Modeler 19.2.0.182.1216
--   en:        2021-04-08 16:42:59 CEST
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



CREATE TABLE alumno (
    id                    NUMBER(6) NOT NULL,
    dni                   VARCHAR2(10) NOT NULL,
    nombre                VARCHAR2(15) NOT NULL,
    apellido1             VARCHAR2(20) NOT NULL,
    apellido2             VARCHAR2(20),
    email_institucional   VARCHAR2(20) NOT NULL,
    email_personal        VARCHAR2(20),
    telefono              NUMBER(9),
    movil                 NUMBER(9),
    direccion             VARCHAR2(50),
    localidad             VARCHAR2(40),
    provincia             VARCHAR2(25),
    cp                    NUMBER(5),
    usuario               VARCHAR2(20)
);

ALTER TABLE alumno ADD CONSTRAINT alumno_pk PRIMARY KEY ( id );

ALTER TABLE alumno ADD CONSTRAINT alumno_dni_un UNIQUE ( dni );

CREATE TABLE asignatura (
    referencia          NUMBER(5) NOT NULL,
    codigo              NUMBER(3) NOT NULL,
    creditos_total      NUMBER(2) NOT NULL,
    creditos_teoria     NUMBER(2),
    ofertada            VARCHAR2(2) NOT NULL,
    nombre              VARCHAR2(30) NOT NULL,
    curso               NUMBER(1),
    caracter            VARCHAR2(10),
    duracion            NUMBER(1),
    unidad_temporal     VARCHAR2(10),
    titulacion_codigo   NUMBER(4) NOT NULL
);

ALTER TABLE asignatura ADD CONSTRAINT asignatura_pk PRIMARY KEY ( referencia );

CREATE TABLE asignaturas_matricula (
    asignatura_referencia                  NUMBER(5) NOT NULL,
    matricula_curso_academico              VARCHAR2(10) NOT NULL,
    grupo_id                               NUMBER(5), 
--  ERROR: Column name length exceeds maximum allowed length(30) 
    matricula_expedientes_num_expediente   NUMBER(9) NOT NULL
);

ALTER TABLE asignaturas_matricula
    ADD CONSTRAINT asignaturas_matricula_pk PRIMARY KEY ( asignatura_referencia,
                                                          matricula_curso_academico,
                                                          matricula_expedientes_num_expediente );

CREATE TABLE centro (
    id                INTEGER NOT NULL,
    nombre            VARCHAR2(80) NOT NULL,
    direccion         VARCHAR2(300) NOT NULL,
    tlf_conserjeria   NUMBER(9)
);

ALTER TABLE centro ADD CONSTRAINT centro_pk PRIMARY KEY ( id );

ALTER TABLE centro ADD CONSTRAINT centro_nombre_un UNIQUE ( nombre );

CREATE TABLE centro_titulacion (
    titulacion_codigo   NUMBER(4) NOT NULL,
    centro_id           INTEGER NOT NULL
);

ALTER TABLE centro_titulacion ADD CONSTRAINT centro_titulacion_pk PRIMARY KEY ( titulacion_codigo,
                                                                                centro_id );

CREATE TABLE clase (
    dia                     DATE NOT NULL,
    hora_inicio             DATE NOT NULL,
    hora_fin                DATE,
    asignatura_referencia   NUMBER(5) NOT NULL,
    grupo_id                NUMBER(5) NOT NULL
);

ALTER TABLE clase
    ADD CONSTRAINT clase_pk PRIMARY KEY ( hora_inicio,
                                          grupo_id,
                                          dia );

CREATE TABLE encuesta (
    fecha_de_envio               DATE NOT NULL,
    expedientes_num_expediente   NUMBER(9) NOT NULL
);

ALTER TABLE encuesta ADD CONSTRAINT encuesta_pk PRIMARY KEY ( fecha_de_envio );

--  ERROR: Table name length exceeds maximum allowed length(30) 
CREATE TABLE encuesta_grupos_por_asignaturas (
    encuesta_fecha_de_envio                  DATE NOT NULL, 
--  ERROR: Column name length exceeds maximum allowed length(30) 
    grupos_por_asignaturas_curso_academico   VARCHAR2(9) NOT NULL,
    grupos_por_asignaturas_id                NUMBER(5) NOT NULL, 
--  ERROR: Column name length exceeds maximum allowed length(30) 
    grupos_por_asignaturas_referencia        NUMBER(5) NOT NULL
);

--  ERROR: PK name length exceeds maximum allowed length(30) 
ALTER TABLE encuesta_grupos_por_asignaturas
    ADD CONSTRAINT encuesta_grupos_por_asignaturas_pk PRIMARY KEY ( encuesta_fecha_de_envio,
                                                                    grupos_por_asignaturas_curso_academico,
                                                                    grupos_por_asignaturas_id,
                                                                    grupos_por_asignaturas_referencia );

CREATE TABLE expedientes (
    num_expediente           NUMBER(9) NOT NULL,
    activo                   CHAR(1),
    nota_media_provisional   NUMBER(4, 2),
    creditos_superados       NUMBER(3),
    alumno_id                NUMBER(6) NOT NULL,
    titulacion_codigo        NUMBER(4) NOT NULL
);

ALTER TABLE expedientes ADD CONSTRAINT expedientes_pk PRIMARY KEY ( num_expediente );

CREATE TABLE grupo (
    id                   NUMBER(5) NOT NULL,
    curso                NUMBER(1) NOT NULL,
    letra                VARCHAR2(1) NOT NULL,
    turno_mañana_tarde   VARCHAR2(6) NOT NULL,
    ingles               VARCHAR2(2) NOT NULL,
    visible              CHAR(1),
    asignar              CHAR(1),
    plazas               NUMBER(3),
    grupo_id             NUMBER(5),
    titulacion_codigo    NUMBER(4) NOT NULL
);

ALTER TABLE grupo ADD CONSTRAINT grupo_pk PRIMARY KEY ( id );

ALTER TABLE grupo ADD CONSTRAINT grupo_curso_letra_un UNIQUE ( curso,
                                                               letra );

CREATE TABLE grupos_por_asignaturas (
    curso_academico         VARCHAR2(9) NOT NULL,
    oferta                  VARCHAR2(10),
    grupo_id                NUMBER(5) NOT NULL,
    asignatura_referencia   NUMBER(5) NOT NULL
);

ALTER TABLE grupos_por_asignaturas
    ADD CONSTRAINT grupos_por_asignaturas_pk PRIMARY KEY ( curso_academico,
                                                           grupo_id,
                                                           asignatura_referencia );

CREATE TABLE idiomas (
    nombre                  VARCHAR2(20) NOT NULL,
    asignatura_referencia   NUMBER(5)
);

ALTER TABLE idiomas ADD CONSTRAINT idiomas_pk PRIMARY KEY ( nombre );

CREATE TABLE matricula (
    curso_academico              VARCHAR2(10) NOT NULL,
    estado                       VARCHAR2(10) NOT NULL,
    fecha_de_matricula           DATE NOT NULL,
    num_archivo                  NUMBER(10),
    turno_preferente             VARCHAR2(6),
    nuevo_ingreso                VARCHAR2(2),
    listado_asignaturas          VARCHAR2(200),
    expedientes_num_expediente   NUMBER(9) NOT NULL
);

ALTER TABLE matricula ADD CONSTRAINT matricula_pk PRIMARY KEY ( curso_academico,
                                                                expedientes_num_expediente );

CREATE TABLE optativa (
    referencia   NUMBER(5) NOT NULL,
    plazas       NUMBER(2) NOT NULL,
    mencion      VARCHAR2(30)
);

ALTER TABLE optativa ADD CONSTRAINT optativa_pk PRIMARY KEY ( referencia );

CREATE TABLE titulacion (
    codigo     NUMBER(4) NOT NULL,
    nombre     VARCHAR2(20 BYTE) NOT NULL,
    creditos   NUMBER(3) NOT NULL
);

ALTER TABLE titulacion ADD CONSTRAINT titulacion_pk PRIMARY KEY ( codigo );

ALTER TABLE asignatura
    ADD CONSTRAINT asignatura_titulacion_fk FOREIGN KEY ( titulacion_codigo )
        REFERENCES titulacion ( codigo );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE asignaturas_matricula
    ADD CONSTRAINT asignaturas_matricula_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE asignaturas_matricula
    ADD CONSTRAINT asignaturas_matricula_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE asignaturas_matricula
    ADD CONSTRAINT asignaturas_matricula_matricula_fk FOREIGN KEY ( matricula_curso_academico,
                                                                    matricula_expedientes_num_expediente )
        REFERENCES matricula ( curso_academico,
                               expedientes_num_expediente );

ALTER TABLE centro_titulacion
    ADD CONSTRAINT centro_titulacion_centro_fk FOREIGN KEY ( centro_id )
        REFERENCES centro ( id );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE centro_titulacion
    ADD CONSTRAINT centro_titulacion_titulacion_fk FOREIGN KEY ( titulacion_codigo )
        REFERENCES titulacion ( codigo );

ALTER TABLE clase
    ADD CONSTRAINT clase_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE clase
    ADD CONSTRAINT clase_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE encuesta
    ADD CONSTRAINT encuesta_expedientes_fk FOREIGN KEY ( expedientes_num_expediente )
        REFERENCES expedientes ( num_expediente );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE encuesta_grupos_por_asignaturas
    ADD CONSTRAINT encuesta_grupos_por_asignaturas_encuesta_fk FOREIGN KEY ( encuesta_fecha_de_envio )
        REFERENCES encuesta ( fecha_de_envio );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE encuesta_grupos_por_asignaturas
    ADD CONSTRAINT encuesta_grupos_por_asignaturas_grupos_por_asignaturas_fk FOREIGN KEY ( grupos_por_asignaturas_curso_academico
    ,
                                                                                           grupos_por_asignaturas_id,
                                                                                           grupos_por_asignaturas_referencia )
        REFERENCES grupos_por_asignaturas ( curso_academico,
                                            grupo_id,
                                            asignatura_referencia );

ALTER TABLE expedientes
    ADD CONSTRAINT expedientes_alumno_fk FOREIGN KEY ( alumno_id )
        REFERENCES alumno ( id );

ALTER TABLE expedientes
    ADD CONSTRAINT expedientes_titulacion_fk FOREIGN KEY ( titulacion_codigo )
        REFERENCES titulacion ( codigo );

ALTER TABLE grupo
    ADD CONSTRAINT grupo_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE grupo
    ADD CONSTRAINT grupo_titulacion_fk FOREIGN KEY ( titulacion_codigo )
        REFERENCES titulacion ( codigo );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE grupos_por_asignaturas
    ADD CONSTRAINT grupos_por_asignaturas_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE grupos_por_asignaturas
    ADD CONSTRAINT grupos_por_asignaturas_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE idiomas
    ADD CONSTRAINT idiomas_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE matricula
    ADD CONSTRAINT matricula_expedientes_fk FOREIGN KEY ( expedientes_num_expediente )
        REFERENCES expedientes ( num_expediente );

ALTER TABLE optativa
    ADD CONSTRAINT optativa_asignatura_fk FOREIGN KEY ( referencia )
        REFERENCES asignatura ( referencia );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            15
-- CREATE INDEX                             0
-- ALTER TABLE                             38
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                  12
-- WARNINGS                                 0
