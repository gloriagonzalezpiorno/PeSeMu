package dad.practica.pesemu.model;



//@Entity
public class Producto {
	
	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_usuario;
	private String nombre;
	private String descripcion;
	private float precio;
	
	
	public Producto() {
	}
	
	public Producto(int id_usuario, String nombre, String descripcion, float precio) {
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}


	public int getId_usuario() {
		return id_usuario;
	}


	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
	
}
