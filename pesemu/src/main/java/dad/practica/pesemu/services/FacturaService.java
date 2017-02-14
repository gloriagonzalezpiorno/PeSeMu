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
	
/*	public String leerFicheroFactura(long id) {
		//escritura de datos
		StringBuilder sb=new StringBuilder();
		try {	
			BufferedReader br = new BufferedReader(new FileReader("facturas/factura.txt"));
			String linea;
			while((linea=br.readLine())!=null){
				sb.append(linea+"\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}*/
}
