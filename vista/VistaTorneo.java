package vista;

import java.util.Scanner;
import modelo.Equipo;
import modelo.Partido;

public class VistaTorneo {
    private Scanner scanner;

    public VistaTorneo() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuPrincipal() {
        System.out.println("\n==========================================");
        System.out.println("       GESTIÓN DE TORNEO ELIMINATORIO     ");
        System.out.println("==========================================");
        System.out.println("1. Simular nuevo torneo y guardar resultados");
        System.out.println("2. Cargar resultados desde archivo binario");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");

        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Ingrese 1, 2 o 3: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("\n[INFO]: " + mensaje);
    }

    public void mostrarError(String error) {
        System.err.println("\n[ERROR]: " + error);
    }

    public void mostrarBienvenida(String nombreTorneo) {
        System.out.println("\n==========================================");
        System.out.println("  TORNEO: " + nombreTorneo.toUpperCase());
        System.out.println("==========================================");
    }

    public void mostrarOrdenSorteo(Equipo[] equiposSorteados) {
        System.out.println("\n[Sorteo completado - Orden de llaves]:");
        for (int i = 0; i < equiposSorteados.length; i++) {
            System.out.println("  Bombonera #" + (i + 1) + ": " + equiposSorteados[i].getNombre());
        }
    }

    public void mostrarFase(Partido[] partidos) {
        int cantidadEquipos = partidos.length * 2;
        System.out.println("\n--- " + obtenerNombreFase(cantidadEquipos) + " ---");

        for (int i = 0; i < partidos.length; i++) {
            Partido p = partidos[i];
            String notaPenales = p.isDefinidoPorPenales() ? " (Penales)" : "";
            System.out.println(
                "Cruce " + (i + 1) + ": " +
                p.getLocal().getNombre() + " [" + p.getGolesLocal() + " - " + p.getGolesVisitante() + "] " +
                p.getVisitante().getNombre() + notaPenales +
                "  --> Avanza: " + p.getGanador().getNombre()
            );
        }
    }

    public void mostrarCampeon(Equipo campeon) {
        System.out.println("\n==========================================");
        System.out.println("  CAMPEÓN OFICIAL: " + campeon.getNombre().toUpperCase());
        System.out.println("==========================================");
    }

    private String obtenerNombreFase(int cantidadEquipos) {
        switch (cantidadEquipos) {
            case 32: return "Dieciseisavos de Final";
            case 16: return "Octavos de Final";
            case 8:  return "Cuartos de Final";
            case 4:  return "Semifinal";
            case 2:  return "FINAL";
            default: return "Ronda de " + cantidadEquipos + " equipos";
        }
    }
}