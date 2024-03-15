package de.dejan_hopp;

import de.dejan_hopp.io.Delay;
import de.dejan_hopp.io.Print;
import de.dejan_hopp.io.pdf2PNG;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GUI extends javax.swing.JFrame implements ActionListener {

    private static String programVersion="1.0";

    // Menü
    private JMenuBar menuBar=new JMenuBar();
    private JMenu menuDatei=new JMenu("Datei");         //Datei-Menü
    private JMenuItem menuDateiPrint=new JMenuItem("Drucken");
    private JMenuItem menuDateiReset=new JMenuItem("Zurücksetzen");
    private JMenuItem menuDateiSettings=new JMenuItem("Einstellungen");
    private JMenuItem menuDateiAbout=new JMenuItem("Über");
    private JMenuItem menuDateiClose=new JMenuItem("Schließen");

    // Logo
    private String logoPfad="logo.png";

    // Tabs mit den einzelnen PC-Komponenten:
    private JTabbedPane tabPane=new JTabbedPane(JTabbedPane.TOP);
    private JPanel panelTabCPU=new JPanel(null);
    private JPanel panelTabGPU=new JPanel(null);
    private JPanel panelTabStorage=new JPanel(null);
    private JPanel panelTabMemory=new JPanel(null);
    private JPanel panelTabOS=new JPanel(null);
    private JPanel panelTabPrice=new JPanel(null);
    private JPanel panelTabVorschau=new JPanel(null);


    // CPU-Eingabefelder und Beschriftungen:
    private JLabel labelCPUManufacturer=new JLabel("Hersteller:");
    private String cpuManufacturerList[]={"AMD", "Intel"};
    private JComboBox inputComboCPUManufacturer =new JComboBox(cpuManufacturerList);
    private JLabel labelCPUName=new JLabel("CPU-Name:");
    private JTextField inputCPUName=new JTextField();
    private JLabel labelCPUCores=new JLabel("Anzahl der Kerne:");
    private JTextField inputCPUCores=new JTextField();
    private JLabel labelCPUThreads=new JLabel("Anzahl der Threads:");
    private JTextField inputCPUThreads=new JTextField();
    private JLabel labelCPUFrequency=new JLabel("Frequenz in MHz:");
    private JTextField inputCPUFrequency=new JTextField();


    // GPU-Eingabefelder und Beschriftungen:
    private JLabel labelGPUManufacturer=new JLabel("Hersteller:");
    private String gpuManufacturerList[]={"NVIDIA","AMD", "Intel"};
    private JComboBox inputComboGPUManufacturer =new JComboBox(gpuManufacturerList);
    private JLabel labelGPUName=new JLabel("GPU-Name:");
    private JTextField inputGPUName=new JTextField();
    private JLabel labelGPUFrequency=new JLabel("Frequenz in MHz:");
    private JTextField inputGPUFrequency=new JTextField();


    // Datenträger-Eingabefelder und Beschriftungen:

    private String driveCapacities[]={"120","128","240","250","256","480","500","512","750","1000","1024","2000","3000","4000","5000"};
    private String driveTypes[]={"SSD","HDD","SSHD"};

    // 1
    private JLabel labelDrive1=new JLabel("Datenträger 1:");
    private JLabel labelDrive1Capacity=new JLabel("Kapazität in GB");
    private JComboBox inputDrive1Capacity=new JComboBox(driveCapacities);
    private JComboBox inputDrive1Type=new JComboBox(driveTypes);

    // 2
    private JCheckBox inputDrive2Check=new JCheckBox();
    private JLabel labelDrive2=new JLabel("Datenträger 2:");
    private JLabel labelDrive2Capacity=new JLabel("Kapazität in GB");
    private JComboBox inputDrive2Capacity=new JComboBox(driveCapacities);
    private JComboBox inputDrive2Type=new JComboBox(driveTypes);

    // 3
    private JCheckBox inputDrive3Check=new JCheckBox();
    private JLabel labelDrive3=new JLabel("Datenträger 3:");
    private JLabel labelDrive3Capacity=new JLabel("Kapazität in GB");
    private JComboBox inputDrive3Capacity=new JComboBox(driveCapacities);
    private JComboBox inputDrive3Type=new JComboBox(driveTypes);

    // 4
    private JCheckBox inputDrive4Check=new JCheckBox();
    private JLabel labelDrive4=new JLabel("Datenträger 4:");
    private JLabel labelDrive4Capacity=new JLabel("Kapazität in GB");
    private JComboBox inputDrive4Capacity=new JComboBox(driveCapacities);
    private JComboBox inputDrive4Type=new JComboBox(driveTypes);


    // RAM-Eingabefelder und Beschriftungen:
    private JLabel labelRAMVersion=new JLabel("Version:");
    private String ramVersions[]={"DDR3","DDR4","DDR5"};
    private JComboBox inputRAMVersion=new JComboBox(ramVersions);
    private JLabel labelRAMCapacity=new JLabel("Kapazität in GB");
    private JTextField inputRAMCapacity=new JTextField();


    // OS-Eingabefelder und Beschriftungen:
    private JLabel labelOSName=new JLabel("Betriebssystem:");
    private JTextField inputOSName=new JTextField();


    // Price-Eingabefelder und Beschriftungen:
    private JLabel labelPrice =new JLabel("Preis in €:");
    private JTextField inputPrice =new JTextField();

    // Vorschau-Eingabefelder und Beschriftungen:
    private static JLabel labelVorschau=new JLabel();
    private JButton buttonVorschau=new JButton("Aktualisieren");

    public GUI(){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600,400);
        setResizable(false);
        setTitle("Computer-Datenblatt-Ersteller Pro");
        setLocationRelativeTo(null);

        ImageIcon img=new ImageIcon(logoPfad);
        setIconImage(img.getImage());

        setJMenuBar(menuBar);
        menuBar.add(menuDatei);

        menuDatei.add(menuDateiPrint);
        menuDatei.add(menuDateiReset);
        menuDatei.add(menuDateiSettings);
        menuDatei.add(menuDateiAbout);
        menuDatei.add(menuDateiClose);

        menuDatei.setMnemonic('D');
        menuDateiClose.setMnemonic('S');
        menuDateiSettings.setMnemonic('E');
        menuDateiPrint.setMnemonic('D');
        menuDateiReset.setMnemonic('r');
        menuDateiAbout.setMnemonic('Ü');

        menuDateiClose.addActionListener(this);
        menuDateiSettings.addActionListener(this);
        menuDateiPrint.addActionListener(this);
        menuDateiReset.addActionListener(this);
        menuDateiAbout.addActionListener(this);

        KeyStroke keyStrokeMenuPrint=KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
        menuDateiPrint.setAccelerator(keyStrokeMenuPrint);

        KeyStroke keyStrokeMenuClose=KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK);
        menuDateiClose.setAccelerator(keyStrokeMenuClose);

        KeyStroke keyStrokeMenuSettings=KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, KeyEvent.CTRL_DOWN_MASK);
        menuDateiSettings.setAccelerator(keyStrokeMenuSettings);

        KeyStroke keyStrokeMenuReset=KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK);
        menuDateiReset.setAccelerator(keyStrokeMenuReset);

        KeyStroke keyStrokeMenuAbout=KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.CTRL_DOWN_MASK);
        menuDateiAbout.setAccelerator(keyStrokeMenuAbout);

        add(tabPane);

        tabPane.addTab("CPU", panelTabCPU);
        tabPane.addTab("Grafik", panelTabGPU);
        tabPane.addTab("Datenträger", panelTabStorage);
        tabPane.addTab("RAM", panelTabMemory);
        tabPane.addTab("Betriebssystem", panelTabOS);
        tabPane.addTab("Preis", panelTabPrice);
        tabPane.addTab("Vorschau", panelTabVorschau);


        panelTabCPU.setFocusCycleRoot(true);
        panelTabGPU.setFocusCycleRoot(true);
        panelTabStorage.setFocusCycleRoot(true);
        panelTabMemory.setFocusCycleRoot(true);
        panelTabOS.setFocusCycleRoot(true);
        panelTabPrice.setFocusCycleRoot(true);


        setupTabTraversalKeys(tabPane);

        int labelWidth=120;
        int labelHeight=15;
        int labelRow=10;
        int inputRow=labelWidth+labelRow+2;
        int addRow2=300;

        // CPU-Eingabefelder und Beschriftungen:
        panelTabCPU.add(labelCPUManufacturer);
        labelCPUManufacturer.setBounds(labelRow,10,labelWidth,labelHeight);

        panelTabCPU.add(inputComboCPUManufacturer);
        inputComboCPUManufacturer.setBounds(inputRow,6,100,20);

        panelTabCPU.add(labelCPUName);
        labelCPUName.setBounds(labelRow,30,labelWidth,labelHeight);

        panelTabCPU.add(inputCPUName);
        inputCPUName.setBounds(inputRow,27,100,20);

        panelTabCPU.add(labelCPUCores);
        labelCPUCores.setBounds(labelRow,50,labelWidth,labelHeight);

        panelTabCPU.add(inputCPUCores);
        inputCPUCores.setBounds(inputRow,47,30,20);

        panelTabCPU.add(labelCPUThreads);
        labelCPUThreads.setBounds(labelRow,70,labelWidth,labelHeight);

        panelTabCPU.add(inputCPUThreads);
        inputCPUThreads.setBounds(inputRow,67,30,20);

        panelTabCPU.add(labelCPUFrequency);
        labelCPUFrequency.setBounds(labelRow,90,labelWidth,labelHeight);

        panelTabCPU.add(inputCPUFrequency);
        inputCPUFrequency.setBounds(inputRow,87,50,20);

        // GPU-Eingabefelder und Beschriftungen:
        panelTabGPU.add(labelGPUManufacturer);
        labelGPUManufacturer.setBounds(labelRow,10,labelWidth,labelHeight);

        panelTabGPU.add(inputComboGPUManufacturer);
        inputComboGPUManufacturer.setBounds(inputRow,6,100,20);

        panelTabGPU.add(labelGPUName);
        labelGPUName.setBounds(labelRow,30,labelWidth,labelHeight);

        panelTabGPU.add(inputGPUName);
        inputGPUName.setBounds(inputRow,27,100,20);

        panelTabGPU.add(labelGPUFrequency);
        labelGPUFrequency.setBounds(labelRow,50,labelWidth,labelHeight);

        panelTabGPU.add(inputGPUFrequency);
        inputGPUFrequency.setBounds(inputRow,47,50,20);


        // Datenträger-Eingabefelder und Beschriftungen:

        inputDrive2Check.addActionListener(this);
        inputDrive3Check.addActionListener(this);
        inputDrive4Check.addActionListener(this);
        buttonVorschau.addActionListener(this);

        inputDrive3Check.setEnabled(false);
        inputDrive4Check.setEnabled(false);

        // 1
        panelTabStorage.add(labelDrive1);
        labelDrive1.setBounds(labelRow+50,10,labelWidth,labelHeight);

        panelTabStorage.add(labelDrive1Capacity);
        labelDrive1Capacity.setBounds(labelRow,30,labelWidth,labelHeight);

        panelTabStorage.add(inputDrive1Capacity);
        inputDrive1Capacity.setBounds(inputRow-30,27,100,20);

        panelTabStorage.add(inputDrive1Type);
        inputDrive1Type.setBounds(inputRow-30,48,60,20);

        // 2
        panelTabStorage.add(labelDrive2);
        labelDrive2.setBounds(labelRow+50,100,labelWidth,labelHeight);

        panelTabStorage.add(inputDrive2Check);
        inputDrive2Check.setBounds(labelRow+180,95,20,20);

        panelTabStorage.add(labelDrive2Capacity);
        labelDrive2Capacity.setBounds(labelRow,120,labelWidth,labelHeight);

        panelTabStorage.add(inputDrive2Capacity);
        inputDrive2Capacity.setBounds(inputRow-30,120,100,20);

        panelTabStorage.add(inputDrive2Type);
        inputDrive2Type.setBounds(inputRow-30,141,60,20);

        // 3
        panelTabStorage.add(labelDrive3);
        labelDrive3.setBounds(labelRow+50+addRow2,10,labelWidth,labelHeight);

        panelTabStorage.add(inputDrive3Check);
        inputDrive3Check.setBounds(labelRow+180+addRow2,5,20,20);

        panelTabStorage.add(labelDrive3Capacity);
        labelDrive3Capacity.setBounds(labelRow+addRow2,27,labelWidth,labelHeight);

        panelTabStorage.add(inputDrive3Capacity);
        inputDrive3Capacity.setBounds(inputRow-30+addRow2,27,100,20);

        panelTabStorage.add(inputDrive3Type);
        inputDrive3Type.setBounds(inputRow-30+addRow2,48,60,20);

        // 4
        panelTabStorage.add(labelDrive4);
        labelDrive4.setBounds(labelRow+50+addRow2,100,labelWidth,labelHeight);

        panelTabStorage.add(inputDrive4Check);
        inputDrive4Check.setBounds(labelRow+180+addRow2,95,20,20);

        panelTabStorage.add(labelDrive4Capacity);
        labelDrive4Capacity.setBounds(labelRow+addRow2,120,labelWidth,labelHeight);

        panelTabStorage.add(inputDrive4Capacity);
        inputDrive4Capacity.setBounds(inputRow-30+addRow2,120,100,20);

        panelTabStorage.add(inputDrive4Type);
        inputDrive4Type.setBounds(inputRow-30+addRow2,141,60,20);


        // RAM-Eingabefelder und Beschriftungen:
        panelTabMemory.add(labelRAMVersion);
        labelRAMVersion.setBounds(labelRow,10,labelWidth,labelHeight);

        panelTabMemory.add(inputRAMVersion);
        inputRAMVersion.setBounds(inputRow,6,100,20);

        panelTabMemory.add(labelRAMCapacity);
        labelRAMCapacity.setBounds(labelRow,30,labelWidth,labelHeight);

        panelTabMemory.add(inputRAMCapacity);
        inputRAMCapacity.setBounds(inputRow,27,30,20);

        // OS-Eingabefelder und Beschriftungen:
        panelTabOS.add(labelOSName);
        labelOSName.setBounds(labelRow,10,labelWidth,labelHeight);

        panelTabOS.add(inputOSName);
        inputOSName.setBounds(inputRow,6,200,20);

        //Price-Eingabefelder und Beschriftungen:
        panelTabPrice.add(labelPrice);
        labelPrice.setBounds(labelRow,10,labelWidth,labelHeight);

        panelTabPrice.add(inputPrice);
        inputPrice.setBounds(inputRow-45,6,50,20);

        // Vorschau-Eingabefelder und Beschriftungen:
        panelTabVorschau.add(buttonVorschau);
        buttonVorschau.setMnemonic('A');
        buttonVorschau.setBounds(labelRow,10,labelWidth-5,labelHeight+10);

        panelTabVorschau.add(labelVorschau);
        labelVorschau.setBounds(150,5,300,300);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==menuDateiClose){
            dispose();
        } else if (e.getSource()==menuDateiSettings) {
            System.out.println("Einstellungsfenster ploppt auf...");
            new SettingsWindow(this).setVisible(true);
        } else if (e.getSource()==menuDateiPrint) {
            inputToProperty();
            Print.createPDF();
            Print.toPrint(false, this);
        } else if (e.getSource()==menuDateiReset) {
            clearFeatures();
        } else if (e.getSource()==menuDateiAbout) {
            System.out.println("Ein Fenster öffnet sich, in dem angezeigt wird, von wem das Programm ist...");
            aboutDialog(this);
        } else if (e.getSource()==inputDrive2Check) {
            if (inputDrive2Check.isSelected()){
                inputDrive3Check.setEnabled(true);
                inputDrive3Check.setSelected(false);

                inputDrive4Check.setEnabled(false);
                inputDrive4Check.setSelected(false);
            } else {
                inputDrive3Check.setEnabled(false);
                inputDrive3Check.setSelected(false);

                inputDrive4Check.setEnabled(false);
                inputDrive4Check.setSelected(false);
            }

        } else if (e.getSource()==inputDrive3Check) {
            if (inputDrive3Check.isSelected()){
                inputDrive4Check.setEnabled(true);
                inputDrive4Check.setSelected(false);
            } else {
                inputDrive4Check.setEnabled(false);
                inputDrive4Check.setSelected(false);
            }
        } else if (e.getSource()==buttonVorschau) {
            view();
            Print.printerList();
        }
    }

    public void view(){
        inputToProperty();
        Print.createPDF();
        Delay.delay(100);
        pdf2PNG.convert();

        BufferedImage img=null;
        try {
            img=ImageIO.read(new File("Datenblatt.png"));
        } catch (IOException e) {
            System.out.println("Fehler");
        }
        Image image= img.getScaledInstance(labelVorschau.getWidth(), labelVorschau.getHeight(), Image.SCALE_SMOOTH);
        labelVorschau.setIcon(new ImageIcon(image));
        labelVorschau.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        File x=new File("Datenblatt.pdf");
        File y=new File("Datenblatt.png");

    }

    public static void aboutDialog(JFrame positionParent) {
        JLabel aboutLabel=new JLabel("<html><center>Datenblatt-Ersteller<br>für PCs und Notebooks<br><br>Version: " + programVersion + "<br><br>Programmiert von<br>Dejan Hopp<br>\u00a92023");
        aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JOptionPane.showMessageDialog(positionParent, aboutLabel, "Über", JOptionPane.PLAIN_MESSAGE);
    }


    // Inhalte der Textfelder in die Properties schreiben
    public void inputToProperty(){
        Datenblatt.cpu.setProperty("manufacturer", inputComboCPUManufacturer.getSelectedItem().toString());
        Datenblatt.cpu.setProperty("name", inputCPUName.getText());
        Datenblatt.cpu.setProperty("cores", inputCPUCores.getText());
        Datenblatt.cpu.setProperty("threads", inputCPUThreads.getText());
        Datenblatt.cpu.setProperty("mhz", inputCPUFrequency.getText());

        Datenblatt.gpu.setProperty("manufacturer", inputComboGPUManufacturer.getSelectedItem().toString());
        Datenblatt.gpu.setProperty("name", inputGPUName.getText());
        Datenblatt.gpu.setProperty("mhz", inputGPUFrequency.getText());

        Datenblatt.storage.setProperty("drive1Type", inputDrive1Type.getSelectedItem().toString());
        Datenblatt.storage.setProperty("drive1Capacity", inputDrive1Capacity.getSelectedItem().toString());

        if (inputDrive2Check.isSelected()){
            Datenblatt.storageAnzahl=2;
        }
        if (inputDrive3Check.isSelected()){
            Datenblatt.storageAnzahl=3;
        }
        if (inputDrive4Check.isSelected()){
            Datenblatt.storageAnzahl=4;
        }

        if (inputDrive2Check.isSelected()){
            Datenblatt.storage.setProperty("drive2Type", inputDrive2Type.getSelectedItem().toString());
            Datenblatt.storage.setProperty("drive2Capacity", inputDrive2Capacity.getSelectedItem().toString());
        }
        if (inputDrive3Check.isSelected()){
            Datenblatt.storage.setProperty("drive3Type", inputDrive3Type.getSelectedItem().toString());
            Datenblatt.storage.setProperty("drive3Capacity", inputDrive3Capacity.getSelectedItem().toString());
        }
        if (inputDrive4Check.isSelected()){
            Datenblatt.storage.setProperty("drive4Type", inputDrive4Type.getSelectedItem().toString());
            Datenblatt.storage.setProperty("drive4Capacity", inputDrive4Capacity.getSelectedItem().toString());
        }

        Datenblatt.memory.setProperty("ddrVersion", inputRAMVersion.getSelectedItem().toString());
        Datenblatt.memory.setProperty("capacity", inputRAMCapacity.getText());

        Datenblatt.os.setProperty("name", inputOSName.getText());

        Datenblatt.price.setProperty("EUR", inputPrice.getText());
    }


    // Folgende Funktion wurde von https://www.davidc.net/programming/java/how-make-ctrl-tab-switch-tabs-jtabbedpane kopiert:
    // Die Funktion sorgt dafür, dass man mit STRG+Tab und STRG+Shift+Tab durch die Tabs Navigieren kann.
    private void setupTabTraversalKeys(JTabbedPane tabbedPane)
    {
        KeyStroke ctrlTab = KeyStroke.getKeyStroke("ctrl TAB");
        KeyStroke ctrlShiftTab = KeyStroke.getKeyStroke("ctrl shift TAB");

        // Remove ctrl-tab from normal focus traversal
        Set<AWTKeyStroke> forwardKeys = new HashSet<AWTKeyStroke>(tabbedPane.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        forwardKeys.remove(ctrlTab);
        tabbedPane.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);

        // Remove ctrl-shift-tab from normal focus traversal
        Set<AWTKeyStroke> backwardKeys = new HashSet<AWTKeyStroke>(tabbedPane.getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS));
        backwardKeys.remove(ctrlShiftTab);
        tabbedPane.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, backwardKeys);

        // Add keys to the tab's input map
        InputMap inputMap = tabbedPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(ctrlTab, "navigateNext");
        inputMap.put(ctrlShiftTab, "navigatePrevious");
    }

    public void clearFeatures(){
        // Features aus Properties löschen:
        Datenblatt.cpu.clear();
        Datenblatt.gpu.clear();
        Datenblatt.storage.clear();
        Datenblatt.memory.clear();
        Datenblatt.os.clear();
        Datenblatt.price.clear();

        Datenblatt.storageAnzahl=1;

        // Features aus Textfeldern löschen:
        inputCPUName.setText("");
        inputCPUCores.setText("");
        inputCPUThreads.setText("");
        inputCPUFrequency.setText("");

        inputGPUName.setText("");
        inputGPUFrequency.setText("");

        inputDrive2Check.setSelected(false);
        inputDrive3Check.setSelected(false);
        inputDrive4Check.setSelected(false);

        inputDrive3Check.setEnabled(false);
        inputDrive4Check.setEnabled(false);

        inputRAMCapacity.setText("");

        inputOSName.setText("");

        inputPrice.setText("");

    }

    @Override
    public void dispose(){
        System.exit(0);
    }

}
