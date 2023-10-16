package org.iesbelen.streams.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.iesbelen.streams.entity.Departamento;

@Entity
@Table(name="empleado")
public class Empleado implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	@NotNull
	private String nif;
	@NotNull
	private String nombre;
	@NotNull
	private String apellido1;
	private String apellido2;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_departamento", referencedColumnName = "codigo", insertable = false, updatable = false)
	private Departamento departamento;

	
	public Empleado() {
	}

	public Empleado(String nif, String nombre, String apellido1, String apellido2) {
		this.nif = nif;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}

	public Empleado(Departamento departamento, String nif, String nombre, String apellido1, String apellido2) {
		this.departamento = departamento;
		this.nif = nif;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	
	public String toString() {
		return "Empleado [codigo=" + codigo + ", NombreDepartamento=" + departamento + ", nif=" + nif + ", nombre=" + nombre
				+ ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + "]";
	}

}
