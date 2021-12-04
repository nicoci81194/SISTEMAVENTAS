package entidad;

public class eTrabajador {
	private String IdTrabajador;
	private String Dni;
	private String Nombres;
	private String Apellidos;
	private String Celular;
	private String Acceso;
	private String Usuario;
	private String Estado;
		
	public eTrabajador() {
		
	}

	public eTrabajador(String idTrabajador, String dni, String nombres, String apellidos, String celular, String acceso,
			String usuario, String estado) {
		IdTrabajador = idTrabajador;
		Dni = dni;
		Nombres = nombres;
		Apellidos = apellidos;
		Celular = celular;
		Acceso = acceso;
		Usuario = usuario;
		Estado = estado;
	}

	public String getIdTrabajador() {
		return IdTrabajador;
	}

	public void setIdTrabajador(String idTrabajador) {
		IdTrabajador = idTrabajador;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getNombres() {
		return Nombres;
	}

	public void setNombres(String nombres) {
		Nombres = nombres;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public String getCelular() {
		return Celular;
	}

	public void setCelular(String celular) {
		Celular = celular;
	}

	public String getAcceso() {
		return Acceso;
	}

	public void setAcceso(String acceso) {
		Acceso = acceso;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
	
	
}
