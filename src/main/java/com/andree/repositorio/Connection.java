package com.andree.repositorio;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Connection {
    
    private static java.sql.Connection con;
    
    static void start() {
        Connection.con=null;
        Statement query = null;
        String driver  = "Driver no encontrado";
        String conex = "";

        driver  = "com.mysql.jdbc.Driver";
        //conex = "jdbc:mysql://"+"localhost"+":"+"3306"+"/"+"reto";
        
        conex = String.format(
        	    "jdbc:mysql://google/%s?cloudSqlInstance=%s"
        	        + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
        	    "reto",
        	    "prueba-de-proyecto-250723:us-central1:reto");

        	//Connection connection = DriverManager.getConnection(conex, "", password);
        
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {}
        
        try {            
            Connection.con = DriverManager.getConnection(conex,"root","ICzbdmEzJiKmIriq");
        } catch (SQLException e) {System.out.println(e.getMessage());}  
        
        try {
            query = (Statement) Connection.con.createStatement();
        } catch (SQLException e) {System.out.println(e.getMessage());}
        
    }
    
    public static String[][] query(String sql, String[] array) { 
        Connection.start();
        PreparedStatement query = null;
        if(!sql.trim().substring(0,6).equalsIgnoreCase("select")){
            try { 
                query = (PreparedStatement) Connection.con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                for(int i=0;i<array.length;i++){
                    query.setString((int)i+1, array[i]);
                }
                
                query.executeUpdate(); 
                String bb = "0";
                try{
                    ResultSet generatedKeys = query.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        bb = ""+generatedKeys.getInt(1);
                    }
                }catch(Exception e){}
                String aux[][] = {{""+bb}};
                return aux;
            }catch (Exception ex) {
                String aux[][] = {{ex.getMessage()}};
                return aux;
            }
        }
        
        try {
            query = (PreparedStatement) Connection.con.prepareStatement(sql);
            for(int i=0;i<array.length;i++){
                query.setString((int)i+1, array[i]);
            }
            
            ResultSet tabla = query.executeQuery(); 
            ResultSetMetaData rsmd = tabla.getMetaData();
            
            int n = rsmd.getColumnCount();
            ArrayList<String[]> a = new ArrayList<String[]>();
            while(tabla.next()){  
                String[] b = new String[n];
                for (int i = 0; i < n; i++) {
                    b[i] = tabla.getString(i+1);
                }
                a.add(b);                 
            }
                
            Connection.con.close();
                
            String[][] d = new String[a.size()][n];
            for(int i = 0; i < a.size(); i++) {
                d[i] = a.get(i);
            }    
            System.out.println("ookkkkk");
            return d;
            
        }catch (SQLException e) {
        		System.out.println(e.getMessage());
        	 String aux[][] = {{"0"}};
             return aux;
        }   
    }
}  