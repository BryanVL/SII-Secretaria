package testEJB;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jpa.Alumno;

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
		
		bryan.setNombre("Bryan");
		bryan.setApellido1("velicka");
		bryan.setDNI("12345678A");
		bryan.setEmail_institucional("velicka.b@uma.es");
		
		fran.setNombre("Franco manuel");
		fran.setApellido1("garcia");
		fran.setDNI("12345679B");
		fran.setEmail_institucional("franco@uma.es");
		
		amin.setNombre("amin");
		amin.setApellido1("chachaSuperFast");
		amin.setDNI("12345680C");
		amin.setEmail_institucional("amin@uma.es");
		
		noel.setNombre("noel");
		noel.setApellido1("ApellidoDeNoel");
		noel.setDNI("12345681D");
		noel.setEmail_institucional("noel@uma.es");
		
		david.setNombre("david");
		david.setApellido1("ApellidoDeDavid");
		david.setDNI("12345682E");
		david.setEmail_institucional("david@uma.es");
		
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
