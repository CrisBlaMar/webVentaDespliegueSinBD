package com.example.demo.service;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.model.Pedidos;
import com.example.demo.model.Producto;

@Service
public class UsuarioService {

	/**
	 * Inyectamos el PedidoSerivce
	 */
	@Autowired
	PedidosService pedidoService;
	
	private List <Usuario> usuarios = new ArrayList<>();
	
	
	/**
	 * Método para devolver todos los usuarios
	 */
	public List<Usuario> findAllUsuarios(){
		
		return usuarios;
	}
	
	/**
	 * Este método nos comprueba que existe el usuario con ese nombre de usuario.
	 * @param usuario
	 * @return nos devuelve el usuario que conincide con ese nombre
	 */
	public Usuario getByUsuario (String usuario) {
		
		Usuario resultado = null;
		
		boolean isEncontrado = false;
		int indice = 0;
		
		while(!isEncontrado && indice<usuarios.size()) {
			if(Objects.equals(usuarios.get(indice).getUsuario(), usuario)) {
				isEncontrado = true;
				resultado = usuarios.get(indice);
			}else {
				indice++;
			}
		}
		
		return resultado;	
	}
	
	/**
	 * Método para comprobar si existe un usuario con esa contraseña
	 * @param usuario
	 * @param contrasenia
	 * @return nos devuelve true o false en función de si lo encuentra o no
	 */
	public boolean hayUsuario (String usuario, String contrasenia) {
		 
		
		boolean resul= false;
		int indice= 0;
		
		while(!resul && indice<usuarios.size()) {
			if(usuarios.get(indice).getUsuario().equals(usuario) && usuarios.get(indice).getContrasenia().equals(contrasenia)) {
				resul= true;
			}else {
				indice++;
			}
		}
		return resul;
			
	}
	
	
	/**
	 * Añadimos usuarios a la lista de usuarios
	 */
	@PostConstruct
	public void init() {
		usuarios.addAll(Arrays.asList(new Usuario("cris123", "crisblamar", "Cristina", "634885823", "cris@gmail.com", "calle amapola"),
				new Usuario("cvc4811", "cvilchescaro", "Carlos", "683774653", "carlos@gmail.com", "calle tamarindo")));
	}
	
	/**
	 * Nos devuelve todos los pedidos que tiene asignados un usuario
	 */
	public List<Pedidos> getAllPedidosUsuario(Usuario usu){
		return usu.getListaPedidos();
	}
	
	
	/**
	 * Nos devuelve el pedido de un usuario por su referencia 
	 */
	public Pedidos getByRef(int ref, Usuario usu) {
		Pedidos pedido = null;
		
		for (int i = 0; i < usu.getListaPedidos().size(); i++) {
			if(usu.getListaPedidos().get(i).getReferencia() == ref) {
				pedido = usu.getListaPedidos().get(i);
			}
		}
		return pedido;
	}
	
	
	
	
	/**
	 * Método para añadir un pedido a un usuario.
	 */
	public void addPedido (Usuario usu, Map<Producto, Integer> cantpro, String envio, String direc, String telefono, String email) {
		
		Pedidos pedido = new Pedidos(cantpro, envio, direc, telefono, email);
		
		pedido.setDireccionEnvio(direc);
		pedido.setEnvio(envio);
		pedido.setEmail(email);
		pedido.setTelefono(telefono);
		pedido.setListaProducto(cantpro);
		
		
		usu.addPedido(pedido);

	}
	
	/**
	 * Método para eliminar un pedido de la lista de pedidos de un usuario
	 * @param pedido
	 * @param usu
	 */
	public void borrarPedidoUsuario (Pedidos pedido, Usuario usu) {
		usu.getListaPedidos().remove(pedido);
	}
}
