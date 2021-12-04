package entidad;

public class eProducto {
	
	private String IdProducto;
	private String Descripcion;
	private double Precio;
	private int Stock;
	private String Estado;
	
	public eProducto() {
	}

	public eProducto(String idProducto, String descripcion, double precio, int stock, String estado) {
		IdProducto = idProducto;
		Descripcion = descripcion;
		Precio = precio;
		Stock = stock;
		Estado = estado;
	}

	public String getIdProducto() {
		return IdProducto;
	}

	public void setIdProducto(String idProducto) {
		IdProducto = idProducto;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public double getPrecio() {
		return Precio;
	}

	public void setPrecio(double precio) {
		Precio = precio;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		Stock = stock;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
	
	
}
