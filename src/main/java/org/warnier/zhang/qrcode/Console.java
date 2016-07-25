package org.warnier.zhang.qrcode;

public class Console {
    public static void main(String[] args) {
//        QRCodeGenerator generator = new QRCodeGenerator();
//        generator.generate("Warnier-zhang!");

// 益达口香糖：6923450657713
        BarcodeGenerator generator = new BarcodeGenerator("3450", "65771");
        generator.generate();

        BarcodeScanner scanner = new BarcodeScanner();
        System.out.println(scanner.scan("default.png"));
    }
}
