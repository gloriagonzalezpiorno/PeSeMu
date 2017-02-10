package dad.practica.pesemu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dad.practica.pesemu.model.CarritoCompra;
import dad.practica.pesemu.model.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
	}

	public Usuario registrarUsuario(Usuario usuario) {
		if (usuarioRepository.findByCorreo(usuario.getCorreo()) == null) {
			usuario.setCarrito(new CarritoCompra());
			return usuarioRepository.save(usuario);
		} else {
			return null;
		}
	}

	public Usuario validarUsuario(String correo, String contrasena) {
		return usuarioRepository.findByCorreoAndContrasena(correo, contrasena);
	}

}
