package dad.practica.pesemu.services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Service;

import dad.practica.pesemu.model.Factura;

@Service
public class FacturaService {

	public void crearFicheroFactura(Factura factura) {
		//escritura de datos
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("facturas/factura.txt"));
			pw.print(factura);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
