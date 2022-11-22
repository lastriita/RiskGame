package util;

import java.util.ArrayList;
import java.util.Collections;

public class Luchar {
    public int[] lucha(int ataque, int defensa)
    {
        ArrayList<Integer> dadosAtacante= new ArrayList<>();
        ArrayList<Integer> dadosDefensor= new ArrayList<>();
        int[] res=new int[2];
        dadosAtacante.add((int)(Math.random()*6+1));
        if(ataque>2)
            dadosAtacante.add((int)(Math.random()*6+1));
        if(ataque>3)
            dadosAtacante.add((int)(Math.random()*6+1));

        dadosDefensor.add((int)(Math.random()*6+1));
        if(defensa>1)
            dadosDefensor.add((int)(Math.random()*6+1));

        Collections.sort(dadosAtacante);
        Collections.sort(dadosDefensor);

        for(int i=1;i<3&&defensa>0&&ataque>1&&(dadosAtacante.size()-i)>=0&&(dadosDefensor.size()-i)>=0;i++){
            if(dadosAtacante.get(dadosAtacante.size()-i)>dadosDefensor.get(dadosDefensor.size()-i))
                defensa--;
            else
                ataque--;
        }
        if(defensa>0&&ataque>1)
            return this.lucha(ataque,defensa);
        else {
            res[0]=ataque;
            res[1]=defensa;
            return res;
        }
    }
}
