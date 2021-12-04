package datos;

import entidad.eCliente;
import java.sql.*;
import java.util.ArrayList;

public class dCliente {
	
	private Conexion mysql = new Conexion();
	private Connection cn = mysql.conectar();
	private ArrayList<eCliente>miLista;
	
	//METODO LISTAR CLIENTE
	public ArrayList<eCliente> Listar(){
		String SQL = "call ListarClientes()";
		ResultSet Rs;
		Statement Cmd;
		miLista = new ArrayList<eCliente>();
		try {
			Cmd = cn.createStatement();
			Rs = Cmd.executeQuery(SQL);
			while(Rs.next()) {
			miLista.add(new eCliente(Rs.getString("IdCliente"),
				Rs.getString("Dni"),
				Rs.getString("Nombres"),
				Rs.getString("Apellidos"),
				Rs.getString("Telefono"),
				Rs.getString("Direccion"),
				Rs.getString("Estado")));
			}
			Rs.close();							
			System.out.println("puede listar");
		} catch (Exception e) {
			System.out.println("ERROR LISTAR CLIENTE:" + e.getMessage());
		}
		return miLista;
	}
	

		
	//METODO BUSCAR CLIENTE
	public eCliente Buscar(String Id){
		String SQL = "call BuscarCliente(?)";
		CallableStatement Stmt;
		ResultSet Rs;
		eCliente ObjC = null;
		try {
			Stmt = cn.prepareCall(SQL);
			Stmt.setString(1, Id);
			Rs = Stmt.executeQuery();
				if(Rs.next()) {
				ObjC = new eCliente(Rs.getString("IdCliente"),
					Rs.getString("Dni"),
					Rs.getString("Nombres"),
					Rs.getString("Apellidos"),
					Rs.getString("Telefono"),
					Rs.getString("Direccion"),
					Rs.getString("Estado"));
		}
			Rs.close();
		} catch (Exception e) {
			System.out.println("ERROR BUSCAR CLIENTE:" + e.getMessage());
		}
		return ObjC;
	}	
	
	//METODO INSERTAR CLIENTE
	public void Insertar(eCliente ObjC){
		String SQL = "call InsertarCliente(?,?,?,?,?,?,?)";
		CallableStatement Stmt;
		try {
			Stmt = cn.prepareCall(SQL);
			Stmt.setString(1, ObjC.getIdCliente());
			Stmt.setString(2, ObjC.getDni());
			Stmt.setString(3, ObjC.getNombres());
			Stmt.setString(4, ObjC.getApellidos());
			Stmt.setString(5, ObjC.getTelefono());
			Stmt.setString(6, ObjC.getDireccion());
			Stmt.setString(7, ObjC.getEstado());
			Stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("ERROR INSERTAR CLIENTE:" + e.getMessage());
		}
	}
	
	//METODO MODIFICAR CLIENTE
	public void Modificar(eCliente ObjC){
		String SQL = "call ModificaCliente(?,?,?,?,?,?,?)";
		CallableStatement Stmt;
		try {
			Stmt = cn.prepareCall(SQL);
			Stmt.setString(1, ObjC.getIdCliente());
			Stmt.setString(2, ObjC.getDni());
			Stmt.setString(3, ObjC.getNombres());
			Stmt.setString(4, ObjC.getApellidos());
			Stmt.setString(5, ObjC.getTelefono());
			Stmt.setString(6, ObjC.getDireccion());
			Stmt.setString(7, ObjC.getEstado());
			Stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("ERROR MODIFICAR PRODUCTO:" + e.getMessage());
		}	
	}
	
	//METODO SUPRIMIR CLIENTE
	public void Suprimir(String Id){
		String SQL = "call SuprimirCliente(?)";
		CallableStatement Stmt;
		try {
			Stmt = cn.prepareCall(SQL);
			Stmt.setString(1, Id);
			Stmt.executeUpdate();						
		} catch (Exception e) {
			System.out.println("ERROR SUPRIMIR CLIENTE:" + e.getMessage());
		}
	}
	
	//METODO PARA GENERAR CODIGO
	public String GeneraCodigoCliente() {
		String SQL = "call  GenerarCodigoCliente()", nuevo = "";
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
}
