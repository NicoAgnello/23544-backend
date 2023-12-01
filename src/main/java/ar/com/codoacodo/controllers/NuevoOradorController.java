package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.time.LocalDate;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.repository.MySQLOradorRepository;
import ar.com.codoacodo.repository.OradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//http://localhost:8080/web-app-23544/api/orador/nuevo

@WebServlet("/api/orador/nuevo")
public class NuevoOradorController extends HttpServlet{
	//crear ?? POST
	
	@Override
	protected void doPost(
			HttpServletRequest request, //aca viene lo que manda el usuario (front-end) --> request.getParameter("nombre")
			HttpServletResponse response) //  lo que manda el backend al frontend
					throws ServletException, IOException {
		
		
		
		//capturo los parametro enviados por el front
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("mail");
		String tema = request.getParameter("tema");
		
		//falta validar!! 
		
		//creo el orador con esos parametros
		
		Orador nuevo = new Orador(nombre, apellido, email, tema, LocalDate.now());
		
		//ahora por medio del repository guardamos en la db;
		
		OradorRepository repository = new MySQLOradorRepository();
		
		repository.save(nuevo);
		
		//Respondo al frontend
		response.getWriter().print("OK"); // json
		
	}
	
	//Preflight
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin","*");
	    response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD");
	    response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
	}
	
	
}
