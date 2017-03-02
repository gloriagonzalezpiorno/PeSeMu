package dad.practica.pesemu.controllers;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.practica.pesemu.model.Producto;
import dad.practica.pesemu.repositories.ProductoRepository;

@Controller
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;

	// Devuelve una vista con los distintos géneros del tipo de producto
	// seleccionado (película, serie o música)
	@RequestMapping("catalogo")
	public String catalogo(Model model, @RequestParam String tipo) {
		model.addAttribute("tipo", tipo);
		switch (tipo) {
		case "pelicula":
			model.addAttribute("generos", Arrays.asList("accion", "comedia", "romantica"));
			break;
		case "serie":
			model.addAttribute("generos", Arrays.asList("animada", "documental", "policiaca"));
			break;
		case "musica":
			model.addAttribute("generos", Arrays.asList("electronica", "pop", "rock"));
		}
		return "catalogo_generos";
	}

	// Devuelve una vista con una lista de todos los productos de un determinado
	// tipo y género
	@RequestMapping("catalogo/{tipo}/{genero}")
	public String verProductos(Model model, @PathVariable String tipo, @PathVariable String genero,HttpServletRequest request) {
		model.addAttribute("productos", productoRepository.findByTipoAndGenero(tipo, genero));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "lista_productos";
	}

	// Devuelve una vista con toda la información de un producto
	@RequestMapping("catalogo/{tipo}/{genero}/{id}")
	public String verProducto(Model model, @PathVariable long id,HttpServletRequest request) {
		model.addAttribute("producto", productoRepository.findOne(id));
		model.addAttribute("usuario", request.isUserInRole("ADMIN") || request.isUserInRole("USUARIO"));
		return "producto";
	}

	
	// Insertamos un nuevo producto en la BBDD
	@RequestMapping("catalogo/{tipo}/{genero}/aniadirProducto")
	public String aniadirProducto() {
		return "nuevo_producto";
	}
	
	
	// Insertamos un nuevo producto en la BBDD
	@RequestMapping("catalogo/nuevoProducto")
	public String nuevoProducto(Model model, Producto producto) {
		productoRepository.save(producto);
		model.addAttribute("operacion", "añadido al stock");
		return "producto_operacion";
	}
	
	
	
	
	
}
