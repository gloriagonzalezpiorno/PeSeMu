package dad.practica.pesemu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import dad.practica.pesemu.model.Producto;


@RestController
public class ProductoController {
	@Autowired
	private ProductoRepository productoRepository;
	
	@RequestMapping(value = "/productos", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addTeam(@RequestBody Producto producto) {

		
		productoRepository.save(producto);
		return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
	}


	@RequestMapping(value = "/producto/{productoName}", method = RequestMethod.GET)
	public Producto getTeam(@PathVariable("productoName") String productoName) {
		return productoRepository.findByNombre(productoName);
	}
	
	

}




	



