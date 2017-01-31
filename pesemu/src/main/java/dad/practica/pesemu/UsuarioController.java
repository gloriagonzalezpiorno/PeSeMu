package dad.practica.pesemu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import dad.practica.pesemu.model.Usuario;

@Controller
public class UsuarioController {

	//List<Usuario> usuarios = new ArrayList<>();
	
	@PostMapping("nuevoUsuario")
	public String adios(Model model, Usuario usuario) {
		//usuarios.add(usuario);
		return "usuario_registrado";
	}
}
