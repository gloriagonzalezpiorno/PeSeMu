package dad.practica.pesemu;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.practica.pesemu.model.CarritoCompra;
import dad.practica.pesemu.model.Usuario;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioServie;
	
	@PostConstruct
	public void init() {
		Usuario usuario = new Usuario("a", "a", "a", "a");
		CarritoCompra carrito = new CarritoCompra();
		carrito.setProductos(new ArrayList<>());
		usuario.setCarrito(carrito);
		usuario.setSaldo(100);
		usuarioServie.registrarUsuario(usuario);
	}

	@PostMapping("usuario/nuevo")
	public String nuevoUsuario(Model model, Usuario usuario) {
		Usuario usuarioGuardado = usuarioServie.registrarUsuario(usuario);
		if (usuarioGuardado != null) {
			model.addAttribute("nombre", usuario.getNombre());
			return "usuario_registrado";
		} else {
			model.addAttribute("mensaje", "Error al registrar usuario");
			return "vista_error";
		}
	}

	@PostMapping("inicio/sesion")
	public String inicioSesion(Model model, HttpSession sesion, @RequestParam String correo,
			@RequestParam String contrasena) {
		Usuario usuarioGuardado = usuarioServie.validarUsuario(correo, contrasena);
		if (usuarioGuardado != null) {
			sesion.setAttribute("idUsuario", usuarioGuardado.getId());
			model.addAttribute("nombre", usuarioGuardado.getNombre());
			return "sesion_iniciada";
		} else {
			model.addAttribute("mensaje", "Error al iniciar sesión");
			return "vista_error";
		}
	}
}
