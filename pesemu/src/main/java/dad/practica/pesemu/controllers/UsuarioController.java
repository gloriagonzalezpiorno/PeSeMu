package dad.practica.pesemu.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/loginerror")
	public String loginerror(Model model) {
		model.addAttribute("mensaje", "Error al iniciar sesión");
		return "fallo";
	}
	
	
	@GetMapping("/sesionIniciada")
	public String sesionIniciada(Model model, HttpSession sesion) {
		Usuario usuario = usuarioRepository
				.findByCorreo(SecurityContextHolder.getContext().getAuthentication().getName());
		sesion.setAttribute("idUsuario", usuario.getId());
		model.addAttribute("nombre", usuario.getNombre());
		return "sesion_iniciada";
	}

	@GetMapping("/registroUsuario")
	public String registroUsuario() {
		return "registro_usuario";
	}

	// Registro de un nuevo usuario
	@PostMapping("/usuarioNuevo")
	public String nuevoUsuario(Model model, @RequestParam String nombre, @RequestParam String apellidos,
			@RequestParam String correo, @RequestParam String contrasenia) {
		// Si no existe un usuario con el mismo correo, se registra
		if (usuarioRepository.findByCorreo(correo) == null) {
			Usuario usuario = new Usuario(nombre, apellidos, correo, contrasenia, "ROLE_USUARIO");
			usuario.setCarrito(new CarritoCompra());
			usuarioRepository.save(usuario);
			model.addAttribute("nombre", usuario.getNombre());
			return "usuario_registrado";
		} else {
			model.addAttribute("mensaje", "Error al registrar usuario");
			return "fallo";
		}
	}

	// Añadir saldo a la cuenta del usuario
	@GetMapping("/aniadirSaldo")
	public String aniadirSaldo(Model model, HttpSession sesion, @RequestParam float cantidad) {
		Usuario usuario = usuarioRepository.findOne((long) sesion.getAttribute("idUsuario"));
		usuario.setSaldo(Float.sum(usuario.getSaldo(), cantidad));
		usuarioRepository.save(usuario);
		return "saldo_aniadido";
	}
}
