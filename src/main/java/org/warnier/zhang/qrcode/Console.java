package org.warnier.zhang.qrcode;

/**
 * Created by Warnier-zhang on 2016/7/19.
 */
public class Console {
    public static void main(String[] args) {
//        QRCodeGenerator generator = new QRCodeGenerator();
//        generator.generate("test!");
        JQRCodeFrame frame = new JQRCodeFrame();
        frame.pack();
        QRCodeScanner scanner = new QRCodeScanner();
        String text = scanner.scan();
        System.out.println(text);
        frame.updateText(text);
    }
}
