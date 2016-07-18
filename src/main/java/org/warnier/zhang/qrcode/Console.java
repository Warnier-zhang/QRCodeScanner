package org.warnier.zhang.qrcode;

import javax.swing.*;

public class Console {
    public static void main(String[] args) {
//        QRCodeGenerator generator = new QRCodeGenerator();
//        generator.generate("上海心源计算机技术有限公司！");
//        QRCodeScanner scanner = new QRCodeScanner();
//        System.out.println(scanner.scan());
        JQRCodeFrame frame = new JQRCodeFrame();
        frame.setTitle("QR Code");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setText(new QRCodeScanner().scan());
    }
}
