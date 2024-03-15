package de.dejan_hopp.io;

import java.util.Date;

public class Delay {

    //https://javabeginners.de/Grundlagen/Zeitsteuerung_ohne_Threads.php#:~:text=Nach%20jedem%20Durchlauf%20wird%20die,der%20aktuellen%20Unix%2DZeit%20beginnt.
    public static void delay(int milliseconds){
        long ende=(new Date()).getTime() + milliseconds;
        while ((new Date()).getTime() < ende){
            // abwarten und Tee trinken
        }
    }
}
