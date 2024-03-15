package de.dejan_hopp;

import de.dejan_hopp.io.Kbd;

import java.io.IOException;


public class Features {

    public static void cpu(){
        System.out.println("--------------------");
        System.out.println("CPU-Eigenschaften:");
        System.out.print("Hersteller:");
        Datenblatt.cpu.setProperty("manufacturer", Kbd.readString());
        System.out.print("CPU-Name:");
        Datenblatt.cpu.setProperty("name",Kbd.readString());
        System.out.print("Anzahl der Kerne:");
        Datenblatt.cpu.setProperty("cores",Kbd.readString());
        System.out.print("Anzahl der Threads:");
        Datenblatt.cpu.setProperty("threads",Kbd.readString());
        System.out.print("Taktfrequenz in MHz:");
        Datenblatt.cpu.setProperty("mhz",Kbd.readString());
    }

    public static void gpu(){
        System.out.println("--------------------");
        System.out.println("Grafik-Eigenschaften:");
        System.out.print("Hersteller:");
        Datenblatt.gpu.setProperty("manufacturer",Kbd.readString());
        System.out.print("GPU-Name:");
        Datenblatt.gpu.setProperty("name",Kbd.readString());
        System.out.print("Taktfrequenz in MHz:");
        Datenblatt.gpu.setProperty("mhz",Kbd.readString());
    }

    public static void storage(){
        System.out.println("--------------------");
        System.out.println("Datenträger:");
        System.out.print("Anzahl:");
        try {
            Datenblatt.storageAnzahl=Kbd.readInt();
        }catch (IOException ioe){
            System.out.println("FEHLER!: Es wurde keine Zahl eingegeben!");
        }

        if(Datenblatt.storageAnzahl==1){
            System.out.print("Datenträgertyp(SSD oder HDD):");
            Datenblatt.storage.setProperty("drive1Type",Kbd.readString());
            System.out.print("Kapazität in GB:");
            Datenblatt.storage.setProperty("drive1Capacity",Kbd.readString());
        } else if (Datenblatt.storageAnzahl==2) {
            //Datenträger 1
            System.out.print("1. Datenträgertyp(SSD oder HDD):");
            Datenblatt.storage.setProperty("drive1Type",Kbd.readString());
            System.out.print("Kapazität in GB:");
            Datenblatt.storage.setProperty("drive1Capacity",Kbd.readString());

            //Datenträger 2
            System.out.print("2. Datenträgertyp(SSD oder HDD):");
            Datenblatt.storage.setProperty("drive2Type",Kbd.readString());
            System.out.print("Kapazität in GB:");
            Datenblatt.storage.setProperty("drive2Capacity",Kbd.readString());

        } else if (Datenblatt.storageAnzahl==3) {
            //Datenträger 1
            System.out.print("1. Datenträgertyp(SSD oder HDD):");
            Datenblatt.storage.setProperty("drive1Type",Kbd.readString());
            System.out.print("Kapazität in GB:");
            Datenblatt.storage.setProperty("drive1Capacity",Kbd.readString());

            //Datenträger 2
            System.out.print("2. Datenträgertyp(SSD oder HDD):");
            Datenblatt.storage.setProperty("drive2Type",Kbd.readString());
            System.out.print("Kapazität in GB:");
            Datenblatt.storage.setProperty("drive2Capacity",Kbd.readString());

            //Datenträger 3
            System.out.print("3. Datenträgertyp(SSD oder HDD):");
            Datenblatt.storage.setProperty("drive3Type",Kbd.readString());
            System.out.print("Kapazität in GB:");
            Datenblatt.storage.setProperty("drive3Capacity",Kbd.readString());

        } else if (Datenblatt.storageAnzahl==4) {
            //Datenträger 1
            System.out.print("1. Datenträgertyp(SSD oder HDD):");
            Datenblatt.storage.setProperty("drive1Type",Kbd.readString());
            System.out.print("Kapazität in GB:");
            Datenblatt.storage.setProperty("drive1Capacity",Kbd.readString());

            //Datenträger 2
            System.out.print("2. Datenträgertyp(SSD oder HDD):");
            Datenblatt.storage.setProperty("drive2Type",Kbd.readString());
            System.out.print("Kapazität in GB:");
            Datenblatt.storage.setProperty("drive2Capacity",Kbd.readString());

            //Datenträger 3
            System.out.print("3. Datenträgertyp(SSD oder HDD):");
            Datenblatt.storage.setProperty("drive3Type",Kbd.readString());
            System.out.print("Kapazität in GB:");
            Datenblatt.storage.setProperty("drive3Capacity",Kbd.readString());

            //Datenträger 4
            System.out.print("1. Datenträgertyp(SSD oder HDD):");
            Datenblatt.storage.setProperty("drive4Type",Kbd.readString());
            System.out.print("Kapazität in GB:");
            Datenblatt.storage.setProperty("drive4Capacity",Kbd.readString());

        } else System.out.println("Falsche Eingabe!");


    }

    public static void memory(){
        System.out.println("--------------------");
        System.out.println("RAM:");
        System.out.print("Kapazität in GB:");
        Datenblatt.memory.setProperty("capacity",Kbd.readString());
        System.out.print("DDR-Version(\"DDRx\"):");
        Datenblatt.memory.setProperty("ddrVersion",Kbd.readString());
    }

    public static void os(){
        System.out.println("--------------------");
        System.out.print("Welches Betreibssystem ist installiert?:");
        Datenblatt.os.setProperty("name",Kbd.readString());
    }

    public static void clearFeatures(){
        System.out.println("--------------------");
        System.out.print("Datenblatt zurücksetzen?(y/n)");

        if(Kbd.readString().equals("y")||Kbd.readString().equals("Y")){
            Datenblatt.cpu.clear();
            Datenblatt.gpu.clear();
            Datenblatt.storage.clear();
            Datenblatt.memory.clear();
            Datenblatt.os.clear();
            Datenblatt.price.clear();
            Datenblatt.storageAnzahl=1;
        } else System.out.println("Abgebrochen");
    }

    public static void setPrice(){
        System.out.println("--------------------");
        System.out.print("Wieviel kostet dieser PC/dieses Notebook(in EURO)?: ");
        Datenblatt.price.setProperty("EUR",Kbd.readString());
    }

}
