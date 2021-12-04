package entidad;

public class eDetalleVenta {
	private String IdDetalleVentas;
	private String IdVenta;
	private String IdProducto;
	private int Cantidad;
	private double PrecioVenta;
	
	public eDetalleVenta() {
	}

	public eDetalleVenta(String idDetalleVentas, String idVenta, String idProducto, int cantidad, double precioVenta) {
		IdDetalleVentas = idDetalleVentas;
		IdVenta = idVenta;
		IdProducto = idProducto;
		Cantidad = cantidad;
		PrecioVenta = precioVenta;
	}

	public String getIdDetalleVentas() {
		return IdDetalleVentas;
	}

	public void setIdDetalleVentas(String idDetalleVentas) {
		IdDetalleVentas = idDetalleVentas;
	}

	public String getIdVenta() {
		return IdVenta;
	}

	public void setIdVenta(String idVenta) {
		IdVenta = idVenta;
	}

	public String getIdProducto() {
		return IdProducto;
	}

	public void setIdProducto(String idProducto) {
		IdProducto = idProducto;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}

	public double getPrecioVenta() {
		return PrecioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		PrecioVenta = precioVenta;
	}
	
	
}
