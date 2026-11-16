import controlador.ControladorTorneo;
import modelo.PersistenciaTorneo;
import modelo.Torneo;
import vista.VistaTorneo;

public class Main {
    public static void main(String[] args) {
        Torneo modelo = new Torneo("Copa Eliminatoria 2026");
        PersistenciaTorneo persistencia = new PersistenciaTorneo("torneo_resultados.dat");
        VistaTorneo vista = new VistaTorneo();
        ControladorTorneo controlador = new ControladorTorneo(modelo, persistencia, vista);

        controlador.iniciarPrograma();
    }
}