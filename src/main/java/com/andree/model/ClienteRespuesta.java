package com.andree.model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class ClienteRespuesta extends Cliente{
	public String fechaMuerte;
	public String edadReal;
	
	public String getFechaMuerte() {
		return this.fechaMuerte;
	}
	
	public void calcularEdadReal() {
		this.edadReal = ""+(Period.between(
				LocalDate.of(this.fechaNacimiento.getYear(),this.fechaNacimiento.getMonth()+1,this.fechaNacimiento.getDay()),
				LocalDate.now()
			).getYears()-1900);
	}
	
	public void calcularFechaMuerte() {
		// Esperanza de vida es de: 75   Fuente INEI
		
		Calendar date = Calendar.getInstance();
	    date.setTime(this.fechaNacimiento);
	    Format f = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
	    date.add(Calendar.YEAR,75);
	    
		this.fechaMuerte = f.format(date.getTime());
	}
}
