package dad.practica.pesemu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.practica.pesemu.model.Usuario;

@Controller
public class UsuarioController {

	List<Usuario> usuarios = new ArrayList<>();
	Map<String, String> usuarioContraseña = new HashMap<>();

	@PostMapping("nuevoUsuario")
	public String nuevoUsuario(Model model, Usuario usuario) {
		usuarios.add(usuario);
		usuarioContraseña.put(usuario.getCorreo(), usuario.getContraseña());
		model.addAttribute("usuarios", usuarios);
		return "usuario_registrado";
	}

	@PostMapping("inicioSesion")
	public String inicioSesion(Model model, @RequestParam String correo, @RequestParam String contraseña) {
		// comprobar que los datos son validos
		if(usuarioContraseña.get(correo).equals(contraseña)){
			model.addAttribute("correo", correo);
			return "sesion_iniciada";
		} else {
			return "error_inicio_sesion";
		}
	}
}
