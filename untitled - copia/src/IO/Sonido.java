package IO;

import javax.sound.sampled.*;
import java.io.IOException;

public class Sonido{
    Clip clip;

    public void ReproducirSonido(java.net.URL url){

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    public void stop(){
        if(clip.isRunning())
            clip.stop();
        else{
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

    }
}