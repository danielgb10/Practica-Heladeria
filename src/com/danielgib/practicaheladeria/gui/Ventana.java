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
    private JButton nuevoHeladoButton;
    private JButton exportarButton;
    private JButton importarButton;

    public JFrame frame;
    public DefaultListModel<Helado> dlmHelado;

}
