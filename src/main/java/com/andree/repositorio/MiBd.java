package com.andree.repositorio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.andree.model.Cliente;

public class MiBd {
	public static String guardarDatos(Cliente cliente) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] a = {cliente.nombre,cliente.apellido,""+cliente.edad,sdf.format(cliente.fechaNacimiento)};
		String[][] aa = Connection.query("INSERT INTO cliente (nombre, apellido, edad, fecha) VALUES (?,?,?,?)", a);
		return "Se guardo el cliente con ID: "+aa[0][0];
	}
	
	public static ArrayList<Cliente> leerDatos() {
		String[][] aa = Connection.query("SELECT nombre, apellido, edad, fecha FROM cliente", new String[0]);
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
				
		for(int i=0;i<aa.length;i++) {
			Cliente cliente = new Cliente();
			cliente.setNombre(aa[i][0]);
			cliente.setApellido(aa[i][1]);
			try {
				cliente.setEdad(Integer.parseInt(aa[i][2]));
				cliente.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(aa[i][3]));
			}catch(Exception e) {
				cliente.setEdad(0);
				cliente.setFechaNacimiento(new Date());
			}
			clientes.add(cliente);
		}
		
		return clientes;
	}
	
	public static String eliminarDatos() {
		Connection.query("DELETE FROM cliente", new String[0]);
		return "Eliminado";
	}
}
