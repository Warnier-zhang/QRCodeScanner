package org.warnier.zhang.qrcode;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.awt.image.BufferedImage;

/**
 * QRCodeScanner can scan and decode QR codes with the build-in camera on laptop PC.
 */
public class QRCodeScanner {
    private QRCodeReader reader;

    public QRCodeScanner() {
        reader = new QRCodeReader();
    }

    public String scan() {
        // Open camera on the computer;
        Webcam camera = Webcam.getDefault();
        camera.open();
        BufferedImage image = camera.getImage();
        return decode(image);
    }

    private String decode(BufferedImage image) {
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
        return result == null ? "二维码失效！" : result.getText();
    }
}
