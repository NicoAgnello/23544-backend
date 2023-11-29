package ar.com.codoacodo.interfaces;

//Interfaz: Es un contrato que parece una clase pero no lo es, donde podemos encontrar metodos y constantes (definidas con final)
//

public interface Interface {
	
	final String hola = ""; // Constante
	
	public void metodo1 (); // Definicion del metodo pero no tiene cuerpo. Osea no tiene {}.
	
	public default void metodo2 () {   // Se puede agregar un metodo con cuerpo es necesaria la palabra default.
		System.out.println("Metodo2");
	}
}
