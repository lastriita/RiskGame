package ui;

import IO.IOFiles;
import IO.Sonido;
import domain.Gestor;
import domain.Jugador;
import util.Luchar;
import util.RangoTropasException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Risk extends JFrame
{
    Gestor g;
    PanelDerecho panelDerecho;
    Tablero t;
    Sonido sonido= new Sonido();

    public static void main (String[] argv)
    {
        new Risk();
    }

    public Risk() {
        super();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.g = new Gestor();
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setPreferredSize(new Dimension(1920,1080));
        setMinimumSize(new Dimension(1920,1070));
        //setMinimumSize(new Dimension(500,500));
        setResizable(false);

        panelDerecho = new PanelDerecho(g);

        JPanel tablero=new JPanel();
        tablero.setLayout(null);
        t= new Tablero();
        t.setBounds(0,0,1200,675);
        tablero.add(t);
        this.add(tablero);
        this.add(panelDerecho, BorderLayout.EAST);

        t.setImagenInicial();

        JButton buttonCargar=panelDerecho.getCargar();
        buttonCargar.addActionListener(e->this.cargarPartida());

        JButton buttonAceptar=panelDerecho.getAceptar();
        buttonAceptar.addActionListener(actionEvent -> {
            panelDerecho.nombrarJugadores();
            t.setImagenSecundaria();
                }
        );
        JButton buttonContinuar=panelDerecho.getContinuar();
        buttonContinuar.addActionListener(actionEvent -> {
            t.colocarFichas(g.gethmJugadores());
            panelDerecho.anadirfichas(g.getJugadores().get(panelDerecho.getContJugadores()));
        }
        );
        JButton buttonAnadir=panelDerecho.getAnadir();
        buttonAnadir.addActionListener(actionEvent ->{
            int tropas=panelDerecho.getNumTropasMovidas();
            if(tropas<100) {
                panelDerecho.getComboTerritorioMover().addTropas(tropas);
            }
            t.colocarFichas(g.gethmJugadores());
        });

        JButton buttonALuchar=panelDerecho.getALuchar();
        buttonALuchar.addActionListener(actionEvent -> panelDerecho.lucha(g.getJugadores().get(panelDerecho.getContJugadores())));

        Luchar luchar=new Luchar();
        JButton buttonLuchar=panelDerecho.getLuchar();
        buttonLuchar.addActionListener(actionEvent -> {
                    int a=panelDerecho.getTerritorioAtaque().getTropas();
                    int d=panelDerecho.getTerritorioDefensa().getTropas();
                    int[] res=luchar.lucha(a,d);
                    panelDerecho.getTerritorioAtaque().setTropas(res[0]);
                    panelDerecho.getTerritorioDefensa().setTropas(res[1]);
                    if(res[1]==0) {
                        g.territorioConquistado(panelDerecho.getTerritorioDefensa(), g.getJugadores().get(panelDerecho.getContJugadores()));
                        if(res[0]>2)
                            panelDerecho.moverTropas(g.getJugadores().get(panelDerecho.getContJugadores()));
                        else {
                            panelDerecho.getTerritorioDefensa().setTropas(1);
                            panelDerecho.getTerritorioAtaque().setTropas(1);
                            panelDerecho.lucha(g.getJugadores().get(panelDerecho.getContJugadores()));
                        }
                    }
                    t.colocarFichas(g.gethmJugadores());
                }
        );
        JButton buttonMover=panelDerecho.getMover();
        buttonMover.addActionListener(actionEvent -> {
            try {
                int num = panelDerecho.getNumTropasMovidas();
                if (num < panelDerecho.getTerritorioAtaque().getTropas() && num > 0) {
                    panelDerecho.getTerritorioDefensa().addTropas(num);
                    panelDerecho.getTerritorioAtaque().addTropas(-num);
                    t.colocarFichas(g.gethmJugadores());
                    panelDerecho.lucha(g.getJugadores().get(panelDerecho.getContJugadores()));
                } else {
                    throw new RangoTropasException();
                }
            }catch(RangoTropasException e){
                panelDerecho.errorTropas();
            }
        }
        );
        JButton buttonPasar=panelDerecho.getButtonPasar();
        buttonPasar.addActionListener(actionEvent -> {
            panelDerecho.setNumJugadores(g.getNumJugadores());
            t.colocarFichas(g.gethmJugadores());
            panelDerecho.etapaUltima(g.getJugadores().get(panelDerecho.getContJugadores()));
        });

        JButton buttonDesplazar=panelDerecho.getButtonMover();
        buttonDesplazar.addActionListener(actionEvent ->{
            try {
                int tropas = panelDerecho.getNumTropasMovidas();
                if (tropas < panelDerecho.getComboTerritorioMover().getTropas() && tropas > 0) {
                    panelDerecho.getComboMover().addTropas(tropas);
                    panelDerecho.getComboTerritorioMover().addTropas(-tropas);
                    panelDerecho.borrarError();
                } else {
                    throw new RangoTropasException();
                }
            }catch(RangoTropasException e){
                panelDerecho.errorTropas();
            }
            t.colocarFichas(g.gethmJugadores());
        });
        JButton buttonFinalizar=panelDerecho.getButtonFinalizar();
        buttonFinalizar.addActionListener(actionEvent ->{
            for(Jugador j:g.getJugadores()){
                if(g.getTerritoriosJugador(j).size()<1)
                    g.actualizarJugadores(j);
            }
            panelDerecho.setNumJugadores(g.getNumJugadores());
            panelDerecho.anadirfichas(g.getJugadores().get(panelDerecho.getContJugadores()));
            t.colocarFichas(g.gethmJugadores());
        });

        JLabel guardar=t.getGuardar();
        guardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                IOFiles.write(g);
            }
        });
        JLabel sonidoLabel=t.getSonido();
        sonidoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                sonido.stop();
            }
        });
        //musica
        java.net.URL url = this.getClass().getResource("/IO/piratas-del-caribe-cancion-completa.wav");
        sonido.ReproducirSonido(url);

        JLabel proyecto = new JLabel("RiskIcaI 2020");
        JLabel asignatura = new JLabel("Programacion orientada a objetos");
        JLabel alumno = new JLabel("Alvaro Lastra Aragoneses");

        Font auxFont=proyecto.getFont();
        proyecto.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),17));

        JPanel copyright = new JPanel();
        this.add(copyright, BorderLayout.SOUTH);

        copyright.setLayout(new GridLayout(1, 3));
        copyright.add(proyecto);
        copyright.add(asignatura);
        copyright.add(alumno);

        this.setTitle("Risk");

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void cargarPartida(){
        JFileChooser fileChooser= new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(this);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Objetos", "obj",".obj");
        fileChooser.addChoosableFileFilter(filter);
        if (seleccion == JFileChooser.APPROVE_OPTION)
        {
            File fichero = fileChooser.getSelectedFile();
            if (fichero!=null) {
                g = IOFiles.read(fichero);
                panelDerecho.setGestor(g);
                panelDerecho.setContJugadores(1);
                panelDerecho.anadirfichas(g.getJugadores().get(panelDerecho.getContJugadores()));
                t.colocarFichas(g.gethmJugadores());
            }else
                panelDerecho.errorFichero();
        }
    }
}
