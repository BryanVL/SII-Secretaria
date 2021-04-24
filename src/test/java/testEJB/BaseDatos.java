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
		
		Alumno bryan = new Alumno();
		Alumno fran = new Alumno();
		Alumno amin = new Alumno();
		Alumno noel = new Alumno();
		Alumno david = new Alumno();
		
		Expediente b = new Expediente();
		Expediente f = new Expediente();
		Expediente a = new Expediente();
		Expediente n = new Expediente();
		Expediente d = new Expediente();
		
		Matricula br = new Matricula();

		
		bryan.setNombre("Bryan");
		bryan.setApellido1("velicka");
		bryan.setDNI("125678A");
		bryan.setEmail_institucional("velicka.b@uma.es");
		b.setNum_expediente(123);
		Titulacion tit = new Titulacion();
		tit.setCodigo(1234);
		b.setTitulacion(tit);
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
		bryan.setExpedientes(lb);
		
		
		
		fran.setNombre("Franco manuel");
		fran.setApellido1("garcia");
		fran.setDNI("125679B");
		fran.setEmail_institucional("franco@uma.es");
		f.setNum_expediente(124);
		
		amin.setNombre("amin");
		amin.setApellido1("chachaSuperFast");
		amin.setDNI("125680C");
		amin.setEmail_institucional("amin@uma.es");
		a.setNum_expediente(125);
		
		noel.setNombre("noel");
		noel.setApellido1("ApellidoDeNoel");
		noel.setDNI("125681D");
		noel.setEmail_institucional("noel@uma.es");
		n.setNum_expediente(126);
		
		david.setNombre("david");
		david.setApellido1("ApellidoDeDavid");
		david.setDNI("125682E");
		david.setEmail_institucional("david@uma.es");
		d.setNum_expediente(127);
		
		em.persist(bryan);
		em.persist(fran);
		em.persist(amin);
		em.persist(noel);
		em.persist(david);
		
		
		Asignatura asignatura = new Asignatura();
		asignatura.setReferencia(12345);
		asignatura.setCodigo(900);
		asignatura.setCreditos_total((float)6);
		asignatura.setOfertada("Si");
		asignatura.setNombre("Pruebas con Junit");
		
		Clase cAsig = new Clase();
		Clase_PK cAsigPK = new Clase_PK();
		cAsigPK.setDia(new Date("24/09/2018"));
		//cAsigPK.setHora_inicio(new);
		cAsig.setAsignatura(asignatura);
		//cAsig
		
		
		
		/*Ingrediente carne = new Ingrediente ("Carne picada");
		Ingrediente pimienta = new Ingrediente ("Pimienta");
		Ingrediente especias = new Ingrediente("Especias de hamburguesa");
		Ingrediente pimenton = new Ingrediente ("Pimentón");
		Ingrediente sal = new Ingrediente ("Sal");
		Ingrediente azucar = new Ingrediente ("Azúcar");
		Ingrediente perejil = new Ingrediente ("Perejil");
		
		for (Ingrediente ingrediente: new Ingrediente [] {carne, pimienta, especias, pimenton, sal, azucar, perejil}) {
			em.persist(ingrediente);
		}
		
		Producto chorizo = new Producto ("Chorizo");
		Producto salchicha = new Producto ("Salchicha");
		Producto hamburguesa = new Producto ("Hamburguesa");
		
		chorizo.setIngredientes(Stream.of(carne, pimienta, pimenton, sal)
				.collect(Collectors.toSet()));
		
		salchicha.setIngredientes(Stream.of(carne, sal, azucar, perejil)
				.collect(Collectors.toSet()));
		
		hamburguesa.setIngredientes(Stream.of(carne, especias, sal, azucar)
				.collect(Collectors.toSet()));
		
		for (Producto producto: new Producto [] {chorizo, salchicha, hamburguesa}) {
			em.persist(producto);
		}
		
		Lote lote = new Lote ("LT1", chorizo, BigDecimal.TEN, Date.valueOf("2021-04-11"));
		lote.setLoteIngredientes(new HashMap<Ingrediente, String>());
		lote.getLoteIngredientes().put(carne, "C1");
		lote.getLoteIngredientes().put(pimienta, "Pi1");
		lote.getLoteIngredientes().put(pimenton, "PM1");
		lote.getLoteIngredientes().put(sal, "S1");
		
		em.persist(lote);
		
		lote = new Lote ("LT2", chorizo, BigDecimal.valueOf(25L), Date.valueOf("2021-04-12"));
		lote.setLoteIngredientes(new HashMap<Ingrediente, String>());
		lote.getLoteIngredientes().put(carne, "C2");
		lote.getLoteIngredientes().put(pimienta, "Pi2");
		lote.getLoteIngredientes().put(pimenton, "PM2");
		lote.getLoteIngredientes().put(sal, "S2");
		
		em.persist(lote);*/
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
}
