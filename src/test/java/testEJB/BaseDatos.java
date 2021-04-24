package testEJB;

import java.math.BigDecimal;
//import java.sql.Date;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jpa.Alumno;
import jpa.Asignatura;
import jpa.Clase;
import jpa.Clase_PK;
import jpa.Expediente;
import jpa.Grupo;
import jpa.Matricula;
import jpa.Matricula_PK;
import jpa.Titulacion;

/*import es.uma.informatica.sii.ejb.practica.entidades.Ingrediente;
import es.uma.informatica.sii.ejb.practica.entidades.Lote;
import es.uma.informatica.sii.ejb.practica.entidades.Producto;*/

public class BaseDatos {
	public static void inicializaBaseDatos(String nombreUnidadPersistencia) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		//Creamos 5 alumnos
		Alumno bryan = new Alumno();
		Alumno fran = new Alumno();
		Alumno amin = new Alumno();
		Alumno noel = new Alumno();
		Alumno david = new Alumno();
		
		//Sus 5 expedientes (inicial de cada alumno)
		Expediente b = new Expediente();
		Expediente f = new Expediente();
		Expediente a = new Expediente();
		Expediente n = new Expediente();
		Expediente d = new Expediente();
		

		//---------------------Bryan---------------------//
		bryan.setNombre("Bryan");
		bryan.setApellido1("velicka");
		bryan.setDNI("125678A");
		bryan.setEmail_institucional("velicka.b@uma.es");
		b.setNum_expediente(123);
	
		/*Titulacion tit = new Titulacion();
		tit.setCodigo(1234);
		tit.setNombre("Informatica");
		tit.setCreditos( 240f );
		
		b.setNum_expediente(123);
		b.setTitulacion(tit);
		
		Matricula br = new Matricula();
		Matricula_PK brk = new Matricula_PK();
		brk.setCurso_academico("18/19");
		brk.setIdExp(123);
		
		br.setId(brk);
		br.setEstado("Activo");
		br.setFecha_de_matricula(new Date("12/09/2018"));
		
		List<Matricula> lbr = new ArrayList<Matricula>();
		lbr.add(br);
		b.setMatriculas(lbr);
		
		List<Expediente> lb = new ArrayList<Expediente>();
		lb.add(b);
		bryan.setExpedientes(lb);*/
		
		
		//---------------------Fran---------------------//
		fran.setNombre("Franco manuel");
		fran.setApellido1("garcia");
		fran.setDNI("125679B");
		fran.setEmail_institucional("franco@uma.es");
		
		f.setNum_expediente(124);
		//f.setTitulacion(tit);
		f.setAlumno(fran);
		
		
		//---------------------Amin---------------------//
		amin.setNombre("amin");
		amin.setApellido1("chachaSuperFast");
		amin.setDNI("125680C");
		amin.setEmail_institucional("amin@uma.es");
		
		a.setNum_expediente(125);
		//a.setTitulacion(tit);
		a.setAlumno(amin);
		
		
		//---------------------Noel---------------------//
		noel.setNombre("noel");
		noel.setApellido1("ApellidoDeNoel");
		noel.setDNI("125681D");
		noel.setEmail_institucional("noel@uma.es");
		
		n.setNum_expediente(126);
		//n.setTitulacion(tit);
		n.setAlumno(noel);
		
		
		//---------------------David---------------------//
		david.setNombre("david");
		david.setApellido1("ApellidoDeDavid");
		david.setDNI("125682E");
		david.setEmail_institucional("david@uma.es");
		
		d.setNum_expediente(127);
		//d.setTitulacion(tit);
		d.setAlumno(david);
		
		
		//-----------------------------------------------//
		em.persist(bryan);
		em.persist(fran);
		em.persist(amin);
		em.persist(noel);
		em.persist(david);
		
		
		//-----------------------------------------------//
		/*Asignatura asignatura = new Asignatura();
		asignatura.setReferencia(12345);
		asignatura.setCodigo(900);
		asignatura.setCreditos_total((float)6);
		asignatura.setOfertada("Si");
		asignatura.setNombre("Pruebas con Junit");
		//asignatura.setTitulacion(tit);
		
		em.persist(asignatura);*/
		
	/*	Clase cAsig = new Clase();
		Clase_PK cAsigPK = new Clase_PK();
		cAsigPK.setDia(new Date("24/09/2018"));
		//cAsigPK.setHora_inicio(new);
		cAsig.setAsignatura(asignatura);
		//cAsig*/
		
		/*Grupo grupoA = new Grupo();
		grupoA.setCurso(1);
		grupoA.setLetra("A");
		grupoA.setTurno_Mañana_Tarde("Mañana");
		grupoA.setIngles("Sí");
		grupoA.setPlazas(50);
		grupoA.setPlazasDisponibles(50);
		//grupoA.setTitulacion(tit);
		em.persist(grupoA);
		
		Grupo grupoB = new Grupo();
		grupoB.setCurso(1);
		grupoB.setLetra("B");
		grupoB.setTurno_Mañana_Tarde("Mañana");
		grupoB.setIngles("Sí");
		grupoB.setPlazas(50);
		grupoB.setPlazasDisponibles(50);
		//grupoB.setTitulacion(tit);
		em.persist(grupoB);*/
		
		
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
