package dad.practica.pesemu.controllers;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.practica.pesemu.model.Opinion;
import dad.practica.pesemu.model.Producto;
import dad.practica.pesemu.repositories.ProductoRepository;

@Controller
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;

	@PostConstruct
	public void init() {

		Opinion opinion1 = new Opinion("Me ha gustado mucho");
		Opinion opinion2 = new Opinion("Es muy triste");

		Producto producto1 = new Producto("Titanic", "Va sobre un barco", (float) 10.99, "pelicula", "accion");
		Producto producto2 = new Producto("El quijote", "Un libro muy bonito", (float) 6.0, "pelicula", "comedia");

		producto1.getOpiniones().add(opinion1);
		producto1.getOpiniones().add(opinion2);

		productoRepository.save(producto1);
		productoRepository.save(producto2);

	}

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
	public String verProductos(Model model, @PathVariable String tipo, @PathVariable String genero) {
		model.addAttribute("productos", productoRepository.findByTipoAndGenero(tipo, genero));
		return "lista_productos";
	}

	// Devuelve una vista con toda la información de un producto
	@RequestMapping("catalogo/{tipo}/{genero}/{id}")
	public String verProducto(Model model, @PathVariable long id) {
		model.addAttribute("producto", productoRepository.findOne(id));
		return "producto";
	}

	// Insertamos un nuevo producto en la BBDD
	@RequestMapping("catalogo/nuevoProducto")
	public String nuevoProducto(Model model, Producto producto) {
		productoRepository.save(producto);
		return "producto_guardado";
	}

}
