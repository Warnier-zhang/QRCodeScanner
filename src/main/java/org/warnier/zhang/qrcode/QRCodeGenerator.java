package org.warnier.zhang.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * QRCodeGenerator is capable of rendering QR code.
 */
public class QRCodeGenerator {
    private QRCodeWriter writer;

    public QRCodeGenerator() {
        writer = new QRCodeWriter();
    }

    public void generate(String text) {
        generate(text, "default.png");
    }

    public void generate(String text, String file) {
        // Support zh_CN.
        HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix matrix;
        try {
            // 256*256 representing the preferred size in pixels.
            // Represent the barcode image using a matrix of bits.
            matrix = writer.encode(text, BarcodeFormat.QR_CODE, 400, 400, hints);
            ImageWriter.renderFile(matrix, "PNG", file);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
