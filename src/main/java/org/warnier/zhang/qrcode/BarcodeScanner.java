package org.warnier.zhang.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * BarcodeScanner can scan and decode barcode with the portable device.
 */
public class BarcodeScanner {
    private MultiFormatReader reader;

    public BarcodeScanner() {
        reader = new MultiFormatReader();
    }

    public String scan(String spec) {
        Result result = null;
        try {
            BufferedImage image = ImageIO.read(new File(spec));
            result = decode(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.getText();
    }

    private Result decode(BufferedImage image) {
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = null;
        try {
            result = reader.decode(bitmap);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void makeLog(String msg) {
        System.out.println(msg);
    }
}
