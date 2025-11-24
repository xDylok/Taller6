package ed.u2.sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArchivosCSV {

    private static final String RUTA_BASE = "src/ed/u2/CSV/";

    public static Cita[] leerCitas(String nombreArchivo) throws IOException {
        List<Cita> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_BASE + nombreArchivo))) {
            String linea = br.readLine(); // leer y descartar encabezado
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    lista.add(new Cita(partes[0], partes[1], LocalDateTime.parse(partes[2])));
                }
            }
        }
        return lista.toArray(new Cita[0]);
    }

    public static Paciente[] leerPacientes(String nombreArchivo) throws IOException {
        List<Paciente> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_BASE + nombreArchivo))) {
            String linea = br.readLine(); // leer y descartar encabezado
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    lista.add(new Paciente(partes[0], partes[1], Integer.parseInt(partes[2])));
                }
            }
        }
        return lista.toArray(new Paciente[0]);
    }

    public static Item[] leerInventario(String nombreArchivo) throws IOException {
        List<Item> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_BASE + nombreArchivo))) {
            String linea = br.readLine(); // leer y descartar encabezado
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    lista.add(new Item(partes[0], partes[1], Integer.parseInt(partes[2])));
                }
            }
        }
        return lista.toArray(new Item[0]);
    }
}