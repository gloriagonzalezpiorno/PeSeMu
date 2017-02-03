package dad.practica.pesemu;

import org.springframework.data.jpa.repository.JpaRepository;


import dad.practica.pesemu.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByNombre(String name);
}



