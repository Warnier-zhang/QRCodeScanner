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
        BitMatrix matrix;
        try {
            // Represent the barcode image using a matrix of bits.
            //matrix = writer.encode(appendCheckSum(), BarcodeFormat.EAN_13, 63, 46);
            // The size of barcode image affects whether can be detected or not!
            matrix = writer.encode(appendCheckSum(), BarcodeFormat.EAN_13, 320, 230);
            ImageWriter.renderFile(matrix, "PNG", file);
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
        return raw + c;
    }

    private void makeLog(String msg) {
        System.out.println(msg);
    }
}
