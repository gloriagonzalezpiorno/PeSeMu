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
	private void initDatabase(){
		Usuario user = new Usuario("nombre","apellidos","usuario","pass", "USUARIO");
		user.setCarrito(new CarritoCompra());
		usuarioRepository.save(user);
		Usuario admin = new Usuario("nombre","apellidos","admin","adminpass", "USUARIO", "ADMIN");
		admin.setCarrito(new CarritoCompra());
		usuarioRepository.save(admin);
	}
}
