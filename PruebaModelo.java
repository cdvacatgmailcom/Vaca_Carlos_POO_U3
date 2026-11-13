import modelo.*;
import java.util.ArrayList;

public class PruebaModelo{
	public static void main(String [] args){

		ArrayList <Equipo> equipos = new ArrayList <> ();

		equipos.add (0, new Equipo("Luchito FC"));
		equipos.add (1, new Equipo("Landiland FC"));
		equipos.add (2, new Equipo("Aguilar SC"));
		equipos.add (3, new Equipo("Independiente Frontier"));
		equipos.add (4, new Equipo("SD Sindicato MSC"));
		equipos.add (5, new Equipo("Venom FC"));
		equipos.add (6, new Equipo("AsoTrans FC"));
		equipos.add (7, new Equipo("Partido Obrero FC"));
		equipos.add (8, new Equipo("La Ley & El Orden FC"));
		equipos.add (9, new Equipo("Pulgas FC"));
		equipos.add (10, new Equipo("Mano de Obra FC"));
		equipos.add (11, new Equipo("Demoledor FC"));
		equipos.add (12, new Equipo("Maquinaria Pesada FC"));
		equipos.add (13, new Equipo("Lalita Cárnicos FC"));
		equipos.add (14, new Equipo("Fábrica de Servilletas FC"));
		equipos.add (15, new Equipo("Software FC"));
		Torneo torneo = new Torneo (equipos);
		
		System.out.print((torneo.sortearCruces().getNombre()));
	}
}