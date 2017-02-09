package dad.practica.pesemu.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CarritoCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToMany
	private List<Producto> productos; // TODO inicializar

	@OneToOne(mappedBy = "carrito")
	private Usuario usuario;

	private double precioTotal;
	private String fecha;

	public CarritoCompra() {
		// SpringData
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void anadirProducto(Producto producto) {
		productos.add(producto);
		precioTotal += producto.getPrecio();
	}

	public void cerrarCompra() {
		fecha = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
		// Eliminar productos de la base de datos
		// Eliminar saldo del cliente
	}

}
