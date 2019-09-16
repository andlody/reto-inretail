package com.andree.reto;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andree.formulas.Mates;
import com.andree.model.Cliente;
import com.andree.model.ClienteRespuesta;
import com.andree.model.Promedios;
import com.andree.repositorio.MiRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Clientes Microservicios", description = "Apis de Clientes")
public class ClienteController {
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/crearcliente")
    @ApiOperation(value = "Crear cliente", notes = "Crear nuevo cliente")
	public String crearcliente(@RequestBody Cliente cliente) {		 
		 return MiRepo.guardarDatos(cliente);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/kpideclientes")
	@ApiOperation(value = "Promedios de clientes", notes = "Muestra el promedio de edad de todos los clientes y la desviacion estandar")
	public ResponseEntity<Promedios> kpideclientes() {
		
		ArrayList<Cliente> clientes = MiRepo.leerDatos();
		
		ArrayList<Integer> edades = new ArrayList<Integer>();
		for(int i=0;i<clientes.size();i++) {
			edades.add(clientes.get(i).edad);
		}
		
		Promedios promedios = new Promedios();
		promedios.setPromedio(Mates.promedio(edades));
		promedios.setDesviacionEstandar(Mates.desviacionEstandar(edades));
		return new ResponseEntity<Promedios>(promedios,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/listclientes")
	@ApiOperation(value = "Lista de clientes", notes = "Lista de todos los clientes con la fecha probable de su muerte")
	public ResponseEntity<ArrayList<ClienteRespuesta>>  listclientes() {
		ArrayList<Cliente> clientes = MiRepo.leerDatos();
		ArrayList<ClienteRespuesta> clientesRespuesta = new ArrayList<ClienteRespuesta>();
		
		for(int i=0;i<clientes.size();i++) {
			ClienteRespuesta clienteRespuesta = new ClienteRespuesta();
			clienteRespuesta.nombre = clientes.get(i).nombre;
			clienteRespuesta.apellido = clientes.get(i).apellido;
			clienteRespuesta.edad = clientes.get(i).edad;
			clienteRespuesta.fechaNacimiento = clientes.get(i).fechaNacimiento;
			clienteRespuesta.calcularFechaMuerte();
			clienteRespuesta.calcularEdadReal();
			
			clientesRespuesta.add(clienteRespuesta);
		}
		
		return new ResponseEntity<ArrayList<ClienteRespuesta>>(clientesRespuesta,HttpStatus.OK);
	}	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/api/eliminarclientes")
	@ApiOperation(value = "Eliminar clientes", notes = "Elimina todos los clientes guardados.")
	public String eliminarclientes() {
		return MiRepo.eliminarDatos();
	}	
}
