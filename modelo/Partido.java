package modelo;

import java.io.Serializable;
import java.util.Random;

public class Partido implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Equipo local;
    private Equipo visitante;
    private int golesLocal;
    private int golesVisitante;
    private boolean definidoPorPenales;
    private Equipo ganador;

    public Partido(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
    }

    public Equipo jugar(Random random) {
        this.golesLocal = random.nextInt(5);
        this.golesVisitante = random.nextInt(5);

        if (golesLocal > golesVisitante) {
            this.ganador = local;
        } else if (golesVisitante > golesLocal) {
            this.ganador = visitante;
        } else {
            this.definidoPorPenales = true;
            this.ganador = resolverPenales(random);
        }
        return this.ganador;
    }

    private Equipo resolverPenales(Random random) {
        int penalesLocal = 0;
        int penalesVisitante = 0;
        while (penalesLocal == penalesVisitante) {
            penalesLocal = random.nextInt(6);
            penalesVisitante = random.nextInt(6);
        }
        return (penalesLocal > penalesVisitante) ? local : visitante;
    }

    public Equipo getLocal() { return local; }
    public Equipo getVisitante() { return visitante; }
    public int getGolesLocal() { return golesLocal; }
    public int getGolesVisitante() { return golesVisitante; }
    public boolean isDefinidoPorPenales() { return definidoPorPenales; }
    public Equipo getGanador() { return ganador; }
}