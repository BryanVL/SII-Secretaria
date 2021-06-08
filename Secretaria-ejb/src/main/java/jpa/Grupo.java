package jpa;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.*;

@Entity
@Table( uniqueConstraints = { @UniqueConstraint(columnNames = {"Curso","Letra"}) } )
public class Grupo implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;
	
	@Column(length=1, nullable=false)
	private Integer Curso;
	@Column(length=1, nullable=false)
	private String Letra;
	
	@Column(length=7, nullable=false)
	private String Turno_Mañana_Tarde;
	@Column(length=2, nullable=false)
	private String Ingles;
	private Boolean Visible;
	private Boolean Asignar;
	@Column(length=3)
	private Integer Plazas;
	@Column(length=3)
	private Integer PlazasDisponibles;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Titulacion titulacion;
	
	@OneToMany(mappedBy="ID1", cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )
	private List<Grupo> grupos;
	@ManyToOne
	private Grupo ID1;
	
	@OneToMany(mappedBy="grupo", cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )
	private List<Asignaturas_Matricula> AsigMat;
	
	@OneToMany(mappedBy="grupo", cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )
	private List<Clase> clases;
	
	@OneToMany(mappedBy="grupo", cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )
	private List<Grupos_por_Asignatura> GrAsig;

	
	public Grupo() {
		super();
	}
	
	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}
	
	public Integer getCurso() {
		return Curso;
	}

	public void setCurso(Integer curso) {
		Curso = curso;
	}

	public String getLetra() {
		return Letra;
	}

	public void setLetra(String letra) {
		Letra = letra;
	}


	public String getTurno_Mañana_Tarde() {
		return Turno_Mañana_Tarde;
	}

	public void setTurno_Mañana_Tarde(String turno_Mañana_Tarde) {
		Turno_Mañana_Tarde = turno_Mañana_Tarde;
	}

	public String getIngles() {
		return Ingles;
	}

	public void setIngles(String ingles) {
		Ingles = ingles;
	}

	public Boolean getVisible() {
		return Visible;
	}

	public void setVisible(Boolean visible) {
		Visible = visible;
	}

	public Boolean getAsignar() {
		return Asignar;
	}

	public void setAsignar(Boolean asignar) {
		Asignar = asignar;
	}

	public Integer getPlazas() {
		return Plazas;
	}

	public void setPlazas(Integer plazas) {
		Plazas = plazas;
	}
	
	public Integer getPlazasDisponibles() {
		return PlazasDisponibles;
	}

	public void setPlazasDisponibles(Integer plazas) {
		PlazasDisponibles = plazas;
	}

	public Titulacion getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Grupo getID1() {
		return ID1;
	}

	public void setID1(Grupo iD1) {
		ID1 = iD1;
	}

	public List<Asignaturas_Matricula> getAsigMat() {
		return AsigMat;
	}

	public void setAsigMat(List<Asignaturas_Matricula> AsigMat) {
		this.AsigMat = AsigMat;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	public List<Grupos_por_Asignatura> getGrAsig() {
		return GrAsig;
	}

	public void setGrAsig(List<Grupos_por_Asignatura> grAsig) {
		GrAsig = grAsig;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ","Grupo [","]");
		StringJoiner sj2;
		if(ID != null) 					{sj.add("ID=" + ID);}
		if(Curso != null) 				{sj.add("Curso=" + Curso);}
		if(Letra != null) 				{sj.add("Letra=" + Letra);}
		if(Turno_Mañana_Tarde != null) 	{sj.add("Turno_Mañana_Tarde=" + Turno_Mañana_Tarde);}
		if(Ingles != null) 				{sj.add("Ingles=" + Ingles);}
		if(Visible != null) 			{sj.add("Visible=" + Visible);}
		if(Asignar != null) 			{sj.add("Asignar=" + Asignar);}
		if(Plazas != null) 				{sj.add("Plazas=" + Plazas);}
		if(PlazasDisponibles != null) 	{sj.add("PlazasDisponibles=" + PlazasDisponibles);}
		if(titulacion != null) 			{sj.add("titulacion=" + titulacion.getCodigo());}
		if(grupos != null) {
			sj2 = new StringJoiner(", ","Grupos=(",")");
			for(Grupo e : grupos) { if(e.getID() != null) { sj2.add(e.getID().toString());}}
			sj.add(sj2.toString());
		}
		if(ID1 != null) 				{sj.add("ID1=" + ID1.getID());}
		if(AsigMat != null) {
			sj2 = new StringJoiner(", ","Asignaturas_Matricula=(",")");
			for(Asignaturas_Matricula e : AsigMat) { if(e.getId() != null) { sj2.add(e.getId().toString());}}
			sj.add(sj2.toString());
		}
		if(clases != null) {
			sj2 = new StringJoiner(", ","Clases=(",")");
			for(Clase e : clases) { if(e.getId() != null) { sj2.add(e.getId().toString());}}
			sj.add(sj2.toString());
		}
		if(GrAsig != null) {
			sj2 = new StringJoiner(", ","Grupos_por_Asignatura=(",")");
			for(Grupos_por_Asignatura e : GrAsig) { if(e.getId() != null) { sj2.add(e.getId().toString());}}
			sj.add(sj2.toString());
		}
		return sj.toString();
	}

}
