package dad.practica.pesemu.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.OneToOne;//para el carrito

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nombre;
	private String apellidos;

	// Se logeara con su correo y su contraseña
	private String correo;
	private String contraseniaHash;
	private float saldo;

	@OneToOne(cascade = CascadeType.ALL)
	private CarritoCompra carrito;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	public Usuario() {

	}

	public Usuario(String nombre, String apellidos, String correo, String contrasenia, String... roles) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.contraseniaHash = new BCryptPasswordEncoder().encode(contrasenia);
		this.roles = new ArrayList<>(Arrays.asList(roles));
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

	public String getContraseniaHash() {
		return contraseniaHash;
	}

	public void setContraseniaHash(String contrasena) {
		this.contraseniaHash = contrasena;
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", correo=" + correo
				+ ", contrasena=" + contraseniaHash + ", carrito=" + carrito + "]";
	}


}
