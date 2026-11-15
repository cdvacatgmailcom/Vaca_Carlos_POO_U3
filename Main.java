import controlador.ControladorTorneo;
import modelo.Equipo;
import modelo.PersistenciaTorneo;
import modelo.Torneo;
import vista.VistaTorneo;

public class Main {
    public static void main(String[] args) {
        Equipo[] participantes = {
            new Equipo("Liga de Quito"),
            new Equipo("Independiente del Valle"),
            new Equipo("Barcelona SC"),
            new Equipo("Emelec"),
            new Equipo("El Nacional"),
            new Equipo("Aucas"),
            new Equipo("Universidad Católica"),
            new Equipo("Deportivo Cuenca"),
            new Equipo("Real Madrid"),
            new Equipo("FC Barcelona"),
            new Equipo("Bayern Múnich"),
            new Equipo("Manchester City"),
            new Equipo("Liverpool"),
            new Equipo("Arsenal"),
            new Equipo("Inter de Milán"),
            new Equipo("Boca Juniors")
        };

        Torneo modelo = new Torneo("Copa Eliminatoria 2026", participantes);
        PersistenciaTorneo persistencia = new PersistenciaTorneo("torneo_resultados.dat");
        VistaTorneo vista = new VistaTorneo();
        ControladorTorneo controlador = new ControladorTorneo(modelo, persistencia, vista);

        controlador.iniciarPrograma();
    }
}