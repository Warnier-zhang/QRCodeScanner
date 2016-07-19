package org.warnier.zhang.qrcode;

public class Console {
    public static void main(String[] args) {
//        QRCodeGenerator generator = new QRCodeGenerator();
//        generator.generate("上海心源计算机技术有限公司！");
//        QRCodeScanner scanner = new QRCodeScanner();
//        System.out.println(scanner.scan());
        JQRCodeFrame frame = new JQRCodeFrame();
        frame.pack();
        QRCodeScanner scanner = new QRCodeScanner();
        String text = scanner.scan();
        System.out.println(text);
        frame.updateText(text);
    }
}
