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
	private String texto;
	

	public Factura() {
	
	}

	public Factura(CarritoCompra compra) {
		StringBuilder sb = new StringBuilder();
		sb.append("------- Compra PeSeMu ------\n");
		sb.append("Usuario: " + compra.getUsuario().getNombre() + "\n");
		sb.append("Correo: " + compra.getUsuario().getCorreo() + "\n");
		sb.append("Fecha: " + compra.getFecha() + "\n");
		sb.append("Productos: \n");
		for (Producto producto : compra.getProductos()) {
			sb.append("\t" + producto.getNombre() + ":\t" + producto.getPrecio() + "\n");
		}
		sb.append("Precio total: " + compra.getCosteTotal() + "\n");
		texto = sb.toString();
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return texto;
	}

}
