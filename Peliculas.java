import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Peliculas{
    int countFilesLines(String fileName){
        File file; //mayuscula clase minuscula variable. Apunta a un archivo fisico del disco duro
        FileReader reader;//llave con permiso de solo lectura
        BufferedReader bufer;//para recuperar informacion del archivo
        int numLines=0;
        try{
            file = new File("C:\\ProgramasJava2A\\Peliculas\\" + fileName);
            reader = new FileReader(file);
            bufer = new BufferedReader(reader);
            while ( ( bufer.readLine()) != null){
                numLines++;
            }
            reader.close();
        }catch(Exception e){
            System.out.println("Error al leer el archivo: " + e.toString());
        }
        return numLines;
    }
    public int[] fileToIntArray(String fileName){
        File file; //mayuscula clasee minuscula variable. Apunta a un archivo fisico del disco duro
        FileReader reader;//llave con permiso de solo lectura
        BufferedReader bufer;//para recuperar informacion del archivo
        String linea;//una linea de archivo
        int[] array = null;//arreglo de numeros resultantes
        int i = 0;//indice
        int t; //tamaño del arreglo
        try{
        t = countFilesLines(fileName);
        array = new int[t];
        file = new File("C:\\ProgramasJava2A\\Peliculas\\" + fileName);
            reader = new FileReader(file);
            bufer = new BufferedReader(reader);
            //leer cada linea del archivo y almacenarlo en un arreglo de enteros
            while ((linea = bufer.readLine()) != null) {
                array[i] = Integer.parseInt(linea);
                i++;
            }
            reader.close();
        }catch(Exception e){
            System.out.println("Error al leer el archivo: " + e.toString());
        }
        return array;
    }
}