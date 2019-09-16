package com.andree.repositorio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;

import com.andree.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class MiRepo {
	public static String guardarDatos(Cliente cliente) {
		ArrayList<Cliente> clientes = MiRepo.leerDatos();

        clientes.add(cliente);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		try {
            File file = new ClassPathResource("repo.txt").getFile();
            String content = ow.writeValueAsString(clientes);
            
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            return "Cliente guardado";
        } catch (IOException e) {
        	return e.getMessage();
        } 
	}
	
	public static ArrayList<Cliente> leerDatos(){	
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
				
		StringBuilder sb = new StringBuilder();
        try {
        	File file = new ClassPathResource("repo.txt").getFile();
        	
            FileInputStream fis = new FileInputStream(file);
            int content;
            while ((content = fis.read()) != -1) {
            	sb.append((char) content);
            }

            fis.close();
        } catch (IOException e) {
        	System.out.println(e.getMessage());
        }
        
        ObjectMapper mapper = new ObjectMapper();
        String str = sb.toString();
        //*
        try {
			clientes = mapper.readValue(str, mapper.getTypeFactory().constructCollectionType(
                    ArrayList.class, Cliente.class));
		} catch (Exception e) {System.out.println(e.getMessage());}
        
        return clientes;
	}
	
	public static String eliminarDatos() {
		try {
            File file = new ClassPathResource("repo.txt").getFile();
            
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("[]");
            bw.close();
            return "Eliminado";
        } catch (IOException e) {
        	return e.getMessage();
        }
	}
}
