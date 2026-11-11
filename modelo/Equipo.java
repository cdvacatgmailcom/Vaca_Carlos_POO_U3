package modelo;

public class Equipo{
	private String nombre;
	private String fase = "inicial";

	public Equipo(String nombre){
		this.nombre = nombre;
	}

//Getters
	public String getNombre(){
		return nombre;
	}

	public void setFase(String fase){
		this.fase = fase;
	}
}