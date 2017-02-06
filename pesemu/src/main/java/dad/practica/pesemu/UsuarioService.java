package dad.practica.pesemu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dad.practica.pesemu.model.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositorioUsuarios;

	public List<Usuario> getUsuarios() {
		return repositorioUsuarios.findAll();
	}

	public Usuario registrarUsuario(Usuario usuario) {
		if (repositorioUsuarios.findOne(usuario.getCorreo()) == null) {
			return repositorioUsuarios.save(usuario);
		} else {
			return null;
		}
	}

	public Usuario validarUsuario(String correo, String contrasena) {
		return repositorioUsuarios.findByCorreoAndContrasena(correo, contrasena);
	}

}
