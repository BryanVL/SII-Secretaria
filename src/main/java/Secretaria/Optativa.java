package Secretaria;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Optativa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(precision=2, nullable=false)
	private double Plazas;
	@Column(length=30)
	private String Mencion;
	
	
	public double getPlazas() {
		return Plazas;
	}
	public void setPlazas(double plazas) {
		Plazas = plazas;
	}
	public String getMencion() {
		return Mencion;
	}
	public void setMencion(String mencion) {
		Mencion = mencion;
	}

	
}
