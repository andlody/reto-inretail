package com.andree.formulas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;

import com.andree.reto.Inicio;

public class Mates {
	public static double desviacionEstandar(ArrayList<Integer> edades) {
		if(edades.size()<2)return 0;
		int[] valor = new int[edades.size()];
	    for (int i=0; i < valor.length; i++)
	    {
	    	valor[i] = edades.get(i).intValue();
	    }

		int sumatoria = 0;
		float media = 0;
		float varianza = 0;
		double desviacion = 0;
		
		for(int i=0;i<valor.length;i++) {
			sumatoria+=valor[i];
		}
		
		media = sumatoria/(valor.length*1.0f);
		
		for(int i=0;i<valor.length;i++) {
			double rango= Math.pow(valor[i]-media,2);
			varianza += rango;
		}
		
		varianza = varianza/(valor.length-1);
		desviacion = Math.sqrt(varianza);
		
		return desviacion;
	}
	
	public static float promedio(ArrayList<Integer> edades) {
		if(edades.size()==0)return 0;
		int[] valor = new int[edades.size()];
	    for (int i=0; i < valor.length; i++)
	    {
	    	valor[i] = edades.get(i).intValue();
	    }

		int sumatoria = 0;
		float media = 0;
		
		for(int i=0;i<valor.length;i++) {
			sumatoria+=valor[i];
		}
		
		media = sumatoria/(valor.length*1.0f);
		
		return media;
	}
}
