package modelo;

public class Partido{

	public Equipo avanzarFase(Equipo equipoA, Equipo equipoB) {
		if(Math.random() > 0.5){
			return equipoA;
		}	
		
		else{
			return equipoB;
		}	
	}
}