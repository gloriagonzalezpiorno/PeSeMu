package dad.practica.pesemu;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Service;

import dad.practica.pesemu.model.CarritoCompra;
import dad.practica.pesemu.model.Factura;

@Service
public class FacturaService {

	// TODO
	// Recibe compra como parametro
	// deberia recibir el id de la compra
	// hacer una consulta a la BBDD (apoyandose en el servicio)
	public void crearFactura(CarritoCompra compra) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("factura.txt"));
			pw.print(new Factura(compra));
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
