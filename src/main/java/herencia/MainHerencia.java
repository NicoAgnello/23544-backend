package herencia;

public class MainHerencia {

	public static void main(String[] args) {
			
		//crear un Articulo
		
		Articulo a1 = new Articulo("titulo1", 150.0f, "htttp://url.com/bla/1");
		
		//llamo al setter de autor
		
		a1.setAutor("autor a1");
		
		//
		
		System.out.println(a1);
		
		//Hijo h = new Hijo;
		
		Musica m1 = new Musica("m1", 250.7f, "htttp://url.com/bla/1");
		m1.setAutor("autor m1"); // esto es de articulo
		
		String [] temas = new String [] {"t1", "t2", "t3"};
		
		m1.setTemas(temas); // esto es musica
		
		//Que tiene disponible ??
		// Lo suyo mas lo del padre
		
		System.out.println(m1);
		
		
		
		//
		Articulo p1  = new Pelicula("p1", 230.1f, "htttp://url.com/bla/1");
		
		p1.setAutor("autor p1");
		
		Pelicula peli = (Pelicula) p1;
		
		peli.setFormato("BLUE RAY 3D");
		peli.setProductora("WARNER");

		/// vector para guardar insatancias .
		Articulo [] resultados = new Articulo [3];
		
		resultados [0] = a1;
		
		resultados [1] = m1;
		
		resultados [2] = p1;
		
		for(Articulo aux : resultados) {
			System.out.println(aux);
		}
	}

}
