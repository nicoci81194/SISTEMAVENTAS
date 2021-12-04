package util;

import vista.mProducto;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidad.eProducto;
import negocio.nProducto;

public class uProducto {
	
	mProducto ObjVP = new mProducto();
	nProducto ObjNP = new nProducto();
	DefaultTableModel tabla;
	String Columnas[] = {"IdProducto","Descripcion","Precio","Stock","Estado"};
	Object Filas[][];	
	ArrayList<eProducto>miLista;
	
	public void cargarTabla(JTable ObjP) {
		tabla = new DefaultTableModel();
		miLista = new ArrayList<eProducto>();
		miLista = ObjNP.Listar();
		Filas = new Object[miLista.size()][5];
		
		for(int i = 0; i < Columnas.length; i++)tabla.addColumn(Columnas[i]);
		for(int i = 0; i < miLista.size(); i++) {
			Filas[i][0] = miLista.get(i).getIdProducto();
			Filas[i][1] = miLista.get(i).getDescripcion();
			Filas[i][2] = miLista.get(i).getPrecio();
			Filas[i][3] = miLista.get(i).getStock();
			Filas[i][4] = miLista.get(i).getEstado();
			tabla.addRow(Filas[i]);		
		}
		ObjP.setModel(tabla);		
	}
	
	

}
