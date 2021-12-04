package entidad;

public class eVenta {
	private String IdVenta;
	private String IdCliente;
	private String IdTrabajador;
	private String NumeroVenta;
	private String FechaVenta;
	private double Monto;
	private String Estado;
	
	public eVenta() {
	}
	
	public eVenta(String idVenta, String idCliente, String idTrabajador, String numeroVenta, String fechaVenta,
			double monto, String estado) {
		IdVenta = idVenta;
		IdCliente = idCliente;
		IdTrabajador = idTrabajador;
		NumeroVenta = numeroVenta;
		FechaVenta = fechaVenta;
		Monto = monto;
		Estado = estado;
	}
	public String getIdVenta() {
		return IdVenta;
	}
	public void setIdVenta(String idVenta) {
		IdVenta = idVenta;
	}
	public String getIdCliente() {
		return IdCliente;
	}
	public void setIdCliente(String idCliente) {
		IdCliente = idCliente;
	}
	public String getIdTrabajador() {
		return IdTrabajador;
	}
	public void setIdTrabajador(String idTrabajador) {
		IdTrabajador = idTrabajador;
	}
	public String getNumeroVenta() {
		return NumeroVenta;
	}
	public void setNumeroVenta(String numeroVenta) {
		NumeroVenta = numeroVenta;
	}
	public String getFechaVenta() {
		return FechaVenta;
	}
	public void setFechaVenta(String fechaVenta) {
		FechaVenta = fechaVenta;
	}
	public double getMonto() {
		return Monto;
	}
	public void setMonto(double monto) {
		Monto = monto;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	
		
}
