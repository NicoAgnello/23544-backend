package herencia;

public class Libro extends Articulo {

	private String isbn;
	
	// libro no puede nacer si primero no nace Articulo
	
	public Libro(String titulo, Float precio, String img, String isbn) {
		super(titulo, precio, img); // nace articulo, invocando al constructor del padre.
									// tambien se puede acceder a las propiedades del padre con super.algo
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		String toStringPadre = super.toString()  ;
		String toStringHijo = "Libro [isbn=" + isbn + "]";
		return toStringPadre + toStringHijo;
	}
	
	
	
}
