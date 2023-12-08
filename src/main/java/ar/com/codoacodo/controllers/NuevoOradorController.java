package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.repository.MySQLOradorRepository;
import ar.com.codoacodo.repository.OradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//http://localhost:8080/web-app-23544/api/orador/nuevo
@WebServlet("/api/orador")
public class NuevoOradorController extends HttpServlet{
	
	private OradorRepository repository = new MySQLOradorRepository();

	//crear > POST
	protected void doPost(
				HttpServletRequest request, //aca viene lo que manda el usuario 
				HttpServletResponse response /*manda el backend al frontend*/
			) throws ServletException, IOException {
		
		//OradorRequest oradorJson = (OradorRequest )fromJSON(OradorRequest.class, request, response);
		//obtengo el json desde el frontend
		String json = request.getReader()
				.lines()
				.collect(Collectors.joining(System.lineSeparator()));//spring
		
		//convierto de json String a Objecto java usando libreria de jackson2
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		OradorRequest oradorRequest = mapper.readValue(json, OradorRequest.class);
		
		//creo mi orador con esos parametros
		Orador nuevo = new Orador(
				oradorRequest.getNombre(), 
				oradorRequest.getApellido(),
				oradorRequest.getEmail(),
				oradorRequest.getTema(),
				LocalDate.now()
		);
		
		//ahora por medio del repository guarda en la db
//		OradorRepository repository = new MySQLOradorRepository();
		
		this.repository.save(nuevo);
		
		//ahora respondo al front: json, Convirtiendo el nuevo Orador a json
		String jsonParaEnviarALFrontend = mapper.writeValueAsString(nuevo);
		
		response.getWriter().print(jsonParaEnviarALFrontend);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//ahora por medio del repository guarda en la db
//		OradorRepository repository = new MySQLOradorRepository();
		List<Orador> listado = this.repository.findAll();
		
		//convierto de objeto java a json
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		
		String jsonParaEnviarALFrontend = mapper.writeValueAsString(listado);
		response.getWriter().print(jsonParaEnviarALFrontend);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");

//		OradorRepository repository = new MySQLOradorRepository();
		this.repository.delete(Long.parseLong(id));
		
		response.setStatus(HttpServletResponse.SC_OK);// 200
		
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		//Ahora quiero los datos que vienen del body.
		
		String json = request.getReader()
				.lines()
				.collect(Collectors.joining(System.lineSeparator()));//spring
		
		//convierto de json String a Objecto java usando libreria de jackson2
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		OradorRequest oradorRequest = mapper.readValue(json, OradorRequest.class);
		
		//Ahora busco el orador en la db para el id.
		
		Orador orador = this.repository.getById(Long.parseLong(id));
		
		//ahora actualizo los datos
		
		orador.setApellido(oradorRequest.getApellido());
		orador.setNombre(oradorRequest.getNombre());
		orador.setEmail(oradorRequest.getEmail());
		orador.setTema(oradorRequest.getTema());
		
		//ahora si actualizo en la db
		
		this.repository.update(orador);

		//ahora informo al front
		
		response.setStatus(HttpServletResponse.SC_OK);
	}

}





//package ar.com.codoacodo.controllers;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.stream.Collectors;
//
//import ar.com.codoacodo.entity.Orador;
//import ar.com.codoacodo.repository.MySQLOradorRepository;
//import ar.com.codoacodo.repository.OradorRepository;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//
////http://localhost:8080/web-app-23544/api/orador/nuevo
//
//@WebServlet("/api/orador/nuevo")
//public class NuevoOradorController extends HttpServlet{
//	//crear ?? POST
//	
//	@Override
//	protected void doPost(
//			HttpServletRequest request, //aca viene lo que manda el usuario (front-end) --> request.getParameter("nombre")
//			HttpServletResponse response) //  lo que manda el backend al frontend
//					throws ServletException, IOException {
//		
//		//obtengo el json desde el frontend
//		
//		String json = request.getReader()
//						.lines()
//						.collect(Collectors.joining(System.lineSeparator()));//spring
//				
//				
//		//convertir de json a objeto java con jackson
//				
//		
//		
//		//capturo los parametro enviados por el front
//		String nombre = request.getParameter("nombre");
//		String apellido = request.getParameter("apellido");
//		String email = request.getParameter("mail");
//		String tema = request.getParameter("tema");
//		
//		//falta validar!! 
//		
//		//creo el orador con esos parametros
//		
//		Orador nuevo = new Orador(nombre, apellido, email, tema, LocalDate.now());
//		
//		//ahora por medio del repository guardamos en la db;
//		
//		OradorRepository repository = new MySQLOradorRepository();
//		
//		repository.save(nuevo);
//		
//		//Respondo al frontend
//		response.getWriter().print("OK"); // json
//		
//	}
//	
//	//Preflight
//	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.addHeader("Access-Control-Allow-Origin","*");
//	    response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD");
//	    response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//	}
//	
//	
//}
