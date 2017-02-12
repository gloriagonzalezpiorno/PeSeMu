package dad.practica.pesemu.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Service;

import dad.practica.pesemu.model.CarritoCompra;
import dad.practica.pesemu.model.Factura;

@Service
public class FacturaService {

	public void crearFactura(CarritoCompra compra) {
		//escritura de datos
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("facturas/factura.txt"));
			pw.print(new Factura(compra));
			pw.flush();
			pw.close();
/*		//lectura de datos
			BufferedReader br=new BufferedReader(new FileReader("\factura.txt"));
			String s,s2=new String();
			while((s=br.readLine())!=null)
				s2+=s+"\n";
			System.out.println("Texto leido:"+"\n"+s2);
			br.close();*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
