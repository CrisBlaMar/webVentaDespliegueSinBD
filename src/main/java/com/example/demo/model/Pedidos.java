package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pedidos {

	private int referencia;
	protected static int contador = 0;
	private String direccionEnvio;
	private Date fecha;
	private String envio;
	private String telefono;
	private String email;
	private double total;
	private Map <Producto, Integer> listaProducto = new HashMap <>();

	
	
	/**
	 * Constructor vacio
	 */
	public Pedidos () {}

	/**
	 * Constructor que le pasamos solo la referencia
	 * @param referencia
	 */
	public Pedidos(int referencia) {
		this.referencia= referencia;
	}
	
	/**
	 * Constructor al que le pasamos el mapa, la direccion y el envio
	 * @param listaProducto
	 * @param direccionEnvio
	 * @param envio
	 */
	public Pedidos(Map<Producto, Integer> listaProducto, String direccionEnvio, String envio, String telefono, String email) {
		super();
		//id automático e único
		this.referencia = referencia + contador;
		contador++;
		this.direccionEnvio = direccionEnvio;
		this.fecha= new Date();
		this.listaProducto = listaProducto;
		this.envio=envio;
		this.email = email;
		this.telefono = telefono;
	}
	
	
	/**
	 * Gets y Sets
	 * @return
	 */
	public int getReferencia() {
		return referencia;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public static int getContador() {
		return contador;
	}

	public static void setContador(int contador) {
		Pedidos.contador = contador;
	}

	
	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Map<Producto, Integer> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(Map<Producto, Integer> listaProducto) {
		this.listaProducto = listaProducto;
	}
	

	public String getEnvio() {
		return envio;
	}

	public void setEnvio(String envio) {
		this.envio = envio;
	}
	
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String formatoFecha() {
		return new SimpleDateFormat("dd-MM-yyyy HH:MM:ss").format(this.fecha);
	}
	

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * HashCode y Equals
	 */
	@Override
	public int hashCode() {
		return Objects.hash(referencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedidos other = (Pedidos) obj;
		return referencia == other.referencia;
	}

	/**
	 * ToString
	 */
	@Override
	public String toString() {
		return "Pedidos [referencia=" + referencia +  ", direccionEnvio=" + direccionEnvio
				+ ", listaProducto=" + listaProducto;
	}
	
	
	
	
}
