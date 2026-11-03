package com.danielgib.practicaheladeria.gui;

import com.danielgib.practicaheladeria.base.Helado;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Ventana {
    public JPanel panel1;
    public JButton nuevoButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JList list1;
    public DatePicker fechaCompraDatePicker;
    public JTextField toppingsTxt;
    public JSpinner numBolasInt;
    public JTextField precioTxt;
    public JRadioButton conoRadioButton;
    public JRadioButton tarrinaRadioButton;
    public JRadioButton conAzucarRadioButton;
    public JRadioButton sinAzucarRadioButton;
    public JComboBox saborBox;
    public JTextArea observaciones;
    public JComboBox tamanoBox;
    public JLabel tamanoLbl;
    public JButton editarButton;
    public JButton eliminarButton;

    public JFrame frame;
    public  DefaultListModel<Helado> dlmHelado;

    public Ventana() {
        frame = new JFrame("Heladeria");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        initComponents();
    }

    public void initComponents() {
        dlmHelado = new DefaultListModel<Helado>();
        list1.setModel(dlmHelado);

        saborBox.addItem("Chocolate");
        saborBox.addItem("Fresa");
        saborBox.addItem("Vainilla");
        saborBox.addItem("Limón");
        saborBox.addItem("Pistacho");
        saborBox.setSelectedIndex(-1);
    }


}
