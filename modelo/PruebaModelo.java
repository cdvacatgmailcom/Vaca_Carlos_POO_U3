package modelo;

public class PruebaModelo{
	public static void main(String [] args){
		
		Partido partido = new Partido(new Equipo("Luchito FC"), 3, new Equipo("Landiland FC"), 2);

		System.print(partido.avanzarFase.getNombre);
	}
}