package dad.practica.pesemu.repositories;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dad.practica.pesemu.model.CarritoCompra;
import dad.practica.pesemu.model.Usuario;

@Component
public class DatabaseUsersLoader {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostConstruct
	private void initDatabase() {
		if (usuarioRepository.findByCorreo("usuario") == null) {
			Usuario user = new Usuario("usuario", "usuario", "usuario", "pass", "ROLE_USUARIO");
			user.setCarrito(new CarritoCompra());
			usuarioRepository.save(user);
		}
		if (usuarioRepository.findByCorreo("admin") == null) {
			Usuario admin = new Usuario("admin", "admin", "admin", "adminpass", "ROLE_USUARIO", "ROLE_ADMIN");
			admin.setCarrito(new CarritoCompra());
			usuarioRepository.save(admin);
		}
	}
}
