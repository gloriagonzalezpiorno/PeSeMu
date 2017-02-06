package dad.practica.pesemu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.practica.pesemu.model.Usuario;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService servicioUsuarios;

	@PostMapping("nuevousuario")
	public String nuevoUsuario(Model model, Usuario usuario) {
		Usuario usuarioGuardado = servicioUsuarios.registrarUsuario(usuario);
		if (usuarioGuardado != null) {
			model.addAttribute("nombre", usuario.getNombre());
			return "usuario_registrado";
		} else {
			return "error_registro_usuario";
		}
	}

	@PostMapping("iniciosesion")
	public String inicioSesion(Model model, @RequestParam String correo, @RequestParam String contrasena) {
		Usuario usuarioGuardado = servicioUsuarios.validarUsuario(correo, contrasena);
		if (usuarioGuardado != null) {
			model.addAttribute("nombre", usuarioGuardado.getNombre());
			return "sesion_iniciada";
		} else {
			return "error_inicio_sesion";
		}
	}
}
