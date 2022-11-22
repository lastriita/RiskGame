package domain;

import java.util.HashSet;
import java.util.Vector;

public class Territorio implements java.io.Serializable{
    private String nombre;
    private int x;
    private int y;
    private int tropas=0;
    HashSet<Territorio> frontera= new HashSet<>();

    public Territorio(String nombre, int x, int y){
        this.nombre=nombre;
        this.x=x;
        this.y=y;
    }

    public int getTropas() {
        return tropas;
    }

    public void setTropas(int tropas) {
        this.tropas = tropas;
    }

    public void addTropas(int tropas) {
        this.tropas += tropas;
    }

    public String getNombre(){
        return nombre;
    }

    public void addFrontera(Territorio t){
        frontera.add(t);
    }

    public boolean compareTo(String nombre){
        return nombre.equals(this.getNombre());
    }

    public Vector<Territorio> getFrontera(){
        return new Vector<>(frontera);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        return nombre;
    }
}
