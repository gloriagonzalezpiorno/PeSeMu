package dad.practica.pesemu.model;

public class Factura {

	private String texto;

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
		sb.append("Precio total: " + compra.getPrecioTotal() + "\n");
		texto = sb.toString();
	}

	@Override
	public String toString() {
		return texto;
	}
	
}
