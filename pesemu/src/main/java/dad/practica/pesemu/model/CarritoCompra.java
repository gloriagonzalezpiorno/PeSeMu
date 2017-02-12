package dad.practica.pesemu.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	private List<Producto> productos = new ArrayList<>();

	@OneToOne(mappedBy = "carrito")
	private Usuario usuario;

	private float costeTotal;
	private String fecha;

	public CarritoCompra() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public float getCosteTotal() {
		return costeTotal;
	}

	public void setCosteTotal(float precioTotal) {
		this.costeTotal = precioTotal;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void aniadirProducto(Producto producto) {
		productos.add(producto);
		costeTotal = Float.sum(costeTotal, producto.getPrecio());
	}
	
	public void eliminarProducto(Producto producto) {
		productos.remove(producto);
		costeTotal = Float.sum(costeTotal, -producto.getPrecio());
	}

	public boolean puedeComprar() {
		return (costeTotal <= usuario.getSaldo()) && (!productos.isEmpty());
	}

	public void finalizarCompra() {
		usuario.setSaldo(usuario.getSaldo() - costeTotal);
		fecha = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
	}

	public void reiniciarCarrito() {
		costeTotal = 0;
		productos.clear();
	}

	@Override
	public String toString() {
		return "CarritoCompra [id=" + id + ", productos=" + productos.toString() + ", usuario=" + usuario
				+ ", precioTotal=" + costeTotal + ", fecha=" + fecha + "]";
	}

}
