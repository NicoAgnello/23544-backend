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
		String email = request.getParameter("email");
		String tema = request.getParameter("tema");
		
		//creo el orador con esos parametros
		
		Orador nuevo = new Orador(nombre, apellido, email, tema, LocalDate.now());
		
		//ahora por medio del repository guardamos en la db;
		
		OradorRepository repository = new MySQLOradorRepository();
		
		repository.save(nuevo);
		
		//Respondo al frontend
		response.getWriter().print("OK"); // json
		
		
	}
}
