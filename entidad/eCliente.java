package entidad;

public class eCliente {
	private String IdCliente;
	private String Dni;
	private String Nombres;
	private String Apellidos;
	private String Telefono;
	private String Direccion;
	private String Estado;
	
	public eCliente() {
	}

	public eCliente(String idCliente, String dni, String nombres, String apellidos, String telefono, String direccion,
			String estado) {
		IdCliente = idCliente;
		Dni = dni;
		Nombres = nombres;
		Apellidos = apellidos;
		Telefono = telefono;
		Direccion = direccion;
		Estado = estado;
	}

	public String getIdCliente() {
		return IdCliente;
	}

	public void setIdCliente(String idCliente) {
		IdCliente = idCliente;
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

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
	
	
}
