import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestingPeliculas {

    // Método que cuenta cuántas películas hay por género
    public static int[] contarGenero(String[] peliculas) {
        int[] generosCont = new int[5]; // 0: Fantasía, 1: Romance, 2: Terror, 3: Comedia, 4: Otro
        for (String pelicula : peliculas) {
            String genero = pelicula.split(",")[1].trim().toLowerCase();
            switch (genero) {
                case "Fantasia":
                    generosCont[0]++;
                    break;
                case "Romance":
                    generosCont[1]++;
                    break;
                case "Terror":
                    generosCont[2]++;
                    break;
                case "Comedia":
                    generosCont[3]++;
                    break;
                default:
                    generosCont[4]++;
            }
        }
        return generosCont;
    }

    // Película más vieja
    public static String[] PeliculaVieja(String[] peliculas) {
        String[] resultado = new String[2];
        int minAño = Integer.MAX_VALUE;

        for (String pelicula : peliculas) {
            String[] partes = pelicula.split(",");
            int año = Integer.parseInt(partes[2].trim());
            if (año < minAño) {
                minAño = año;
                resultado[0] = partes[0].trim();
                resultado[1] = String.valueOf(año);
            }
        }
        return resultado;
    }

    // Película más nueva
    public static String[] PeliculaNueva(String[] peliculas) {
        String[] resultado = new String[2];
        int maxAño = Integer.MIN_VALUE;

        for (String pelicula : peliculas) {
            String[] partes = pelicula.split(",");
            int año = Integer.parseInt(partes[2].trim());
            if (año > maxAño) {
                maxAño = año;
                resultado[0] = partes[0].trim();
                resultado[1] = String.valueOf(año);
            }
        }
        return resultado;
    }

    // Total de películas
    public static int TotalPeliculas(String[] peliculas) {
        return peliculas.length;
    }

    // Método main
    public static void main(String[] args) throws IOException {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        Peliculas archivo = new Peliculas();

        System.out.print("Ingresa el nombre del archivo de películas (ej: peliculas.txt): ");
        String nombreArchivo = entrada.readLine();

        String[] peliculas = archivo.fileToStringArray(nombreArchivo);

        if (peliculas == null || peliculas.length == 0) {
            System.out.println("No se encontraron películas.");
            return;
        }

        int[] generos = contarGenero(peliculas);
        System.out.println("Películas por género:");
        System.out.println("Fantasía: " + generos[0]);
        System.out.println("Romance: " + generos[1]);
        System.out.println("Terror: " + generos[2]);
        System.out.println("Comedia: " + generos[3]);
        System.out.println("Otro: " + generos[4]);

        String[] vieja = PeliculaVieja(peliculas);
        System.out.println("Película más vieja: " + vieja[0] + " (" + vieja[1] + ")");

        String[] nueva = PeliculaNueva(peliculas);
        System.out.println("Película más nueva: " + nueva[0] + " (" + nueva[1] + ")");

        System.out.println("Total de películas: " + TotalPeliculas(peliculas));
    }
}
