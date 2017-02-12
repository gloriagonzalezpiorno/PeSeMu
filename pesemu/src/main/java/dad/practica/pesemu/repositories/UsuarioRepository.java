package dad.practica.pesemu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import dad.practica.pesemu.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByCorreo(String correo);

	Usuario findByCorreoAndContrasena(String correo, String contrasena);

}
