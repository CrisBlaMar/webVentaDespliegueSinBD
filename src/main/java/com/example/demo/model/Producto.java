package com.example.demo.model;

import java.util.Objects;

public class Producto {

	private int id;
	protected static int contador = 0;
	
	private String nombre;
	private double precio;
	
	
	/**
	 * Constructor con parámetros
	 * @param id
	 * @param nombre
	 * @param precio
	 */
	public Producto(String nombre, double precio) {
		super();
		//id automático e único
		this.id = contador + id ;
		contador ++;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	/**
	 * Constructor vacio
	 */
	public Producto () {}

	
	/**
	 * Get y sets
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	

	/**
	 * ToString
	 */
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
	}

	
	/**
	 * HashCode y Equals
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return id == other.id;
	}
	
	
	
	
	
	
}
