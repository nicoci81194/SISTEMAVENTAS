package datos;

import entidad.eProducto;
import java.sql.*;
import java.util.ArrayList;

public class dProducto {
	
	private Conexion mysql = new Conexion();
	private Connection cn = mysql.conectar();
	private ArrayList<eProducto> miLista;
	
	//METODO LISTAR PRODUCTOS
	public ArrayList<eProducto> Listar(){
		String SQL = "call ListarProductos()";
		ResultSet Rs;
		Statement Cmd;
		miLista = new ArrayList<eProducto>();
		try {
			Cmd = cn.createStatement();
			Rs = Cmd.executeQuery(SQL);
			while(Rs.next()) {
				miLista.add(new eProducto(Rs.getString("IdProducto"),
					Rs.getString("Descripcion"),
					Rs.getDouble("Precio"),
					Rs.getInt("Stock"),
					Rs.getString("Estado")));
			}
			Rs.close();				
		} catch (Exception e) {
			System.out.println("ERROR LISTAR PRODUCTOS:" + e.getMessage());
		}
		return miLista;
	}
	
	//METODO BUSCAR PRODUCTO
	public eProducto Buscar(String Id){
		String SQL = "call BuscarProducto(?)";
		CallableStatement Stmt;
		ResultSet Rs;
		eProducto ObjP = null;
		try {
				Stmt = cn.prepareCall(SQL);
				Stmt.setString(1, Id);
				Rs = Stmt.executeQuery();
				if(Rs.next()) {
					ObjP = new eProducto(Rs.getString("IdProducto"),
							Rs.getString("Descripcion"),
							Rs.getDouble("Precio"),
							Rs.getInt("Stock"),
							Rs.getString("Estado"));
				}
				Rs.close();
		} catch (Exception e) {
				System.out.println("ERROR BUSCAR PRODUCTO:" + e.getMessage());
		}
		return ObjP;
	}
	
	//METODO INSERTAR PRODUCTO
	public void Insertar(eProducto ObjP){
		String SQL = "call InsertarProducto(?,?,?,?,?)";
		CallableStatement Stmt;
		try {
				Stmt = cn.prepareCall(SQL);
				Stmt.setString(1, ObjP.getIdProducto());
				Stmt.setString(2, ObjP.getDescripcion());
				Stmt.setDouble(3, ObjP.getPrecio());
				Stmt.setInt(4, ObjP.getStock());
				Stmt.setString(5, ObjP.getEstado());				
				Stmt.executeUpdate();
		} catch (Exception e) {
				System.out.println("ERROR INSERTAR PRODUCTO:" + e.getMessage());
		}
	}
	
	//METODO MODIFICAR PRODUCTO
	public void Modificar(eProducto ObjP){
		String SQL = "call ModificaProducto(?,?,?,?,?)";
		CallableStatement Stmt;
		try {
				Stmt = cn.prepareCall(SQL);
				Stmt.setString(1, ObjP.getIdProducto());
				Stmt.setString(2, ObjP.getDescripcion());
				Stmt.setDouble(3, ObjP.getPrecio());
				Stmt.setInt(4, ObjP.getStock());
				Stmt.setString(5, ObjP.getEstado());
				Stmt.executeUpdate();
		} catch (Exception e) {
				System.out.println("ERROR MODIFICAR PRODUCTO:" + e.getMessage());
		}
	}
	
	//METODO SUPRIMIR PRODUCTO
	public void Suprimir(String Id){
		String SQL = "call SuprimirProducto(?)";
		CallableStatement Stmt;
		try {
				Stmt = cn.prepareCall(SQL);
				Stmt.setString(1, Id);
				Stmt.executeUpdate();
		} catch (Exception e) {
				System.out.println("ERROR SUPRIMIR PRODUCTO:" + e.getMessage());
		}
	}
	
	//METODO PARA GENERAR CODIGO
	public String GeneraCodigoProducto() {
		String SQL = "call GenerarCodigoProducto()", nuevo = "";
		Statement Cmd;
		ResultSet Rs;
		try {
			Cmd = cn.createStatement();
			Rs = Cmd.executeQuery(SQL);
			if(Rs.next()) {
				nuevo = Rs.getString(1);
			}
			Rs.close();
		} catch (Exception e) {
			System.out.println("ERROR GENERAR CODIGO:" + e.getMessage());
		}
		return nuevo;
	}
	
	//ACTUALIZAR STOCK 
	public void actualizarStock(int cant, String idp){
		String SQL = "update producto set Stock=? where IdProducto=?";
		CallableStatement Stmt;
		try{
			Stmt = cn.prepareCall(SQL);
			Stmt.setInt(1, cant);
			Stmt.setString(2, idp);
			Stmt.executeUpdate();
		}catch(Exception e){
			System.out.println("ERROR ACTULZIAR STOCK:" + e.getMessage());
		
		}
	}

}
