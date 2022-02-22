package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Usuario {

	
	/**
	 * Usando @ Size le indicamos que el tamaño minimo y máximo será el indicado
	 */
	@Size(min=4, max=8, message="Oh oh, la contraseña es inválida, debe tener mínimo 3 carácteres y máximo 6") 
	private String contrasenia;
	
	@NotEmpty 
	private String user;
	

	private String nombre;
	

	private String telefono;
	
	//@Email(message="Oh oh, el formato tiene que ser de tipo email") 
	private String email;
	
	
	private String direccion;
	
	
	private List <Pedidos> listaPedidos = new ArrayList<>();
	
	

	
	/**
	 * Constructor con parámetros
	 * @param contrasenia
	 * @param usuario
	 * @param nombre
	 * @param telefono
	 * @param email
	 * @param direccion
	 * @param listaPedidos
	 */
	public Usuario(String contrasenia, String usuario, String nombre, String telefono, @Email String email, String direccion,
			List<Pedidos> listaPedidos) {
		super();
		this.contrasenia = contrasenia;
		this.user = usuario;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.listaPedidos = listaPedidos;
	}
	
	/**
	 * Constructor sin lista de pedidos
	 * @param contrasenia
	 * @param usuario
	 * @param nombre
	 * @param telefono
	 * @param email
	 * @param direccion
	 */
	public Usuario(String contrasenia, String usuario, String nombre, String telefono, String email, String direccion) {
		super();
		this.contrasenia = contrasenia;
		this.user = usuario;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
	}


	/**
	 * Constructor vacío
	 */
	public Usuario() {}

	/**
	 * Constructor para probar usuario y contrasenia
	 * @param usuario
	 * @param contrasenia
	 */
	public Usuario (String usuario, String contrasenia, String nombre) {
		this.contrasenia= contrasenia;
		this.user= usuario;
		this.nombre= nombre;
	}
	
	/**
	 * Constructor para probar usuarios, contraseña y pedido
	 * @param usuario
	 * @param contrasenia
	 * @param listaPedidos
	 */
	public Usuario (String usuario, String contrasenia, List<Pedidos> listaPedidos) {
		this.contrasenia= contrasenia;
		this.user= usuario;
		this.listaPedidos = listaPedidos;
	}
	
	/**
	 * Método para añadir pedido, al añadirlo siempre nos lo pondrá en primera posición
	 * al indicarle que el indice sea el 0, de esa forma nos lo ordena por fecha, ya que
	 * siempre el último producto añadido tendrá la fecha más reciente.
	 * @param pedido
	 */
	public void addPedido(Pedidos pedido) {
		this.listaPedidos.add(0, pedido);
	}
	
	/**
	 * GETS Y SETS
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getUsuario() {
		return user;
	}

	public void setUsuario(String usuario) {
		this.user = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
	public List<Pedidos> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<Pedidos> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	/**
	 * ToString
	 */
	@Override
	public String toString() {
		return "El usuario: " + user + ", con contraseña: " + contrasenia + ", con nombre: " + nombre
				+ ", teléfono=" + telefono + ", email=" + email + " y dirección=" + direccion;
	}
	
	/**
	 * HASHCODE Y EQUALS
	 */
	@Override
	public int hashCode() {
		return Objects.hash(user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(user, other.user);
	}
	
	
	
	
	
	
	
	
	
}
