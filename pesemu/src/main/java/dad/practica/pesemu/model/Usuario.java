package dad.practica.pesemu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;//para el carrito

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nombre;
	private String apellidos;

	// Se logeara con su correo y su contraseña
	private String correo;
	private String contrasena;
	private float saldo;

	@OneToOne(cascade = CascadeType.ALL)
	private CarritoCompra carrito;

	
	public Usuario() {

	}

	public Usuario(String nombre, String apellidos, String correo, String contraseña) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.contrasena = contraseña;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public CarritoCompra getCarrito() {
		return carrito;
	}

	public void setCarrito(CarritoCompra carrito) {
		this.carrito = carrito;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", correo=" + correo
				+ ", contrasena=" + contrasena + ", carrito=" + carrito + "]";
	}


}
