package dad.practica.pesemu.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class InicioController {

	@RequestMapping("/")
	public String ventanaPrincipal(Model model, HttpServletRequest request) {
		model.addAttribute("login",!request.isUserInRole("ADMIN") && !request.isUserInRole("USUARIO"));
		return "principal";
	}

	
}
