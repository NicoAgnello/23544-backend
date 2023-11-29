package ar.com.codoacodo.repository;

//import java.util.List;

import ar.com.codoacodo.entity.Orador;

public class MainRepository {

	public static void main(String[] args) {
		OradorRepository repository = new MySQLOradorRepository ();
		
		// obtener un orador de la db
		
		Orador orador1 = repository.getById(1L);
		Orador orador2 = repository.getById(4L);
		System.out.println(orador1);
		System.out.println(orador2);
		
//		Orador orador3 = repository.save(orador3);
		
//		List<Orador> oradores = repository.findAll(); 
//		 System.out.println(oradores);
		
		Orador carlos = repository.getById(1L);
		
		carlos.setTema("C#");
		
		repository.update(carlos);
		
		System.out.println(repository.findAll());
		 
	}

}
