package dad.practica.pesemu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Factura {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String titulo;
	private String nombreUsuario;
	private String correo;
	private String fecha;
	private String productos;
	

	public Factura() {
	
	}

	public Factura(CarritoCompra compra) {
		StringBuilder sb = new StringBuilder();
		titulo = "------- Compra PeSeMu ------\n";
		nombreUsuario = "Usuario: " + compra.getUsuario().getNombre() + "\n";
		correo = "Correo: " + compra.getUsuario().getCorreo() + "\n";
		fecha = "Fecha: " + compra.getFecha() + "\n";
		sb.append("Productos: \n");
		for (Producto producto : compra.getProductos()) {
			sb.append("\t" + producto.getNombre() + ":\t" + producto.getPrecio() + "\n");
		}
		sb.append("Precio total: " + compra.getCosteTotal() + "\n");
		productos = sb.toString();
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getProductos() {
		return productos;
	}

	public void setProductos(String texto) {
		this.productos = texto;
	}

	@Override
	public String toString() {
		return titulo + nombreUsuario + correo + fecha + productos;
	}

}
