package dad.practica.pesemu.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import dad.practica.pesemu.model.Factura;


public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
