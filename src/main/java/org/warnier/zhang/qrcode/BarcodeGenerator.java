package org.warnier.zhang.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * BarcodeGenerator renders barcode of type EAN-13.
 */
public class BarcodeGenerator {
    private MultiFormatWriter writer;

    public BarcodeGenerator() {
        writer = new MultiFormatWriter();
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
            // Represent the barcode image using a matrix of bits.
            matrix = writer.encode(text, BarcodeFormat.EAN_13, 400, 300, hints);
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
