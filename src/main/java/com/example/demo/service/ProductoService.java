package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;

@Service
public class ProductoService {

	
	private List <Producto> productos = new ArrayList <>();
	
	
	/**
	 * Añadimos productos 
	 */
	@PostConstruct
	public void init() {
		productos.addAll(Arrays.asList(new Producto("Peineta de buganvillas", 15.5),
				new Producto("Corona de flores blancas", 25.14),
				new Producto("Tocado flores secas", 10.50),
				new Producto("Par de horquillas de pétalos secos", 5.99),
				new Producto("Diadema de amapolas", 10.00)));
	}
	
	/**
	 * Método para devolver todos los productos
	 */
	public List<Producto> getAllProducts() {
		return productos;
	}
	
}
