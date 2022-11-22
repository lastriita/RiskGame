package domain;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class Jugador implements java.io.Serializable{
    private String nombre;
    private int tropas;
    private Color color;
    private ImageIcon imagen;
    HashSet<Integer> cartas= new HashSet<>();
    public Jugador(String nombre, int tropas, Color c, String s){
        this.nombre=nombre;
        this.tropas=tropas;
        this.color=c;
        java.net.URL url = this.getClass().getResource(s);
        this.imagen=new javax.swing.ImageIcon(url);
    }

    public ImageIcon getImagen(){
        return imagen;
    }

    public void getCartas(){
        int contSoldado=0;
        int contCanion=0;
        int contCaballo=0;
        for(Integer c:cartas){
            if(c==1)
                contSoldado++;
            else if(c==0)
                contCaballo++;
            else
                contCanion++;
        }
    }

    public void addCarta(){
        cartas.add(1);
    }

    public int getTropas() {
        return tropas;
    }

    public Color getColor() {
        return color;
    }

    public String getNombre() {
        return nombre;
    }

}
