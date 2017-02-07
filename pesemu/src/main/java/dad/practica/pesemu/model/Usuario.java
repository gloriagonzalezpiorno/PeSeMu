package dad.practica.pesemu.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;//para las opiniones
import javax.persistence.OneToOne;//para el carrito

@Entity
public class Usuario {

	// Nivel de privilegio (1 admin) (0 cliente)
	// private int privilegio;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nombre;
	private String apellidos;

	// Se logeara con su correo y su contraseña
	private String correo;
	private String contrasena;
	
	@OneToMany(cascade=CascadeType.ALL)
	//lista de opiniones de cada producto
	private List<Opinion> opiniones=new ArrayList<>();
	// TODO faltan gets y sets del carrito
	@OneToOne(cascade=CascadeType.ALL)
	private CarritoCompra carrito=new CarritoCompra();

	public Usuario() {
		
	}
	
	public Usuario(String nombre, String apellidos, String correo, String contraseña) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.contrasena = contraseña;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return nombre + " " + apellidos;
	}

}
