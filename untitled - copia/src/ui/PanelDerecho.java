package ui;

import domain.Gestor;
import domain.Jugador;
import domain.Territorio;
import util.FondoSwing;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class PanelDerecho extends JPanel {
    JButton buttonAceptar, buttonLuchar, buttonContinuar, buttonPasar,buttonMover,buttonDesplazar,buttonALuchar,buttonAnadir,buttonFinalizar;
    JButton buttonNuevaPartida, buttonCargarPartida;
    Font fuente, fuente2;
    Gestor g;
    int numJugadores, cont_jugadores;
    JPanel mapa;
    JComboBox<Integer> comboNumJugadores;
    JTextField j1,j2,j3,j4,j5,j6,numero;
    public JComboBox<Territorio> comboFrontera, comboTerritorio, comboMover, comboTerritorioMover;
    JLabel errorFichero;
    JLabel errorTropas=new JLabel();
    public PanelDerecho(Gestor g){
        JLabel numeroJugadores=new JLabel(" Numero de Jugadores: ");
        try {
            InputStream is =  getClass().getResourceAsStream("/IO/Font.ttf");
            fuente = Font.createFont(Font.TRUETYPE_FONT, is);
            fuente = fuente.deriveFont(Font.BOLD, 29f);
            fuente2 = fuente.deriveFont(Font.PLAIN, 19f);
        } catch (Exception ex) {
            System.err.println(" No se cargo la fuente");
            Font auxFont=numeroJugadores.getFont();
            this.fuente=new Font("Ink Free", auxFont.getStyle(), 25);
            this.fuente2=new Font("Ink Free", auxFont.getStyle(), 15);
        }


        this.g=g;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setBackground(Color.ORANGE);

        java.net.URL url = this.getClass().getResource("/IO/marco.jpg");

        try {
            FondoSwing fondo = new FondoSwing(ImageIO.read(url));
            mapa =this;
            mapa.setBorder(fondo);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        //this.setMinimumSize(new Dimension(300,500));

        errorFichero=new JLabel("");

        this.buttonAceptar=new JButton("Aceptar");
        buttonAceptar.setMaximumSize(new Dimension(200,20));
        buttonAceptar.setFont(fuente2);
        Border line = new LineBorder(Color.ORANGE);
        Border margin = new EmptyBorder(10,10,10,10);
        Border compound = new CompoundBorder(line,margin);
        buttonNuevaPartida=new JButton("Nueva Partida");
        buttonNuevaPartida.setMaximumSize(new Dimension(200,20));
        buttonNuevaPartida.setFont(fuente2);
        buttonCargarPartida=new JButton("Cargar Partida");
        buttonCargarPartida.setMaximumSize(new Dimension(200,20));
        buttonCargarPartida.setFont(fuente2);

        JLabel risk= new JLabel("       BIENVENIDO!!!        ");
        risk.setFont(fuente);
        this.add(Box.createVerticalGlue());
        this.add(risk);
        risk.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(buttonNuevaPartida);
        buttonNuevaPartida.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(buttonCargarPartida);
        buttonCargarPartida.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(errorFichero);
        errorFichero.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalGlue());

        buttonContinuar=new JButton("Continuar");
        buttonContinuar.setMaximumSize(new Dimension(200,20));
        buttonContinuar.setFont(fuente2);
        buttonPasar=new JButton("Pasar");
        buttonPasar.setMaximumSize(new Dimension(200,20));
        buttonPasar.setFont(fuente2);
        buttonMover=new JButton("Mover");
        buttonMover.setMaximumSize(new Dimension(200,20));
        buttonMover.setFont(fuente2);
        buttonLuchar=new JButton("Luchar");
        buttonLuchar.setMaximumSize(new Dimension(200,20));
        buttonLuchar.setFont(fuente2);
        buttonDesplazar=new JButton("Desplazar");
        buttonDesplazar.setMaximumSize(new Dimension(200,20));
        buttonDesplazar.setFont(fuente2);
        buttonAnadir=new JButton("Añadir:");
        buttonAnadir.setMaximumSize(new Dimension(200,20));
        buttonAnadir.setFont(fuente2);
        buttonALuchar=new JButton("A luchar!!!");
        buttonALuchar.setMaximumSize(new Dimension(200,20));
        buttonALuchar.setFont(fuente2);
        buttonFinalizar=new JButton("Finalizar Ronda");
        buttonFinalizar.setMaximumSize(new Dimension(200,20));
        buttonFinalizar.setFont(fuente2);
        errorTropas.setFont(fuente2);

        buttonNuevaPartida.addActionListener(e->this.numJugadores());
    }

    public JButton getCargar(){
        return buttonCargarPartida;
    }

    public void setGestor(Gestor g){
        this.g=g;
    }

    public void setContJugadores(int cont){
        this.cont_jugadores=cont;
    }

    public void numJugadores(){
        this.removeAll();
        JLabel numeroJugadores=new JLabel("   Numero de Jugadores:   ");
        numeroJugadores.setFont(fuente);
        this.add(Box.createVerticalGlue());
        this.add(numeroJugadores);
        numeroJugadores.setAlignmentX(Component.CENTER_ALIGNMENT);

        Integer[] vectorGrupos = new Integer[4];
        vectorGrupos[0] = 3;
        vectorGrupos[1] = 4;
        vectorGrupos[2] = 5;
        vectorGrupos[3] = 6;
        comboNumJugadores = new JComboBox<>(vectorGrupos);
        this.add(comboNumJugadores);
        comboNumJugadores.setFont(fuente2);
        comboNumJugadores.setMaximumSize(new Dimension(200,20));
        comboNumJugadores.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(comboNumJugadores);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(buttonAceptar);
        buttonAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalGlue());
        this.updateUI();
    }

    public void nombrarJugadores(){
        this.numJugadores=((Integer)comboNumJugadores.getSelectedItem());
        this.removeAll();
        this.add(Box.createVerticalGlue());
        JLabel nombrar=new JLabel("   Nombrar jugadores:  ");
        nombrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        nombrar.setFont(fuente);
        this.add(nombrar);
        j1=new JTextField("Jugador 1");
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(j1);
        j1.setAlignmentX(Component.CENTER_ALIGNMENT);
        j1.setFont(fuente2);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        j1.setMaximumSize(new Dimension(200,30));
        j2=new JTextField("Jugador 2");
        j2.setFont(fuente2);
        this.add(j2);
        j2.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        j2.setMaximumSize(new Dimension(200,30));
        j3=new JTextField("Jugador 3");
        j3.setFont(fuente2);
        j3.setMaximumSize(new Dimension(200,30));
        this.add(j3);
        j3.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        if(numJugadores>3) {
            j4 = new JTextField("Jugador 4");
            j4.setMaximumSize(new Dimension(200,30));
            this.add(j4);
            j4.setAlignmentX(Component.CENTER_ALIGNMENT);
            j4.setFont(fuente2);
            this.add(Box.createRigidArea(new Dimension(10, 20)));}
        if(numJugadores>4){
            j5=new JTextField("Jugador 5");
            j5.setMaximumSize(new Dimension(200,30));
            this.add(j5);
            j5.setAlignmentX(Component.CENTER_ALIGNMENT);
            j5.setFont(fuente2);
            this.add(Box.createRigidArea(new Dimension(10, 20)));}
        if(numJugadores>5){
            j6=new JTextField("Jugador 6");
            j6.setMaximumSize(new Dimension(200,30));
            this.add(j6);
            j6.setFont(fuente2);
            j6.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(buttonContinuar);
        buttonContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.updateUI();
        buttonContinuar.addActionListener(actionEvent -> {
            int numTropas=(10-numJugadores)*5;
            String s=j1.getText();
            Jugador a= new Jugador(j1.getText(),numTropas,Color.BLUE,"/IO/soldadoAzul.png");
            g.addJugador(a);
            g.addJugador(new Jugador(j2.getText(),numTropas,Color.MAGENTA,"/IO/soldadoMorado.png"));
            g.addJugador(new Jugador(j3.getText(),numTropas,Color.GREEN,"/IO/soldadoVerde.png"));
            if(numJugadores>3) {
                g.addJugador(new Jugador(j4.getText(),numTropas,Color.GRAY,"/IO/soldadoGris.png"));
            }
            if(numJugadores>4) {
                g.addJugador(new Jugador(j5.getText(),numTropas,Color.red,"/IO/soldadoRojo.png"));
            }
            if(numJugadores>5) {
                g.addJugador(new Jugador(j6.getText(),numTropas,Color.YELLOW,"/IO/soldadoAmarillo.png"));
            }
            g.repartoRandomTerritorios();
            g.rellenarRandomTerritorio();
        });
        this.add(Box.createVerticalGlue());
    }

    public JButton getContinuar(){
        return buttonContinuar;
    }

    public JButton getAceptar() {
        return buttonAceptar;
    }

    public Territorio getTerritorioAtaque() {
        return (Territorio) comboTerritorio.getSelectedItem();
    }
    public Territorio getTerritorioDefensa() {
        return (Territorio) comboFrontera.getSelectedItem();
    }

    public void anadirfichas(Jugador j){
        this.removeAll();
        this.add(Box.createVerticalGlue());
        JLabel numJugador=new JLabel("  "+j.getNombre()+"  ");
        numJugador.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel numJugadorImagen= new JLabel(j.getImagen());
        numJugador.setFont(fuente);
        this.add(numJugadorImagen);
        numJugadorImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(numJugador);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        JLabel numFichas= new JLabel("          Numero de fichas a añadir:          ");
        numFichas.setFont(fuente2);
        this.add(numFichas);
        numFichas.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        numero=new JTextField("1");
        numero.setFont(fuente2);
        numero.setMaximumSize(new Dimension(200,25));
        numero.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(numero);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        JLabel a=new JLabel("Añadir fichas a:");
        this.add(a);
        a.setFont(fuente2);
        a.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        comboTerritorioMover=new JComboBox<>(g.getTerritoriosJugador(j));
        comboTerritorioMover.setMaximumSize(new Dimension(200,25));
        comboTerritorioMover.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboTerritorioMover.setFont(fuente2);
        this.add(comboTerritorioMover);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(buttonAnadir);
        buttonAnadir.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(buttonALuchar);
        buttonALuchar.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalGlue());
        this.updateUI();
    }

    public JButton getAnadir(){
        return buttonAnadir;
    }

    public JButton getALuchar(){
        return buttonALuchar;
    }

    public void lucha(Jugador j){
        JLabel numJugador=new JLabel("  "+j.getNombre()+"  ");
        numJugador.setFont(fuente);
        JLabel atacarLabel=new JLabel("                   Atacar desde:                    ");
        atacarLabel.setFont(fuente2);
        JLabel aLabel=new JLabel("a:");
        aLabel.setFont(fuente2);
        JLabel numJugadorImagen= new JLabel(j.getImagen());
        buttonAceptar.setMaximumSize(new Dimension(200,20));

        Vector<Territorio> vectorTerritorios = g.getTerritoriosJugador(j);

        comboTerritorio = new JComboBox<>(vectorTerritorios);
        comboTerritorio.setMaximumSize(new Dimension(200,25));
        comboFrontera=new JComboBox<>();
        comboTerritorio.addActionListener(e -> {
            Territorio t=(Territorio) comboTerritorio.getSelectedItem();
            assert t != null;
            Vector<Territorio> vectorFrontera = t.getFrontera();
            vectorFrontera.removeIf(vectorTerritorios::contains);
            comboFrontera=new JComboBox<>(vectorFrontera);
            comboFrontera.setMinimumSize(new Dimension(40,25));
            comboFrontera.setMaximumSize(new Dimension(200,25));
            this.mostrarSeccionLucha(numJugadorImagen,numJugador,atacarLabel,comboTerritorio,aLabel);
        });
        comboFrontera.setMinimumSize(new Dimension(40,25));
        comboFrontera.setMaximumSize(new Dimension(200,25));

        this.mostrarSeccionLucha(numJugadorImagen,numJugador,atacarLabel,comboTerritorio,aLabel);
    }
    public JButton getLuchar() {
        return buttonLuchar;
    }

    public JButton getButtonPasar(){
        return buttonPasar;
    }

    public void mostrarSeccionLucha(JLabel numJugadorImagen, JLabel numJugador,JLabel atacarLabel,JComboBox<Territorio> comboTerritorio, JLabel aLabel){
        this.removeAll();
        this.add(Box.createVerticalGlue());
        this.add(numJugadorImagen);
        numJugadorImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(numJugador);
        numJugador.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(atacarLabel);
        atacarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(comboTerritorio);
        comboTerritorio.setFont(fuente2);
        comboTerritorio.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(aLabel);
        aLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(comboFrontera);
        comboFrontera.setFont(fuente2);
        comboFrontera.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(buttonLuchar);
        buttonLuchar.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(buttonPasar);
        buttonPasar.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalGlue());
        this.updateUI();
    }

    public void moverTropas(Jugador j){
        this.removeAll();
        this.add(Box.createVerticalGlue());
        JLabel numJugador=new JLabel("  "+j.getNombre()+"  ");
        JLabel numJugadorImagen= new JLabel(j.getImagen());
        this.add(numJugadorImagen);
        numJugadorImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        numJugador.setFont(fuente);
        JLabel atacarLabel=new JLabel("            Numero de tropas a mover:             ");
        atacarLabel.setFont(fuente2);
        numero=new JTextField("1");
        numero.setAlignmentX(Component.CENTER_ALIGNMENT);
        numero.setMinimumSize(new Dimension(40,25));
        numero.setMaximumSize(new Dimension(200,25));
        numero.setPreferredSize(new Dimension(100,25));
        numero.setFont(fuente2);
        this.add(numJugador);
        numJugador.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(atacarLabel);
        atacarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(numero);
        numero.setFont(fuente2);
        numero.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(buttonMover);
        buttonMover.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        errorTropas.setForeground(Color.RED);
        errorTropas.setText("");
        this.add(errorTropas);
        errorTropas.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalGlue());
        this.updateUI();
    }

    public int getNumTropasMovidas(){
        return Integer.parseInt(numero.getText());
    }

    public JButton getMover(){
        return buttonMover;
    }

    public void etapaUltima(Jugador j) {
        this.removeAll();
        JLabel numJugador = new JLabel("  "+j.getNombre()+"  ");
        numJugador.setFont(fuente);
        JLabel numJugadorImagen= new JLabel(j.getImagen());
        JLabel moverLabel = new JLabel("          Numero de tropas a mover:          ");
        numero= new JTextField("1");
        numero.setMinimumSize(new Dimension(40,25));
        numero.setMaximumSize(new Dimension(200,20));
        JLabel desdeLabel = new JLabel("Desde:");
        JLabel hastaLabel = new JLabel("Hasta");

        if(numJugadores-1>cont_jugadores)
            cont_jugadores++;
        else
            cont_jugadores=0;

        comboMover = new JComboBox<>();
        comboMover.setMinimumSize(new Dimension(40,25));
        comboMover.setMaximumSize(new Dimension(200, 20));
        Vector<Territorio> vectorTerritorios = g.getTerritoriosJugador(j);
        comboTerritorioMover = new JComboBox<>(vectorTerritorios);
        comboTerritorioMover.setMinimumSize(new Dimension(40,25));
        comboTerritorioMover.setMaximumSize(new Dimension(200, 20));
        comboTerritorioMover.addActionListener(e -> {
            Territorio t = (Territorio) comboTerritorioMover.getSelectedItem();
            assert t != null;
            Vector<Territorio> vectorFrontera = g.moverTropas(t,j);
            comboMover = new JComboBox<>(vectorFrontera);
            comboMover.setMaximumSize(new Dimension(300, 20));
            this.mostrarUltimaEtapa(numJugadorImagen,numJugador, moverLabel, desdeLabel, hastaLabel);
        });
        this.mostrarUltimaEtapa(numJugadorImagen,numJugador, moverLabel, desdeLabel, hastaLabel);
    }

    public void mostrarUltimaEtapa(JLabel numJugadorImagen, JLabel numJugador, JLabel moverLabel, JLabel desdeLabel, JLabel hastaLabel){
        this.removeAll();
        this.add(Box.createVerticalGlue());
        this.add(numJugadorImagen);
        numJugadorImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(numJugador);
        numJugador.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(moverLabel);
        moverLabel.setFont(fuente2);
        moverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(numero);
        numero.setFont(fuente2);
        numero.setAlignmentX(Component.CENTER_ALIGNMENT);
        numero.setMinimumSize(new Dimension(40,25));
        numero.setMaximumSize(new Dimension(200,25));
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(desdeLabel);
        desdeLabel.setFont(fuente2);
        desdeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(comboTerritorioMover);
        comboTerritorioMover.setFont(fuente2);
        comboTerritorioMover.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboTerritorioMover.setMinimumSize(new Dimension(40,25));
        comboTerritorioMover.setMaximumSize(new Dimension(200,25));
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(hastaLabel);
        hastaLabel.setFont(fuente2);
        hastaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(comboMover);
        comboMover.setFont(fuente2);
        comboMover.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboMover.setMinimumSize(new Dimension(40,25));
        comboMover.setMaximumSize(new Dimension(200,25));
        this.add(Box.createRigidArea(new Dimension(10, 25)));
        this.add(buttonDesplazar);
        buttonDesplazar.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        this.add(buttonFinalizar);
        buttonFinalizar.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(10, 20)));
        errorTropas.setForeground(Color.RED);
        errorTropas.setText("");
        this.add(errorTropas);
        errorTropas.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalGlue());
        this.updateUI();
    }

    public void setNumJugadores(int numero){
        numJugadores=numero;
    }

    public int getContJugadores(){
        return cont_jugadores;
    }

    public Territorio getComboMover() {
        return (Territorio) comboMover.getSelectedItem();
    }

    public Territorio getComboTerritorioMover() {
        return (Territorio) comboTerritorioMover.getSelectedItem();
    }

    public JButton getButtonMover(){
        return buttonDesplazar;
    }

    public JButton getButtonFinalizar(){
        return buttonFinalizar;
    }

    public void errorTropas(){
        errorTropas.setForeground(Color.RED);
        errorTropas.setText("No tienes suficientes tropas");
    }

    public void borrarError(){
        errorTropas.setForeground(Color.RED);
        errorTropas.setText("");
    }

    public void errorFichero(){
        errorFichero.setForeground(Color.RED);
        errorFichero.setText("Fichero no compatible");
    }
}