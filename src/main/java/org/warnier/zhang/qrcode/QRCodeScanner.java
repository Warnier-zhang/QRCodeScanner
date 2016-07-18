package org.warnier.zhang.qrcode;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * QRCodeScanner can scan and decode QR codes with the build-in camera on laptop PC.
 */
public class QRCodeScanner {
    private QRCodeReader reader;

    public QRCodeScanner() {
        reader = new QRCodeReader();
    }

    public String scan() {
        Result result;
        Webcam camera = Webcam.getDefault();
        camera.open();
        do {
            if (!camera.isOpen()) {
                camera.open();
            }
            BufferedImage image;
            if ((image = camera.getImage()) == null) {
                continue;
            }
            makeLog(image);
            result = decode(image);
            if (result != null) {
                break;
            }
            delayMillis(1750);
        } while (true);
        playSound("sound.mp3");
        return result.getText();
    }

    private Result decode(BufferedImage image) {
        // A standard interface for abstracting different bitmaps;
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        // It is designed for high frequency images of barcodes with black data on white backgrounds.
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        // A String representing the content encoded by the QR code;
        Result result = null;
        try {
            result = reader.decode(bitmap);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ChecksumException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void makeLog(BufferedImage image) {
        try {
            ImageIO.write(image, "PNG", new File("scanner.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playSound(String mp3) {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(mp3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Player player = new Player(stream);
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    private void delayMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
