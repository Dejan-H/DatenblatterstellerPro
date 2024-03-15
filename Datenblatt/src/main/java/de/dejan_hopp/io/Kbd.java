package de.dejan_hopp.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Kbd {
    public static String readString(){
        try {
            BufferedReader r=new BufferedReader(new InputStreamReader(System.in));
            return r.readLine();
        } catch (IOException ioe) {
            System.out.println(ioe);
            return "FEHLER BEIM EINLESEN!";
        }

    }

    public static int readInt() throws IOException{
            BufferedReader r=new BufferedReader(new InputStreamReader(System.in));
            return Integer.parseInt(r.readLine());
    }
}
