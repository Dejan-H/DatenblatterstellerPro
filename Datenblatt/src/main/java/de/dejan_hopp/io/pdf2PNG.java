package de.dejan_hopp.io;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class pdf2PNG {
    public static void convert(){

        try {
            File sourceFile = new File("Datenblatt.pdf");

            if (sourceFile.exists()) {

                RandomAccessFile raf = new RandomAccessFile(sourceFile, "r");
                FileChannel channel = raf.getChannel();
                ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                PDFFile pdf = new PDFFile(buf);

                int pageNumber = 1;// which PDF page to be converted
                PDFPage page = pdf.getPage(pageNumber);

                // image dimensions
                int width = 800;
                int height = 800;

                // create the image
                Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(), (int) page.getBBox().getHeight());
                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

                // width & height, // clip rect, // null for the ImageObserver, // fill background with white, // block until drawing is done
                Image image = page.getImage(width, height, rect, null, true, true );
                Graphics2D bufImageGraphics = bufferedImage.createGraphics();
                bufImageGraphics.drawImage(image, 0, 0, null);

                File imageFile = new File("Datenblatt.png" );// change file format here. Ex: .png, .jpg, .jpeg, .gif, .bmp

                ImageIO.write(bufferedImage, "png", imageFile);
            } else {
                System.err.println(sourceFile.getName() +" File not exists");
            }
            if(sourceFile.delete()){System.out.println("PDF gelöscht!");}
        } catch (Exception e) {
            e.printStackTrace();
        }

        //JOptionPane.showMessageDialog(null, "Es kann nicht gedruckt werden. Ein anderer Prozess verwendet die Datei!","Fehler!",JOptionPane.INFORMATION_MESSAGE);

    }
}
