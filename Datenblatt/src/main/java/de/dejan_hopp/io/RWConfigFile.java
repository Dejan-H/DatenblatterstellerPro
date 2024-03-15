package de.dejan_hopp.io;

import java.io.*;
import java.util.Properties;

public class RWConfigFile {

    public static String getPrinterName(){
        Properties settings=new Properties();
        String printerName="Kein Drucker Ausgewählt";

        try {
            FileInputStream in=new FileInputStream("settings.conf");
            settings.load(in);
            printerName=settings.getProperty("printerName");
        } catch (IOException e) {
            //throw new RuntimeException(e);
            File config=new File("settings.conf");

            if (!config.exists()){
                try{
                    boolean created=config.createNewFile();
                    if (created){
                        getPrinterName();
                    } else {
                        new File("settings.conf");
                        getPrinterName();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return printerName;
    }

    public static void setPrinter(String printerName){
        Properties settings=new Properties();

        try {
            FileOutputStream out=new FileOutputStream("settings.conf");
            settings.setProperty("printerName",printerName);
            settings.store(out,null);

        } catch (FileNotFoundException e) {
            File config=new File("settings.conf");

            if (!config.exists()){
                try {
                    boolean created=config.createNewFile();
                    if (created){
                        setPrinter(printerName);
                    } else {
                        new File("settings.conf");
                        setPrinter(printerName);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
