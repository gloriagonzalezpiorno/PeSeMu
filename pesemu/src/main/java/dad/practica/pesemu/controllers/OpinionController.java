package dad.practica.pesemu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dad.practica.pesemu.model.Opinion;
import dad.practica.pesemu.model.Producto;
import dad.practica.pesemu.repositories.ProductoRepository;

@Controller
public class OpinionController {

	@Autowired
	ProductoRepository productoRepository;

	// Devuelve un formulario al que le pasa la información del producto a
	// comentar
	@RequestMapping("catalogo/{tipo}/{genero}/{id}/opinion")
	public String nuevaOpinion(Model model, @PathVariable String tipo, @PathVariable String genero,
			@PathVariable long id) {
		model.addAttribute("tipo", tipo);
		model.addAttribute("genero", genero);
		model.addAttribute("id", id);
		return "nueva_opinion";
	}

	// Insertamos una nueva opinión
	// Encuentra el producto sobre el que se va a opinar, le añade la nueva
	// opinion y lo actualiza en la tabla de la bbdd
	@RequestMapping("catalogo/{tipo}/{genero}/{id}/opinion/nueva")
	public String guardaOpinion(Model model, @PathVariable long id, Opinion opinion) {
		Producto producto = productoRepository.findOne(id);
		producto.getOpiniones().add(opinion);
		productoRepository.save(producto);
		return "opinion_guardada";
	}

}
