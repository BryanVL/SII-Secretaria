package Secretaria;

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
	
	@Column(length=6, nullable=false)
	private String Turno_Mañana_Tarde;
	@Column(length=2, nullable=false)
	private String Ingles;
	private Boolean Visible;
	private Boolean Asignar;
	@Column(length=3)
	private Integer Plazas;
	
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
		String res = "Grupo [" + (ID != null ? "ID=" + ID : "") + (Curso != null ? ", Curso=" + Curso : "")
				+ (Letra != null ? ", Letra=" + Letra : "")
				+ (Turno_Mañana_Tarde != null ? ", Turno_Mañana_Tarde=" + Turno_Mañana_Tarde : "")
				+ (Ingles != null ? ", Ingles=" + Ingles : "")
				+ (Visible != null ? ", Visible=" + Visible : "")
				+ (Asignar != null ? ", Asignar=" + Asignar : "")
				+ (Plazas != null ? ", Plazas=" + Plazas : "")
				+ (titulacion != null ? ", titulacion=" + titulacion.getCodigo() : "");	
		StringJoiner sj = new StringJoiner(", ", "(",")");
		if(grupos != null) {
			res += ", Grupos=";
			for(Grupo g : grupos) {
				sj.add(g.getID() != null ? g.getID().toString() : "");
			}
			res += sj.toString();
		}
		
		
			res	+= (ID1 != null ? ", ID1=" + ID1 : "");        //DUDA
			
			
		StringJoiner sj2 = new StringJoiner(", ", "(",")");
		if(AsigMat != null) {
			res += ", Asignaturas_Matricula=";
			for(Asignaturas_Matricula a : AsigMat) {
				sj2.add(a.getId() != null ? a.getId().toString() : "");
			}
			res += sj2.toString();
		}		
		StringJoiner sj3 = new StringJoiner(", ", "(",")");
		if(clases != null) {
			res += ", Clases=";
			for(Clase c : clases) {
				sj3.add(c.getId() != null ? c.getId().toString() : "");
			}
			res += sj3.toString();
		}
		StringJoiner sj4 = new StringJoiner(", ", "(",")");
		if(GrAsig != null) {
			res += ", Grupos_por_Asignatura=";
			for(Grupos_por_Asignatura g : GrAsig) {
				sj4.add(g.getId() != null ? g.getId().toString() : "");
			}
			res += sj4.toString();
		}
		return res + "]";
	}


}
