package dad.practica.pesemu;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.practica.pesemu.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

	Producto findByNombre(String name);
	
}

