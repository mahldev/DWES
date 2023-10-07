package org.iesbelen.tiendainformatica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="PRODUCTOS")
public class Producto implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	@NotNull
	private String nombre;
	@NotNull
	private double precio;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idFabricante", nullable = false)
	private Fabricante fabricante;


	public Producto() {
	}

	public Producto(Fabricante fabricante, String nombre, double precio) {
		this.fabricante = fabricante;
		this.nombre = nombre;
		this.precio = precio;
	}

	public Integer getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Fabricante getFabricante() {
		return this.fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", fabricante=" + fabricante.getNombre() + ", nombre=" + nombre + ", precio=" + precio
				+ "]";
	}
}
