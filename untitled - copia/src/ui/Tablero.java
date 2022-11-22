package ui;

import domain.Jugador;
import domain.Territorio;
import util.FondoSwing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Tablero extends JPanel {
    JPanel mapa;
    SpringLayout SL;
    JLabel guardar=new JLabel();
    JLabel sonido=new JLabel();
    Font auxFont;

    public Tablero(){
        SL=new SpringLayout();
        this.setLayout(SL);
        java.net.URL url = this.getClass().getResource("/IO/risk-colored-small.jpg");

        try {
            FondoSwing fondo = new FondoSwing(ImageIO.read(url));
            mapa =this;
            mapa.setBorder(fondo);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.setPreferredSize(new Dimension(1200,675));
        this.setMinimumSize(new Dimension(1200,675));
    }

    public void colocarFichas(HashMap<Jugador, HashSet<Territorio>> hmJugadores){
        this.removeAll();
        try {
            InputStream is =  getClass().getResourceAsStream("/IO/Ink Free.ttf");
            auxFont = Font.createFont(Font.TRUETYPE_FONT, is);
            auxFont = auxFont.deriveFont(Font.BOLD, 17f);
        } catch (Exception ex) {
            System.err.println(" No se cargo la fuente");
            auxFont = new Font("Arial", Font.PLAIN, 14);
        }
        for(Map.Entry<Jugador, HashSet<Territorio>> m:hmJugadores.entrySet()){
            Color color=m.getKey().getColor();
            ImageIcon ii= m.getKey().getImagen();
            for(Territorio t:m.getValue()){
                JLabel LabelTerritorio=new JLabel("  "+ t.getTropas() +"  ");
                LabelTerritorio.setBackground(color);
                LabelTerritorio.setOpaque(true);

                LabelTerritorio.setFont(auxFont);

                Image image= ii.getImage().getScaledInstance(23, 35, Image.SCALE_SMOOTH);
                JLabel LabelTropa= new JLabel(new ImageIcon(image));

                SL.putConstraint(SpringLayout.NORTH, LabelTropa, t.getY(), SpringLayout.NORTH,this);
                SL.putConstraint(SpringLayout.WEST, LabelTropa, t.getX()-11, SpringLayout.WEST,this);
                this.add(LabelTropa);

                SL.putConstraint(SpringLayout.NORTH, LabelTerritorio, t.getY(), SpringLayout.NORTH,this);
                SL.putConstraint(SpringLayout.WEST, LabelTerritorio, t.getX(), SpringLayout.WEST,this);
                this.add(LabelTerritorio);
            }
        }
        java.net.URL url = this.getClass().getResource("/IO/guardar.png");
        guardar.setIcon(new javax.swing.ImageIcon(url));
        this.add(guardar);
        SL.putConstraint(SpringLayout.NORTH, guardar, 610, SpringLayout.NORTH,this);
        SL.putConstraint(SpringLayout.WEST, guardar, 5, SpringLayout.WEST,this);
        java.net.URL url1 = this.getClass().getResource("/IO/sonido.png");
        sonido.setIcon(new javax.swing.ImageIcon(url1));
        this.add(sonido);
        SL.putConstraint(SpringLayout.NORTH, sonido, 620, SpringLayout.NORTH,this);
        SL.putConstraint(SpringLayout.WEST,sonido, 67, SpringLayout.WEST,this);
        this.updateUI();
    }

    public JLabel getGuardar(){
        return guardar;
    }

    public JLabel getSonido(){
        return sonido;
    }

    public void setImagenInicial(){
        java.net.URL urlsonido = this.getClass().getResource("/IO/sonido.png");
        sonido.setIcon(new javax.swing.ImageIcon(urlsonido));
        this.add(sonido);
        SL.putConstraint(SpringLayout.NORTH, sonido, 620, SpringLayout.NORTH,this);
        SL.putConstraint(SpringLayout.WEST,sonido, 5, SpringLayout.WEST,this);
        this.updateUI();

        JLabel imagen= new JLabel();
        java.net.URL url = this.getClass().getResource("/IO/Imagen1.png");
        imagen.setIcon(new javax.swing.ImageIcon(url));
        SL.putConstraint(SpringLayout.NORTH, imagen, 150, SpringLayout.NORTH,this);
        SL.putConstraint(SpringLayout.WEST, imagen, 90, SpringLayout.WEST,this);
        this.add(imagen);
        JLabel soldados= new JLabel();
        java.net.URL url2 = this.getClass().getResource("/IO/soldados.gif");
        soldados.setIcon(new javax.swing.ImageIcon(url2));
        SL.putConstraint(SpringLayout.NORTH, soldados, 340, SpringLayout.NORTH,this);
        SL.putConstraint(SpringLayout.WEST, soldados, 560, SpringLayout.WEST,this);
        this.add(soldados);
        JLabel soldados2= new JLabel();
        java.net.URL url3 = this.getClass().getResource("/IO/soldados2.gif");
        soldados2.setIcon(new javax.swing.ImageIcon(url3));
        SL.putConstraint(SpringLayout.NORTH, soldados2, 340, SpringLayout.NORTH,this);
        SL.putConstraint(SpringLayout.WEST, soldados2, 65, SpringLayout.WEST,this);
        this.add(soldados2);

        JLabel mapa= new JLabel();
        java.net.URL urlmapa = this.getClass().getResource("/IO/risk-colored-small.jpg");
        mapa.setIcon(new javax.swing.ImageIcon(urlmapa));
        SL.putConstraint(SpringLayout.NORTH, mapa, 0, SpringLayout.NORTH,this);
        SL.putConstraint(SpringLayout.WEST, mapa, 0, SpringLayout.WEST,this);
        this.add(mapa);
    }

    public void setImagenSecundaria(){
        removeAll();
        JLabel imagen= new JLabel();
        java.net.URL url = this.getClass().getResource("/IO/Imagen1.png");
        imagen.setIcon(new javax.swing.ImageIcon(url));
        SL.putConstraint(SpringLayout.NORTH, imagen, 150, SpringLayout.NORTH,this);
        SL.putConstraint(SpringLayout.WEST, imagen, 90, SpringLayout.WEST,this);
        this.add(imagen);
        JLabel soldados= new JLabel();
        java.net.URL url2 = this.getClass().getResource("/IO/caballo2.gif");
        soldados.setIcon(new javax.swing.ImageIcon(url2));
        SL.putConstraint(SpringLayout.NORTH, soldados, 280, SpringLayout.NORTH,this);
        SL.putConstraint(SpringLayout.WEST, soldados, 600, SpringLayout.WEST,this);
        this.add(soldados);
        JLabel soldados2= new JLabel();
        java.net.URL url3 = this.getClass().getResource("/IO/img1.gif");
        soldados2.setIcon(new javax.swing.ImageIcon(url3));
        SL.putConstraint(SpringLayout.NORTH, soldados2, 325, SpringLayout.NORTH,this);
        SL.putConstraint(SpringLayout.WEST, soldados2, 55, SpringLayout.WEST,this);
        this.add(soldados2);
        this.updateUI();
        java.net.URL url1 = this.getClass().getResource("/IO/sonido.png");
        sonido.setIcon(new javax.swing.ImageIcon(url1));
        this.add(sonido);
        SL.putConstraint(SpringLayout.NORTH, sonido, 620, SpringLayout.NORTH,this);
        SL.putConstraint(SpringLayout.WEST,sonido, 5, SpringLayout.WEST,this);
    }

}
