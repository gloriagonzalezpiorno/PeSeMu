package dad.practica.pesemu;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import dad.practica.pesemu.model.Producto;

@Controller
public class ProductoController {
	
	@Autowired
	private ProductoRepository productoRepository;

	@PostConstruct
	public void init() {
		productoRepository.save(new Producto("Titanic", "Va sobre un barco", 10.99));
		productoRepository.save(new Producto("El quijote", "Un libro muy bonito", 6.0));
	}

	// Vemos los productos que tenemos en la base de datos
	@RequestMapping("/productos")
	public String tablon(Model model, Pageable page) {
		model.addAttribute("productos", productoRepository.findAll(page));
		return "ver_productos";
	}

	// Insertamos un nuevo producto
	@RequestMapping("/producto/nuevo")
	public String nuevoProducto(Model model, Producto producto) {
		productoRepository.save(producto);
		return "producto_guardado";
	}

	@RequestMapping("/producto/{id}")
	public String verProducto(Model model, @PathVariable long id) {
		Producto producto = productoRepository.findOne(id);
		model.addAttribute("producto", producto);
		return "ver_producto";
	}

}