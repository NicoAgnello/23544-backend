package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;


//Esta clase sirve para evitar codigo repetitivo dentro del hijo.

public class AppBaseServlet extends HttpServlet {
	
	protected ObjectMapper mapper = new ObjectMapper();
	
	
	
	//al instanciar el objeto va a tener mapper como propiedad privada porque el padre lo tiene como protected
	public AppBaseServlet () {
		super(); // en este caso es lo mismo no llamar al padre.
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
	
	public String toJson (HttpServletRequest request) throws IOException {
		
				String json = request.getReader()
						.lines()
						.collect(Collectors.joining(System.lineSeparator()));//spring
				return json;
	}  //del lado del hijo solo queda hacer String json = super.toJson(request);
	
	
	
	
}
