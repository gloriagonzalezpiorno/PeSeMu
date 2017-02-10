package dad.practica.pesemu;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.practica.pesemu.model.Usuario;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioServie;

	@PostMapping("usuario/nuevo")
	public String nuevoUsuario(Model model, Usuario usuario) {
		Usuario usuarioGuardado = usuarioServie.registrarUsuario(usuario);
		if (usuarioGuardado != null) {
			model.addAttribute("nombre", usuario.getNombre());
			return "usuario_registrado";
		} else {
			model.addAttribute("mensaje", "Error al registrar usuario");
			return "error_template";
		}
	}

	@PostMapping("inicio/sesion")
	public String inicioSesion(Model model, HttpSession sesion, @RequestParam String correo,
			@RequestParam String contrasena) {
		
		Usuario usuarioGuardado = usuarioServie.validarUsuario(correo, contrasena);
		if (usuarioGuardado != null) {
			sesion.setAttribute("infoUsuario", usuarioGuardado);
			model.addAttribute("nombre", usuarioGuardado.getNombre());
			return "sesion_iniciada";
		} else {
			model.addAttribute("mensaje", "Error al iniciar sesión");
			return "error_template";
		}
	}
}
