package dad.practica.pesemu.model;

public class Usuario {
	
	// Nivel de privilegio (1 admin) (0 cliente)
	//private int privilegio;
	
	private String nombre;
	private String apellidos;
	
	// Se logeara con su correo y su contraseña
	private String correo;
	private String contraseña;
	
	public Usuario(String nombre, String apellidos, String correo, String contraseña) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.contraseña = contraseña;
	}
	

}
