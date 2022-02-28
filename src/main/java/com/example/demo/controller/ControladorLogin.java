
package com.example.demo.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Pedidos;
import com.example.demo.model.Usuario;
import com.example.demo.service.PedidosService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;

@Controller
public class ControladorLogin {

	/**
	 * Inyectamos los servicios necesarios, producto, pedidos y usuario
	 */
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PedidosService pedidoService;
	@Autowired
	private ProductoService productoService;
	
	/**
	 * Guardamos en variables los redirect y el usuario, ya que SonarLint daba fallo
	 */
	private static String usuario = "usuario";
	private static String rediLogin = "redirect:/login";
	
	private static String rediListaPed = "redirect:/listapedidos";
	
	
	/**
	 * Guardamos en una variable el usuario para poder usarlo posteriormente y asi no repetirlo muchas veces
	 * para que SonarLint no de problemas.
	 */
	
	
	/**
	 * Inyectamos la sesión
	 */
	@Autowired
	 private HttpSession sesion;
	
	/**
	 * Creamos un usuario vacio al iniciar la aplicación
	 */
	 @GetMapping({"/login"})
	 public String newLoginUsuario(Model model) {
		 model.addAttribute(usuario, new Usuario());
		 return "login";
		 
	 }
	 

	 
	 /**
	  * Cuando no hay usuario nos redirige a la página del login nuevamente.
	  */
	 @PostMapping("/login/submit")
	 public String usuValido(@Valid @ModelAttribute("usuario") Usuario usu, Model model, BindingResult error) {
		 if(!usuarioService.hayUsuario(usu.getUsuario(), usu.getContrasenia()) || error.hasErrors()) {
			 return "login";
		 }else {
			 /**
			  * Cuando el usuario si exista con su contraseña, nos guardará en sesión el usuario, hay que llamar al service usuario ya que si no, me sale error.
			  */
			 this.sesion.setAttribute(usuario, this.usuarioService.getByUsuario(usu.getUsuario()));
			 return "/opciones";
		 }
	 }
	
	 /**
	  * Poder volver a la sección de opciones
	  */
	 @GetMapping ("/opciones")
	 public String irAOpciones () {
		 return "/opciones";
	 }
	 
	 /**
	  * Cuando el usuario quiere cerrar sesión, nos la invalida y nos redirige al login
	  */
	 @GetMapping({"/cerrar"})
		public String cerrarSesion() {
			this.sesion.invalidate();
			return rediLogin;
		}
	 
	 /**
	  * Cuando el usuario quiere listar sus productos nos llevará al html listapedidos.html
	  * En el caso de que el usuario no esté logeado (guardado en sesion) nos redigirá al login
	  */
	 @GetMapping({"/listapedidos"})
		public String listapedidos(Model model) {
		 	if(this.sesion.getAttribute(usuario)==null) {
		 		
		 		return rediLogin;
		 		
		 	}else {
		 		
		 		
		 		Usuario usu = (Usuario) this.sesion.getAttribute(usuario);
		 		
		 		model.addAttribute(usuario, usu);
		 		
		 		model.addAttribute("listaPedidos", this.pedidoService.getPedidosUsuario(usu));
		 		
		 		
		 		return "/listapedidos";
		 	}
			
	 }
	 
	 /**
	  * Cuando el usuario quiere realizar un nuevo pedido nos llevará al catalogo 
	  */
	 @GetMapping({"/catalogo"})
		public String catalogo(Model model) {
		 if(this.sesion.getAttribute(usuario)==null) {
		 		return rediLogin;
		 }else {
			 model.addAttribute("listaProductos", productoService.getAllProducts());
			 return "/catalogo";
		 }
	 }
	 
	 /**
	  * Método para registar el pedido que acaba de hacer el usuario y solitiar una confirmación de este
	  */
	 @PostMapping({"/catalogo/submit"})
	 public String hacerPedido (Model model, @RequestParam(required=false, name= "cantidad") Integer [] pedidos) {
		 int cont = 0;
		 
		 if(sesion.getAttribute(usuario)!=null) {
			 
			 for (int i = 0; i < pedidos.length; i++) {
				
				if(pedidos[i]>0){
					cont++;
				}
			 }
			 if(cont>0) {
				 this.pedidoService.addProducto(pedidos);
				 model.addAttribute("listaCantPro", this.pedidoService.getAllProdCant());
				 
				 model.addAttribute(usuario, sesion.getAttribute(usuario));
				 return "/resumen";
			}else {
				return "redirect:/catalogo";
			}
		 }
		 
		 return rediLogin;
	 }
	 
	 /**
	  * Método para mostar en la lista de pedidos el nuevo pedido realizado por el usuario
	  */
	 @PostMapping("/catalogo/listapedidos")
	 public String listarnuevoPedido(Model model, @RequestParam(required=false,value="envio") String envio, @RequestParam(required=false, value="direc") String direc
			 , @RequestParam(required=false, value="email") String email
			 , @RequestParam(required=false, value="tele") String tele) {
		 if(sesion.getAttribute(usuario) == null){
			 return rediLogin;
		 }
		 
		 else {
			 Usuario user = (Usuario) sesion.getAttribute(usuario);
			 this.usuarioService.addPedido(user, this.pedidoService.getAllProdCant(), envio, direc, tele, email);

			 model.addAttribute("listaDePedidos", this.pedidoService.getPedidosUsuario(user));
			 
			 return rediListaPed;
		 }
		 
	 }
	 
	 
	 /**
	  * Recibe una referencia de un pedido para poder posteriomente enviarlo a la edicion para
	  * allí poder editarlo
	  */
	 @GetMapping("/pedido/edicion/{ref}")
	 public String editarPedido(Model model, @PathVariable("ref") int ref) {
		 if(sesion.getAttribute(usuario) == null){
			 return rediLogin;
		 }else {
			 Usuario usu = (Usuario) sesion.getAttribute(usuario);
			 
			 Pedidos pedido = this.usuarioService.getByRef(ref, usu);
			 model.addAttribute(usuario, usu);			
			 model.addAttribute("cantPro", pedido.getListaProducto());
			 model.addAttribute("pedido", pedido);
			 
			 return "/edicion";
		 }
	 }
	
	 /**
	  * Método para poder editar un pedido, donde podremos editar la cantidad de productos, la dirección
	  * el telefono, el email, la dirección, y el tipo de envio elegido
	  */
	 @PostMapping("/edicion/submit/{ref}")
	 public String editarPedidoSubmit(
			 @PathVariable ("ref") int ref, @RequestParam (required=false, value="tele") String tele,
			 @RequestParam (required=false, value="direc") String direc, @RequestParam (required=false, value="cantidad") Integer [] cantidades,
			 @RequestParam (required=false, value="envio") String envio, @RequestParam (required=false, value="email") String email, Model model) {
		 
		 if(sesion.getAttribute(usuario) == null){
			 return rediLogin;
		 }else {
			 Usuario usu = (Usuario) sesion.getAttribute(usuario);
			 
			 this.pedidoService.editarPedido(ref, direc, tele, email, cantidades, envio, usu);
			 
			 Pedidos pedido = this.usuarioService.getByRef(ref, usu);
			 model.addAttribute("pedido", pedido);
			 model.addAttribute(usuario, usu);
			 model.addAttribute("cantPro", pedido.getListaProducto());
			 
			 return rediListaPed;
		 }
		 
	 }
	 
	 /**
	  * Borramos el pedido que nos indique el usuario de su lista de pedidos
	  */
	 @GetMapping("/pedido/borrado/{ref}")
	 public String eliminarPedido (Model model, @PathVariable int ref) {
		 if(sesion.getAttribute(usuario) == null){
			 return rediLogin;
		 }else {
			 Usuario usu = (Usuario) sesion.getAttribute(usuario);
			 
			 Pedidos pedido = this.usuarioService.getByRef(ref, usu);
			 this.usuarioService.borrarPedidoUsuario(pedido, usu);
			 
			 model.addAttribute("pedidos", this.pedidoService.getPedidosUsuario(usu));
			 
			 return rediListaPed;
		 }
	 }
	
}
