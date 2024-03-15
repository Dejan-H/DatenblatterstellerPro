package de.dejan_hopp.io;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import de.dejan_hopp.Datenblatt;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class Print {

    private static final String pdfPathName ="Datenblatt.pdf";
    public static void createPDF(){
        System.out.println("Erstelle PDF-Datei...");
        File file = null;

        try {
            Document doc=new Document();
            file=new File(pdfPathName);
            PdfWriter writer=PdfWriter.getInstance(doc, Files.newOutputStream(file.toPath()));

            Rectangle etikett = new Rectangle(228,228); // entspricht 8cm x 8cm

            doc.setPageSize(etikett);
            doc.setMargins(0,0,40,10);

            doc.open();

            PdfContentByte cb=writer.getDirectContent();
            BaseFont bf= BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1250,true);
            BaseFont bf2= BaseFont.createFont(BaseFont.HELVETICA_BOLDOBLIQUE,BaseFont.CP1250,true);

            cb.beginText();
            cb.setFontAndSize(bf, 20);
            cb.moveText(50,210);
            cb.showText("Überschrift");
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.moveText(85,190);
            cb.showText("Datenblatt:");
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.moveText(145,5);
            cb.showText("Preis: "+Datenblatt.price.get("EUR").toString()+"€");
            cb.endText();

            cb.beginText();
            cb.setFontAndSize(bf, 12);
            cb.moveText(10,5);
            cb.showText("Inkl. Windows");
            cb.endText();

            Font tableFont = new Font(Font.FontFamily.HELVETICA,9, Font.BOLDITALIC);
            Font tableFontHead = new Font(Font.FontFamily.HELVETICA,9, Font.BOLD);


            PdfPTable tabelleCPU=new PdfPTable(1);

            int tableWidth=100;

            tabelleCPU.setTotalWidth(tableWidth);
            tabelleCPU.setLockedWidth(true);
            tabelleCPU.addCell(new PdfPCell(new Phrase("Prozessor:",tableFontHead))).setBorderWidth(1);
            tabelleCPU.addCell(new PdfPCell(new Phrase(Datenblatt.cpu.getProperty("manufacturer"),tableFont))).setBorderWidth(1);
            tabelleCPU.addCell(new PdfPCell(new Phrase(Datenblatt.cpu.getProperty("name") +" ",tableFont))).setBorderWidth(1);
            tabelleCPU.addCell(new PdfPCell(new Phrase(Datenblatt.cpu.getProperty("cores") +" Kerne - "+ Datenblatt.cpu.getProperty("threads") +" Threads",tableFont))).setBorderWidth(1);
            tabelleCPU.addCell(new PdfPCell(new Phrase(Datenblatt.cpu.getProperty("mhz") +" MHz",tableFont))).setBorderWidth(1);
            tabelleCPU.setHeaderRows(1);

            PdfPTable tabelleGPU=new PdfPTable(1);

            tabelleGPU.setTotalWidth(tableWidth);
            tabelleGPU.setLockedWidth(true);
            tabelleGPU.addCell(new PdfPCell(new Phrase("Grafik:",tableFontHead))).setBorderWidth(1);
            tabelleGPU.addCell(new PdfPCell(new Phrase(Datenblatt.gpu.getProperty("manufacturer"),tableFont))).setBorderWidth(1);
            tabelleGPU.addCell(new PdfPCell(new Phrase(Datenblatt.gpu.getProperty("name") +" ",tableFont))).setBorderWidth(1);
            tabelleGPU.addCell(new PdfPCell(new Phrase(Datenblatt.gpu.getProperty("mhz") +" MHz",tableFont))).setBorderWidth(1);
            tabelleGPU.setHeaderRows(1);


            PdfPTable tabelleRAM=new PdfPTable(1);

            tabelleRAM.setTotalWidth(tableWidth);
            tabelleRAM.setLockedWidth(true);
            tabelleRAM.addCell(new PdfPCell(new Phrase("Arbeitsspeicher:",tableFontHead))).setBorderWidth(1);
            tabelleRAM.addCell(new PdfPCell(new Phrase(Datenblatt.memory.getProperty("capacity")+" GB - "+Datenblatt.memory.getProperty("ddrVersion"),tableFont))).setBorderWidth(1);
            tabelleRAM.setHeaderRows(1);

            // Betriebssystem:
            cb.beginText();
            cb.setFontAndSize(bf2, 12);
            cb.moveText(10,20);
            cb.showText(Datenblatt.os.getProperty("name"));
            cb.endText();

            PdfPTable tabelleStorage=new PdfPTable(1);

            if (Datenblatt.storageAnzahl==1){


                tabelleStorage.setTotalWidth(tableWidth);
                tabelleStorage.setLockedWidth(true);
                tabelleStorage.addCell(new PdfPCell(new Phrase("Datenträger:",tableFontHead))).setBorderWidth(1);
                tabelleStorage.addCell(new PdfPCell(new Phrase(Datenblatt.storage.getProperty("drive1Capacity")+" GB "+Datenblatt.storage.getProperty("drive1Type"),tableFont))).setBorderWidth(1);
            } else if (Datenblatt.storageAnzahl==2) {

                tabelleStorage.setTotalWidth(tableWidth);
                tabelleStorage.setLockedWidth(true);
                tabelleStorage.addCell(new PdfPCell(new Phrase("Datenträger 1:",tableFontHead))).setBorderWidth(1);
                tabelleStorage.addCell(new PdfPCell(new Phrase(Datenblatt.storage.getProperty("drive1Capacity")+" GB "+Datenblatt.storage.getProperty("drive1Type"),tableFont))).setBorderWidth(1);

                tabelleStorage.addCell(new PdfPCell(new Phrase("Datenträger 2:",tableFontHead))).setBorderWidth(1);
                tabelleStorage.addCell(new PdfPCell(new Phrase(Datenblatt.storage.getProperty("drive2Capacity")+" GB "+Datenblatt.storage.getProperty("drive2Type"),tableFont))).setBorderWidth(1);
            } else if (Datenblatt.storageAnzahl==3) {

                tabelleStorage.setTotalWidth(tableWidth);
                tabelleStorage.setLockedWidth(true);
                tabelleStorage.addCell(new PdfPCell(new Phrase("Datenträger 1:",tableFontHead))).setBorderWidth(1);
                tabelleStorage.addCell(new PdfPCell(new Phrase(Datenblatt.storage.getProperty("drive1Capacity")+" GB "+Datenblatt.storage.getProperty("drive1Type"),tableFont))).setBorderWidth(1);

                tabelleStorage.addCell(new PdfPCell(new Phrase("Datenträger 2:",tableFontHead))).setBorderWidth(1);
                tabelleStorage.addCell(new PdfPCell(new Phrase(Datenblatt.storage.getProperty("drive2Capacity")+" GB "+Datenblatt.storage.getProperty("drive2Type"),tableFont))).setBorderWidth(1);

                tabelleStorage.addCell(new PdfPCell(new Phrase("Datenträger 3:",tableFontHead))).setBorderWidth(1);
                tabelleStorage.addCell(new PdfPCell(new Phrase(Datenblatt.storage.getProperty("drive3Capacity")+" GB "+Datenblatt.storage.getProperty("drive3Type"),tableFont))).setBorderWidth(1);
            } else if (Datenblatt.storageAnzahl==4) {

                tabelleStorage.setTotalWidth(tableWidth);
                tabelleStorage.setLockedWidth(true);
                tabelleStorage.addCell(new PdfPCell(new Phrase("Datenträger 1:",tableFontHead))).setBorderWidth(1);
                tabelleStorage.addCell(new PdfPCell(new Phrase(Datenblatt.storage.getProperty("drive1Capacity")+" GB "+Datenblatt.storage.getProperty("drive1Type"),tableFont))).setBorderWidth(1);

                tabelleStorage.addCell(new PdfPCell(new Phrase("Datenträger 2:",tableFontHead))).setBorderWidth(1);
                tabelleStorage.addCell(new PdfPCell(new Phrase(Datenblatt.storage.getProperty("drive2Capacity")+" GB "+Datenblatt.storage.getProperty("drive2Type"),tableFont))).setBorderWidth(1);

                tabelleStorage.addCell(new PdfPCell(new Phrase("Datenträger 3:",tableFontHead))).setBorderWidth(1);
                tabelleStorage.addCell(new PdfPCell(new Phrase(Datenblatt.storage.getProperty("drive3Capacity")+" GB "+Datenblatt.storage.getProperty("drive3Type"),tableFont))).setBorderWidth(1);

                tabelleStorage.addCell(new PdfPCell(new Phrase("Datenträger 4:",tableFontHead))).setBorderWidth(1);
                tabelleStorage.addCell(new PdfPCell(new Phrase(Datenblatt.storage.getProperty("drive4Capacity")+" GB "+Datenblatt.storage.getProperty("drive4Type"),tableFont))).setBorderWidth(1);
            }

            PdfPTable alles=new PdfPTable(2);
            alles.setTotalWidth(200);
            alles.setLockedWidth(true);
            alles.addCell(new PdfPCell(tabelleCPU));
            alles.addCell(new PdfPCell(tabelleStorage));
            alles.addCell(new PdfPCell(tabelleGPU));
            alles.addCell(new PdfPCell(tabelleRAM));

            doc.add(alles);

            writer.close();
            doc.close();

        } catch (Exception e) {
            System.out.println("Die Datei wird bereits verwendet!");
        }

    }

    // Der Großteil des Codes der folgenden Funktion ist von hier(https://www.java-forum.org/thema/drucken-einer-pdf-datei.176174/) kopiert.
    public static void toPrint(boolean direct, JFrame positionParent){
        if (!direct) {
            int n = JOptionPane.showConfirmDialog(positionParent, "<html><center>Soll das Datenblatt gedruckt werden?", "Drucken?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (n == JOptionPane.YES_OPTION) {
                System.out.println("Drucken...");
                File datei = new File(pdfPathName);
                if (datei.exists()) {
                    try {
                        PDDocument doc = PDDocument.load(datei);

                        PrintService myPrintService = findPrintService(RWConfigFile.getPrinterName());

                        PrinterJob job = PrinterJob.getPrinterJob();
                        job.setPageable(new PDFPageable(doc));
                        job.setPrintService(myPrintService);
                        job.print();
                        doc.close();
                    } catch (IOException | PrinterException e) {
                        System.out.println("Datei- oder Druckerfehler!");
                        JOptionPane.showConfirmDialog(positionParent, "<html><center>Bitte wählen Sie in den Programmeinstellungen([STRG]+[KOMMA]) einen Drucker aus!", "Drucken?", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }else{
            System.out.println("Drucken...");
            File datei = new File(pdfPathName);
            if (datei.exists()) {
                try {
                    PDDocument doc = PDDocument.load(datei);

                    System.out.print("Druckername: ");
                    String printer=Kbd.readString();
                    PrintService myPrintService = findPrintService(printer);

                    while (true){
                        System.out.println("Soll immer dieser Drucker verwendet werden?(y/n)");
                        if (Kbd.readString().equals("y")||Kbd.readString().equals("Y")){
                            RWConfigFile.setPrinter(printer);
                            break;
                        } else if (Kbd.readString().equals("n")||Kbd.readString().equals("N")) {
                        }else System.out.println("Bitte 'y'/'Y' für \"Ja\" oder 'n'/'N' für \"Nein\" eingeben!");
                    }


                    PrinterJob job = PrinterJob.getPrinterJob();
                    job.setPageable(new PDFPageable(doc));
                    job.setPrintService(myPrintService);
                    job.print();
                    doc.close();
                } catch (IOException | PrinterException e) {
                    System.out.println("Datei- oder Druckerfehler!");
                }
            }
        }
    }

    // https://stackoverflow.com/questions/18636622/pdfbox-how-to-print-pdf-with-specified-printer
    public static PrintService findPrintService(String printerName){
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }

    public static String[] printerList(){
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Number of print services: " + printServices.length);
        String[] printerList=new String[printServices.length];

        for (int i=0;i<printServices.length;i++){
            printerList[i]=printServices[i].getName();
        }
        return printerList;
    }

}
