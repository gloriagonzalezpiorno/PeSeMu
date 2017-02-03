package dad.practica.pesemu.model;

public class Usuario {

	// Nivel de privilegio (1 admin) (0 cliente)
	// private int privilegio;

	private String nombre;
	private String apellidos;

	// Se logeara con su correo y su contraseña
	private String correo;
	private String contraseña;

	public Usuario() {
		
	}
	
	public Usuario(String nombre, String apellidos, String correo, String contraseña) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.contraseña = contraseña;
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return nombre + " " + apellidos;
	}

}
