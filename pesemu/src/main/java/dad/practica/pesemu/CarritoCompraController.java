package dad.practica.pesemu;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dad.practica.pesemu.model.Producto;
import dad.practica.pesemu.model.Usuario;

@Controller
public class CarritoCompraController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	FacturaService facturaService;

	// Insertar producto en el carrito
	@RequestMapping("catalogo/{tipo}/{genero}/{id}/aniadirCarrito")
	public String aniadirCarrito(Model model, @PathVariable long id, HttpSession sesion) {
		Object objId = sesion.getAttribute("idUsuario");
		if (objId != null) {
			Usuario usuario = usuarioRepository.findOne((long) objId);
			usuario.getCarrito().aniadirProducto(productoRepository.findOne(id));
			usuarioRepository.save(usuario);
			return "producto_guardado";
		} else {
			model.addAttribute("mensaje", "Debes registrarte para añadir un producto al carrito");
			return "vista_fallo";
		}
	}

	@RequestMapping("carrito")
	public String verCarrito(Model model, HttpSession sesion) {
		Object objId = sesion.getAttribute("idUsuario");
		if (objId != null) {
			Usuario usuario = usuarioRepository.findOne((long) objId);
			model.addAttribute("nombreUsuario", usuario.getNombre());
			model.addAttribute("productos", usuario.getCarrito().getProductos());
			model.addAttribute("costeTotal", usuario.getCarrito().getCosteTotal());
			model.addAttribute("saldoUsuario", usuario.getSaldo());
			return "ver_carrito";
		} else {
			model.addAttribute("mensaje", "Debes registrarte para ver tu carrito de la compra");
			return "vista_error";
		}
	}

	@RequestMapping("comprar")
	public String finalizarCompra(Model model, HttpSession sesion) {
		Object objId = sesion.getAttribute("idUsuario");
		if (objId != null) {
			Usuario usuario = usuarioRepository.findOne((long) objId);
			if (usuario.getCarrito().cerrarCompra()) {
				for (Producto producto : usuario.getCarrito().getProductos()) {
					productoRepository.delete(producto.getId());
				}
				facturaService.crearFactura(usuario.getCarrito());
				return "compra_finalizada";
			} else {
				model.addAttribute("mensaje", "No tienes suficiente dinero en la cuenta");
				return "vista_fallo";
			}
		} else {
			model.addAttribute("mensaje", "Debes registrarte para ver tu carrito de la compra");
			return "vista_fallo";
		}
	}

}
