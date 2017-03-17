package dad.practica.pesemu.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import dad.practica.pesemu.model.Factura;
import dad.practica.pesemu.model.Producto;
import dad.practica.pesemu.model.Usuario;
import dad.practica.pesemu.repositories.FacturaRepository;
import dad.practica.pesemu.repositories.ProductoRepository;
import dad.practica.pesemu.repositories.UsuarioRepository;

@Controller
public class CarritoCompraController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private FacturaRepository facturaRepository;

	// Insertar producto en el carrito del usuario
	@RequestMapping("catalogo/{tipo}/{genero}/{id}/aniadirACarrito")
	public String aniadirACarrito(Model model, @PathVariable long id, HttpSession sesion) {
		Object objId = sesion.getAttribute("idUsuario");
		if (objId != null) {
			Usuario usuario = usuarioRepository.findOne((long) objId);
			usuario.getCarrito().aniadirProducto(productoRepository.findOne(id));
			usuarioRepository.save(usuario);
			model.addAttribute("operacion", "añadido al carrito");
			return "producto_operacion";
		} else {
			model.addAttribute("mensaje", "Debes registrarte para añadir un producto al carrito");
			return "fallo";
		}
	}

	// Eliminar producto del carrito del usuario
	@RequestMapping("carrito/{id}/eliminarDeCarrito")
	public String eliminarDeCarrito(Model model, @PathVariable long id, HttpSession sesion) {
		Object objId = sesion.getAttribute("idUsuario");
		if (objId != null) {
			Usuario usuario = usuarioRepository.findOne((long) objId);
			usuario.getCarrito().eliminarProducto(productoRepository.findOne(id));
			usuarioRepository.save(usuario);
			model.addAttribute("operacion", "eliminado del carrito");
			return "producto_operacion";
		} else {
			model.addAttribute("mensaje", "Debes registrarte para eliminar un producto del carrito");
			return "fallo";
		}
	}

	// Ver productos del carrito
	@RequestMapping("carrito")
	public String verCarrito(Model model, HttpSession sesion) {
		Object objId = sesion.getAttribute("idUsuario");
		if (objId != null) {
			Usuario usuario = usuarioRepository.findOne((long) objId);
			model.addAttribute("nombreUsuario", usuario.getNombre());
			model.addAttribute("productos", usuario.getCarrito().getProductos());
			model.addAttribute("costeTotal", usuario.getCarrito().getCosteTotal());
			model.addAttribute("saldoUsuario", usuario.getSaldo());
			return "carrito";
		} else {
			model.addAttribute("mensaje", "Debes registrarte para ver tu carrito de la compra");
			return "fallo";
		}
	}

	// Comprar productos del carrito
	@RequestMapping("comprar")
	public String finalizarCompra(Model model, HttpSession sesion) throws RestClientException, URISyntaxException {
		Object objId = sesion.getAttribute("idUsuario");
		if (objId != null) {
			Usuario usuario = usuarioRepository.findOne((long) objId);
			if (usuario.getCarrito().puedeComprar()) {
				usuario.getCarrito().finalizarCompra();
				Factura factura = new Factura(usuario.getCarrito());
				facturaRepository.save(factura);

				RestTemplate rest = new RestTemplate();

				String urlFactura = rest.postForObject(new URI("http://127.0.0.1:8080"), factura, String.class);

				// Utilizamos una lista auxiliar para guardar las referencias a
				// los productos
				List<Producto> productos = new ArrayList<>();
				productos.addAll(usuario.getCarrito().getProductos());
				for (Producto producto : productos) {
					// Eliminamos el producto de la lista "productos" del
					// carrito (OneToMany)
					usuario.getCarrito().getProductos().remove(producto);
					// Eliminamos el producto del repositorio de productos
					productoRepository.delete(producto);
				}
				// Reiniciamos el carrito
				usuario.getCarrito().reiniciarCarrito();
				// Actualizamos el usuario en su repositorio
				usuarioRepository.save(usuario);

				model.addAttribute("idFactura", factura.getId());
				model.addAttribute("urlFactura", urlFactura);
				return "compra_finalizada";
			} else {
				model.addAttribute("mensaje", "No se puede realizar la compra");
				return "fallo";
			}
		} else {
			model.addAttribute("mensaje", "Debes registrarte para ver tu carrito de la compra");
			return "fallo";
		}
	}

}
