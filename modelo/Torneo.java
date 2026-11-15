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

    public Torneo(String nombreTorneo, Equipo[] equipos) throws CantidadEquiposInvalidaException {
        validarCantidadEquipos(equipos);
        this.nombreTorneo = nombreTorneo;
        this.equipos = equipos;
        this.historialFases = new ArrayList<>();
        this.random = new Random();
    }

    private void validarCantidadEquipos(Equipo[] lista) throws CantidadEquiposInvalidaException {
        if (lista == null || lista.length < 2) {
            throw new CantidadEquiposInvalidaException(
                "El torneo requiere al menos 2 equipos participantes."
            );
        }
        if (!esPotenciaDeDosRecursivo(lista.length)) {
            throw new CantidadEquiposInvalidaException(
                "Cantidad recibida (" + lista.length + "): debe ser potencia de 2 (2, 4, 8, 16 o 32)."
            );
        }
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == null) {
                throw new CantidadEquiposInvalidaException(
                    "El equipo en la posición #" + (i + 1) + " no está instanciado (null)."
                );
            }
        }
    }

    private boolean esPotenciaDeDosRecursivo(int n) {
        if (n == 1) {
            return true;
        }
        if (n <= 0 || n % 2 != 0) {
            return false;
        }
        return esPotenciaDeDosRecursivo(n / 2);
    }

    public void iniciar() throws SorteoException {
        try {
            this.historialFases.clear();
            this.campeon = null;

            sortearRecursivo(this.equipos, this.equipos.length - 1);
            this.campeon = jugarFaseRecursiva(this.equipos);

            if (this.campeon == null) {
                throw new SorteoException("La llave finalizó sin determinar un campeón.");
            }
        } catch (SorteoException e) {
            limpiarEstadoFallido();
            throw e; // Re-lanza la excepción propia sin envolverla dos veces
        } catch (Exception e) {
            limpiarEstadoFallido();
            throw new SorteoException("Fallo inesperado durante el sorteo o las fases: " + e.getMessage(), e);
        }
    }

    private void limpiarEstadoFallido() {
        this.historialFases.clear();
        this.campeon = null;
    }

    private void sortearRecursivo(Equipo[] lista, int indiceActual) {
        if (indiceActual <= 0) {
            return;
        }
        int indiceAleatorio = random.nextInt(indiceActual + 1);

        Equipo temporal = lista[indiceActual];
        lista[indiceActual] = lista[indiceAleatorio];
        lista[indiceAleatorio] = temporal;

        sortearRecursivo(lista, indiceActual - 1);
    }

    private Equipo jugarFaseRecursiva(Equipo[] clasificados) throws SorteoException {
        if (clasificados.length == 1) {
            return clasificados[0];
        }

        int totalPartidos = clasificados.length / 2;
        Partido[] partidosRonda = new Partido[totalPartidos];
        Equipo[] ganadores = new Equipo[totalPartidos];

        for (int i = 0; i < totalPartidos; i++) {
            Partido partido = new Partido(clasificados[i * 2], clasificados[i * 2 + 1]);
            ganadores[i] = partido.jugar(random);
            partidosRonda[i] = partido;
        }

        this.historialFases.add(partidosRonda);
        return jugarFaseRecursiva(ganadores);
    }

    public String getNombreTorneo() { return nombreTorneo; }
    public Equipo[] getEquipos() { return equipos; }
    public Equipo getCampeon() { return campeon; }
    public List<Partido[]> getHistorialFases() { return historialFases; }
}