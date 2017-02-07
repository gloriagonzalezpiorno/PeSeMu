package dad.practica.pesemu;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dad.practica.pesemu.model.Opinion;
import dad.practica.pesemu.model.Producto;


@Controller
public class ProductoController {
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private OpinionRepository opinionRepository;
	
	
	@PostConstruct
	public void init() {
		
		Opinion opinion1 = new Opinion("Me ha gustado mucho");
		Opinion opinion2 = new Opinion("Es muy triste");
		
		//opinionRepository.save(opinion1);
		//opinionRepository.save(opinion2);
		
		Producto producto1=new Producto("Titanic", "Va sobre un barco", 10.99);
		Producto producto2=new Producto("El quijote", "Un libro muy bonito", 6.0);
		
		
		
		producto1.getOpiniones().add(opinion1);
		producto1.getOpiniones().add(opinion2);
		
		productoRepository.save(producto1);
		productoRepository.save(producto2);
		
	}
	
	//Vemos los productos que tenemos en la base de datos
	//TENGO QUE CONSEGUIR MOSTRAR LAS OPINIONES--------------------------------------
	@RequestMapping("/")
	public String tablon(Model model, Pageable page) {
		model.addAttribute("productos", productoRepository.findAll(page));
		return "ver_productos"; 
	}
	
	
	//Insertamos un nuevo producto
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




	






