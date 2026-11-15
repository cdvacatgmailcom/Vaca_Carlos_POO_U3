package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Torneo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombreTorneo;
    private Equipo[] equipos;
    private Equipo campeon;
    private List<Partido[]> historialFases;
    private Random random;

    public Torneo(String nombreTorneo, Equipo[] equipos) {
        this.nombreTorneo = nombreTorneo;
        this.equipos = equipos;
        this.historialFases = new ArrayList<>();
        this.random = new Random();
    }

    public void iniciar() {
        this.historialFases.clear();
        // 1. Mezcla recursiva (Fisher-Yates)
        sortearRecursivo(this.equipos, this.equipos.length - 1);
        // 2. Llave eliminatoria recursiva
        this.campeon = jugarFaseRecursiva(this.equipos);
    }

    private void sortearRecursivo(Equipo[] lista, int indiceActual) {
        if (indiceActual <= 0) {
            return; // Caso base
        }
        int indiceAleatorio = random.nextInt(indiceActual + 1);

        Equipo temporal = lista[indiceActual];
        lista[indiceActual] = lista[indiceAleatorio];
        lista[indiceAleatorio] = temporal;

        sortearRecursivo(lista, indiceActual - 1);
    }

    private Equipo jugarFaseRecursiva(Equipo[] clasificados) {
        if (clasificados.length == 1) {
            return clasificados[0]; // Caso base: tenemos campeón
        }

        int totalPartidos = clasificados.length / 2;
        Partido[] partidosRonda = new Partido[totalPartidos];
        Equipo[] ganadores = new Equipo[totalPartidos];

        for (int i = 0; i < totalPartidos; i++) {
            Partido partido = new Partido(clasificados[i * 2], clasificados[i * 2 + 1]);
            ganadores[i] = partido.jugar(random);
            partidosRonda[i] = partido;
        }

        // Guardamos los partidos de esta fase para que la Vista los muestre después
        this.historialFases.add(partidosRonda);

        return jugarFaseRecursiva(ganadores);
    }

    public String getNombreTorneo() { return nombreTorneo; }
    public Equipo[] getEquipos() { return equipos; }
    public Equipo getCampeon() { return campeon; }
    public List<Partido[]> getHistorialFases() { return historialFases; }
}