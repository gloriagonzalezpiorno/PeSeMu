package dad.practica.pesemu;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.practica.pesemu.model.Opinion;
import dad.practica.pesemu.model.Producto;
import dad.practica.pesemu.model.Usuario;


@Controller
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;
	
	//@Autowired
	//private OpinionRepository opinionRepository;
	
	
	@PostConstruct
	public void init() {
		
		Opinion opinion1 = new Opinion("Me ha gustado mucho");
		Opinion opinion2 = new Opinion("Es muy triste");
		
		Producto producto1=new Producto("Titanic", "Va sobre un barco", 10.99,"pelicula","accion");
		Producto producto2=new Producto("El quijote", "Un libro muy bonito", 6.0,"pelicula","comedia");
			
		
		producto1.getOpiniones().add(opinion1);
		producto1.getOpiniones().add(opinion2);
		
		productoRepository.save(producto1);
		productoRepository.save(producto2);
		
	}
	
	
	@GetMapping("catalogo")
	public String catalogo(Model model, @RequestParam String tipo) {
		model.addAttribute("tipo", tipo);
		switch (tipo) {
		case "pelicula":
			model.addAttribute("generos", Arrays.asList("accion", "comedia", "romanticas"));
			break;
		case "serie":
			model.addAttribute("generos", Arrays.asList("animadas", "documentales", "policiacas"));
			break;
		case "musica":
			model.addAttribute("generos", Arrays.asList("electronica", "pop", "rock"));
		}
		return "catalogo_generos";
	}
	
	@GetMapping("catalogo/{tipo}/{genero}")
	public String verProductos(Model model, @PathVariable String tipo, @PathVariable String genero) {
		model.addAttribute("productos", productoRepository.findByTipoAndGenero(tipo, genero));
		return "ver_productos";
	}
	
	
	@RequestMapping("catalogo/{tipo}/{genero}/{id}")
	public String verProducto(Model model, @PathVariable long id) {		
		model.addAttribute("producto", productoRepository.findOne(id));			
		return "ver_producto";
	}
	
	//TODO cambiar codigo
	
	//Insertamos un nuevo producto
	@RequestMapping("catalogo/nuevoProducto")
	public String nuevoProducto(Model model, Producto producto) {
		productoRepository.save(producto);
		return "producto_guardado";
	}
	
	//Vemos la opinion de un producto
	@RequestMapping("catalogo/{tipo}/{genero}/{id}/opinion")
	public String nuevoProducto(Model model,@PathVariable String tipo,@PathVariable String genero, @PathVariable long id) {
		model.addAttribute("tipo",tipo);
		model.addAttribute("genero",genero);
		model.addAttribute("id",id);
		return "nueva_opinion";
	}
	
	

	// Insertar producto en el carrito
	@RequestMapping("catalogo/{tipo}/{genero}/{id}/aniadirCarrito")
	public String aniadirCarrito(Model model, @PathVariable long id, HttpSession sesion){
		Usuario usuario = (Usuario) sesion.getAttribute("infoUsuario");
		usuario.getCarrito().anadirProducto(productoRepository.findOne(id));
		return "producto_aniadido_carrito";
	}
	
	//Insertamos una nueva opinión
		@RequestMapping("catalogo/{tipo}/{genero}/{id}/opinion/nueva")
		public String nuevoProducto(Model model, @PathVariable String tipo,@PathVariable String genero,@PathVariable long id, Opinion opinion) {
			
			Producto producto=productoRepository.findOne(id);
			producto.getOpiniones().add(opinion);
			productoRepository.save(producto);
			return "opinion_guardada";
		}
	
	
	
	


	
	

}




	






