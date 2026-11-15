package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistenciaTorneo {
    private final String rutaArchivo;

    public PersistenciaTorneo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardar(Torneo torneo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(torneo);
        }
    }

    public Torneo cargar() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (Torneo) ois.readObject();
        }
    }

    public boolean existeArchivoGuardado() {
        File archivo = new File(rutaArchivo);
        return archivo.exists() && archivo.length() > 0;
    }
}