package dad.practica.pesemu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dad.practica.pesemu.model.Factura;
import dad.practica.pesemu.repositories.FacturaRepository;

@Controller
public class FacturaController {
	
	@Autowired
	private FacturaRepository facturaRepository;

	@RequestMapping("/leerFactura/{id}")
	public String leerFactura(Model model, @PathVariable long id){
		Factura factura=facturaRepository.findOne(id);
		model.addAttribute("contenidoFactura",factura.getTexto());
		return "leer_factura";
	}
}
