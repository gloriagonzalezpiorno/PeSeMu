package dad.practica.pesemu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dad.practica.pesemu.model.Opinion;
import dad.practica.pesemu.model.Producto;



@Controller
public class OpinionController {
	
	@Autowired
	private OpinionRepository opinionRepository;
	
	//Vemos las opiniones de un producto
	@RequestMapping("/opinion/{id}")
	public String verOpinion(Model model, @PathVariable long id) {
		
		Opinion opinion = opinionRepository.findOne(id);
		
		model.addAttribute("opinion", opinion);
		
		return "ver_opiniones";
	}

	
	//Insertamos una opinion nueva
	@RequestMapping("/opinion/nueva")
	public String nuevaOpinion(Model model, Opinion opinion) {

		opinionRepository.save(opinion);

		return "opinion_guardada";

	}

}
