package de.dejan_hopp;

import de.dejan_hopp.io.Print;
import de.dejan_hopp.io.RWConfigFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JDialog implements ActionListener {
    private JComboBox inputPrinterList=new JComboBox(Print.printerList());
    private JLabel labelPrinterSelect=new JLabel("Drucker:");
    private JButton buttonSave=new JButton("Speichern");
    private JButton buttonCancle=new JButton("Abbrechen");

    public SettingsWindow(JFrame frame){
        setLocationRelativeTo(frame);
        setTitle("Einstellungen");
        setLayout(null);
        setModal(true);
        setSize(400,300);

        buttonSave.addActionListener(this);
        buttonCancle.addActionListener(this);


        // logo
        Image img = new ImageIcon("logo.png").getImage();
        setIconImage(img);

        add(labelPrinterSelect).setBounds(5,10,70,20);
        labelPrinterSelect.setDisplayedMnemonic('D');
        labelPrinterSelect.setLabelFor(inputPrinterList);

        add(inputPrinterList).setBounds(80,10,250,20);
        inputPrinterList.setSelectedItem(RWConfigFile.getPrinterName());

        add(buttonCancle).setBounds(5,230,110,30);
        buttonCancle.setMnemonic('A');

        add(buttonSave).setBounds(120,230,110,30);
        buttonSave.setMnemonic('S');

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==buttonSave){
            System.out.println("Speichern und Einstellungsfenster schließen");
            RWConfigFile.setPrinter(inputPrinterList.getSelectedItem().toString());
            dispose();
        } else if (e.getSource()==buttonCancle) {
            System.out.println("Abbrechen und Einstellungsfenster schließen");
            dispose();
        }
    }
}
