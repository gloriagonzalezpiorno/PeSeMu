package dad.practica.pesemu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PesemuController {

	@RequestMapping("/adios")
	public String adios(Model model) {
		return "adios_template";
	}
}
