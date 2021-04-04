package Secretaria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("Secretaria");
		 EntityManager em = emf.createEntityManager();
		 
		 em.close();
		 emf.close();
		 System.out.println("Tablas y esquemas DDL creados");
		 
		
		//Prueba Alumno
		 Alumno prueba = new Alumno(123321L, "1231231D");
		 Expediente exp1 = new Expediente(12312);
		 Expediente exp2 = new Expediente(21321);
		 List<Expediente> lista = new ArrayList();
		 System.out.println(prueba.toString());
		 
		 //Prueba Expediente
		 List<Encuesta> lista2 = new ArrayList();
		 List<Matricula> lista3 = new ArrayList();
		 lista.add(exp1);
		 lista.add(exp2);
		 prueba.setExpedientes(lista);
		 Encuesta enc1 = new Encuesta(new Date("12/11/2000"));
		 Encuesta enc2 = new Encuesta(new Date("11/10/2003"));
		 lista2.add(enc1);
		 lista2.add(enc2);
		 exp1.setEncuestas(lista2);
		 Matricula mat1 = new Matricula("Segundo", 12);
		 Matricula mat2 = new Matricula("Tercero", 23);
		 lista3.add(mat1);
		 lista3.add(mat2);
		 exp1.setMatriculas(lista3);
		 System.out.println(exp1.toString());
		 
		 //Prueba Matricula
		 System.out.println(mat1.toString());
		 System.out.println(mat2.toString());
		 
		 //Prueba Asignatura_Matricula_PK
		 Asignaturas_Matricula_PK amat = new Asignaturas_Matricula_PK(new Matricula_PK("Segundo", 12), 321);
		 System.out.println(amat.toString());
		 
		 
	}

}
