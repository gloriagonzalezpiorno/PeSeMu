package dad.practica.pesemu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dad.practica.pesemu.model.Opinion;
import dad.practica.pesemu.model.Producto;

@Controller
public class OpinionController {

	@Autowired
	ProductoRepository productoRepository;
	
	// Vemos la opinion de un producto
	@RequestMapping("catalogo/{tipo}/{genero}/{id}/opinion")
	public String nuevaOpinion(Model model, @PathVariable String tipo, @PathVariable String genero,
			@PathVariable long id) {
		model.addAttribute("tipo", tipo);
		model.addAttribute("genero", genero);
		model.addAttribute("id", id);
		return "nueva_opinion";
	}

	// Insertamos una nueva opinión
	@RequestMapping("catalogo/{tipo}/{genero}/{id}/opinion/nueva")
	public String guardaOpinion(Model model, @PathVariable long id, Opinion opinion) {
		Producto producto = productoRepository.findOne(id);
		producto.getOpiniones().add(opinion);
		productoRepository.save(producto);
		return "opinion_guardada";
	}

}
