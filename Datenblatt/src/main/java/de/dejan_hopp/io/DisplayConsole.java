package de.dejan_hopp.io;

import de.dejan_hopp.Datenblatt;
import java.util.Properties;

public class DisplayConsole {
    public static void show(Properties cpu, Properties gpu, Properties storage, Properties memory, Properties os, Properties price){
        System.out.println("Prozessor:");
        System.out.println("+"+cpu.getProperty("manufacturer"));
        System.out.println("+"+cpu.getProperty("name"));
        System.out.println("+"+cpu.getProperty("cores")+" Kerne - "+cpu.getProperty("threads")+" Threads");
        System.out.println("+"+cpu.getProperty("mhr")+" MHz");
        System.out.println("--------------------");
        System.out.println("Grafik:");
        System.out.println("+"+gpu.getProperty("manufacturer"));
        System.out.println("+"+gpu.getProperty("name"));
        System.out.println("+"+gpu.getProperty("mhz")+" MHz");
        System.out.println("--------------------");
        System.out.println("Datenträger:");

        if(Datenblatt.storageAnzahl==1){
            System.out.print("Datenträger 1:");
            System.out.println(storage.getProperty("drive1Capacity")+" GB "+storage.getProperty("drive1Type"));
        } else if (Datenblatt.storageAnzahl==2) {
            //Datenträger 1
            System.out.print("Datenträger 1:");
            System.out.println(storage.getProperty("drive1Capacity")+" GB "+storage.getProperty("drive1Type"));

            //Datenträger 2
            System.out.print("Datenträger 2:");
            System.out.println(storage.getProperty("drive2Capacity")+" GB "+storage.getProperty("drive2Type"));

        } else if (Datenblatt.storageAnzahl==3) {
            //Datenträger 1
            System.out.print("Datenträger 1:");
            System.out.println(storage.getProperty("drive1Capacity")+" GB "+storage.getProperty("drive1Type"));

            //Datenträger 2
            System.out.print("Datenträger 2:");
            System.out.println(storage.getProperty("drive2Capacity")+" GB "+storage.getProperty("drive2Type"));

            //Datenträger 3
            System.out.print("Datenträger 3:");
            System.out.println(storage.getProperty("drive3Capacity")+" GB "+storage.getProperty("drive3Type"));

        } else if (Datenblatt.storageAnzahl==4) {
            //Datenträger 1
            System.out.print("Datenträger 1:");
            System.out.println(storage.getProperty("drive1Capacity")+" GB "+storage.getProperty("drive1Type"));

            //Datenträger 2
            System.out.print("Datenträger 2:");
            System.out.println(storage.getProperty("drive2Capacity")+" GB "+storage.getProperty("drive2Type"));

            //Datenträger 3
            System.out.print("Datenträger 3:");
            System.out.println(storage.getProperty("drive3Capacity")+" GB "+storage.getProperty("drive3Type"));

            //Datenträger 4
            System.out.print("Datenträger 4:");
            System.out.println(storage.getProperty("drive4Capacity")+" GB "+storage.getProperty("drive4Type"));
        }

        System.out.println("--------------------");
        System.out.println("Arbeitsspeicher:");
        System.out.println("+"+memory.getProperty("capacity")+" GB "+memory.getProperty("ddrVersion"));
        System.out.println("--------------------");
        System.out.println("Betriebssystem:");
        System.out.println(os.getProperty("name"));
        System.out.println("--------------------");
        System.out.println("Preis:");
        System.out.println(price.getProperty("EUR"));
        System.out.println("--------------------\n\n");
    }
}