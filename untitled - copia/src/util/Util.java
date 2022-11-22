package util;

import domain.Territorio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** Clase de funcionalidad variada que proporciona una ayuda al alumno */
public class Util
{
    public static final String SEPARADOR = ";";
    /**
     Detiene el programa el tiempo especificado
     @param segundos número de segundos a esperar
     */
    public static void wait(int segundos)
    {
        try
        {
            Thread.sleep(segundos*1000);
        }
        catch(Exception e)
        {

        }
    }

    public static void leerTerritorios(ArrayList<Territorio> todosTerritorios){
        BufferedReader bufferLectura = null;
        try {
            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new InputStreamReader(Util.class.getResourceAsStream("/IO/Territorios.csv")));

            // Leer una linea del archivo
            String linea = bufferLectura.readLine();

            while (linea != null) {
                // Sepapar la linea leída con el separador definido previamente
                String[] campos = linea.split(SEPARADOR);
                todosTerritorios.add(new Territorio(campos[0],Integer. parseInt(campos[1]),Integer. parseInt(campos[2])));
                // Volver a leer otra línea del fichero
                linea = bufferLectura.readLine();
            }

            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new InputStreamReader(Util.class.getResourceAsStream("/IO/Territorios.csv")));

            // Leer una linea del archivo
            linea = bufferLectura.readLine();
            int k=0;
            while (linea != null) {
                // Sepapar la linea leída con el separador definido previamente
                String[] campos = linea.split(SEPARADOR);
                for(int j=3; j<campos.length; j++) {
                    for (Territorio todosTerritorio : todosTerritorios) {
                        if (todosTerritorio.compareTo(campos[j]))
                            todosTerritorios.get(k).addFrontera(todosTerritorio);
                    }
                }
                k++;
                // Volver a leer otra línea del fichero
                linea = bufferLectura.readLine();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

