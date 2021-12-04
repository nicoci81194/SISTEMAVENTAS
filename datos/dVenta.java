package datos;

import entidad.eDetalleVenta;
import entidad.eVenta;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class dVenta {

	private Conexion mysql = new Conexion();
	private Connection cn = mysql.conectar();
	private ArrayList<eVenta>miLista;
	
	public int totalregistros;
	
	//LISTAR VENTAS
	public ArrayList<eVenta> Listar(){
		String SQL = "call ListarVentas()";
		miLista = new ArrayList<eVenta>();
		ResultSet Rs;
		Statement Cmd;
		try {
				Cmd = cn.createStatement();
				Rs = Cmd.executeQuery(SQL);
				while(Rs.next()) {
					// "IdVenta","IdCliente","IdTrabajador","NumeroVenta","FechaVenta","Monto","Estado"
					miLista.add(new eVenta(Rs.getString("IdVenta"),
							Rs.getString("IdCliente"),
							Rs.getString("IdTrabajador"),
							Rs.getString("NumeroVenta"),
							Rs.getString("FechaVenta"),
							Rs.getDouble("Monto"),
							Rs.getString("Estado")));
				}
				Rs.close();							
				System.out.println("puede listar");
		} catch (Exception e) {
			System.out.println("ERROR LISTAR CLIENTE:" + e.getMessage());
		}
		return miLista;
	}
	
	//ID VENTAS
	public String IdVentas(){
		String idv="";
		String SQL = "select max(IdVenta) from venta";
		ResultSet Rs;
		Statement Cmd;
		try{
			Cmd = cn.createStatement();
			Rs = Cmd.executeQuery(SQL);
			while(Rs.next()){
				idv=Rs.getString(1);
			}
		}catch(Exception e){
			System.out.println("ERROR ID VENTA:" + e.getMessage());
		}
		
		return idv;
	}		
	
	//METODO GUARDAR VENTAS
	public void guardarVenta(eVenta ObjVt){
		String SQL = "call InsertarVenta(?,?,?,?,?,?,?)";
		CallableStatement Stmt;
		try {
			Stmt = cn.prepareCall(SQL);
			Stmt.setString(1, ObjVt.getIdVenta());
			Stmt.setString(2, ObjVt.getIdCliente());
			Stmt.setString(3, ObjVt.getIdTrabajador());
			Stmt.setString(4, ObjVt.getNumeroVenta());
			Stmt.setString(5, ObjVt.getFechaVenta());	
			Stmt.setDouble(6, ObjVt.getMonto());	
			Stmt.setString(7, ObjVt.getEstado());				
			Stmt.executeUpdate();
			System.out.print("intento guargar");
		} catch (Exception e) {
				System.out.println("ERROR INSERTAR VENTA:" + e.getMessage());
		}		
	}
	
	//METODO PARA GENERAR CODIGO VENTA 
	public String GeneraCodigoVenta() {
		String SQL = "call GenerarCodigoVenta()", nuevo = "";
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
			System.out.println("ERROR GENERAR CODIGO VENTA:" + e.getMessage());
		}
		return nuevo;
	}

	// GENERAR CODIGO DE NRO DE SERIE 2 INTENTO
	public String GenerarCodigoCBP() {

		String SQL = "call GenerarCodigoCBP" , nuevo = "";
		ResultSet Rs;
		Statement Cmd;
			try {
				Cmd = cn.createStatement();
				Rs = Cmd.executeQuery(SQL);
				while(Rs.next()){
					nuevo=Rs.getString(1);
				}
				Rs.close();
			} catch (Exception e) {
			System.out.println("ERROR AUTOGENERAR CODIGO CBO:" + e.getMessage());
		}
		return nuevo;
	}
	
	//METODO GUARDAR DETALLE VENTAS
	public void guardarDetalleVenta(eDetalleVenta ObjDv){

		String SQL = "call InsertarDetalleVenta(?,?,?,?,?)";
		CallableStatement Stmt;
		try {
			Stmt = cn.prepareCall(SQL);
			Stmt.setString(1, ObjDv.getIdDetalleVentas());
			Stmt.setString(2, ObjDv.getIdVenta());
			Stmt.setString(3, ObjDv.getIdProducto());
			Stmt.setInt(4, ObjDv.getCantidad());	
			Stmt.setDouble(5, ObjDv.getPrecioVenta());					
			Stmt.executeUpdate();
		} catch (Exception e) {
				System.out.println("ERROR INSERTAR DETALLE VENTA:" + e.getMessage());
		}		
	}
	
	//METODO PARA GENERAR CODIGO DETALLE VENTA VENTA
	public String GeneraCodigoDetalleVenta() {
		String SQL = "call GenerarCodigoDetalleVenta()", nuevo = "";
		ResultSet Rs;
		Statement Cmd;
		try {
				Cmd = cn.createStatement();
				Rs = Cmd.executeQuery(SQL);
				if(Rs.next()) {
						nuevo = Rs.getString(1);
				}
				Rs.close();
		} catch (Exception e) {
			System.out.println("ERROR GENERAR CODIGO DETALLE VENTA:" + e.getMessage());
		}
		return nuevo;
	}
	
	//BUSCAR POR VENTA
	public DefaultTableModel fecha(String ob1, String ob2)
	{		
		String [] nombresColumnas = {"IdVenta","IdCliente","IdTrabajador","NumeroVenta","FechaVenta","Monto","Estado"};
		String [] registros = new String[7];
		DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas);
		totalregistros=0;
		String sql ="select * from venta where FechaVenta BETWEEN '" + ob1+ "'AND'" + ob2+"'";
		PreparedStatement pst = null;
		ResultSet rs;
		try
        {
            pst = cn.prepareStatement(sql);                                    
            rs = pst.executeQuery();            
            while(rs.next())
            {                            
                registros[0] = rs.getString("IdVenta");                
                registros[1] = rs.getString("IdCliente");               
                registros[2] = rs.getString("IdTrabajador");         
                registros[3] = rs.getString("NumeroVenta"); 
                registros[4] = rs.getString("FechaVenta"); 
                registros[5] = rs.getString("Monto"); 
                registros[6] = rs.getString("Estado"); 
                totalregistros= totalregistros+1;
                modelo.addRow(registros);                             
            }                      
        }
	       catch(SQLException e)
        {            
            JOptionPane.showMessageDialog(null,"Error al conectar2. "+e.getMessage());            
        }
         return modelo;
	}
	
	// REPORTE POR ID VENTA
	public DefaultTableModel BuscarId(String idV) {
		String [] nombresColumnas = {"IdVenta","IdCliente","IdTrabajador","NumeroVenta","FechaVenta","Monto","Estado"};
		String [] registros = new String[7];
		DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas);
		//totalregistros=0;
		String sql ="select * from venta where IdVenta like '%" + idV+ "%'";
		PreparedStatement pst = null;
		ResultSet rs;
		try
        {
            pst = cn.prepareStatement(sql);                                    
            rs = pst.executeQuery();            
            while(rs.next())
            {                            
                registros[0] = rs.getString("IdVenta");                
                registros[1] = rs.getString("IdCliente");               
                registros[2] = rs.getString("IdTrabajador");         
                registros[3] = rs.getString("NumeroVenta"); 
                registros[4] = rs.getString("FechaVenta"); 
                registros[5] = rs.getString("Monto"); 
                registros[6] = rs.getString("Estado"); 
               // totalregistros= totalregistros+1;
                modelo.addRow(registros);                             
            }                      
        }
	       catch(SQLException e)
        {            
            JOptionPane.showMessageDialog(null,"Error budvar por id. "+e.getMessage());            
        }
		
		
		return modelo;
	}
	
}
