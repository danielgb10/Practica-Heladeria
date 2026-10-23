package com.danielgib.practicaheladeria.gui;

import com.danielgib.practicaheladeria.base.Helado;

import javax.swing.*;

public class Ventana {
    public JPanel panel1;
    private JRadioButton conoRadioButton;
    private JRadioButton tarrinaRadioButton;
    private JTextField textField1;
    private JRadioButton conAzucarRadioButton;
    private JRadioButton sinAzucarRadioButton;

    public JFrame frame;
    public DefaultListModel<Helado> dlmHelado;

    public Ventana() {
        frame = new JFrame("HeladoMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
