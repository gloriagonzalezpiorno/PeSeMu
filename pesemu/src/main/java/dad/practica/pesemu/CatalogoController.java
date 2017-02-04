package dad.practica.pesemu;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatalogoController {

	@GetMapping("catalogo")
	public String catalogo(Model model, @RequestParam String tipo) {
		model.addAttribute("tipo", tipo);
		switch (tipo) {
		case "Películas":
			model.addAttribute("generos", Arrays.asList("Acción", "Comedias", "Románticas"));
			break;
		case "Series":
			model.addAttribute("generos", Arrays.asList("Animadas", "Documentales", "Policiacas"));
			break;
		case "Música":
			model.addAttribute("generos", Arrays.asList("Electrónica", "Pop", "Rock"));
		}
		return "catalogo_tipos";
	}
}
