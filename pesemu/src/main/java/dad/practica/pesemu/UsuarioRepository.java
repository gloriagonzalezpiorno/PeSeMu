package dad.practica.pesemu;

import org.springframework.data.jpa.repository.JpaRepository;
import dad.practica.pesemu.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	Usuario findByCorreoAndContrasena(String correo, String contrasena);
	
}
