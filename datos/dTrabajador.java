package datos;

import entidad.eTrabajador;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class dTrabajador {
	private Conexion mysql = new Conexion();
	private Connection cn = mysql.conectar();
	private ArrayList<eTrabajador> miLista;
	public Integer totalregistros;

	
	//METODO LISTAR VENDEDORES
	public ArrayList<eTrabajador> Listar(){
		String SQL = "call ListarVendedores();";
		ResultSet Rs;
		Statement Cmd;
		miLista = new ArrayList<eTrabajador>();
		try {
				Cmd = cn.prepareCall(SQL);
				Rs = Cmd.executeQuery(SQL);
				while(Rs.next()) {
					miLista.add(new eTrabajador(Rs.getString("IdTrabajador"),
							Rs.getString("Dni"),
							Rs.getString("Nombres"),
							Rs.getString("Apellidos"),
							Rs.getString("Celular"),
							Rs.getString("Acceso"),
							Rs.getString("Usuario"),
							Rs.getString("Estado")));
				}
				Rs.close();				
		} catch (Exception e) {
			System.out.println("ERROR LISTAR VENDEDORES:" + e.getMessage());
		}
		return miLista;
	}
	
	//METODO BUSCAR VENDEDOR
	public eTrabajador Buscar(String Id){
		String SQL = "call BuscarVendedor(?)";
		CallableStatement Stmt;
		ResultSet Rs;
		eTrabajador ObjV = null;
		try {
				Stmt = cn.prepareCall(SQL);
				Stmt.setString(1, Id);
				Rs = Stmt.executeQuery();
				if(Rs.next()) {
					ObjV = new eTrabajador(Rs.getString("IdTrabajador"),
							Rs.getString("Dni"),
							Rs.getString("Nombres"),
							Rs.getString("Apellidos"),
							Rs.getString("Celular"),
							Rs.getString("Acceso"),
							Rs.getString("Usuario"),
							Rs.getString("Estado"));
				}
				Rs.close();
		} catch (Exception e) {
				System.out.println("ERROR BUSCAR VENDEDOR:" + e.getMessage());
		}
		return ObjV;
	}
	
	//METODO INSERTAR VENDEDOR
	public void Insertar(eTrabajador ObjV){
		String SQL = "call InsertarVendedor(?,?,?,?,?,?,?,?)";
		CallableStatement Stmt;
		try {
			Stmt = cn.prepareCall(SQL);
			Stmt.setString(1, ObjV.getIdTrabajador());
			Stmt.setString(2, ObjV.getDni());
			Stmt.setString(3, ObjV.getNombres());
			Stmt.setString(4, ObjV.getApellidos());
			Stmt.setString(5, ObjV.getCelular());	
			Stmt.setString(6, ObjV.getAcceso());
			Stmt.setString(7, ObjV.getUsuario());
			Stmt.setString(8, ObjV.getEstado());
				Stmt.executeUpdate();
		} catch (Exception e) {
				System.out.println("ERROR INSERTAR VENDEDOR:" + e.getMessage());
		}
	}
	
	//METODO MODIFICAR VENDEDOR
	public void Modificar(eTrabajador ObjV){
		String SQL = "call ModificaVendedor(?,?,?,?,?,?,?,?)";
		CallableStatement Stmt;
		try {
			Stmt = cn.prepareCall(SQL);
			Stmt.setString(1, ObjV.getIdTrabajador());
			Stmt.setString(2, ObjV.getDni());
			Stmt.setString(3, ObjV.getNombres());
			Stmt.setString(4, ObjV.getApellidos());
			Stmt.setString(5, ObjV.getCelular());	
			Stmt.setString(6, ObjV.getAcceso());
			Stmt.setString(7, ObjV.getUsuario());
			Stmt.setString(8, ObjV.getEstado());
			Stmt.executeUpdate();
		} catch (Exception e) {
				System.out.println("ERROR MODIFICAR VENDEDOR:" + e.getMessage());
		}
	}
	
	//METODO SUPRIMIR VENDEDOR
	public void Suprimir(String Id){
		String SQL = "call SuprimirVendedor(?)";
		CallableStatement Stmt;
		try {
				Stmt = cn.prepareCall(SQL);
				Stmt.setString(1, Id);
				Stmt.executeUpdate();
		} catch (Exception e) {
				System.out.println("ERROR SUPRIMIR VENDEDOR:" + e.getMessage());
		}
	}
	
	//METODO PARA GENERAR CODIGO VENDEDOR
	public String GeneraCodigoVendedor() {
		String SQL = "call GenerarCodigoVendedor()", nuevo = "";
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
			System.out.println("ERROR GENERAR CODIGO VENDEDOR:" + e.getMessage());
		}
		return nuevo;
	}

	public DefaultTableModel login(String usuario, String clave) {		
		DefaultTableModel modelo;
		String[] titulos = {"IdTrabajador","Dni","Nombres","Apellidos","Celular","Acceso","Usuario","Estado"};
		String[] registro = new String[8];
		totalregistros=0;
		modelo = new DefaultTableModel(null,titulos);		
		String SQL="select * from trabajador where usuario='"+usuario+"'and dni='"+clave+"'";
		ResultSet rs;
		PreparedStatement st = null;	
		try {
			st = cn.prepareStatement(SQL);
            rs = st.executeQuery();            
            while (rs.next()) {
                registro[0] = rs.getString("IdTrabajador");
                registro[1] = rs.getString("Dni");
                registro[2] = rs.getString("Nombres");
                registro[3] = rs.getString("Apellidos");               
                registro[4] = rs.getString("Celular");
                registro[5] = rs.getString("Acceso");
                registro[6] = rs.getString("Usuario");
                registro[7] = rs.getString("Estado");
                
                totalregistros = totalregistros + 1;
                modelo.addRow(registro);
                
                System.out.println("INICIA SESION:");
            }			            
		}catch(Exception e) {
			System.out.println("ERROR LOGIN:" + e.getMessage());
		}		
		return modelo;	
	}

}
