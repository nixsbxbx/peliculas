import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class TestingPeliculas {

    public static void printStringArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        Files files = new Files(); // solo se necesita para el método no estático
        BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "peliculas.txt";  // Archivo donde se encuentran las películas

        List<Pelicula> peliculasList = new ArrayList<>();

        // Leer las películas desde el archivo peliculas.txt
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(", ");
                String nombre = datos[0];
                String genero = datos[1];
                int año = Integer.parseInt(datos[2]);

                peliculasList.add(new Pelicula(genero, nombre, año));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Arreglo de títulos de películas
        String[] titulos = new String[peliculasList.size()];
        for (int i = 0; i < peliculasList.size(); i++) {
            titulos[i] = peliculasList.get(i).getNombre();
        }

        // Contar cuántas películas hay por cada género
        Map<String, Integer> generosContados = new HashMap<>();
        for (Pelicula p : peliculasList) {
            generosContados.put(p.getGenero(), generosContados.getOrDefault(p.getGenero(), 0) + 1);
        }

        // Buscar la película más vieja y la más nueva
        Pelicula masVieja = Collections.min(peliculasList, Comparator.comparingInt(Pelicula::getAno));
        Pelicula masNueva = Collections.max(peliculasList, Comparator.comparingInt(Pelicula::getAno));

        // Imprimir los resultados
        System.out.println("Películas leídas:");
        printStringArray(titulos);

        System.out.println("\nContar películas por género:");
        for (Map.Entry<String, Integer> entry : generosContados.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        System.out.println("\nLa película más vieja es: " + masVieja.getNombre() + " (" + masVieja.getAno() + ")");
        System.out.println("La película más nueva es: " + masNueva.getNombre() + " (" + masNueva.getAno() + ")");
        System.out.println("Total de películas: " + peliculasList.size());

        // Crear archivo con los resultados
        System.out.println("Escribe el nombre del nuevo archivo para guardar los resultados:");
        String resultadoFileName = bufer.readLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultadoFileName))) {
            // Contar cuántas películas hay por cada género
            for (Map.Entry<String, Integer> entry : generosContados.entrySet()) {
                writer.write(entry.getKey() + " - " + entry.getValue() + "\n");
            }

            // Información sobre las películas más viejas y más nuevas
            writer.write("\nLa película más vieja es: " + masVieja.getNombre() + " (" + masVieja.getAno() + ")\n");
            writer.write("La película más nueva es: " + masNueva.getNombre() + " (" + masNueva.getAno() + ")\n");

            // Total de películas
            writer.write("\nTotal de películas en el archivo: " + peliculasList.size() + "\n");

            System.out.println("Archivo creado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Pelicula {
    private String genero;
    private String nombre;
    private int ano;

    public Pelicula(String genero, String nombre, int ano) {
        this.genero = genero;
        this.nombre = nombre;
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAno() {
        return ano;
    }
}

class Files {
    // Métodos estáticos (simulados para este ejemplo)
    public static int[] fileToIntArray(String fileName) {
        // Implementación para leer archivo de enteros
        return new int[]{27, 29, 33, 68, 10, 1, 15, 87, 95};
    }

    public static String[] fileToStringArray(String fileName) {
        // Implementación para leer archivo de cadenas (películas)
        return new String[]{"Película A", "Película B", "Película C", "Película D"};
    }

    // Método no estático para escribir enteros en un archivo
    public void writeIntArrayToFile(String fileName, int[] array) {
        // Implementación para escribir un arreglo de enteros en un archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
