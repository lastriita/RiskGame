package domain;

import util.Util;

import java.util.*;

public class Gestor implements java.io.Serializable{
    HashMap<Jugador, HashSet<Territorio>> hmJugadores= new HashMap<>();
    ArrayList<Territorio> todosTerritorios= new ArrayList<>();

    public Gestor(){
        Util.leerTerritorios(todosTerritorios);
    }

    public HashMap<Jugador, HashSet<Territorio>> gethmJugadores(){
        return hmJugadores;
    }

    public void addJugador(Jugador jugador)
    {
        hmJugadores.put(jugador, new HashSet<>());
    }

    public Vector<Territorio> getTerritoriosJugador(Jugador j){
        return new Vector<>(hmJugadores.get(j));
    }

    public void territorioConquistado(Territorio t, Jugador j){
        for(Map.Entry<Jugador, HashSet<Territorio>> m:hmJugadores.entrySet()){

            if(j.equals(m.getKey())){
                m.getValue().add(t);
            }else{
                if(m.getValue().contains(t)){
                    m.getValue().remove(t);
                }
            }
        }
    }

    public Vector<Jugador> getJugadores(){
        //HashSet<Jugador> j=new HashSet<>();
        Vector<Jugador> j=new Vector<>();
        for(Map.Entry<Jugador, HashSet<Territorio>> m:hmJugadores.entrySet()){
            j.add(m.getKey());
        }
        return j;
    }

    public void rellenarRandomTerritorio()
    {
        int tropas,tterritorio;
        for(Map.Entry<Jugador, HashSet<Territorio>> m:hmJugadores.entrySet()){
            tropas=m.getKey().getTropas();
            while(tropas>0){
            for(Territorio t:m.getValue()){
                tterritorio=(int)(Math.random()*2+1);
                if(tropas-tterritorio>=0) {
                    t.addTropas(tterritorio);
                    tropas = tropas - tterritorio;
                }
            }
            }
        }
    }

    public void repartoRandomTerritorios(){
        ArrayList<Territorio> t=todosTerritorios;
        int i=0;
        int size=todosTerritorios.size();
        while(i<size) {
            for (Map.Entry<Jugador, HashSet<Territorio>> m : hmJugadores.entrySet()) {
                if(i<size) {
                    int index=(int) (Math.random() * (size-1 - i));
                    m.getValue().add(t.get(index));
                    t.remove(index);
                }
                i++;
            }
        }
    }

    public Vector<Territorio> moverTropas(Territorio territorio, Jugador a){
        Vector<Territorio> moverTropas=new Vector<>();
        moverTropas.add(territorio);
        Vector<Territorio> moverCopia=moverTropas;
        int i=0;
        while(i==0) {
            i=1;
            for (int j=0; j<moverCopia.size(); j++) {
                for (Territorio t2: moverCopia.get(j).getFrontera()) {
                    if(hmJugadores.get(a).contains(t2)) {
                        if(!moverCopia.contains(t2)) {
                            moverTropas.add(t2);
                            i = 0;
                        }
                    }
                }
            }
            moverCopia = moverTropas;
        }
        moverTropas.remove(territorio);
        return moverTropas;
    }

    public int getNumJugadores(){
        int numJugadores=0;
        for(Jugador j:hmJugadores.keySet()){
            numJugadores++;
        }
        return numJugadores;
    }

    public void actualizarJugadores(Jugador j){
        hmJugadores.remove(j);
    }
}
