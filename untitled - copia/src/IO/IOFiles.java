package IO;

import domain.Gestor;

import java.io.*;

public class IOFiles {

    public static Gestor read(File file){
        Gestor dibujo= null;
        FileInputStream fis;
        ObjectInputStream ois;
        try{
            fis= new FileInputStream(file);
            ois= new ObjectInputStream(fis);
            dibujo=(Gestor)ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("No existe el archivo");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dibujo;
    }

    public static void write(Gestor g){
        FileOutputStream fos;
        ObjectOutputStream oos;

        try{
            fos= new FileOutputStream("Partida.obj");
            oos= new ObjectOutputStream(fos);
            oos.writeObject(g);
            fos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
