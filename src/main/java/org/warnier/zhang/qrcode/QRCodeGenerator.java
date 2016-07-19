package org.warnier.zhang.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
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
            renderFile(matrix, "PNG", file);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private void renderFile(BitMatrix matrix, String format, String file) {
        BufferedImage image = renderImage(matrix);
        try {
            ImageIO.write(image, format, new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage renderImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Gets the requested bit, where true means black.
                image.setRGB(x, y, (matrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB()));
            }
        }
        return image;
    }
}
