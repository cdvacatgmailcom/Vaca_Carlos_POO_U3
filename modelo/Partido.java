package modelo;

public class Partido{
	private Equipo equipoA;
	private Equipo equipoB;

	public Partido(Equipo equipoA, int golesEquipoA, Equipo equipoA, int golesEquipoB){
		this.equipoA = equipoA;
		this.equipoB = equipoB;
	}

	private Equipo avanzarFase {
		if(golesEquipoA > golesEquipoB){
			return equipoA;
		}
		else{
			return equipoB;
		}
	}
}