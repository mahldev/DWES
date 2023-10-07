package org.iesbelen.tiendainformatica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="FABRICANTES")
public class Fabricante implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFabricante;
	@NotNull
	private String nombre;

	@OneToMany(mappedBy = "fabricante",fetch = FetchType.EAGER)
	private Set<Producto> productos = new HashSet<>(0);

	public Fabricante() {
	}

	public Fabricante(String nombre) {
		this.nombre = nombre;
	}

	public Fabricante(String nombre, Set<Producto> productos) {
		this.nombre = nombre;
		this.productos = productos;
	}

	public Integer getIdFabricante() {
		return this.idFabricante;
	}

	public void setIdFabricante(Integer idFabricante) {
		this.idFabricante = idFabricante;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void addProducto(Producto producto) {
		productos.add(producto);
		producto.setFabricante(this);
	}
	public void removeProducto(Producto producto) {
		productos.remove(producto);
		producto.setFabricante(null);
	}

	public Set<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "Fabricante [idFabricante=" + idFabricante + ", nombre=" + nombre + "]";
	}
	
}
