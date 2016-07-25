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
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * BarcodeGenerator renders barcode of type EAN-13.
 * 3 bits for China(690~691), 4 bits for company, 5 bits for product!
 */
public class BarcodeGenerator {
    private String raw;
    private MultiFormatWriter writer;

    public BarcodeGenerator() {
        this("0000", "00000");
    }

    public BarcodeGenerator(String company, String batch) {
        writer = new MultiFormatWriter();
        raw = "692" + company + batch;
    }

    public void generate() {
        generate("default.png");
    }

    public void generate(String file) {
        // Support zh_CN.
        HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix matrix;
        try {
            // Represent the barcode image using a matrix of bits.
            matrix = writer.encode(appendCheckSum(), BarcodeFormat.EAN_13, 63, 46, hints);
            renderFile(matrix, "PNG", file);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private String appendCheckSum() {
        int c1 = 0;
        int c2 = 0;
        int cc = 0;
        int c = 0;

        List<Integer> eans = new ArrayList<Integer>(12);
        for (char ch : raw.toCharArray()) {
            eans.add(Integer.parseInt(String.valueOf(ch)));
        }
        for (int i = 0; i < 12; i = i + 2) {
            c1 += eans.get(i);
        }
        for (int j = 1; j < 12; j = j + 2) {
            c2 += eans.get(j);
        }
        c2 *= 3;
        cc = (c1 + c2) % 10;
        c = 10 - cc;
        if (c == 10) {
            c = 0;
        }
        makeLog(raw + c);
        return raw + c;
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

    private void makeLog(String msg) {
        System.out.println(msg);
    }
}
