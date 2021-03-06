package dad.practica.pesemu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import dad.practica.pesemu.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

	List<Producto> findByTipoAndGenero(String tipo, String genero);

}
