package dad.practica.pesemu.controllers;


import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import dad.practica.pesemu.model.CarritoCompra;
import dad.practica.pesemu.model.Usuario;
import dad.practica.pesemu.repositories.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/registroUsuario")
	public String registroUsuario() {
		return "registro_usuario";
	}
	
	
	@GetMapping("/sesionIniciada")
	public String sesionIniciada() {
		return "sesion_iniciada";
	}
	
	
	// Registro de un nuevo usuario
	@PostMapping("usuarioNuevo")
	public String nuevoUsuario(Model model, Usuario usuario) {
		// Si no existe un usuario con el mismo correo, se registra
		if (usuarioRepository.findByCorreo(usuario.getCorreo()) == null) {
			usuario.setCarrito(new CarritoCompra());
			usuarioRepository.save(usuario);
			model.addAttribute("nombre", usuario.getNombre());
			return "usuario_registrado";
		} else {
			model.addAttribute("mensaje", "Error al registrar usuario");
			return "fallo";
		}
	}

/*	// Inicio de sesión de un usuario
	@PostMapping("inicioSesion")
	public String inicioSesion(Model model, HttpSession sesion, @RequestParam String correo,
			@RequestParam String contrasena) {
		Usuario usuarioGuardado = usuarioRepository.findByCorreoAndContrasena(correo, contrasena);
		if (usuarioGuardado != null) {
			sesion.setAttribute("idUsuario", usuarioGuardado.getId());
			model.addAttribute("nombre", usuarioGuardado.getNombre());
			return "sesion_iniciada";
		} else {
			model.addAttribute("mensaje", "Error al iniciar sesión");
			return "fallo";
		}
	}*/
	
	// Añadir saldo a la cuenta del usuario
	@RequestMapping("aniadirSaldo")
	public String aniadirSaldo(Model model, HttpSession sesion, @RequestParam float cantidad){
		Usuario usuario = usuarioRepository.findOne((long) sesion.getAttribute("idUsuario"));
		usuario.setSaldo(Float.sum(usuario.getSaldo(), cantidad));
		usuarioRepository.save(usuario);
		return "saldo_aniadido";
	}
}
