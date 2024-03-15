package de.dejan_hopp;

import de.dejan_hopp.io.*;

public class Main {

    public static void main(String[] args) {

        try{
            if (args[0].equals("nogui")){
                System.out.println("Programm ohne GUI gestartet");
            } else {
                System.out.println("FEHLER: Kein gültiger Startparameter!");
            }
        } catch(ArrayIndexOutOfBoundsException aioE){
            System.out.println("Programm ohne Startparameter gestartet");
            new GUI().setVisible(true);
        }


        System.out.println(" Datenblatt-Editor");
        System.out.println("<#################>\n");

        while(true){
            //Menu
            System.out.println("Menü:");
            System.out.println("[1]Datenblatt erstellen ]");
            System.out.println("[2]Datenblatt bearbeiten]");
            System.out.println("[3]Drucken              ]");
            System.out.println("[4]Datenblatt anzeigen  ]");
            System.out.println("[5]Beenden              ]");
            System.out.print(">");

            int in=0;
            try {
                 in=Integer.parseInt(Kbd.readString());
                 if (in<1||in>5) throw new Exception();
            } catch(Exception e) {
                System.out.println("Bitte eine Zahl von 1-5 eingeben");
            }


            switch (in) {
                case 1: create(); break;
                case 2: edit(); break;
                case 3: Print.createPDF();Print.toPrint(true,null); break;
                case 4: show(); break;
                case 5: exit(); break;
            }
        }


    }

    public static void create(){
        System.out.println("\n\nFeature hinzufügen:");
        System.out.println("[1]CPU           ]");
        System.out.println("[2]Grafik        ]");
        System.out.println("[3]Datenträger   ]");
        System.out.println("[4]RAM           ]");
        System.out.println("[5]Betriebssystem]");
        System.out.println("[6]Preis         ]");
        System.out.println("[7]RESET         ]");
        System.out.print(">");

        int in=0;
        try {
            in=Integer.parseInt(Kbd.readString());
            if (in<1||in>5) throw new Exception();
        } catch(Exception e) {
            System.out.println("Bitte eine Zahl von 1-5 eingeben");
        }

        System.out.println();

        switch (in){
            case 1: Features.cpu(); break;
            case 2: Features.gpu(); break;
            case 3: Features.storage(); break;
            case 4: Features.memory(); break;
            case 5: Features.os(); break;
            case 6: Features.setPrice(); break;
            case 7: Features.clearFeatures(); break;
        }

    }

    public static void edit(){
        System.out.println("\n\nFeature bearbeiten:");
        System.out.println("[1]CPU           ]");
        System.out.println("[2]Grafik        ]");
        System.out.println("[3]Datenträger   ]");
        System.out.println("[4]RAM           ]");
        System.out.println("[5]Betriebssystem]");
        System.out.println("[6]Preis         ]");
        System.out.println("[7]RESET         ]");
        System.out.print(">");

        int in=0;
        try {
            in=Integer.parseInt(Kbd.readString());
            if (in<1||in>5) throw new Exception();
        } catch(Exception e) {
            System.out.println("Bitte eine Zahl von 1-5 eingeben");
        }

        System.out.println();

        switch (in){
            case 1: Features.cpu(); break;
            case 2: Features.gpu(); break;
            case 3: Features.storage(); break;
            case 4: Features.memory(); break;
            case 5: Features.os(); break;
            case 6: Features.setPrice(); break;
            case 7: Features.clearFeatures(); break;
        }
    }


    public static void show(){
        DisplayConsole.show(Datenblatt.cpu, Datenblatt.gpu, Datenblatt.storage, Datenblatt.memory, Datenblatt.os, Datenblatt.price);
    }

    public static void exit(){
        System.out.println("Beenden...");
        System.exit(0);
    }

}