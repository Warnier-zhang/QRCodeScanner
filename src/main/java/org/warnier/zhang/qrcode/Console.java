package org.warnier.zhang.qrcode;

public class Console {
    public static void main(String[] args) {
        // 益达口香糖：6923450657713
        BarcodeGenerator generator = new BarcodeGenerator("3450", "65771");
        generator.generate();
    }
}
