package com.danielgib.practicaheladeria.gui;

import com.danielgib.practicaheladeria.base.Helado;

import javax.swing.*;

public class Ventana {
    public JPanel panel1;
    public JRadioButton conoRadioButton;
    public JRadioButton tarrinaRadioButton;
    public JTextField textField1;
    public JRadioButton conAzucarRadioButton;
    public JRadioButton sinAzucarRadioButton;
    public JButton nuevoHeladoButton;
    public JButton exportarButton;
    public JButton importarButton;

    public JFrame frame;
    public  DefaultListModel<Helado> dlmHelado;

    public Ventana() {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
