package dad.practica.pesemu;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

//TODO pasar a ProductoController
@Controller
public class CatalogoController {

	@GetMapping("catalogo")
	public String catalogo(Model model, @RequestParam String tipo) {
		model.addAttribute("tipo", tipo);
		switch (tipo) {
		case "peliculas":
			model.addAttribute("generos", Arrays.asList("accion", "comedias", "romanticas"));
			break;
		case "series":
			model.addAttribute("generos", Arrays.asList("animadas", "documentales", "policiacas"));
			break;
		case "musica":
			model.addAttribute("generos", Arrays.asList("electronica", "pop", "rock"));
		}
		return "catalogo_tipos";
	}
	
	@GetMapping("{tipo}/{genero}")
	public String verProductos(Model model, @PathVariable String tipo, @PathVariable String genero) {
		return "ver_productos";
	}
}
