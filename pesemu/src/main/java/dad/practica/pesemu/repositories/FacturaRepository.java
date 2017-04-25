package dad.practica.pesemu.repositories;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import dad.practica.pesemu.model.Factura;

@CacheConfig(cacheNames="facturasCache")
public interface FacturaRepository extends JpaRepository<Factura, Long> {
	
	@CacheEvict
	Factura save(Factura factura);
	
	@Cacheable
	Factura findOne(long id);

}
