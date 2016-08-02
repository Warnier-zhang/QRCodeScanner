package org.warnier.zhang.qrcode;

import com.google.zxing.common.BitMatrix;
import com.sun.prism.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Utility class for rendering image file.
 */
public class ImageWriter {
    public static void renderFile(BitMatrix matrix, String format, String file) {
        BufferedImage image = renderImage(matrix);
        try {
            ImageIO.write(image, format, new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void renderFile(String ean13Code, BitMatrix matrix, String format, String file) {
        BufferedImage image = renderImage(ean13Code, matrix);
        try {
            ImageIO.write(image, format, new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage renderImage(String ean13Code, BitMatrix matrix) {
        BufferedImage image = renderImage(matrix);
        Graphics2D graphics = image.createGraphics();
        BasicStroke stroke = new BasicStroke(8, BasicStroke.CAP_ROUND, com.sun.prism.BasicStroke.JOIN_ROUND);
        graphics.setStroke(stroke);
        graphics.setColor(Color.BLACK);
        graphics.drawString(ean13Code, 15, matrix.getHeight() + 12);
        graphics.dispose();
        image.flush();
        return image;
    }

    private static BufferedImage renderImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height + 16, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Gets the requested bit, where true means black.
                image.setRGB(x, y, (matrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB()));
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = height; y < height + 16; y++) {
                image.setRGB(x, y, Color.WHITE.getRGB());
            }
        }
        return image;
    }
}
