package org.iesbelen.streams.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="departamento")
public class Departamento implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	@NotNull
	private String nombre;
	@NotNull
	private double presupuesto;
	@NotNull
	private double gastos;
	@OneToMany(mappedBy = "departamento",fetch = FetchType.EAGER)
	private Set<Empleado> empleados = new HashSet<>(0);

	public Departamento() {
	}

	public Departamento(String nombre, double presupuesto, double gastos) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.gastos = gastos;
	}

	public Departamento(String nombre, double presupuesto, double gastos, Set<Empleado> empleados) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.gastos = gastos;
		this.empleados = empleados;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPresupuesto() {
		return this.presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public double getGastos() {
		return this.gastos;
	}

	public void setGastos(double gastos) {
		this.gastos = gastos;
	}

	public Set<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(Set<Empleado> empleados) {
		this.empleados = empleados;
	}

	public String toString() {
		return "Departamento [codigo=" + codigo + ", nombre=" + nombre + ", presupuesto=" + presupuesto + ", gastos="
				+ gastos + "]";
	}
}
