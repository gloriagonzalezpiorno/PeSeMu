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
	Map<String, String> usuarioContrasena = new HashMap<>();
	Map<String, String> usuarioCorreoNombre = new HashMap<>();

	@PostMapping("nuevousuario")
	public String nuevoUsuario(Model model, Usuario usuario) {
		usuarios.add(usuario);
		usuarioContrasena.put(usuario.getCorreo(), usuario.getContrasena());
		usuarioCorreoNombre.put(usuario.getCorreo(), usuario.getNombre());
		model.addAttribute("usuarios", usuarios);
		return "usuario_registrado";
	}

	@PostMapping("iniciosesion")
	public String inicioSesion(Model model, @RequestParam String correo, @RequestParam String contrasena) {
		if (usuarioContrasena.containsKey(correo) && usuarioContrasena.get(correo).equals(contrasena)) {
			model.addAttribute("nombre", usuarioCorreoNombre.get(correo));
			return "sesion_iniciada";
		} else {
			return "error_inicio_sesion";
		}
	}
}
