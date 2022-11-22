package domain;

import util.Luchar;

import java.awt.*;
import java.util.Vector;

public class AI  extends Jugador{

    public AI(String nombre, int tropas, Color c){
        super(nombre,tropas,c,"");
    }

    public String anadirTropasAI(Vector<Territorio> territorios, int numTropas){
        String cadena="";
        for(int i=numTropas; i>0; i--){
            int rndm=(int)(Math.random()*territorios.size());
            territorios.get(rndm).addTropas(1);
        }
        return cadena;
    }

    public String lucharAI(Vector<Territorio> territorios, Gestor g){
        String cadena="";
        for(int i=0; i<territorios.size(); i++){
            for(Territorio t:territorios.get(i).getFrontera()){
                if(t.getTropas()<2*(territorios.get(i).getTropas())){
                    this.movimientoLucha(territorios.get(i),t,g);
                }else if(t.getTropas()>=2*(territorios.get(i).getTropas())&&t.getTropas()<(territorios.get(i).getTropas())){
                    int rndm=(int)(Math.random()*3);
                    if(rndm==0)
                        this.movimientoLucha(territorios.get(i),t,g);
                }else{
                    int rndm=(int)(Math.random()*15);
                    if(rndm==0)
                        this.movimientoLucha(territorios.get(i),t,g);
                }
            }
        }
        return cadena;
    }

    public void movimientoLucha(Territorio ataque, Territorio defensa, Gestor g){
        Luchar lucha=new Luchar();
        int a=ataque.getTropas();
        int d=defensa.getTropas();
        int[] res=lucha.lucha(a,d);
        ataque.setTropas(res[0]);
        defensa.setTropas(res[1]);
        if(res[1]==0) {
            g.territorioConquistado(defensa, this);
            if(res[0]>2) {
                defensa.setTropas(res[0]-2);
                ataque.setTropas(2);
            }else {
                defensa.setTropas(1);
                ataque.setTropas(1);
            }
        }
    }

    public String moverAI(Vector<Territorio> territorios, Gestor g){
        String cadena="";
        boolean comprobador;
        for (Territorio territorio : territorios) {
            comprobador = true;
            for (Territorio t : territorio.getFrontera()) {
                if (!g.getTerritoriosJugador(this).contains(t))
                    comprobador = false;
            }
            if(comprobador=true){
                for (Territorio t : territorio.getFrontera()) {
                    if (!g.getTerritoriosJugador(this).contains(t))
                        comprobador = false;
                }
            }
        }
        return cadena;
    }
}
