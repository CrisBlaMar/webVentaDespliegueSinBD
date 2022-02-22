package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pedidos;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;

@Service
public class PedidosService {
	
	/**
	 * Inyectamos el producto y usuario service.
	 */
	@Autowired
	ProductoService productoService;
	@Autowired
	UsuarioService usuarioService;
	
	private Map <Producto, Integer> productoCantidad = new HashMap <>();
	
	
	private List <Producto> listaproductos= new ArrayList <>();
	
	/**
	 * Devuelve todos los pedidos de un usuario
	 * @return
	 */
	public List<Pedidos> getPedidosUsuario (Usuario usuario) {
		
		return usuario.getListaPedidos();
	}

	/**
	 * Metemos unos productos con unas cantidades para probar
	 */
	public void init() {
		this.listaproductos = productoService.getAllProducts();
		productoCantidad.put(listaproductos.get(0),2);
		productoCantidad.put(listaproductos.get(1), 3);
		productoCantidad.put(listaproductos.get(2), 1);
		productoCantidad.put(listaproductos.get(3), 1);
		productoCantidad.put(listaproductos.get(4), 3);
	}
	
	
	
	/**
	 * Método para añadir productos con sus cantidades correspondientes indicadas
	 */
	public void addProducto(Integer [] cantidad) {
		
		List<Producto> productos = productoService.getAllProducts();
		Map <Producto, Integer> prodCant = new HashMap <>();
		
		for (int i = 0; i < cantidad.length; i++) {
			prodCant.put(productos.get(i), cantidad[i]);
		}
		this.productoCantidad =  prodCant;
		
	}

	/**
	 * Devolvemos los productos con su cantidad
	 */
	public Map<Producto, Integer> getAllProdCant(){
		return this.productoCantidad;
	}
	
	public double precioTotal() {
		double total = 0.0;
		
		for(Map.Entry<Producto, Integer> producto : this.productoCantidad.entrySet() ) {
			
			total += producto.getKey().getPrecio()*producto.getValue();
		}
		
		return total;
	}
	

	/**
	 * Método para editar los pedidos de un usuario
	 * @param refe
	 * @param email
	 * @param telefono
	 * @param direccion
	 * @param cantidades
	 * @param envio
	 * @param usuario
	 */
	public void editarPedido(int refe, String direccion, String telefono,  String email, Integer[] cantidades, String envio, Usuario usuario) {
		
		Pedidos pedido = usuarioService.getByRef(refe, usuario);

		pedido.setDireccionEnvio(direccion);
		pedido.setEmail(email);
		pedido.setTelefono(telefono);
		pedido.setEnvio(envio);
		
		Map<Producto, Integer> proCant = new HashMap<>();
		List<Producto> listaprod = productoService.getAllProducts(); 
		
		for(int i=0; i<cantidades.length; i++) {
			proCant.put(listaprod.get(i), cantidades[i]);
		}
		
		pedido.setListaProducto(proCant);
		
	}
	

}
