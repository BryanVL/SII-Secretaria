INSERT INTO Usuario (usuario,password,rol) VALUES ('admin','admin','Admin');
insert into Alumno (nombre, apellido1, id, dni, Email_institucional) values ('Bryan', 'velicka', 1234, '125678A', 'velicka.b@uma.es');
INSERT INTO Usuario (usuario,password,rol, alumno_id) VALUES ('bvl','test','Alumno', 1234);
insert into Titulacion (codigo, creditos, nombre) values (1234, 240, 'Informatica');
insert into Expediente (num_expediente, alumno_id, TITULACION_CODIGO) values (123,1234, 1234);
insert into Matricula (Curso_academico, EXPEDIENTE_NUM_EXPEDIENTE, Estado, Fecha_de_matricula) values ('18/19', 123, 'Activo', str_to_date('12/09/2018', '%d/%m/%Y'));
insert into Asignatura (referencia, codigo, Creditos_total, Ofertada, Nombre, TITULACION_CODIGO) values (12345, 950, 6, 'Si', 'Pruebas de integracion', 1234);
insert into Asignatura (referencia, codigo, Creditos_total, Ofertada, Nombre, TITULACION_CODIGO) values (12346, 951, 6, 'Si', 'Pruebas de integracion 2', 1234);
insert into Optativa (ASIGNATURA_REFERENCIA, Mencion, Plazas) values (12346, 'Informatica', 50);
insert into Grupo(id, curso, letra, Turno_Mañana_Tarde, Ingles, Plazas, PlazasDisponibles, TITULACION_CODIGO) values (1,1,'A', 'Mañana', 'Sí', 50, 50, 1234);
insert into Grupo(id, curso, letra, Turno_Mañana_Tarde, Ingles, Plazas, PlazasDisponibles, TITULACION_CODIGO) values (2,1,'B', 'Mañana', 'Sí', 50, 50, 1234);

