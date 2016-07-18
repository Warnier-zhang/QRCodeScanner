package org.warnier.zhang.qrcode;

/**
 * Created by Warnier-zhang on 2016/7/16.
 */
public class Console {
    public static final void main(String[] args) {
//        QRCodeGenerator generator = new QRCodeGenerator();
//        generator.generate("test!");
        QRCodeScanner scanner = new QRCodeScanner();
        System.out.println(scanner.scan());
    }
}
