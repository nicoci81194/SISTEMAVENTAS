package negocio;

import entidad.eTrabajador;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import datos.dTrabajador;
public class nTrabajador {
	
	dTrabajador dt;
	
	public nTrabajador() {
		dt = new dTrabajador();
	}
	
	public ArrayList<eTrabajador>Listar(){
		return dt.Listar();
	}
	
	public eTrabajador Buscar(String Id) {
		return dt.Buscar(Id);
	}
	
	public void Insertar(eTrabajador ObjT) {
		dt.Insertar(ObjT);
	}
	
	public void Modificar(eTrabajador ObjT) {
		dt.Insertar(ObjT);
	}
	
	public void Suprimir(String Id) {
		dt.Suprimir(Id);
	}
	
	public String GeneraCodigoVendedor() {
		return dt.GeneraCodigoVendedor();
	}
	
	public DefaultTableModel login(String usuario, String clave) {	
		return dt.login(usuario, clave);
	}

}
